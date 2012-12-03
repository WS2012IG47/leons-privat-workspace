package uebung04;

import static gdi.MakeItSimple.*;

/*
 * Gruppe 4 - 7
 * Philip Dombrowski, Matrikelnummer: 1220235
 * Tobias Kunz, Matrikelnummer: 1232749
 * Timm Dobhan, Matrikelnummer: 1232995
 */

public class MergeSort {

	public static int zahl1;
	public static int zahl2;

	public static void main(String[] args) {
		// println("geben sie den Pfard zu der zu sortierenden Datei an");
		// String filename = readLine();
		String filename1 = "G:\\workspace\\leons-privat-workspace\\ADS\\uebung04\\numbers.txt";
		String filename2 = "G:\\workspace\\leons-privat-workspace\\ADS\\uebung04\\hilfsbandEins.txt";
		String filename3 = "G:\\workspace\\leons-privat-workspace\\ADS\\uebung04\\hilfsbandZwei.txt";

		if (isFilePresent(filename1)) {
			Object quellband;
			Object hilfsbandEins;
			Object hilfsbandZwei;

			for (int lauflänge = 1; lauflänge < 11; lauflänge = lauflänge * 2) {
				quellband = openInputFile(filename1);
				hilfsbandEins = openOutputFile(filename2);
				hilfsbandZwei = openOutputFile(filename3);

				split(lauflänge, quellband, hilfsbandEins, hilfsbandZwei);

				closeInputFile(quellband);
				closeOutputFile(hilfsbandEins);
				closeOutputFile(hilfsbandZwei);

				quellband = openOutputFile(filename1);
				hilfsbandEins = openInputFile(filename2);
				hilfsbandZwei = openInputFile(filename3);

				merge(lauflänge, quellband, hilfsbandEins, hilfsbandZwei);

				closeOutputFile(quellband);
				closeInputFile(hilfsbandEins);
				closeInputFile(hilfsbandZwei);
			}
		}
	}

	public static void merge(int lauflänge, Object quellband,
			Object hilfsbandEins, Object hilfsbandZwei) {
		zahl1 = readInt(hilfsbandEins);
		zahl2 = readInt(hilfsbandZwei);

		while (onePartOfMerge(lauflänge, quellband, hilfsbandEins, hilfsbandZwei));
	}

	public static boolean onePartOfMerge(int lauflänge, Object quellband,
			Object hilfsbandEins, Object hilfsbandZwei) {
		int hilfsbandEinsZähler = 0;
		int hilfsbandZweiZähler = 0;

		while (true) {
			// ---------> Band1
			if (hilfsbandEinsZähler < lauflänge) {
				if (zahl1 <= zahl2) {

					if (isEndOfInputFile(hilfsbandEins)) {
						while (!isEndOfInputFile(hilfsbandZwei)) {
							print(quellband, " " + zahl2);
							zahl2 = readInt(hilfsbandZwei);
							hilfsbandZweiZähler++;
						}

						return false;
					}

					print(quellband, " " + zahl1);
					hilfsbandEinsZähler++;
					if (!isEndOfInputFile(hilfsbandEins)) {
						zahl1 = readInt(hilfsbandEins);
					} else {
						return false;
					}
				}
			} else {
				while (hilfsbandZweiZähler < lauflänge) {
					if (!isEndOfInputFile(hilfsbandZwei)) {
						print(quellband, " " + zahl2);
						zahl2 = readInt(hilfsbandZwei);
						hilfsbandZweiZähler++;
					} else {
						return false;
					}
				}

				return true;
			}

			// <--------- Band1

			// ---------> Band2

			if (hilfsbandZweiZähler < lauflänge) {
				if (zahl1 > zahl2) {

					if (isEndOfInputFile(hilfsbandZwei)) {
						while (!isEndOfInputFile(hilfsbandEins)) {
							print(quellband, " " + zahl1);
							zahl1 = readInt(hilfsbandEins);
							hilfsbandEinsZähler++;
						}

						return false;
					}

					print(quellband, " " + zahl2);
					hilfsbandZweiZähler++;

					if (!isEndOfInputFile(hilfsbandZwei)) {
						zahl2 = readInt(hilfsbandZwei);
					} else {
						return false;
					}
				}
			} else {
				while (hilfsbandEinsZähler < lauflänge) {
					if (!isEndOfInputFile(hilfsbandEins)) {
						print(quellband, " " + zahl1);
						zahl1 = readInt(hilfsbandEins);
						hilfsbandEinsZähler++;
					} else {
						return false;
					}
				}

				return true;
			}

			// <--------- Band2
		}
	}

	public static void split(int lauflänge, Object quellband,
			Object hilfsbandEins, Object hilfsbandZwei) {
		boolean bandauswahl = true;

		int zahl;

		while (!isEndOfInputFile(quellband)) {
			if (bandauswahl) {
				for (int i = 0; i < lauflänge; i++) {
					zahl = readInt(quellband);
					print(hilfsbandEins, " " + zahl);

					if (isEndOfInputFile(quellband)) {
						return;
					}
				}

				bandauswahl = false;
			} else {
				for (int i = 0; i < lauflänge; i++) {
					zahl = readInt(quellband);
					print(hilfsbandZwei, " " + zahl);

					if (isEndOfInputFile(quellband)) {
						return;
					}
				}

				bandauswahl = true;
			}
		}
	}

}
