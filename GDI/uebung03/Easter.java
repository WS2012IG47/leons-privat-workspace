package uebung03;

import static gdi.MakeItSimple.*;

public class Easter {

	public static void main(String[] args) {
		int c, g, h, i, j, l, year, month, day;
		println("Bitte gib eine vierstellige Jahreszahl ein:");
		year = readInt();
		readLine();
		while (year < 1582 || year >= 9999) {
		      println("Bitte gib ein vierstelliges Jahr nach 1582 ein.");
		      year = readInt();
		      readLine();
		}
		// Formel zur Berechnung des Ostersonntags
		g = year % 19;
		c = year / 100;
		h = (c - (c / 4) - ((8 * c + 13) / 25) + 19 * g + 15) % 30;
		i = h - (h / 28) * (1 - (29 / h + 1) * ((21 - g) / 11));
		j = (year + (year / 4) + i + 2 - c + (c / 4)) % 7;
		l = i - j;
		month = 3 + ((l + 40) / 44);
		day = l + 28 - 31 * (month / 4);

		println("Der Ostersonntag im Jahr " + year + " fällt auf den " + day
		           + "." + month + "." + year);
	}

}