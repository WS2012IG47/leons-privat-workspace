package uebung04;
import static gdi.MakeItSimple.*;
public class IntReader {

	private Object Quelldatei;
	private boolean numberAvailable;
	private int number;
	
	public IntReader(String QuelldateiPfad)
	{
		Quelldatei = openInputFile(QuelldateiPfad);
		
		if(isEndOfInputFile(Quelldatei))
		{
			numberAvailable = false;
		}
		else {
			number = readInt(Quelldatei);
			numberAvailable = true;
		}
	}
	
	public int readAndNextNumber()
	{
		if(numberAvailable)
		{
			int tempNumber;
			
			if(isEndOfInputFile(Quelldatei))
			{
				tempNumber = number;
				numberAvailable = false;
			}
			else
			{
				tempNumber = number;
				number = readInt(Quelldatei);
			}
			
			return tempNumber;
		}
		
		// Hier würde ein exeption geworfen werden wen wir das schon gelernt hätten.
		// Um das zu vermeiden kann mit isNumberAvailable geprüft werden ob eine zahl verfügbar ist.
		return Integer.MAX_VALUE;
	}
	
	public int readNumber()
	{
		return number;
	}
	
	public void nextNumber()
	{
		if(!isEndOfInputFile(Quelldatei))
		{
			number = readInt(Quelldatei);
		}
		else
		{
			// wir werden alle sterben!!!
		}
	}
	
	public boolean isNumberAvailable()
	{
		return numberAvailable;
	}
	
	public void closeIntReader()
	{
		closeInputFile(Quelldatei);
	}
}
