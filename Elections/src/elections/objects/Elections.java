package elections.objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import election.listeners.ElectionEventsListener;


public class Elections {

	private int month;
	private int year;

	private static Set<Citizen> electors = new Set();
	private static Vector<PoliticalParty> partiesList = new Vector();
	private Vector<Ballot> ballotsCitizens = new Vector();
	private Vector<Ballot> ballotsSickCitizens = new Vector();
	private Vector<Ballot> ballotsSoldiers = new Vector();
	private Vector<Ballot> ballotsSickSoldiers = new Vector();
	private Vector<Citizen> isVoted = new Vector();
	private Vector<ElectionEventsListener> listenerModel= new Vector<ElectionEventsListener>();

	public Elections(int month, int year) {
		this.month = month;
		this.year = year;
	}
	
	public void registerListenerModel(ElectionEventsListener listener) {
		listenerModel.add(listener);
	}

	public void addBallotCitizen(Ballot newBallot) {
		ballotsCitizens.add(newBallot);
	}

	public void addBallotSickCitizen(Ballot newBallot) {
		ballotsSickCitizens.add(newBallot);
	}

	public void addBallotSickSoldiers(Ballot newBallot) {
		ballotsSickSoldiers.add(newBallot);
	}

	public void addBallotSoldiers(Ballot newBallot) {
		ballotsSoldiers.add(newBallot);
	}

	public boolean addNewRegularCitizen(Citizen newCitizen) {
		findBallotCitizens(newCitizen.whichBallot).addCitizensToBallot(newCitizen);
		return electors.add(newCitizen);
	}
	public boolean addNewSickCitizen(Citizen newCitizen) {
		findBallotSickCitizens(newCitizen.whichBallot).addCitizensToBallot(newCitizen);
		return electors.add(newCitizen);
	}
	public boolean addNewSickSoldiers(Citizen newCitizen) {
		findBallotSickSoldiers(newCitizen.whichBallot).addCitizensToBallot(newCitizen);
		return electors.add(newCitizen);
	}
	public boolean addNewSoldiers(Citizen newCitizen) {
		findBallotSoldiers(newCitizen.whichBallot).addCitizensToBallot(newCitizen);
		return electors.add(newCitizen);
	}
	public void addNewCandidate(Citizen newCitizen) {
		addNewRegularCitizen((Citizen)newCitizen);
		addToPoliticalParty((((Candidate) newCitizen)), ((Candidate) newCitizen).getPoliticalPartyName() );
	}
	public void addNewSickCandidate(Citizen newCitizen) {
		addNewSickCitizen((Citizen)newCitizen);
		addToPoliticalParty(((Candidate) newCitizen), ((Candidate) newCitizen).getPoliticalPartyName() );
	}

	public Ballot findBallotCitizens(String address) {
		for (int i = 0; i < ballotsCitizens.size(); i++) {
			if (ballotsCitizens.elementAt(i).getAddress().equalsIgnoreCase(address))
				return ballotsCitizens.elementAt(i);
		}
		//ui.showMessage("ballot is not exist haim sheli");
		return null;
	}

	public Ballot findBallotSickCitizens(String address) {
		for (int i = 0; i < ballotsSickCitizens.size(); i++) {
			if (ballotsSickCitizens.elementAt(i).getAddress().equalsIgnoreCase(address))
				return ballotsSickCitizens.elementAt(i);
		}
		//ui.showMessage("ballot is not exist haim sheli");
		return null;
	}

	public Ballot findBallotSoldiers(String address) {
		for (int i = 0; i < ballotsSoldiers.size(); i++) {
			if (ballotsSoldiers.elementAt(i).getAddress().equalsIgnoreCase(address))
				return ballotsSoldiers.elementAt(i);
		}
		//ui.showMessage("ballot is not exist haim sheli");
		return null;
	}

	public Ballot findBallotSickSoldiers(String address) {
		for (int i = 0; i < ballotsSickSoldiers.size(); i++) {
			if (ballotsSickSoldiers.elementAt(i).getAddress().equalsIgnoreCase(address))
				return ballotsSickSoldiers.elementAt(i);
		}
		//ui.showMessage("ballot is not exist haim sheli");
		return null;
	}

	public static Citizen findCitizenById(int id) {
		return electors.findById(id);
	}

	public void addPoliticalParty(PoliticalParty newPoliticalParty) {
		partiesList.add(newPoliticalParty);
	}

	public void addToPoliticalParty(Candidate newCandidate, String politicalPartyName) {
		findPoliticalParty(politicalPartyName).addCandidate(newCandidate);
	}

