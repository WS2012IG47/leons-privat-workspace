package uebung05;

import static gdi.MakeItSimple.*;

public class StringExtensions {

	public static void main(String[] args) {

		println("Aufgabe 1: Ein eingegebener String wird in Großbuchstaben umgewandelt.");
		println("Bitte geben Sie einen beliebigen Satz ein.");
		println(strToUpper(readLine()));

		println("Aufgabe 2: Ein eingegebener String wird an den Trennzeichen aufgeteilt und in einem passend langem String-Array zurückgegeben.");
		println("Das Trennzeichen ist selber zu bestimmen und nach Eingabe des Strings nochmal gesondert einzugeben.");
		String[] ausgabeStringArray = strSplit(readLine(), readChar());
		readLine();
		int i = 0;

		while (i < ausgabeStringArray.length) {
			print(ausgabeStringArray[i] + " ");
			i++;
		}

		println();
		println("Aufgabe 3: Der von Ihnen eingegebene String wird nach einem Teilstring durchsucht.");
		println("Geben Sie zuerst einen String ein, bestätigen diesen mit der Return-Taste und geben dann den zu suchenden Teil ein.");
		println(strScan(readLine(), readLine()));
	}

	public static String strToUpper(String original) {
		int i = 0;
		String umwandlungsHilfe = "";
		char[] großBuchstaben = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'Ä', 'Ö', 'Ü' };
		char[] kleinBuchstaben = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'ä', 'ö', 'ü' };
		int position = 0;

		while (i < strLen(original)) { // Der eingegebene String wird bis zum Ende durchlaufen
			char charAnPosition = strCharAt(original, i);
			// Sofern der Char an der aktuellen Position einem Char zwischen 'a' und 'z'
			// oder den Umlauten ä,ö und ü entspricht, wird der Char, der an der selben 
			// Stelle im großBuchstaben Array steht, in den umwandlungsHilfe String geschrieben.
			if (charAnPosition >= 'a' && charAnPosition <= 'z'
					|| charAnPosition == 'ä' || charAnPosition == 'ö'
					|| charAnPosition == 'ü') {
				position = charPositionInArray(kleinBuchstaben, charAnPosition);
				umwandlungsHilfe += großBuchstaben[position];
			}
			// Falls das Char nicht die oberen Kriterien erfüllt, wird es unverändert in den 
			// umwandlungsHilfe String geschrieben, da es sich dann entweder um einen Großbuchstaben
			// oder um ein Sonderzeichen handelt.
			else
				umwandlungsHilfe += charAnPosition;
			i++;
		}
		return umwandlungsHilfe;
	}

	// Methode zum Feststellen der Position des Chars innerhalb des Arrays.
	public static int charPositionInArray(char[] geprüftesArray, char gesuchterChar) {
		int i = 0;
		while (i < geprüftesArray.length) {
			if (geprüftesArray[i] == gesuchterChar) {
				return i;
			}
			i++;
		}
		return -1;
	}

	// Diese Methode teilt die Zeichenkette in Teil-Strings auf und gibt diese
	// in einem passend langen String-Array zurück.
	public static String[] strSplit(String original, char delimiter) {
		int arrayiterator = 0;
		int arrayGröße = 1; // Das Array braucht mindestens ein Feld das einen leeren String aufnehmen kann.
		int originalStringIterator = 0;

		// Hier werden vor dem Auftrennen in einzelne Strings die vorhandenen "delimiter" gezählt.
		// Diese Anzahl + 1 ergibt die Anzahl von benötigten Arrayfeldern.
		while (originalStringIterator < strLen(original)) {
			if (strCharAt(original, originalStringIterator) == delimiter) {
				arrayGröße++;
			}
			originalStringIterator++;
		}
		originalStringIterator = 0;

		String[] strSplit = new String[arrayGröße];
		
		// Das erste String Element wird initialisiert um die Ausgabe von "null" zu verhindern.
		strSplit[0] = new String(); 

		while (originalStringIterator < strLen(original)) {

			// Falls das aktuell geprüfte Char ein "delimiter" ist, wird das nächste Arrayfeld ausgewählt.
			if (strCharAt(original, originalStringIterator) == delimiter) {
				arrayiterator++;
				
				// Vor dem Nutzen des neuen Feldes wird dieses initialisiert.
				strSplit[arrayiterator] = new String(); 
				}

			// Wenn der aktuelle char kein "delimiter" ist, wird er dem String
			// in dem Ausgewählten Arrayfeld hinzugefügt.
			else {
				strSplit[arrayiterator] += strCharAt(original, originalStringIterator);
			}
			originalStringIterator++;
		}
		return strSplit;
	}

	// Die Funktion durchsucht den String "original" nach einem Teil-String
	// "needle" und gibt dessen Position innerhalb von "original" zurück. 
	// Wenn keiner gefunden wird, wird -1 zurückgegeben.
	static int strScan(String original, String needle) {

		// Prüfung ob einer der Strings leer ist, in dem Fall ist keine
		// Übereinstimmung möglich und es wird -1 ausgegeben.
		if (strEqual(needle, "") || strEqual(original, "")) {
			return -1;
		}

		int originalZähler = 0;
		int needleZähler = 0;
		char originalChar, needleChar;

		while (originalZähler < strLen(original)) {
			// Für die momentane Stelle des Zählers wird der entsprechende char gesucht.
			originalChar = strCharAt(original, originalZähler);
			needleChar = strCharAt(needle, needleZähler);

			if (originalChar == needleChar) {
				// Falls das Ende des "needle" erreicht ist, wird die Stelle,
				// die in dem orginal String, das erste Char repräsentiert, ausgegeben.
				if (needleZähler == strLen(needle) - 1) {
					return originalZähler - strLen(needle) + 1;
				}
				needleZähler++;
			} else {
				if (needleZähler > 0) { 
					// Dies ist der Fall wenn ein oder mehrere übereinstimmende Chars gefunden wurden.
					// Zurücksetzen des needleZählers, falls bereits ein oder mehrere identische 
					// Chars gefunden wurden und der darauffolgende dann nichtmehr dem needle
					// entspricht. In diesem Fall wird bei dem nächsten Schleifendurchlauf der 
					// originalZähler um 1 erhöht und ab dieser Stelle erneut geprüft.
					originalZähler -= needleZähler;
					needleZähler = 0;
				}
			}
			originalZähler++;
		}
		// Keine Übereinstimmung gefunden. Ausgabe -1
		return -1;
	}
}
