
package uebung04;

import static gdi.MakeItSimple.*;

public class Palindrome {

	public static void main(String[] args) {

		// Aufforderung zur Eingabe eines möglichen Palindroms.
		println("Geben Sie eine Zeichenfolge ein: ");
		if (isPalindrome(readLine())) {
			println("Palindrom erkannt");
		} else {
			println("Kein Palindrom");
		}
	}

	// Methode zum Vergleichen der char Werte mithilfe von zwei Zählern, die in
	// entgegengesetzter Richtung die Zeichen vergleichen.
	public static boolean isPalindrome(String text) {
		int zähler = 0;
		int zähler2 = strLen(text) - 1;
		while (zähler != zähler2) {

			// Überspringen der Leerzeichen bei dem Zähler der von vorne zählt.
			while (strCharAt(text, zähler) == ' ') {
				zähler++;
			}

			// Überspringen der Leerzeichen bei dem Zähler der von hinten zählt.
			while (strCharAt(text, zähler2) == ' ') {
				zähler2--;
			}

			// Wenn die char Werte der beiden Zähler nicht identisch sind,
			// wird ein false ausgegeben, da es sich nicht um ein Palindrom
			// handelt.
			if (strCharAt(text, zähler) != strCharAt(text, zähler2)) {
				return false;
			}
			zähler++;
			zähler2--;
			  /* Wenn die beiden Zähler jeweils bis zur Mitte des Strings die char
			  Werte verglichen haben und sie übereinstimmten, wurde ein
			  Palindrom erkannt und es wird ein true ausgegeben. */
			if (zähler >= strLen(text) / 2 + 1 || zähler2 <= strLen(text) / 2 - 1) {
				return true;
			}
		}
		return true;
	}
}

