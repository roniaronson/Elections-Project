package elections.view;

import java.time.LocalDate;

import election.controller.ElectionsController;
import elections.objects.Ballot;
import elections.objects.Citizen;
import elections.objects.Elections;
import elections.objects.MyDate;
import elections.objects.PoliticalParty;
import elections.objects.SickCandidate;
import elections.objects.SickCitizen;
import elections.objects.SickSoldier;
import elections.objects.Soldier;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage arg0) throws Exception {
		Elections theModel= new Elections(10, 2020);
		view_menu view= new view_menu(arg0);

		Ballot b1 = new Ballot<Citizen>("Alenbi 4", "Citizens");
		Ballot b2 = new Ballot<Soldier>("Alenbi 8", "Soldiers");
		Ballot b3 = new Ballot<SickSoldier>("Alenbi 9", "Sick Soldiers");
		Ballot b4 = new Ballot<SickCitizen>("Alenbi 66", "Sick Citizens");
		theModel.addBallotCitizen(b1);
		theModel.addBallotSoldiers(b2);
		theModel.addBallotSickSoldiers(b3);
		theModel.addBallotSickCitizen(b4);

		LocalDate newDate1= LocalDate.of(2000, 4, 26);
		PoliticalParty p1 = new PoliticalParty("Rak Bibi", "Right", newDate1);
		LocalDate newDate2 = LocalDate.of(2000, 4, 26);
		PoliticalParty p2 = new PoliticalParty("King Yair", "Left", newDate2);
		LocalDate newDate3 = LocalDate.of(2000, 4, 26);
		PoliticalParty p3 = new PoliticalParty("Luis The Dog", "Central", newDate3);
		theModel.addPoliticalParty(p1);
		theModel.addPoliticalParty(p2);
		theModel.addPoliticalParty(p3);
		view.addPartyToGoVote(p1);
		view.addPartyToGoVote(p2);
		view.addPartyToGoVote(p3);
		

		Citizen c1 = new Citizen("roni", 315315848, 1996, "Alenbi 4");
		Soldier c2 = new Soldier("idan", 315317848, 2000, "Alenbi 8", true);
		SickSoldier c3 = new SickSoldier("bambi", 313315848, 2001, "Alenbi 9", false, false, 39);
		SickCandidate c4 = new SickCandidate("tor", 315355848, 1996, "Alenbi 66", 1, "Rak Bibi", true, 10);
		
		view.addCitizenToGoVote(c1);
		view.addCitizenToGoVote(c2);
		view.addCitizenToGoVote(c3);
		view.addCitizenToGoVote(c4);
		theModel.addNewSickCandidate(c4);
		theModel.addNewRegularCitizen(c1);
		theModel.addNewSoldiers(c2);
		theModel.addNewSickSoldiers(c3);
		
		System.out.println(theModel.showAllBallots());
		
		
		ElectionsController controller= new ElectionsController(theModel, view);
		//view_select view2= new view_select(new Stage());
		
	}

}
