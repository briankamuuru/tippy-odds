package com.example.localization

object Strings {

    fun navFreeTips(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Free Tips"
        AppLanguage.AR -> "نصائح مجانية"
        AppLanguage.ES -> "Consejos Gratis"
        AppLanguage.DE -> "Gratis-Tipps"
    }

    fun navVipTips(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "VIP Tips"
        AppLanguage.AR -> "نصائح VIP"
        AppLanguage.ES -> "Consejos VIP"
        AppLanguage.DE -> "VIP-Tipps"
    }

    fun navSettings(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Settings"
        AppLanguage.AR -> "الإعدادات"
        AppLanguage.ES -> "Ajustes"
        AppLanguage.DE -> "Einstellungen"
    }

    // Free Tips Tabs
    fun tabCurrentFreeTips(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Current Free Tips"
        AppLanguage.AR -> "النصائح الحالية"
        AppLanguage.ES -> "Consejos Actuales"
        AppLanguage.DE -> "Aktuelle Tipps"
    }

    fun tabFreeTipsHistory(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Free Tips History"
        AppLanguage.AR -> "سجل النصائح"
        AppLanguage.ES -> "Historial Gratis"
        AppLanguage.DE -> "Tipp-Verlauf"
    }

    // VIP Grid Sections
    fun vip2Plus(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "2+ Tips VIP"
        AppLanguage.AR -> "نصائح +2 VIP"
        AppLanguage.ES -> "2+ Tips VIP"
        AppLanguage.DE -> "2+ Tipps VIP"
    }

    fun vip5Plus(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "5+ Tips VIP"
        AppLanguage.AR -> "نصائح +5 VIP"
        AppLanguage.ES -> "5+ Tips VIP"
        AppLanguage.DE -> "5+ Tipps VIP"
    }

    fun vip10Plus(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "10+ Tips VIP"
        AppLanguage.AR -> "نصائح +10 VIP"
        AppLanguage.ES -> "10+ Tips VIP"
        AppLanguage.DE -> "10+ Tipps VIP"
    }

    fun vipCorrectScore(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Correct Score VIP"
        AppLanguage.AR -> "النتيجة الدقيقة VIP"
        AppLanguage.ES -> "Marcador Exacto VIP"
        AppLanguage.DE -> "Ergebnis VIP"
    }

    fun vipHistory(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "History VIP"
        AppLanguage.AR -> "سجل VIP"
        AppLanguage.ES -> "Historial VIP"
        AppLanguage.DE -> "VIP-Verlauf"
    }

    // VIP History Independent Tabs
    fun vipHistory2Plus(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "2+ VIP History"
        AppLanguage.AR -> "سجل +2 VIP"
        AppLanguage.ES -> "Historial 2+ VIP"
        AppLanguage.DE -> "2+ VIP Verlauf"
    }

    fun vipHistory5Plus(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "5+ VIP History"
        AppLanguage.AR -> "سجل +5 VIP"
        AppLanguage.ES -> "Historial 5+ VIP"
        AppLanguage.DE -> "5+ VIP Verlauf"
    }

    fun vipHistory10Plus(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "10+ VIP History"
        AppLanguage.AR -> "سجل +10 VIP"
        AppLanguage.ES -> "Historial 10+ VIP"
        AppLanguage.DE -> "10+ VIP Verlauf"
    }

    fun vipHistoryCorrectScore(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Correct Score VIP History"
        AppLanguage.AR -> "سجل النتيجة الدقيقة VIP"
        AppLanguage.ES -> "Historial Marcador Exacto"
        AppLanguage.DE -> "Ergebnis VIP Verlauf"
    }

