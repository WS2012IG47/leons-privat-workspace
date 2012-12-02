package uebung01;

import static gdi.MakeItSimple.*;

public class NimSpiel {
	public static void main(String[] args) {	
		println("gebe eine beliebige Menge von Streichhölzern ein");
		int anzahlStreichhölzer = readInt();
		readLine();
				
		while(anzahlStreichhölzer > 0)
		{
			int cpuRunde = cpuPlayer(anzahlStreichhölzer);
			anzahlStreichhölzer -= cpuRunde; 
			
			if(anzahlStreichhölzer <= 0) {
				println("Du hast gewonnen!");
				break;
			}
			
			println("Der rechner zieht " + cpuRunde +" Ho(ö)lz(er) es sind " + anzahlStreichhölzer + " Ho(ö)lz(er) übrig" );
			println("ziehe bitte 1 - 3 Streichhölzer");
			
			int genommeneHölzer = readInt();
			readLine();
			
			while (genommeneHölzer > 3 || 1 > genommeneHölzer ) {
				println("Hey nicht Schumeln! Neue eingabe?");	
				genommeneHölzer = readInt();
				readLine();
			}
			anzahlStreichhölzer -= genommeneHölzer;
			
			if(anzahlStreichhölzer <= 0) {
				println("Der Rechner hat gewonnen!");
				break;
			}
		}
	}
	
	public static int cpuPlayer(int anzahlHölzchen){
		if ((anzahlHölzchen - 1 - 1) % 4 == 0) {
			return 1;
		}
		
		if ((anzahlHölzchen - 2 - 1) % 4 == 0) {
			return 2;
		}
		
		if ((anzahlHölzchen - 3 - 1) % 4 == 0) {
			return 3;
		}
		
		return 1;
	}

}

