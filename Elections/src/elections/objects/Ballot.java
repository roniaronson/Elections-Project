package elections.objects;

import java.io.File;
import java.util.Scanner;
import java.util.Vector;

public class Ballot<T extends Citizen> {

	private int id;
	private String address;
	private Vector<T> citizens;
	private int citizensCounter = 0;
	private Vector<Integer> electionResults = new Vector();
	private static int counter = 1;
	private double percentages = 0;
	private String type;

	public Ballot(String address, String type) {
		citizens = new Vector<T>();
		this.address = address;
		id = counter++;
		this.type = type;
	}

	public void addCitizensToBallot(T newCitizen) {
		citizens.add(newCitizen);
		citizensCounter++;
	}

	public boolean setPercentage() {
		double counter = 0;
		for (int i = 0; i < citizens.size(); i++) {
			if (citizens.elementAt(i).getIsVoting())
				counter++;
		}
		this.percentages = (counter / this.citizensCounter) * 100;
		return true;
	}

	public double getPercentage() {
		return this.percentages;
	}

	public String showResultByPoliticalPartyName(String name) {
		int counter = 0;
		for (int i = 0; i < citizens.size(); i++) {
			if (citizens.elementAt(i).getIsVoting()) {
				if (citizens.elementAt(i).getVote().equalsIgnoreCase(name)) {
					counter++;
					Elections.findPoliticalParty(name).setTotalVotes();
				}
			}
		}
		return ("in ballot number " + this.id + ", political party: " + name + ", have: " + counter + " votes \n");
	}

	public String getAddress() {
		return this.address;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Ballot))
			return false;

		Ballot temp = (Ballot) other;
		return (temp.id == id);
	}

	@Override
	public String toString() {
		return "Ballot in " + address + ", is typed for " + type + ", it has " + this.citizensCounter
				+ " voters";
	}

}
