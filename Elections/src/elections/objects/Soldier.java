package elections.objects;

public class Soldier extends Citizen{
	boolean carryWeapon;
	
	public Soldier(String name, int id, int year, String wichBallot, boolean carryWeapon) {
		super(name,id,year,wichBallot);
		this.carryWeapon=carryWeapon;
	}
	
	public String toString() {
		String str="";
		if(carryWeapon==true)
			str=" the soldier is carry weapon";
		return super.toString()+str;
	}
}
