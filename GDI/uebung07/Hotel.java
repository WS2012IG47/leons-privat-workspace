package uebung07;

interface Hotel{
/**
 * Ein Zimmer für einen gewissen Zeitraum reservieren
 * @param anreise Das Datum der Anreise um zu wissen, ab wann das Zimmer reserviert werden soll.
 * @param abreise Das Datum der Abreise um zu wissen, bis wann das Zimmer reserviert werden soll.
 * @param leistungsUmfang Um den Umfang der Reserrvierung zu wissen. Wie z.B. Vollpension, Halbpension,...
 * @param kunde Die Daten des Kunden um zu wissen, für wen das Zimmer reserviert ist.
 * @param kreditkarte Die Kreditkartendaten werden zur Authentifizierung des Kunden bei der Bestätigung benötigt.
 * @param extrawünsche Hier drin werden eventuelle Extrawünsche des Kunden gespeichert.
 * @return Gibt ein Objekt mit allen Informationen zur Reservierung zurück. 
 */
	Reservierung reservierung (String anreise, String abreise, int leistungsUmfang, Personendaten kunde, String extrawünsche);

/**
 * Falls der Kunde seine Reservierung stornieren möchte. 	
 * @param reservierung Dieses Objekt wird benötigt, um die vorhandenen Reservierungsdaten abzufragen.
 * @return Der Kunde erhält eine Stornierungsbestätigung.
 */
	String stornieren(Reservierung reservierung);

/**
 * 	Wenn der Kunde per Kreditkarte zahlen möchte, werden diese Daten benötigt.
 * @param bezahlung Daten zur Zahlungsart des Kunden.
 * @param reservierung Um den Preis berechnen zu können, werden die Daten des Aufenthaltes benötigt
 * @return Zahlungsquittung 
 */
	String bezahlen(Bezahlung bezahlung, Reservierung reservierung);
	
/**
 * Beim Check-in des Kunden wird nur die Reservierungsnummer gebraucht.	
 * @param reservierung Damit das Hotel weiß, welches Zimmer, auf welchen Namen mit welchen Sonderleistungen der Kunde gebucht hat.
 * @return Ein String mit allen Daten und Sonderwünchen des Kunden wird ausgegeben.
 */
	String einchecken(Reservierung reservierung);

/**
 * Beim auschecken benötigt der Kunde seine Reservierungsnummer, damit das Hotel weiß, welches Zimmer abgerechnet werden muss.	
 * @param reservierung Um den Preis berechnen zu können, werden die Daten des Aufenthaltes benötigt
 * @return Es wird eine Rechnung ausgegeben auf der alle eventuellen Sonderwünsche des Kunden mit einbezogen werden.
 */
	String auschecken(Reservierung reservierung);
}
