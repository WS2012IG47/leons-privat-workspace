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
		String filename1 = "C:\\Users\\Leon\\Documents\\workspace\\leons-privat-workspace\\ADS\\uebung04\\numbers.txt";
		String filename2 = "C:\\Users\\Leon\\Documents\\workspace\\leons-privat-workspace\\ADS\\uebung04\\hilfsbandEins.txt";
		String filename3 = "C:\\Users\\Leon\\Documents\\workspace\\leons-privat-workspace\\ADS\\uebung04\\hilfsbandZwei.txt";

		if (isFilePresent(filename1)) {
			Object quellband;
			Object hilfsbandEins;
			Object hilfsbandZwei;

			for (int lauflänge = 1; lauflänge < 25; lauflänge++)
			{
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

	public static void merge(int lauflänge, Object quellband, Object hilfsbandEins, Object hilfsbandZwei) {
		zahl1 = readInt(hilfsbandEins);
		zahl2 = readInt(hilfsbandZwei);
	
		while(onePartOfMerge(lauflänge, quellband, hilfsbandEins, hilfsbandZwei));
	}
	
	public static boolean onePartOfMerge(int lauflänge, Object quellband, Object hilfsbandEins, Object hilfsbandZwei)
	{		
		int hilfsbandEinsZähler = 0;
		int hilfsbandZweiZähler = 0;
		
		while (true) {

			// ---------> Band1		
			if (hilfsbandEinsZähler < lauflänge) {
				if (zahl1 <= zahl2) {
					print(quellband, " " + zahl1);
					
					if(isEndOfInputFile(hilfsbandEins))
					{
						while (!isEndOfInputFile(hilfsbandZwei)) {
							print(quellband, " " + zahl2);
							zahl2 = readInt(hilfsbandZwei);
							hilfsbandZweiZähler++;
						}
						
						return false;
					}
					
					zahl1 = readInt(hilfsbandEins);
					hilfsbandEinsZähler++;
				}
			} else {
				while (hilfsbandZweiZähler < lauflänge && !isEndOfInputFile(hilfsbandZwei)) {
					print(quellband, " " + zahl2);
					zahl2 = readInt(hilfsbandZwei);
					hilfsbandZweiZähler++;
				}

				return true;
			}

			// <--------- Band1
						
			// ---------> Band2

			if (hilfsbandZweiZähler < lauflänge) {
				if (zahl1 > zahl2) {
					print(quellband, " " + zahl2);
					
					if(isEndOfInputFile(hilfsbandZwei))
					{
						while (!isEndOfInputFile(hilfsbandEins)) {
							print(quellband, " " + zahl1);
							zahl1 = readInt(hilfsbandEins);
							hilfsbandEinsZähler++;
						}
						
						return false;
					}
					
					zahl2 = readInt(hilfsbandZwei);
					hilfsbandZweiZähler++;
				}
			} else {
				while (hilfsbandEinsZähler < lauflänge&& !isEndOfInputFile(hilfsbandEins)) {
					print(quellband, " " + zahl1);
					zahl1 = readInt(hilfsbandEins);
					hilfsbandEinsZähler++;
				}
				
				return true;
			}

			// <--------- Band2
		}
	}
	
	public static void split(int lauflänge, Object quellband,
			Object hilfsbandEins, Object hilfsbandZwei) {
		int zahl = readInt(quellband);
		boolean bandauswahl = true;

		while (!isEndOfInputFile(quellband)) {
			if (bandauswahl) {
				for (int i = 0; i < lauflänge; i++) {
					print(hilfsbandEins, " " + zahl);
					
					if (isEndOfInputFile(quellband))
					{
						return;
					}
					
					zahl = readInt(quellband);
				}

				bandauswahl = false;
			} else {
				for (int i = 0; i < lauflänge; i++) {
					print(hilfsbandZwei, " " + zahl);
					
					if (isEndOfInputFile(quellband))
					{
						return;
					}
					
					zahl = readInt(quellband);
				}

				bandauswahl = true;
			}
		}
	}

}
