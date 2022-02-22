package election.controller;

import java.time.LocalDate;

import election.listeners.ElectionEventsListener;
import election.listeners.ElectionsUIListener;
import elections.objects.Ballot;
import elections.objects.Candidate;
import elections.objects.Citizen;
import elections.objects.Elections;
import elections.objects.PoliticalParty;
import elections.objects.SickCandidate;
import elections.objects.SickCitizen;
import elections.objects.SickSoldier;
import elections.objects.Soldier;
import elections.view.view_menu;

public class ElectionsController implements ElectionEventsListener, ElectionsUIListener {
	
	private Elections electionsModel;
	private view_menu electionsView;
	private Citizen newCitizen = null;
	
	public ElectionsController(Elections electionsModel, view_menu electionsView) {
        this.electionsModel = electionsModel;
        this.electionsView  = electionsView;
        
        electionsModel.registerListenerModel(this);
        electionsView.registerListenerUI(this);
    }
	
	
	public void addCitizenBallotToModel(String address , String type) {
		Ballot<Citizen> newBallot = new Ballot<Citizen>(address, type);
		electionsModel.addBallotCitizen(newBallot);
		electionsView.addBallotCitizenToCmb(newBallot);
	}
	public void addSickCitizenBallotToModel(String address , String type) {
		Ballot<SickCitizen> newBallot = new Ballot<SickCitizen>(address, type);
		electionsModel.addBallotSickCitizen(newBallot);	
		electionsView.addBallotSickCitizenToCmb(newBallot);
	}
	public void addSoldiersBallotToModel(String address , String type) {
		Ballot<Soldier> newBallot = new Ballot<Soldier>(address, type);
		electionsModel.addBallotSoldiers(newBallot);	
		electionsView.addBallotSoldiersToCmb(newBallot);
	}
	public void addSickSoldiersBallotToModel(String address , String type) {
		Ballot<SickSoldier> newBallot = new Ballot<SickSoldier>(address, type);
		electionsModel.addBallotSickSoldiers(newBallot);	
		electionsView.addBallotSickSoldiersToCmb(newBallot);

	}
	
	public void addRegularCitizenToModel(String name, String id, String year,String address) {
		newCitizen = new Citizen(name, Integer.parseInt(id), Integer.parseInt(year), address);
		electionsModel.addNewRegularCitizen(newCitizen);
		electionsView.addCitizenToGoVote(newCitizen);
	}

	public void addSickCitizenToModel(String name, String id, String year,String address, boolean protectiveSuit, String howManyDays) {
		newCitizen = new SickCitizen(name, Integer.parseInt(id), Integer.parseInt(year), address, protectiveSuit, Integer.parseInt(howManyDays));
		electionsModel.addNewSickCitizen(newCitizen);
		electionsView.addCitizenToGoVote(newCitizen);
	}

	public void addCandidateToModel(String name, String id, String year,String address, String placement, String politicalPartyName) {
		newCitizen = new Candidate(name, Integer.parseInt(id), Integer.parseInt(year), address, Integer.parseInt(placement), politicalPartyName);
		electionsModel.addNewCandidate((Citizen)newCitizen);
		electionsView.addCitizenToGoVote(newCitizen);
	}
	
	public void addSickCandidateToModel(String name, String id, String year,String address, String placement, String politicalPartyName,boolean protectiveSuit, String howManyDays) {
		newCitizen = new SickCandidate(name, Integer.parseInt(id), Integer.parseInt(year), address, Integer.parseInt(placement), politicalPartyName,protectiveSuit, Integer.parseInt(howManyDays));
		electionsModel.addNewSickCandidate((Citizen)newCitizen);
		electionsView.addCitizenToGoVote(newCitizen);
	}

	public void addSoldierToModel(String name, String id, String year,String address, boolean carryWeapon) {
		newCitizen = new Soldier(name, Integer.parseInt(id), Integer.parseInt(year), address, carryWeapon);	
		electionsModel.addNewSoldiers(newCitizen);
		electionsView.addCitizenToGoVote(newCitizen);
	}
	
	public void addSickSoldierToModel(String name, String id, String year,String address, boolean carryWeapon,boolean protectiveSuit, String howManyDays) {
		newCitizen = new SickSoldier(name, Integer.parseInt(id), Integer.parseInt(year), address, carryWeapon,protectiveSuit,Integer.parseInt(howManyDays));	
		electionsModel.addNewSickSoldiers(newCitizen);
		electionsView.addCitizenToGoVote(newCitizen);
	}

	public void addPoliticalPartyToModel(String name, String wing, LocalDate date) {
		PoliticalParty newPoliticalParty= new PoliticalParty(name, wing, date);
		electionsModel.addPoliticalParty(newPoliticalParty);
		electionsView.addPartyToGoVote(newPoliticalParty);
	}
	
	public String viewAsksForBallotsDetails() {
		return electionsModel.showAllBallots();
	}
	public String viewAsksForCitizensDetails() {
		return electionsModel.showAllCitizens();
	}
	public String viewAsksForPoliticalPartyDetails() {
		return electionsModel.showAllPoliticalParties();
	}
	public Citizen viewAsksForCitizenById(int id) {
		return electionsModel.findCitizenById(id);
	}
	public String viewAsksToSetVoteToCitizen(Citizen voter, String vote) {
		return (electionsModel.goVote(voter, vote));
	}
	public String viewAsksForResults() {
		return electionsModel.showElectionResult();
	}
	
	
	
	
}
