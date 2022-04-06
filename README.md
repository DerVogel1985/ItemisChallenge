# ItemisChallenge
Project for Itemis Application
Sehr geehrte Damen und Herren,
ich habe mich entschieden, von den drei zur Auswahl stehenden Aufgaben, die erste zu lösen.

Zuerst hatte ich nur eine Lösung erarbeitet, die sich nur auf das Ausgeben der "shopping baskets" fokusierte und statisch war.

Nachdem ich das Ganze aber lieber objektorientiert haben wollte, habe ich noch einmal eine zweite Lösung erarbeitet, die etwas komplexer ist und das Ausgeben des "reciept" nur als eine Funktion der "Order" hat. Somit ist diese Lösung viel einfacher zu erweitern und an neue Bedürfnisse anzupassen.

Leider hatte ich ein Problem mit dem Runden, welches nur bei dem letzten Produkt des dritten "baskets" auftrat. Nachdem ich zunächst dachte, dies könnte durch die Ungenauigkeit kommen, die mit dem Dateityp "double" verbunden ist, habe ich nochmals eine Lösung erarbeitet die mit "BigDecimal" statt "double" arbeitet. Allerdings habe ich auch hier das Rundungsproblem und habe dieses leider auch bis jetzt nicht beheben können.
Mir ist klar, wo das Problem entsteht, nur leider nicht warum.
Es geht um die Aufrundung in 0.05 Schritten. 
Hierfür nehme ich den Produktpreis, multipliziere diesen mit dem errechneten Steuersatz und teile durch 100, um die Steuer pro Bestellposition zu erhalten. 
Dann kürze ich die Dezimalzahl auf zwei Nachkommastellen, multipliziere das Ganze mit "20.0"(um die 0.05 Schritte zu erhalten), runde auf und teile wieder durch "20.0".
Das funktioniert auch für die ersten zwei "baskets", für den letzten aber leider nicht.

Falls sich jemand Fachkundiges mein Ergebnis ansieht und den Fehler findet, wäre ich über eine kurze Rückmeldung, wo genau mein Problem liegt, ausgesprochen dankbar!

Nachdem mir der Fehler und die Ungenauigkeit in meiner Lösung keine Ruhe lässt, werde ich für mich noch weiter an der Lösung arbeiten.

Ich bitte zu berücksichtigen, dass ich durch den sehr spontanen Erhalt der Aufgabe und durch anderweitige Auslastung meiner Zeit leider nicht imstande war, so viel Zeit zu investieren, wie ich es gerne getan hätte, um eine optimale Lösung zu präsentieren.

Vielen Dank für die interessante Aufgabe und das Interesse an meiner Person.
Ich freue mich auf Ihr Feedback und hoffe, dass mein Code und die Kommentare gut verständlich sind.

Einen schönen Tag und freundliche Grüße,
Frank Schmitteckert
