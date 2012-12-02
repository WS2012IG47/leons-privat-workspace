package uebung03;

import static gdi.MakeItSimple.*;

public class Divisor2 {

	public static void main(String[] args) {

		int ganzeZahl;
		int zählerVariable = 1;

		// Aufforderung zur Eingabe einer ganzen Zahl.
		println("Bitte geben Sie eine ganze Zahl ein: ");
		ganzeZahl = readInt();
		readLine();

		int ersterTeiler = 1;
		boolean ersterTeilerErreicht = false;

		// Abfangen der Eingabe von Zahlen die kleiner sind als eins.
		if (ganzeZahl >= 1) {
			print("Die Teiler der Zahl " + ganzeZahl + " sind {");

			/*
 * Der Zahlenbereich von 1 bis zur der, durch den ersten gefundenen
			 * Teiler (außer eins) geteilte, eingegebenen Zahl wird auf
			 * teilbarkeit mit dieser geprüft. Fals bis zu der Wurzel der
			 * eingegebenen Zahl keine Zahl gefunden wurde wird die suche
			 * abgebrochen den die Zahl ist eine Primzahl
			 */
			
			while (zählerVariable < (ganzeZahl / ersterTeiler) + 1) {
				// Bei Rest 0 ist eine Teilbarkeit gegeben.
				if (ganzeZahl % zählerVariable == 0) {
					print(zählerVariable + " ");

					// Fals ein Teiler ausgegeben wurde und dieser größer 1 ist
					// wurde der erste Teiler erreicht.
					if (!ersterTeilerErreicht && zählerVariable > 1) {
						ersterTeilerErreicht = true;
					}
				}

				// Fals der erste Teiler noch nicht erreicht wurde wird die
				// nächste Zahl als möglicher erster Teiler angenommen.
				if (!ersterTeilerErreicht) {
					ersterTeiler = zählerVariable + 1;
				}

				zählerVariable++;
			}
			// Der Ausgabe wird die eingegebene Zahl als möglicher Teiler
			// hinzugefügt.
			print(ganzeZahl + "}");
			println();

			// Debung Ausgaben
			// println(Math.sqrt(ganzeZahl));
			// println(ersterTeiler);
		}
		// Bei einer Eingabe welche kleiner als eins ist wird das Programm mit
		// Fehlermeldung beendet.
		else {
			println("Eingabe ungültig");
		}
	}
}

