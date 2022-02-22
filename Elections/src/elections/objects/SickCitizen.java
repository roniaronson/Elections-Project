package elections.objects;

public class SickCitizen extends Citizen implements Sickable  {

	private boolean protectiveSuit;
	private int howManyDays;
	
	public SickCitizen(String name, int id, int year, String wichBallot, boolean protectiveSuit, int howManyDays) {
		super(name,id,year,wichBallot);
		this.protectiveSuit=protectiveSuit;
		this.howManyDays=howManyDays;
	}
	
	@Override
	public boolean setHowManyDays(int howManyDays) {
		this.howManyDays=howManyDays;
		return true;
	}
	
	@Override
	public int getHowManyDays() {
		return this.howManyDays;
	}
	
	@Override
	public boolean setProtectiveSuit(boolean protectiveSuit) {;
		this.protectiveSuit=protectiveSuit;
		return true;
	}
	
	@Override
	public boolean getProtectiveSuit() {
		return this.protectiveSuit;
	}
	
	@Override
	public String toString() {
		String str;
		if(this.protectiveSuit==true)
			str=" and wearing protective suit.";
		else
			str=" and dont wearing protective suit.";
		return super.toString()+" ,"+this.getName()+" sick:"+this.getHowManyDays()+" days"+str;
	}
}
