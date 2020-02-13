Hallo,

Ich habe heute Abend die große Freude mit Ihnen über das Thema “Exactly once processing” zu sprechen.

Ich stelle zuerst meinen Arbeitgeber und mich vor. Danach werde ich Ihnen die Problemstellungen schildern & Ihnen danach mögliche Problemlösungen vorstellen. Sobald wir den trockenen Theorie-Teil hinter uns haben werde ich Ihnen das anhand eines Szenarios vorführen und Code zeigen. Abschließend können wir in einer kurzen Q&A Session offene Fragen klären.

Wir als adesso Austria GmbH sind ein IT-Dienstleister der seine Kunden als starker Partner vom Requirements Engineering bis zum Rollout in allen Projekt-Stadien begleitet.

Mein Name ist Dominik Dorfstetter und ich bin Full-Stack Software Engineer. Wer nach meinem Vortrag mit mir in Kontakt bleiben möchte kann mir gerne auf Twitter oder auf GitHub folgen.

In der von Natur aus lose-gekoppelten Welt der Micro-Service Architekturen müssen wir uns in einigen Belangen darum kümmern, dass Anfragen garantiert abgearbeitet werden. Dabei unterscheiden wir zwischen “at-least-once”, “at-most-once” und “exactly-once” processing.

Wenn wir uns aufgrund von Geschäftsprozessen drauf verlassen müssen, dass wir eine Anfrage genau einmal verarbeiten müssen wir dafür Vorkehrungen treffen. Beispiele dafür wären Bestellprozesse oder Überweisungen. Es bedeutet, dass unsere Systeme die Daten senden und die, die diese Daten wieder weiter verarbeiten eng zusammenarbeiten müssen.

In der Einmal-Verarbeitung müssen wir uns darum kümmern, dass wir unsere Operationen idempotent sind, da es sonst in unserem System zu Inkonsistenzen kommt, weiterst müssen wir uns um darum kümmern unsere Nachrichten zu deduplizieren.

Eine idempotente Operation ist eine, die viele Male ausgeführt werden kann, ohne einen Effekt zu verursachen als nur einmal ausgeführt zu werden. Der Http 1.1 Standard definiert idempotenz wie folgt. GET, HEAD, PUT und DELETE wären Beispiele für idempotente Operationen wogegen POST ein Beispiel für eine nicht idempotente Operation ist.

Wie können wir uns diesen Problemen stellen?

Da unsere produzierenden und konsumierenden Systeme eng miteinander zusammenarbeiten müssen, müssen wir uns darum kümmern eine Ende-zu-Ende Garantie herzustellen.

Dazu gibt es mehrere Lösungsansätze, ein paar davon stelle ich Ihnen jetzt vor. Verteilte Transaktions-Logs, zu denen ich auch LRU-Caches oder Bloom-Filter zähle oder auf konsumierender Seite die Entscheidung zu treffen nur abgeschlossene Transaktionen zu lesen.

Um dazu auch Idempotenz herzustellen können wir auf atomare Transaktionen zurückgreifen oder unsere Operationen in einer Workflow-Engine durchführen. 

Ein Bild sagt bekanntlich mehr als tausend Worte und aus diesem Grund habe ich ein Szenario vorbereitet und möchte danach noch kurz über dessen Architektur sprechen.

Wir befassen uns mit einer Betriebsdatenerfassung und konkret in diesem Beispiel mit Wind-Turbinen. Die Sensor-Daten werden an einen Kafka-Broker geschickt und durch Spring-Boot basiere Micro-Services verarbeitet. Am Ende werden diese Daten über eine API einem Angular-Dashboard zur Verfügung gestellt.

* Erklärung Diagramm

Damit hätten wir den theoretischen Abschnitt erledigt und ich zeige Ihnen das jetzt Anhand einer Live-Demo.

* Live-Demo

* Q&A Session

* Verabschiedung
