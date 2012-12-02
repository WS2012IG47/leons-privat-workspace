package uebung06;

import static gdi.MakeItSimple.*;

public class StringExtensionsRecursive {

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
	}

	/**
	 * Diese Methode stellt sicher, dass die rekursive Umsetzung weiterhin über die selbe Signatur erreichbar ist.
	 * 
	 * @param original Ist der eingegebene String, der umgewandelt werden soll.
	 * @return Der in Großbuchstaben umgewandelte String wird zurückgegeben.
	 */
	public static String strToUpper(String original) {
		return strToUppercharZähler(original, "");
	}

	/**
	 * Sämtliche Chars die nicht großgeschrieben sind und keine Sonderzeichen sind, werden
	 * in Großbuchstaben umgewandelt.  
	 * 
	 * @param original Der umzuwandelnde String.
	 * @param convertHelper In diesem String werden die bereits bearbeiteten Chars zwischengespeichert.
	 * 						Bei einem fremden Aufruf wird ein leerer String erwartet.
	 * @return Der in Großbuchstaben umgewandelte String wird zurückgegeben.
	 */

	public static String strToUppercharZähler(String original,
			String convertHelper) {

		if (original.length() == convertHelper.length()) {
			return convertHelper;
		}

		char[] großBuchstaben = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
				'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
				'V', 'W', 'X', 'Y', 'Z', 'Ä', 'Ö', 'Ü' };
		char[] kleinBuchstaben = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', 'ä', 'ö', 'ü' };
		int position = 0;

		char charAnPosition = strCharAt(original, strLen(convertHelper));

		if (charAnPosition >= 'a' && charAnPosition <= 'z' || charAnPosition == 'ä' || charAnPosition == 'ö' || charAnPosition == 'ü') {
			position = charPositionInArray(kleinBuchstaben, charAnPosition, 0, kleinBuchstaben.length);
			convertHelper += großBuchstaben[position];
		} else {
			convertHelper += charAnPosition;
		}

		return strToUppercharZähler(original, convertHelper);
	}

	/**
	 * Auffinden des gesuchten Chars innerhalb des Arrays durch binäre
	 * Suche.
	 * 
	 * @param geprüftesArray Innerhalb des Arrays wird das Char gesucht.
	 * @param gesuchterChar Der Char, der innrerhalb des Array gesucht wird.
	 * @param bereichAnfang Es wird ein Wert entsprechend 0 erwartet.
	 * @param bereichEnde Es wird ein Wert entsprechend array.length() erwartet.
	 * @return Bei erfolgreicher Suche wird die Position des Chars innerhalb des
	 *         Arrays zurückgegeben, andernfalls wird -1 zurückgegeben.
	 */
	public static int charPositionInArray(char[] targetArray, char searchChar, int bereichAnfang, int bereichEnde) {

		int i = ((bereichEnde - bereichAnfang) / 2) + bereichAnfang;

		if (bereichAnfang > bereichEnde) {
			return -1;
		}
		if (targetArray[i] == searchChar) {
			return i;
		}
		if (targetArray[i] < searchChar) {
			return charPositionInArray(targetArray, searchChar, i + 1, bereichEnde);
		}
		if (targetArray[i] > searchChar) {
			return charPositionInArray(targetArray, searchChar, bereichAnfang, i - 1);
		}

		return -1;
	}

	/**
	 *  Diese Methode stellt sicher, dass die rekursive Umsetzung weiterhin über die selbe Signatur erreichbar ist.
	 * 
	 * @param original Der String der aufgetrennt wird.
	 * @param delimiter Das Trennzeichen an dem der String aufgetrennt wird.
	 * @return Das neue Array mit den aufgetrennten Teilstrings wird zurückgegeben.
	 */
	public static String[] strSplit(String original, char delimiter) {
		String[] array = new String[delimiterZähler(original, 1, 0, delimiter)];
		array[0] = new String();
		strSplit(array, original, 0, 0, delimiter);
		return array;
	}

	/**
	 *  Der eingegebene String wird an den delimitern aufgetrennt und die
	 * einzelnen Strings werden in ein neues Array geschrieben.
	 *
	 * @param array In diesem Array werden die Teilstrings gespeichert. Das erste Feld des Arrays muss bereits initialisiert sein.
	 * @param original Der String inklusive Trennzeichen.
	 * @param arrayZähler Definiert das aktive Arrayfeld. Es erwartet eine Übergabe von 0.
	 * @param charZähler Muss mit 0 initialisiert werden.
	 * @param delimiter Definiert das Trennzeichen, an dem der String in Teilstrings aufgeteilt werden soll.
	 */
	public static void strSplit(String[] array, String original, int arrayZähler, int charZähler, char delimiter) {
		if (charZähler < strLen(original)) {
			if (strCharAt(original, charZähler) == delimiter) {
				array[arrayZähler + 1] = new String();
				strSplit(array, original, arrayZähler + 1, charZähler + 1,
						delimiter);
				return;
			} else {
				array[arrayZähler] += strCharAt(original, charZähler);
				strSplit(array, original, arrayZähler, charZähler + 1,
						delimiter);
				return;
			}
		} else {
			return;
		}
	}

	/**
	 * Die delimiterZähler Methode stellt fest, wie groß das benötigte Array
	 * sein muss. Pro vorhandenem Delimiter wird das Array um ein weiteres Feld
	 * vergrößert.
	 * 
	 * @param original Der umzuwandelnde String.
	 * @param arrayZähler Muss mit 1 übergeben werden.
	 * @param charZähler Muss mit 0 übergebe werden.
	 * @param delimiter Ist das vorgegebene Trennzeichen, nachdem in dieser Methode gesucht wird.
	 * @return Die benötigte Größe des Arrays wird zurückgegeben.
	 */
	public static int delimiterZähler(String original, int arrayZähler, int charZähler, char delimiter) {
		if (charZähler < strLen(original)) {
			if (strCharAt(original, charZähler) == delimiter) {
				return delimiterZähler(original, arrayZähler + 1,
						charZähler + 1, delimiter);
			} else {
				return delimiterZähler(original, arrayZähler, charZähler + 1,
						delimiter);
			}
		} else {
			return arrayZähler;
		}
	}
}