	public String showAllBallots() {
		StringBuffer str = new StringBuffer("");
		for (int i = 0; i < ballotsCitizens.size(); i++) {
			str.append(ballotsCitizens.elementAt(i).toString() + "\n");
		}
		for (int i = 0; i < ballotsSickCitizens.size(); i++) {
			str.append(ballotsSickCitizens.elementAt(i).toString() + "\n");
		}
		for (int i = 0; i < ballotsSickSoldiers.size(); i++) {
			str.append(ballotsSickSoldiers.elementAt(i).toString() + "\n");
		}
		for (int i = 0; i < ballotsSoldiers.size(); i++) {
			str.append(ballotsSoldiers.elementAt(i).toString() + "\n");
		}
		return (str.toString());
	}

	public String showAllCitizens() {
		return(electors.toString());
	}

	public String showAllPoliticalParties() {
		StringBuffer str= new StringBuffer(" ");
		for (int i = 0; i < partiesList.size(); i++) {
			str.append(partiesList.elementAt(i).toString());
		}
		return(str.toString());
	}

	public String goVote(Citizen voter, String vote) {
			if (voter instanceof Sickable
				&& ((Sickable) (voter)).getProtectiveSuit() == false) {
				voter.setIsVoting(false);
				return("sorry " + voter.getName() + ", you cant vote without protective suit.");	
			}
			else {
				voter.setIsVoting(true);
				voter.setVote(vote);
				return("you have been voted successfully");
			}
		}
	

	public String showElectionResult() {
		StringBuffer str = new StringBuffer("");
		for (int i = 0; i < partiesList.size(); i++) {
			for (int j = 0; j < ballotsCitizens.size(); j++) {
				str.append(ballotsCitizens.elementAt(j)
						.showResultByPoliticalPartyName(partiesList.elementAt(i).getName()));
			}
			for (int j = 0; j < ballotsSickCitizens.size(); j++) {
				str.append(ballotsSickCitizens.elementAt(j)
						.showResultByPoliticalPartyName(partiesList.elementAt(i).getName()));
			}
			for (int j = 0; j < ballotsSoldiers.size(); j++) {
				str.append(ballotsSoldiers.elementAt(j)
						.showResultByPoliticalPartyName(partiesList.elementAt(i).getName()));
			}
			for (int j = 0; j < ballotsSickSoldiers.size(); j++) {
				str.append(ballotsSickSoldiers.elementAt(j)
						.showResultByPoliticalPartyName(partiesList.elementAt(i).getName()));
			}
		}
		for (int i = 0; i < partiesList.size(); i++) {
			str.append("the political party: " + partiesList.elementAt(i).getName() + " have "
					+ partiesList.elementAt(i).getTotalVotes() + " votes \n");
		}
		for (int j = 0; j < ballotsCitizens.size(); j++) {
			ballotsCitizens.elementAt(j).setPercentage();
			str.append("ballot in address " + ballotsCitizens.elementAt(j).getAddress() + " have "
					+ ballotsCitizens.elementAt(j).getPercentage() + " precentage of voters \n");
		}
		for (int j = 0; j < ballotsSickCitizens.size(); j++) {
			ballotsSickCitizens.elementAt(j).setPercentage();
			str.append("ballot in address " + ballotsSickCitizens.elementAt(j).getAddress() + " have "
					+ ballotsSickCitizens.elementAt(j).getPercentage() + " precentage of voters \n");
		}
		for (int j = 0; j < ballotsSoldiers.size(); j++) {
			ballotsSoldiers.elementAt(j).setPercentage();
			str.append("ballot in address " + ballotsSoldiers.elementAt(j).getAddress() + " have "
					+ ballotsSoldiers.elementAt(j).getPercentage() + " precentage of voters \n");
		}
		for (int j = 0; j < ballotsSickSoldiers.size(); j++) {
			ballotsSickSoldiers.elementAt(j).setPercentage();
			str.append("ballot in address " + ballotsSickSoldiers.elementAt(j).getAddress() + " have "
					+ ballotsSickSoldiers.elementAt(j).getPercentage() + " precentage of voters \n");
		}
		return(str.toString());
	}

	public static PoliticalParty findPoliticalParty(String name) {
		for (int i = 0; i < partiesList.size(); i++) {
			if (partiesList.elementAt(i).getName().equalsIgnoreCase(name))
				return partiesList.elementAt(i);
		}
		//ui.showMessage("political party is not exist haim sheli");
		return null;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Elections))
			return false;

		Elections temp = (Elections) other;
		return (temp.month == month) && (temp.year == year);
	}

	@Override
	public String toString() {
		return "Elections at " + month + "//" + year;
	}

}
