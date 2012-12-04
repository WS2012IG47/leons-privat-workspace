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
	public static boolean reloadZahl1 = false;
	public static boolean reloadZahl2 = false;

	public static void main(String[] args) {
		// println("geben sie den Pfard zu der zu sortierenden Datei an");
		// String filename = readLine();
		String filename1 = "C:\\Users\\Leon\\Documents\\workspace\\leons-privat-workspace\\ADS\\uebung04\\numbers.txt";
		String filename2 = "C:\\Users\\Leon\\Documents\\workspace\\leons-privat-workspace\\ADS\\uebung04\\hilfsbandEins.txt";
		String filename3 = "C:\\Users\\Leon\\Documents\\workspace\\leons-privat-workspace\\ADS\\uebung04\\hilfsbandZwei.txt";

		if (isFilePresent(filename1)) {
			Object quellband;
			Object hilfsbandEins;
			Object hilfsbandZwei;
			Object ausgabeBand;

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
				
					ausgabeBand = openInputFile(filename1);
					printTape(ausgabeBand, '*', lauflänge);
					closeInputFile(ausgabeBand);
			}
		}
	}

	public static void merge(int lauflänge, Object quellband, Object hilfsbandEins, Object hilfsbandZwei) {
		zahl1 = readInt(hilfsbandEins);
		zahl2 = readInt(hilfsbandZwei);

		while (onePartOfMerge(lauflänge, quellband, hilfsbandEins, hilfsbandZwei));
	}

	public static boolean onePartOfMerge(int lauflänge, Object quellband, Object hilfsbandEins, Object hilfsbandZwei) {
		int hilfsbandEinsZähler = 0;
		int hilfsbandZweiZähler = 0;

		while (true) {

			// ---------> Band1 EOF
			if (isEndOfInputFile(hilfsbandEins)) {
				while (!isEndOfInputFile(hilfsbandZwei)) {
					
					if (reloadZahl2) {
						zahl2 = readInt(hilfsbandZwei);
					}
					
					print(quellband, " " + zahl2);
					hilfsbandZweiZähler++;
					
					reloadZahl2 = true;
				}
				return false;
			}
			// <--------- Band1 EOF

			// ---------> Band1 EOS
			if (!(hilfsbandEinsZähler < lauflänge)) {
				while (hilfsbandZweiZähler < lauflänge) {

					if (reloadZahl2) {
						zahl2 = readInt(hilfsbandZwei);
					}
					
					print(quellband, " " + zahl2);
					hilfsbandZweiZähler++;
					
					reloadZahl2 = true;
				}
				return true;

			}
			// <--------- Band1 EOS

			// ---------> Band2 EOF
			if (isEndOfInputFile(hilfsbandZwei)) {
				while (!isEndOfInputFile(hilfsbandEins)) {
					
					if (reloadZahl1) {
						zahl1 = readInt(hilfsbandEins);
					}
					
					print(quellband, " " + zahl1);
					hilfsbandEinsZähler++;
					
					reloadZahl1 = true;
				}

				return false;
			}
			// <--------- Band2 EOF

			// ---------> Band2 EOS
			if (!(hilfsbandZweiZähler < lauflänge)) {
				while (hilfsbandEinsZähler < lauflänge) {

					if (reloadZahl1) {
						zahl1 = readInt(hilfsbandEins);
					}
					
					print(quellband, " " + zahl1);
					hilfsbandEinsZähler++;
					
					reloadZahl1 = true;
				}
				return true;
			}
			// <--------- Band2 EOS

			if (reloadZahl1) {
				zahl1 = readInt(hilfsbandEins);
				reloadZahl1 = false;
			}

			if (reloadZahl2) {
				zahl2 = readInt(hilfsbandZwei);
				reloadZahl2 = false;
			}

			if (zahl1 <= zahl2) {
				print(quellband, " " + zahl1);
				reloadZahl1 = true;
			} else {
				print(quellband, " " + zahl2);
				reloadZahl2 = true;
			}
		}
	}

	public static void split(int lauflänge, Object quellband, Object hilfsbandEins, Object hilfsbandZwei) {
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
	
	public static void printTape(Object ausgabeBand, char delimiter, int länge)
	{
		int i = 0;
		
		while (!isEndOfInputFile(ausgabeBand)) {
			if (i == länge)
			{
				print(delimiter);
				i = 0;
			}
			
			print(" " + readInt(ausgabeBand) + " ");
			
			i++;
		}
		
		println();
	}

}
