const {
  initializeTestEnvironment,
  assertFails,
  assertSucceeds,
} = require("@firebase/rules-unit-testing");
const { test, before, after, beforeEach } = require("node:test");
const fs = require("node:fs");

let testEnv;
const PROJECT_ID = process.env.GCP_PROJECT || "demo-no-project";
const ALICE_UID = "alice_123";
const BOB_UID = "bob_456";
const ADMIN_UID = "admin_789";
const ADMIN_EMAIL = "savdatapointsurveys@gmail.com";

const [emulatorHost, emulatorPortStr] = (process.env.FIRESTORE_EMULATOR_HOST || "127.0.0.1:8085").split(":");
const emulatorPort = parseInt(emulatorPortStr, 10);

before(async () => {
  const rules = fs.readFileSync("./firestore.rules", "utf8");
  testEnv = await initializeTestEnvironment({
    projectId: PROJECT_ID,
    firestore: {
      rules,
      host: emulatorHost,
      port: emulatorPort,
    },
  });
});

after(async () => {
  if (testEnv) {
    await testEnv.cleanup();
  }
});

beforeEach(async () => {
  if (testEnv) {
    await testEnv.clearFirestore();
  }
});

// --- TIPS COLLECTION TESTS ---

test("Unauthenticated user: cannot read tips", async () => {
  const unauthDb = testEnv.unauthenticatedContext().firestore();
  await assertFails(unauthDb.collection("tips").get());
});

test("Authenticated user: can read tips", async () => {
  // Seed tip via admin bypass
  await testEnv.withSecurityRulesDisabled(async (context) => {
    await context.firestore().collection("tips").doc("tip_1").set({
      id: "tip_1",
      homeTeam: "Arsenal",
      awayTeam: "Chelsea",
      league: "Premier League",
      predictionType: "Full Time Result",
      predictionValue: "Arsenal Win",
      odds: "1.85",
      matchDate: "Oct 10, 2026",
      kickoffTime: "17:30",
      category: "FREE",
      resultState: "PENDING",
      createdAt: new Date(),
      updatedAt: new Date(),
    });
  });

  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  await assertSucceeds(aliceDb.collection("tips").get());
  await assertSucceeds(aliceDb.collection("tips").doc("tip_1").get());
});

test("Non-admin user: cannot create tip", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  await assertFails(
    aliceDb.collection("tips").doc("tip_2").set({
      id: "tip_2",
      homeTeam: "Arsenal",
      awayTeam: "Chelsea",
      league: "Premier League",
      predictionType: "Full Time Result",
      predictionValue: "Arsenal Win",
      odds: "1.85",
      matchDate: "Oct 10, 2026",
      kickoffTime: "17:30",
      category: "FREE",
      resultState: "PENDING",
      createdAt: new Date(),
      updatedAt: new Date(),
    })
  );
});

test("Admin user: can create tip", async () => {
  const adminDb = testEnv.authenticatedContext(ADMIN_UID, { email: ADMIN_EMAIL }).firestore();
  await assertSucceeds(
    adminDb.collection("tips").doc("tip_3").set({
      id: "tip_3",
      homeTeam: "Real Madrid",
      awayTeam: "Barcelona",
      league: "La Liga",
      predictionType: "Total Goals",
      predictionValue: "Over 2.5",
      odds: "1.65",
      matchDate: "Oct 10, 2026",
      kickoffTime: "20:00",
      category: "FREE",
      resultState: "PENDING",
      createdAt: new Date(),
      updatedAt: new Date(),
    })
  );
});

// --- USER PROFILE TESTS ---

test("User: can create and read own profile", async () => {
  const aliceDb = testEnv.authenticatedContext(ALICE_UID).firestore();
  await assertSucceeds(
    aliceDb.collection("users").doc(ALICE_UID).set({
      userId: ALICE_UID,
      displayName: "Alice",
      language: "en",
      createdAt: new Date(),
      updatedAt: new Date(),
    })
  );
  await assertSucceeds(aliceDb.collection("users").doc(ALICE_UID).get());
});

test("Cross-user: cannot read another user's profile", async () => {
  await testEnv.withSecurityRulesDisabled(async (context) => {
    await context.firestore().collection("users").doc(ALICE_UID).set({
      userId: ALICE_UID,
      displayName: "Alice",
      language: "en",
      createdAt: new Date(),
      updatedAt: new Date(),
    });
  });

  const bobDb = testEnv.authenticatedContext(BOB_UID).firestore();
  await assertFails(bobDb.collection("users").doc(ALICE_UID).get());
});
