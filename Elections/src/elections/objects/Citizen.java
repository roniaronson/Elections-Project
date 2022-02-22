package elections.objects;

import java.util.Scanner;

public class Citizen {
	
	protected String name;
	protected int id;
	protected int year;
	public String whichBallot;
	protected boolean isVoting=false;
	protected String vote;
	
	public Citizen(String name, int id, int year, String whichBallot) {
		this.name=name;
		this.id=id;
		this.year=year;
		this.whichBallot=whichBallot;
	}

	public Citizen (Scanner scan) {
		this.name = scan.next();
		this.id = scan.nextInt();
		this.year = scan.nextInt();
		this.whichBallot = scan.next();
	}

	public String getWhichBallot() {
			return this.whichBallot;
	}
		
	public void setIsVoting(boolean answer) {
		this.isVoting=answer;
	}
	
	public void setVote(String vote) {
		this.vote=vote;
	}
	
	public boolean getIsVoting() {
		return this.isVoting;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getBallot() {
		return this.whichBallot;
	}
	
	public String getVote() {
		return this.vote;
	}
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object other) {
		if(!(other instanceof Citizen))
			return false;
		
		Citizen temp = (Citizen)other;
		return (temp.id == id);
	}
	
	@Override
	public String toString() {
		String str="";
		if(isVoting==true)
			str=", and votes for "+vote;
		return "Citizen " + name + ", id:" + id + ", " + (2020-year) + " years old, votes in " + whichBallot+ str;
	}


}
