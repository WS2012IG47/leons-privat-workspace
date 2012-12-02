package uebung03;

import static gdi.MakeItSimple.*;

/*
 * Gruppe 4 - 7
 * Philip Dombrowski, Matrikelnummer: 1220235
 * Tobias Kunz, Matrikelnummer: 1232749
 * Timm Dobhan, Matrikelnummer: 1232995
 */

public class ShakeSort {
	static int equationCounter;
	static int permutationCounter;

	public static void main(String[] args) {
		int[] testArray = new int[24];

		// Array gets filled with random numbers (0 to testArray.length)
		println("Array mit zufälligen Zahlen: ");
		print(" ");
		for (int i = 0; i < testArray.length; i++) {
			testArray[i] = (int) Math.floor(Math.random() * (testArray.length));
			print(testArray[i] + "  ");
		}
		println("");
		// Call-up function, sort numbers in array
		shakeSort(testArray);
		println("");

		// print sorted numbers
		println("");
		print("Das Array, jetzt mit den sortierten Zahlen: ");
		
		for (int i = 0; i < testArray.length; i++)
			print(testArray[i] + " ");
		println();
		// print number of equations
		println("Anzahl der Vergleiche: " + equationCounter);
		// print number of permutations
		println("Anzahl der Vertauschungen: " + permutationCounter);
	}

	// Function to sort numbers via ShakeSort algorithm
	public static void shakeSort(int[] targetArray) {
		boolean unsorted = true;
		int temp;
		
		while (unsorted) {
			unsorted = false;
			
			// This loop counts from left to right. Make a comparison between
			// neighboring numbers and interchange them if necessary.
			for (int i = 0; i < targetArray.length - 1; i++)
				if (targetArray[i] > targetArray[i + 1]) {
					equationCounter++;
					temp = targetArray[i];
					targetArray[i] = targetArray[i + 1];
					permutationCounter++;
					targetArray[i + 1] = temp;
					unsorted = true;

					// print numbers if LOOP finished ONCE.
					println(makeMarkedArray(targetArray, i, i + 1));
				}

			// This loop counts from right to left. Make a comparison between
			// neighboring numbers and interchange them if necessary.
			for (int i = targetArray.length - 1; i > 0; i--)
				if (targetArray[i] < targetArray[i - 1]) {
					equationCounter++;
					temp = targetArray[i];
					targetArray[i] = targetArray[i - 1];
					permutationCounter++;
					targetArray[i - 1] = temp;
					unsorted = true;

					// print numbers if LOOP finished ONCE.
					println(makeMarkedArray(targetArray, i - 1, i));
			}
		}
	}

	// Function to highlight swapped numbers.
	static String makeMarkedArray(int[] a, int k, int j) {
		String out = "";
		int i = 0;
		do {
			if (i == k) {
				out += "{" + a[i] + "-";
			}
			else if(i == j){
				out += "-" + a[i] + "}";
			}
			else {
				out += " " +  a[i] + " ";
			}
		} while (++i < a.length);
		return out;
	}
}