    // Common UI Labels
    fun oddsLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Odds"
        AppLanguage.AR -> "احتمال"
        AppLanguage.ES -> "Cuota"
        AppLanguage.DE -> "Quote"
    }

    fun tipLabel(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Tip"
        AppLanguage.AR -> "التوقع"
        AppLanguage.ES -> "Pronóstico"
        AppLanguage.DE -> "Tipp"
    }

    fun totalAccumulatedOdds(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Total Accumulated Odds"
        AppLanguage.AR -> "مجموع الاحتمالات التراكمية"
        AppLanguage.ES -> "Cuota Total Acumulada"
        AppLanguage.DE -> "Gesamte Kombi-Quote"
    }

    fun numberOfOdds(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Number of Odds"
        AppLanguage.AR -> "عدد الاحتمالات"
        AppLanguage.ES -> "Número de Cuotas"
        AppLanguage.DE -> "Anzahl der Quoten"
    }

    fun emptyActiveTips(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "No active tips at the moment"
        AppLanguage.AR -> "لا توجد نصائح حالية في الوقت الحالي"
        AppLanguage.ES -> "No hay consejos activos en este momento"
        AppLanguage.DE -> "Zurzeit keine aktiven Tipps vorhanden"
    }

    fun emptyHistoryTips(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "No historical tips available"
        AppLanguage.AR -> "لا توجد نصائح مسجلة في السجل"
        AppLanguage.ES -> "No hay consejos en el historial"
        AppLanguage.DE -> "Keine archivierten Tipps verfügbar"
    }

    fun back(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Back"
        AppLanguage.AR -> "رجوع"
        AppLanguage.ES -> "Atrás"
        AppLanguage.DE -> "Zurück"
    }

    fun cancel(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Cancel"
        AppLanguage.AR -> "إلغاء"
        AppLanguage.ES -> "Cancelar"
        AppLanguage.DE -> "Abbrechen"
    }

    // Settings
    fun settingsLanguage(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Language"
        AppLanguage.AR -> "اللغة"
        AppLanguage.ES -> "Idioma"
        AppLanguage.DE -> "Sprache"
    }

    fun settingsVersion(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Version"
        AppLanguage.AR -> "الإصدار"
        AppLanguage.ES -> "Versión"
        AppLanguage.DE -> "Version"
    }

    fun settingsPrivacyPolicy(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Privacy Policy"
        AppLanguage.AR -> "سياسة الخصوصية"
        AppLanguage.ES -> "Política de Privacidad"
        AppLanguage.DE -> "Datenschutzerklärung"
    }

    fun settingsAbout(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "About"
        AppLanguage.AR -> "حول التطبيق"
        AppLanguage.ES -> "Acerca de"
        AppLanguage.DE -> "Über"
    }

    fun chooseLanguage(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Select Language"
        AppLanguage.AR -> "اختر اللغة"
        AppLanguage.ES -> "Seleccionar Idioma"
        AppLanguage.DE -> "Sprache auswählen"
    }

    // Admin result control (discreet, for testing and declared results)
    fun adminTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Result Management"
        AppLanguage.AR -> "إدارة النتائج"
        AppLanguage.ES -> "Gestión de Resultados"
        AppLanguage.DE -> "Ergebnisverwaltung"
    }

    fun adminSetWon(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Set Won"
        AppLanguage.AR -> "تحديد فوز"
        AppLanguage.ES -> "Marcar Ganada"
        AppLanguage.DE -> "Als gewonnen setzen"
    }

    fun adminSetLost(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Set Lost"
        AppLanguage.AR -> "تحديد خسارة"
        AppLanguage.ES -> "Marcar Perdida"
        AppLanguage.DE -> "Als verloren setzen"
    }

    fun adminReset(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Reset to Pending"
        AppLanguage.AR -> "إعادة إلى قيد الانتظار"
        AppLanguage.ES -> "Restablecer a Pendiente"
        AppLanguage.DE -> "Auf Ausstehend zurücksetzen"
    }

    // Privacy Policy Content
    fun privacyPolicyTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Privacy Policy"
        AppLanguage.AR -> "سياسة الخصوصية"
        AppLanguage.ES -> "Política de Privacidad"
        AppLanguage.DE -> "Datenschutzerklärung"
    }

    fun privacyPolicyContent(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> """
1. Purpose of the Application
TIPPY ODDS is designed solely to display football predictions, odds, and historical match results for informational and entertainment purposes.

2. Information Collected
TIPPY ODDS operates locally on your device. We do not collect, store, or transmit personal identity data, location data, or contact details. No user account registration or sign-in is required.

3. App Preferences and Local Data
Your selected language and local tip database are stored on your device using local storage. This data remains on your device and is not shared with external servers.

4. Device and Diagnostic Information
The application does not collect device telemetry, identifiers, or browsing habits. Android system crash diagnostics may be handled by the operating system according to your system-level settings.

5. Data Storage and Security
All local application data is stored within your device's protected app sandbox.

6. Third-Party Services
This application does not integrate third-party analytics trackers, advertising SDKs, or external marketing services.

7. Data Retention and Deletion
You can clear all application data and preferences at any time by clearing the application storage in your Android device settings or by uninstalling the application.

8. Children's Privacy
The application does not knowingly target, collect, or solicit information from children.

9. Changes to This Policy
Any updates to this policy will be reflected directly in this section in future application updates.

10. Contact
For any privacy enquiries regarding this application, please use the contact channels provided in the official application listing.
""".trimIndent()

        AppLanguage.AR -> """
١. الغرض من التطبيق
تم تصميم تطبيق TIPPY ODDS لعرض توقعات مباريات كرة القدم والاحتمالات ونتائج المباريات التاريخية لأغراض إعلامية وترفيهية فقط.

٢. البيانات التي يتم جمعها
يعمل تطبيق TIPPY ODDS محلياً على جهازك. نحن لا نجمع أو نخزن أو ننقل أي بيانات هوية شخصية أو بيانات موقع أو تفاصيل اتصال. لا يلزم تسجيل حساب أو تسجيل دخول.

٣. تفضيلات التطبيق والبيانات المحلية
يتم حفظ اللغة المختارة وقاعدة بيانات النصائح المحلية مباشرة على جهازك في وحدة التخزين المحلية. تظل هذه البيانات على جهازك ولا تتم مشاركتها مع أي خوادم خارجية.

٤. معلومات الجهاز والتشخيص
لا يقوم التطبيق بجمع أي بيانات عن الجهاز أو المعرفات. قد يتعامل نظام تشغيل أندرويد مع تقارير الأعطال وفقاً لإعدادات النظام الخاصة بك.

٥. تخزين البيانات والأمان
يتم تخزين جميع بيانات التطبيق المحلية داخل بيئة الحماية الآمنة للتطبيق على جهازك.

٦. خدمات الطرف الثالث
لا يدمج هذا التطبيق أي أدوات تتبع أو تحليلات من طرف ثالث أو حزم برمجية إعلانية.

٧. الاحتفاظ بالبيانات وحذفها
يمكنك مسح جميع بيانات وتفضيلات التطبيق في أي وقت عن طريق مسح بيانات التخزين من إعدادات جهاز أندرويد أو عبر إلغاء تثبيت التطبيق.

٨. خصوصية الأطفال
التطبيق لا يستهدف أو يجمع أو يطلب معلومات من الأطفال.

٩. التغييرات على هذه السياسة
ستنعكس أي تحديثات لهذه السياسة مباشرة في هذا القسم في تحديثات التطبيق القادمة.

١٠. التواصل
لأي استفسارات بخصوص الخصوصية، يرجى استخدام وسائل الاتصال المتاحة في صفحة التطبيق الرسمية.
""".trimIndent()

        AppLanguage.ES -> """
1. Propósito de la Aplicación
TIPPY ODDS está diseñada para mostrar pronósticos de fútbol, cuotas y resultados históricos de partidos con fines puramente informativos y de entretenimiento.

2. Información Recopilada
TIPPY ODDS funciona localmente en su dispositivo. No recopilamos, almacenamos ni transmitimos datos de identidad personal, ubicación ni contactos. No se requiere registro ni inicio de sesión.

3. Preferencias y Datos Locales
Su idioma seleccionado y la base de datos local de pronósticos se almacenan en su dispositivo. Estos datos no se comparten con servidores externos.

4. Información del Dispositivo y Diagnóstico
La aplicación no recopila identificadores de dispositivo ni hábitos de navegación. Los diagnósticos del sistema Android se gestionan según la configuración de su sistema operativo.

5. Almacenamiento y Seguridad
Todos los datos locales de la aplicación se almacenan en el entorno aislado seguro de su dispositivo.

6. Servicios de Terceros
Esta aplicación no integra rastreadores analíticos de terceros ni redes publicitarias.

7. Retención y Eliminación de Datos
Puede borrar todos los datos y preferencias en cualquier momento borrando el almacenamiento de la aplicación en los ajustes de Android o desinstalándola.

8. Privacidad de Menores
La aplicación no recopila intencionadamente información de menores.

9. Cambios en la Política
Cualquier actualización se reflejará directamente en esta sección en futuras versiones.

10. Contacto
Para consultas sobre privacidad, utilice los canales de contacto de la ficha oficial de la aplicación.
""".trimIndent()

        AppLanguage.DE -> """
1. Zweck der Anwendung
TIPPY ODDS dient der Anzeige von Fußballvorhersagen, Quoten und historischen Spielergebnissen zu reinen Informations- und Unterhaltungszwecken.

2. Erfasste Daten
TIPPY ODDS läuft lokal auf Ihrem Gerät. Wir erfassen, speichern oder übertragen keine persönlichen Identitätsdaten, Standortdaten oder Kontakte. Eine Registrierung ist nicht erforderlich.

3. App-Einstellungen und lokale Daten
Ihre ausgewählte Sprache und die lokale Spieldatenbank werden auf Ihrem Gerät gespeichert. Diese Daten verbleiben auf Ihrem Gerät und werden nicht geteilt.

4. Geräte- und Diagnosedaten
Die Anwendung sammelt keine Gerätekennungen oder Nutzungsstatistiken. Systemdiagnosen werden durch das Android-Betriebssystem verwaltet.

5. Datenspeicherung und Sicherheit
Alle lokalen Anwendungsdaten werden im geschützten App-Speicherbereich Ihres Geräts aufbewahrt.

6. Dienste von Drittanbietern
Diese App bindet keine Werbenetzwerke oder Analyse-Tracker von Drittanbietern ein.

7. Datenlöschung
Sie können alle Anwendungsdaten jederzeit über die Android-Geräteeinstellungen löschen oder die App deinstallieren.

8. Jugendschutz
Die Anwendung sammelt wissentlich keine Daten von Kindern.

9. Änderungen dieser Erklärung
Etwaige Änderungen werden direkt in künftigen Versionen dieser Ansicht aktualisiert.

10. Kontakt
Für Datenschutzanfragen nutzen Sie bitte die im offiziellen App-Eintrag angegebenen Kontaktmöglichkeiten.
""".trimIndent()
    }

    // About Screen Content
    fun aboutTitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "About TIPPY ODDS"
        AppLanguage.AR -> "حول TIPPY ODDS"
        AppLanguage.ES -> "Acerca de TIPPY ODDS"
        AppLanguage.DE -> "Über TIPPY ODDS"
    }

    fun aboutContent(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> """
TIPPY ODDS provides football predictions and tips to help users review match selections, predictions, odds, and historical results.

Purpose of Predictions
Football predictions and tips are based on statistical assessment and historical performance trends. Football matches are inherently unpredictable, and match outcomes cannot be guaranteed.

Informational & Entertainment Purpose
All content and odds provided in this application are strictly for informational and entertainment purposes. TIPPY ODDS is not a gambling operator and does not accept bets or wagers.

Responsible Decision Making
Users are encouraged to exercise independent judgement and make responsible decisions. We urge all users to approach sports insights with discipline and awareness.

No Guaranteed Outcomes
No prediction guarantees winnings or profits. Past statistical performance does not guarantee future results.
""".trimIndent()

        AppLanguage.AR -> """
يقدم تطبيق TIPPY ODDS توقعات ونصائح لمباريات كرة القدم لمساعدة المستخدمين على مراجعة اختيارات المباريات والتوقعات والاحتمالات والنتائج السابقة.

الغرض من التوقعات
تستند توقعات ونصائح كرة القدم إلى التحليلات الإحصائية وتاريخ الأداء. مباريات كرة القدم بطبيعتها غير قابلة للتنبؤ الدقيق، ولا يمكن ضمان نتائج المباريات على الإطلاق.

أغراض إعلامية وترفيهية
جميع المحتويات والاحتمالات المعروضة في هذا التطبيق هي لأغراض إعلامية وترفيهية فقط. تطبيق TIPPY ODDS ليس منصة مراهنات ولا يقبل أي رهانات.

اتخاذ قرارات مسؤولة
يُنصح المستخدمون بممارسة الحكم المستقل واتخاذ قرارات مسؤولة وواعية.

لا توجد أرباح أو نتائج مضمونة
لا يوجد أي توقع يضمن الفوز أو الأرباح. النتائج الإحصائية السابقة ليست ضماناً للنتائج المستقبلية.
""".trimIndent()

        AppLanguage.ES -> """
TIPPY ODDS proporciona pronósticos y consejos de fútbol para ayudar a los usuarios a consultar selecciones de partidos, predicciones, cuotas y resultados históricos.

Propósito de los Pronósticos
Los pronósticos de fútbol se basan en análisis estadísticos y tendencias de rendimiento. Los partidos de fútbol son inherentemente impredecibles y ningún resultado está garantizado.

Fines Informativos y de Entretenimiento
Todo el contenido y las cuotas de esta aplicación son estrictamente para fines informativos y de entretenimiento. TIPPY ODDS no es una casa de apuestas y no acepta apuestas de ningún tipo.

Decisiones Responsables
Se recomienda a los usuarios actuar con juicio independiente y tomar decisiones responsables en todo momento.

Sin Garantías de Ganancias
Ningún pronóstico garantiza ganancias o beneficios. El rendimiento pasado no garantiza resultados futuros.
""".trimIndent()

        AppLanguage.DE -> """
TIPPY ODDS bietet Fußballtipps und Vorhersagen, damit Nutzer Spielauswahlen, Prognosen, Quoten und historische Ergebnisse einsehen können.

Zweck der Vorhersagen
Fußballvorhersagen basieren auf statistischen Analysen und Trends. Fußballspiele sind unvorhersehbar, und Ergebnisse können niemals garantiert werden.

Informations- und Unterhaltungszweck
Alle in dieser App bereitgestellten Inhalte und Quoten dienen ausschließlich Informations- und Unterhaltungszwecken. TIPPY ODDS ist kein Wettanbieter und nimmt keine Einsätze an.

Verantwortungsbewusstes Handeln
Nutzer werden gebeten, eigenverantwortliche und bewusste Entscheidungen zu treffen.

Keine Gewinngarantie
Keine Vorhersage garantiert Gewinne. Statistische Werte der Vergangenheit sind keine Garantie für zukünftige Spielausgänge.
""".trimIndent()
    }

    // Authentication strings
    fun signInTitle(lang: AppLanguage): String = "TIPPY ODDS"

    fun signInSubtitle(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Daily Football Predictions & Betting Tips"
        AppLanguage.AR -> "توقعات ونصائح مباريات كرة القدم اليومية"
        AppLanguage.ES -> "Pronósticos y Consejos Diarios de Fútbol"
        AppLanguage.DE -> "Tägliche Fußballtipps & Prognosen"
    }

    fun signInWithGoogle(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Sign in with Google"
        AppLanguage.AR -> "تسجيل الدخول باستخدام Google"
        AppLanguage.ES -> "Iniciar sesión con Google"
        AppLanguage.DE -> "Mit Google anmelden"
    }

    fun signOut(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Sign Out"
        AppLanguage.AR -> "تسجيل الخروج"
        AppLanguage.ES -> "Cerrar sesión"
        AppLanguage.DE -> "Abmelden"
    }

    fun account(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Account"
        AppLanguage.AR -> "الحساب"
        AppLanguage.ES -> "Cuenta"
        AppLanguage.DE -> "Konto"
    }

    fun signInRequiredNotice(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Sign in with your Google account to access real-time football predictions and betting tips."
        AppLanguage.AR -> "سجّل الدخول بحساب Google للوصول إلى توقعات كرة القدم المحدثة مباشرة."
        AppLanguage.ES -> "Inicia sesión con tu cuenta de Google para acceder a los pronósticos en tiempo real."
        AppLanguage.DE -> "Melden Sie sich mit Ihrem Google-Konto an, um Echtzeit-Fußballtipps zu erhalten."
    }

    fun signInFailed(lang: AppLanguage): String = when (lang) {
        AppLanguage.EN -> "Sign-in was cancelled or failed. Please try again."
        AppLanguage.AR -> "تم إلغاء تسجيل الدخول أو فشل. يُرجى المحاولة مرة أخرى."
        AppLanguage.ES -> "El inicio de sesión se canceló o falló. Inténtalo de nuevo."
        AppLanguage.DE -> "Anmeldung abgebrochen oder fehlgeschlagen. Bitte erneut versuchen."
    }
}
