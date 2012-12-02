package uebung04;

import static gdi.MakeItSimple.*;

public class Conversion {

	public static void main(String[] args) {
		println("Gebe Sie eine Ziffernfolge ein: ");
		println(convert(readLine()));
	}
	// Methode zur Faktorberechnung.
	public static int factor(int basis, int exponent) {
		int ergebnis = basis;
		int zähler = 0;

		while (zähler < exponent - 1) {
			// Abfangen eines Überlaufs.
			// -1 wird als Signal für ein ungültiges, 
			// bzw. überlaufendes Integer übergeben.
			if (ergebnis > Integer.MAX_VALUE / basis) { 
				return -1;
			}
			ergebnis *= basis;
			zähler++;
		}
		if (exponent == 0) {
			return 1;
		}
		return ergebnis;
	}
	// Methode zum Umwandeln des Strings in ein Int.
	public static int convert(String digits) {
		int ergebnis = 0;
		int zähler = 0;
		int charValue = 0;

		// Schleife zur Prüfung und Konvertierung der einzelnen Zeichen.
		while (zähler < strLen(digits)) {

			int momentaneStelle = strCharAt(digits, zähler);
			
			// Ignorieren von Vorzeichen.
			if (zähler == 0 && (momentaneStelle == '+' || momentaneStelle == '-')) {
				zähler++;
			}
			// Abbruch der Methode bei einer ungültigen Eingabe.
			else if (momentaneStelle < '0' || momentaneStelle > '9') {
				return 0;
			}
			else {
				// Überprüfung der aktuelle Stelle auf einen 
				// Überlauf durch Faktorberechnung.
				if(factor(10, strLen(digits) - zähler - 1) == -1) {
					return 0;
				}
				// Berechnung der aktuellen Stelle durch Faktorberechnung.	
				charValue = (momentaneStelle - '0') * factor(10, strLen(digits) - zähler - 1);
				
				// Prüfung des Ergebnisses auf Überlauf durch 
				// Addition der aktuellen Stelle.
				if (ergebnis > Integer.MAX_VALUE - charValue) { 							
					return 0;
				}
				
				// Dem Ergebnis wird der Wert der aktuelle Stelle aufaddiert.
				ergebnis += charValue;
				zähler++;
			}
		}
		return ergebnis;
	}

}

