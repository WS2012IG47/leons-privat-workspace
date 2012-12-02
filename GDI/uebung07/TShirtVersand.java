package uebung07;
 
interface TShirtVersand{

/**
 * Um eine Bestellung aufgeben zu können, werden sämtliche unten stehende Daten benötigt.	
 * @param kunde Alle gespeicherten Daten über den Kunden, wie zum Beispiel Name, Vorname, Adresse, Telefonnummer...
 * @param artikelnummer Hinter der Artikelnummer ist ein einziger, unverwechselbarer Artikel aus dem Shop gespeichert.
 * @param anzahl Die Anzahl der einzenen Artikel (Artikelnummern)
 * @param bezahlung Auf welche Art möchte der Kunde bezahlen. Kreditkarte (1), PayPal (2), EC-Karte (3), Überweisung (4), Barzahlung bei Abholung (5), Rechnung (6). 
 * @return Es wird eine Bestellbestätigung ausgegeben, in der für den Kunden alle Artikel und Daten ersichtlich sind.
 */
	String bestellung(Personendaten kunde, int[] artikelnummer, int[] anzahl, int bezahlung);

/**
 * Um eine Rechnung erstellen zu können, benötigt man die Kundendaten und die Bestellten Artikel, die man durch die Bestellnummer abfragen kann.
 * @param bestellnummer Mit der Bestellnummer kann man abfragen, welche Artikel in welcher Anzahl wann und wie bestellt wurden.
 * @return Es wird eine Rechnung ausgegeben.
 */
	String rechnungen(int bestellnummer);	 
		
/**
 * Dieses Interface gibt die Verfügbarkeit der einzelnen Artikel an. Diese Information ist sowohl für den Shop Betrieber wie auch für den Kunden nützlich.
 * @param artikelnummer Hinter der Artikelnummer ist ein einziger, unverwechselbarer Artikel aus dem Shop gespeichert.
 * @param Anzahl Gibt an, wieviele Artikel mit dieser Artikelnummer noch auf Lager sind.
 * @param verfügbarkeit Falls der Artikel nicht verfügbar ist, wird das voraussichtliche Verfügbarkeitsdatum angegeben.
 * @return Die Verfügbarkeit wird als String zu dem jeweiligen Artikel angezeigt.
 */
	String verfügbarkeit(int Artikelnummer, int Anzahl, String erneuteLieferung);
	
/**
 * Falls der Kunde seine Bestellung stornieren möchte. 	
 * @param bestellnummer Mit der Bestellnummer kann man abfragen, welche Artikel in welcher Anzahl wann und wie bestellt wurden.
 * @param kunde Um zu wissen, wer die Artikel bestellt hat
 * @return Der Kunde erhält eine Stornierungsbestätigung.
 */
	String stornieren(int bestellnummer, Personendaten kunde);
	
/**
 * Sobald der Versand erfolgt ist, bekommt der Kunde eine Versandbestätigung per E-Mail zugesendet.
 * @param bestellnummer Mit der Bestellnummer kann man abfragen, welche Artikel in welcher Anzahl wann und wie bestellt wurden.
 * @param kunde Alle gespeicherten Daten über den Kunden, wie zum Beispiel Name, Vorname, Adresse, Telefonnummer...
 * @return Der Kunde erhält per E-Mail eine Versandbestätigung.
 */
	String versandbestätigug (int bestellnummer, Personendaten kunde);
}
