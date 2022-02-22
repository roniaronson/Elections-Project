package elections.objects;

public interface Sickable {
	
	boolean setProtectiveSuit(boolean protectiveSuit);
	boolean getProtectiveSuit();
	
	boolean setHowManyDays(int howManyDays);
	int getHowManyDays();
	
	String toString();
}

