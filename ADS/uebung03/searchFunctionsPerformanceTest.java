package uebung03;
import static gdi.MakeItSimple.*;

/*
 * Gruppe 4 - 7
 * Philip Dombrowski, Matrikelnummer: 1220235
 * Tobias Kunz, Matrikelnummer: 1232749
 * Timm Dobhan, Matrikelnummer: 1232995
 */

public class searchFunctionsPerformanceTest {
	
	public static void main(String[] args) {

		int ArraySize = 2048;

		println("Statistische Testreihe zur Ermittlung der durschnittlichen Anzahl vergleiche bei verschiedenen Suchalgorithmen.");
		println("Die Daten wurden aus 7500 automatisierten Testläufen ermittelt.");
		println();
		println("sequentielle suche (letzter Index) | sequentielle suche (erster Index) | binäre suche rekursiv implementiert | binäre suche iterativ implementiert");
		
		// Loop to fill 3 arrays with different sizes with numbers.
		for (int i2 = 0; i2 < 3; i2++) {
			int[] testArray = new int[ArraySize];
			for (int i = 0; i < testArray.length; i++) {
				testArray[i] = i;
			}

			int int1 = 0, int2 = 0, int3 = 0, int4 = 0, int5 = 0, int6 = 0, int7 = 0, int8 = 0;

			// Do 7500 testings with different random numbers.
			for (int i = 0; i < 7500; i++) {
				int testNumber = (int) Math.floor(Math.random() * testArray.length);

				int1 += searchLastIndex(testArray, testNumber);
				int2 += searchFirstIndex(testArray, testNumber);
				int3 += searchBinaryRecursive(testArray, testNumber, 0, testArray.length, 0);
				int4 += searchBinary(testArray, testNumber, 0, testArray.length);
				
				int5 += searchLastIndex(testArray, -1);
				int6 += searchFirstIndex(testArray, -1);
				int7 += searchBinaryRecursive(testArray, -1, 0, testArray.length, 0);
				int8 += searchBinary(testArray, -1, 0, testArray.length);
			}
			
			// Calculating the average sum from 7500 testings.
			int1 /= 7500;
			int2 /= 7500;
			int3 /= 7500;
			int4 /= 7500;
			
			int5 /= 7500;
			int6 /= 7500;
			int7 /= 7500;
			int8 /= 7500;
			
			println();
			println("Erfolgreiche Suche bei einer ArraySize von: " + ArraySize + " = " + int1 + " | " + int2 + " | " + int3 + " | " + int4);
			println("Erfolglose Suche bei einer ArraySize von: " + ArraySize + " = " + int5 + " | " + int6 + " | " + int7 + " | " + int8);

			ArraySize = ArraySize * 2;
		}		
	}
	
	// Search for the last index in the array and return it's position.
	// The complete array will be checked and the last saved position will be returned.	
	public static int searchLastIndex(int[] targetArray, int searchNumber) {
		int comparisonCounter = 0;

		for (int i = 0; i < targetArray.length; i++) {
			comparisonCounter++;
		}
		return comparisonCounter;
	}
	
	// Search for the first position of the number in the array.	
	// The method returns as soon as the first postition of the number were found.
	public static int searchFirstIndex(int[] targetArray, int searchNumber) {
		int comparisonCounter = 0;

		for (int i = 0; i < targetArray.length; i++) {
			comparisonCounter++;
			if (targetArray[i] == searchNumber) {
				return comparisonCounter;
			}
		}
		return comparisonCounter;
	}
	
	// Recursive binary search.
	// As long as the checked number is smaller or bigger than the searched one the method returns to the beginning and starts again with 
	// different values.
	public static int searchBinaryRecursive(int[] targetArray, int searchInt,
			int searchAreaStart, int searchAreaEnd, int comparisonCounter) {

		int i = ((searchAreaEnd - searchAreaStart) / 2) + searchAreaStart;
		
		if (searchInt >= targetArray.length){
			return comparisonCounter;
		}
		if (searchAreaStart > searchAreaEnd) {
			return comparisonCounter;
		}
		comparisonCounter++;
		if (targetArray[i] == searchInt) {
			return comparisonCounter;
		}
		if (targetArray[i] < searchInt) {
			return searchBinaryRecursive(targetArray, searchInt, i + 1,
					searchAreaEnd, comparisonCounter);
		}
		if (targetArray[i] > searchInt) {
			return searchBinaryRecursive(targetArray, searchInt, searchAreaStart,
					i - 1, comparisonCounter);
		}
		return comparisonCounter;
	}
	
	// Binary search.
	// The binary search without recursion is working as a loop, that changes the values if the checked number 
	// is bigger or smaller than the searched one.
	public static int searchBinary(int[] targetArray, int searchInt,
			int searchAreaStart, int searchAreaEnd) {

		int comparisonCounter = 0;

		while (searchAreaStart <= searchAreaEnd) {

			int i = ((searchAreaEnd - searchAreaStart) / 2) + searchAreaStart;

			if (searchInt >= targetArray.length){
				return comparisonCounter;
			}
			comparisonCounter++;
			if (targetArray[i] == searchInt) {
				return comparisonCounter;
			}
			if (targetArray[i] < searchInt) {
				searchAreaStart = i + 1;
			}
			if (targetArray[i] > searchInt) {
				searchAreaEnd = i - 1;
			}
		}
		return comparisonCounter;
	}
}