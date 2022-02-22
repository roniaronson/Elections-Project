package elections.objects;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

public class PoliticalParty {
	private enum Wing {
		Right, Left, Central
	};

	private int id;
	private String name;
	private Wing wing;
	//private MyDate date;
	private LocalDate date1;
	private Vector candidates = new Vector();
	private int candidateCounter = 0;
	private static int counter = 0;
	private int totalVotes = 0;

	/*
	 * public PoliticalParty(String name, String wing, MyDate date) { this.name =
	 * name; this.wing = Wing.valueOf(wing); ; this.date = date; id = counter++; }
	 */
	public PoliticalParty(String name, String wing, LocalDate date1) {
		this.name = name;
		this.wing = Wing.valueOf(wing);
		;
		this.date1 = date1;
		id = counter++;
	}

	public void setTotalVotes() {
		totalVotes++;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return this.name;
	}

	public void addCandidate(Citizen newCandidate) {
		candidates.add(newCandidate);
		candidateCounter++;
	}

	public int getTotalVotes() {
		return totalVotes;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof PoliticalParty))
			return false;

		PoliticalParty temp = (PoliticalParty) other;
		return (temp.id == id);
	}

	@Override
	public String toString() {

		return "Political party " + name + ", id:" + id + ", is " + wing + " wing, created at " + date1.toString()
				+ ", the party has " + candidateCounter + " candidates. \n";
	}

}
