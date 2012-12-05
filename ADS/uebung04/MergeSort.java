package uebung04;

import static gdi.MakeItSimple.*;

/*
 * Gruppe 4 - 7
 * Philip Dombrowski, Matrikelnummer: 1220235
 * Tobias Kunz, Matrikelnummer: 1232749
 * Timm Dobhan, Matrikelnummer: 1232995
 */

public class MergeSort {

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

			for (int lauflänge = 1; lauflänge < 1001; lauflänge = lauflänge * 2) {
				IntReader quellbandReader = new IntReader(filename1);
				hilfsbandEins = openOutputFile(filename2);
				hilfsbandZwei = openOutputFile(filename3);

				split(lauflänge, quellbandReader, hilfsbandEins, hilfsbandZwei);

				quellbandReader.closeIntReader();
				closeOutputFile(hilfsbandEins);
				closeOutputFile(hilfsbandZwei);

				quellband = openOutputFile(filename1);
				IntReader hilfsbandEinsReader = new IntReader(filename2);
				IntReader hilfsbandZweiReader = new IntReader(filename3);

				merge(lauflänge, quellband, hilfsbandEinsReader, hilfsbandZweiReader);

				closeOutputFile(quellband);
				hilfsbandEinsReader.closeIntReader();
				hilfsbandZweiReader.closeIntReader();

				quellband = openInputFile(filename1);
				printTape(quellband, '*', lauflänge);
				closeInputFile(quellband);
			}
		}
	}

	public static void merge(int lauflänge, Object quellband, IntReader hilfsbandEins, IntReader hilfsbandZwei) {
		while (onePartOfMerge(lauflänge, quellband, hilfsbandEins, hilfsbandZwei));
	}

	public static boolean onePartOfMerge(int lauflänge, Object quellband, IntReader hilfsbandEins, IntReader hilfsbandZwei) {
		int hilfsbandEinsZähler = 0;
		int hilfsbandZweiZähler = 0;

		while (true) {

			// ---------> Band1 EOF
			if (!hilfsbandEins.isNumberAvailable()) {
				while (hilfsbandZwei.isNumberAvailable()) {
					print(quellband, "  " + hilfsbandZwei.readAndNextNumber());
				}
				return false;
			}
			// <--------- Band1 EOF

			// ---------> Band1 EOS
			if (!(hilfsbandEinsZähler < lauflänge)) {
				while (hilfsbandZweiZähler < lauflänge) {
					print(quellband, "  " + hilfsbandZwei.readAndNextNumber());
					hilfsbandZweiZähler++;
				}
				return true;

			}
			// <--------- Band1 EOS

			// ---------> Band2 EOF
			if (!hilfsbandZwei.isNumberAvailable()) {
				while (hilfsbandEins.isNumberAvailable()) {
					print(quellband, "  " + hilfsbandEins.readAndNextNumber());
				}

				return false;
			}
			// <--------- Band2 EOF

			// ---------> Band2 EOS
			if (!(hilfsbandZweiZähler < lauflänge)) {
				while (hilfsbandEinsZähler < lauflänge) {
					print(quellband, "  " + hilfsbandEins.readAndNextNumber());
					hilfsbandEinsZähler++;
				}
				return true;
			}
			// <--------- Band2 EOS

			if (hilfsbandEins.readNumber() <= hilfsbandZwei.readNumber()) {
				print(quellband, "  " + hilfsbandEins.readAndNextNumber());
				hilfsbandEinsZähler++;
			} 
			else 
			{
				print(quellband, "  " + hilfsbandZwei.readAndNextNumber());
				hilfsbandZweiZähler++;
			}
		}
	}

	public static void split(int lauflänge, IntReader quellband, Object hilfsbandEins, Object hilfsbandZwei) {
		boolean bandauswahl = true;

		while (quellband.isNumberAvailable()) {
			if (bandauswahl) {
				for (int i = 0; i < lauflänge; i++) {
					if (quellband.isNumberAvailable()) {
						print(hilfsbandEins, "  " + quellband.readAndNextNumber());
					} else {
						return;
					}
				}

				bandauswahl = false;
			} else {
				for (int i = 0; i < lauflänge; i++) {
					if (quellband.isNumberAvailable()) {
						print(hilfsbandZwei, "  " + quellband.readAndNextNumber());
					} else {
						return;
					}
				}

				bandauswahl = true;
			}
		}

	}

	public static void printTape(Object ausgabeBand, char delimiter, int länge) {
		int i = 0;

		while (!isEndOfInputFile(ausgabeBand)) {
			if (i == länge) {
				print(delimiter);
				i = 0;
			}

			print(" " + readInt(ausgabeBand) + " ");
			i++;
		}

		println();
	}

}
