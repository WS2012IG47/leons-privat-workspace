
package uebung03;

import static gdi.MakeItSimple.*;

public class Divisor1 {

            public static void main(String[] args) {
		int number;
		int divisor = 1;

		// Aufforderung zur Eingabe einer ganzen Zahl.
		println("Bitte geben Sie eine ganze Zahl ein: ");
		number = readInt();
		readLine();

		// Abfangen von eingegebenen Zahlen die < 1 sind.
		if (number >= 1) {
			print("Die Teiler der Zahl " + number + " sind {");

			/* Der Zahlenbereich von 1 bis zur  eingegebenen Zahl 
			 * wird auf Teilbarkeit mit dieser geprüft. 
                                                      */

			while (divisor < number) {

				// Bei einem Restwert von 0, ist die Teilbarkeit gegeben und der
				// gefundene Teiler wird ausgegeben.
				if (number % divisor == 0) {
					print(divisor + " ");
				}
				divisor++;
			}

			// Der Ausgabe wird die eingegebene Zahl als Teiler
			// hinzugefügt.
			print(number + "}");
		}

		// Bei einer Eingabe die < 1 ist, wird das Programm mit einer
		// Fehlermeldung beendet.
		else {
			println("Eingabe ungültig");
		}
	}
}
