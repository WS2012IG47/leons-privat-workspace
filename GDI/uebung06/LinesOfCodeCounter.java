package uebung06;

import static gdi.MakeItSimple.*;

public class LinesOfCodeCounter {

	public static void main(String[] args) {
		print("Bitte geben Sie den Pfad zur Java Quelldatei an: ");
		println("Anzahl Lines of Code: " + countLines(readLine()));
	}
	/**
	 * Gezählt werden Blockkommentare und normale Kommentare. zusätzlich sind inline Kommentare nur mit // möglich. 
	 * @param filename Hier wird Pfad zu dem Dokument beid em die Zeilen gezählt werden sollen erwartet.
	 * @return Anzahl der Zeilen.
	 */
	public static int countLines(String filename) {
		int lineCounter = 0;
		boolean blockKommentar = false;
		boolean codeZeileVorhanden = false;

		Object datei = openInputFile(filename);
		
		if (isFilePresent(filename)) {
			// Es wird jede Zeile einzeln eingelesen, bis das Ende der aufgerufenenen Datei erreicht ist.
			while (!isEndOfInputFile(datei)) {
				String zeile = readLine(datei);
				
				// Prüfung auf das Ende eines angefangenen Blockkommentars.
				if (blockKommentar == true) {
					if (!(strScan(zeile, "*/") == -1))
						blockKommentar = false;
				}
				
				else {
					codeZeileVorhanden = isInlineKommentar(zeile);
					
					// Prüfungen ob es sich um eine Codezeile handelt.
					if (strScan(zeile, "/*") == -1	&& !strEqual(zeile, "")	&& strScan(zeile, "//") == -1) { 
						codeZeileVorhanden = true;	
					} 
					// Prüfung ob sich in der eingelesenen Zeile ein Blockkommentar befindet
					if (!(strScan(zeile, "/*") == -1)) {
						if (strScan(zeile, "*/") == -1) {
							blockKommentar = true;
						}
					}
				}
				// Wenn kein Kommentar vorhanden ist oder sich der Kommentar am Ende einer Codezeile befindet, 
				// wird die geprüfte Zeile mitgezählt.
				if(codeZeileVorhanden)
				{
					lineCounter++;
					codeZeileVorhanden = false;
				}
			}
		}
		closeInputFile(datei);
		return lineCounter;
	}
/**
 * Diese Methode überprüft ob in einer Zeile in der ein Kommentar steht sich noch Code befindet.
 * Wenn ja, wird geprüft ob der Code sich in dem Kommentar oder davor befindet. 
 * @param zeile Dieser String wird nach enthaltenen inline Kommentaren durchsucht.
 * @return Wenn vor einem inline Kommentar noch Code steht, wird ein true zurückgegeben, andernfalls ein false.
 */
	public static boolean isInlineKommentar(String zeile){
		if (strScan(zeile, ";") != -1){							
			if (strScan(zeile, ";") < strScan(zeile, "//")) {
				return true;
			}
		} 		
		if (strScan(zeile, ".") != -1){							
			if (strScan(zeile, ".") < strScan(zeile, "//")) {
				return true;
			}
		} 		
		if (strScan(zeile, ",") != -1){							
			if (strScan(zeile, ",") < strScan(zeile, "//")) {
				return true;
			}
		} 
		if (strScan(zeile, "(") != -1){							
			if (strScan(zeile, "(") < strScan(zeile, "//")) {
				return true;
			}
		} 
		if (strScan(zeile, ")") != -1){							
			if (strScan(zeile, ")") < strScan(zeile, "//")) {
				return true;
			}
		} 
		if (strScan(zeile, "{") != -1){							
			if (strScan(zeile, "{") < strScan(zeile, "//")) {
				return true;
			}
		} 
		if (strScan(zeile, "}") != -1){							
			if (strScan(zeile, "}") < strScan(zeile, "//")) {
				return true;
			}
		} 
		if (strScan(zeile, "'") != -1){							
			if (strScan(zeile, "'") < strScan(zeile, "//")) {
				return true;
			}
		} 
		if (strScan(zeile, "\"") != -1){							
			if (strScan(zeile, "\"") < strScan(zeile, "//")) {
				return true;
			}
		} 
		return false;
	}

/**
 * Die Funktion durchsucht den String "original" nach einem Teil-String
 * "needle" und gibt dessen Position innerhalb von "original" zurück. 
 * Wenn keiner gefunden wird, wird -1 zurückgegeben.
 * 
 * @param original Wird von der Methode nach dem needle String durchsucht.
 * @param needle Der gesuchte String.
 * @return Zurückgegeben wird die erste Stelle des gesuchten Strings.
 */
		static int strScan(String original, String needle) {
			// Prüfung ob einer der Strings leer ist. In dem Fall ist keine
			// Übereinstimmung möglich und es wird -1 ausgegeben.
			if (strEqual(needle, "") || strEqual(original, "")) {
				return -1;
			}
			int originalZähler = 0;
			int needleZähler = 0;
			char originalChar, needleChar;

			while (originalZähler < strLen(original)) {
				// Für die momentane Stelle des Zählers wird der entsprechende char
				// gesucht.
				originalChar = strCharAt(original, originalZähler);
				needleChar = strCharAt(needle, needleZähler);

				if (originalChar == needleChar) {
					// Falls das Ende des "needle" erreicht ist, wird die Stelle,
					// die in dem orginal String,
					// das erste Char repräsentiert, ausgegeben.
					if (needleZähler == strLen(needle) - 1) {
						return originalZähler - strLen(needle) + 1;
					}
					needleZähler++;
				} else {
					if (needleZähler > 0) { 
						// Dies ist der Fall wenn ein (oder mehrere)
						// übereinstimmende Chars gefunden wurden.
						// Zurücksetzen des needleZählers, falls bereits ein oder
						// mehrere identische Chars gefunden wurden
						// und der darauffolgende dann nichtmehr dem needle
						// entspricht. In diesem Fall wird der originalZähler um den needleZähler 
						// subtrahiert.
						originalZähler -= needleZähler;
						needleZähler = 0;
					}
				}
				originalZähler++;
			}
			// Keine Übereinstimmung gefunden. Ausgabe -1
			return -1;
		}
		// Aufgabe strScan Ende
	
}
