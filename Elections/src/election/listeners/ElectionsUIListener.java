package election.listeners;

import java.time.LocalDate;

import elections.objects.Citizen;

public interface ElectionsUIListener {
	 void addCitizenBallotToModel(String address , String type);
	 void addSickCitizenBallotToModel(String address , String type);
	 void addSoldiersBallotToModel(String address , String type);
	 void addSickSoldiersBallotToModel(String address , String type);
	 void addRegularCitizenToModel(String name, String id, String year,String address);
	 void addSickCitizenToModel(String name, String id, String year,String address, boolean protectiveSuit, String howManyDays);
	 void addCandidateToModel(String name, String id, String year,String address, String placement, String politicalPartyName);
	 void addSickCandidateToModel(String name, String id, String year,String address, String placement, String politicalPartyName,boolean protectiveSuit, String howManyDays);
	 void addSoldierToModel(String name, String id, String year,String address, boolean carryWeapon);
	 void addSickSoldierToModel(String name, String id, String year,String address, boolean carryWeapon,boolean protectiveSuit, String howManyDays);
	 void addPoliticalPartyToModel(String name, String wing, LocalDate date);
	String viewAsksForBallotsDetails();
	String viewAsksForCitizensDetails();
	String viewAsksForPoliticalPartyDetails();
	Citizen viewAsksForCitizenById(int id);
	String viewAsksToSetVoteToCitizen(Citizen voter, String vote);
	String viewAsksForResults();
	
}
