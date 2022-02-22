package elections.view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class view_select {

		private GridPane gpSelect;
		
		public view_select(Stage theStage) {
			gpSelect = new GridPane();
			gpSelect.setPadding(new Insets(10));
			gpSelect.setHgap(10);
			gpSelect.setVgap(10);
			
			
			
			theStage.setScene(new Scene(gpSelect, 350, 350));
			theStage.show();
			
}
		public void AddBallotIsSelected() {
			ComboBox<String> cbAddBallot=new ComboBox<String>();
			cbAddBallot.getItems().addAll("Citizen","SickCitizen","Soldiers","SickSoldier");
			Label lblBallotType= new Label("Select ballot's type:");
			Label lblBallotAdress= new Label("Enter ballot's address:");
			TextField tfBallotAdress= new TextField();
			Button btnAddBallot= new Button("Add new ballot box");
			
			gpSelect.add(lblBallotType, 0, 0);
			gpSelect.add(cbAddBallot, 0, 1);
			gpSelect.add(tfBallotAdress,1,2);
			gpSelect.add(lblBallotAdress, 0, 2);
			gpSelect.add(btnAddBallot, 0, 3);
		}
		
		public void AddCitizenIsSelected() {
			
			GridPane gpAddCitizen = new GridPane();
			gpAddCitizen.setPadding(new Insets(10));
			gpAddCitizen.setHgap(10);
			gpAddCitizen.setVgap(10);
			
			ComboBox<String> cmbAddCitizen=new ComboBox<String>();
			cmbAddCitizen.getItems().addAll("Regular Citizen","Candidate","Soldier");
			
			Button btnRegularCitizen= new Button("Add Regular Citizen !");
			btnRegularCitizen.setVisible(false);
			
			Label lblCandidatePoliticalParty= new Label("Enter political party name:");
			TextField tfCandidatePoliticalParty= new TextField();
			Label lblCandidatePlacement= new Label("Enter your placement:");
			TextField tfCandidatePlacement= new TextField();
			Button btnCandidate= new Button("Add Candidate !");
			lblCandidatePlacement.setVisible(false);
			tfCandidatePlacement.setVisible(false);
			lblCandidatePoliticalParty.setVisible(false);
			tfCandidatePoliticalParty.setVisible(false);
			btnCandidate.setVisible(false);
			
			Label lblSoldierCarryWeapon= new Label("If you carry weapon enter the button:");
			CheckBox chkSoldierCarryWeapon= new CheckBox("I'm carrying a weapon");
			Button btnSoldier= new Button("Add Soldier !");
			lblSoldierCarryWeapon.setVisible(false);
			chkSoldierCarryWeapon.setVisible(false);
			btnSoldier.setVisible(false);
			
			cmbAddCitizen.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if(cmbAddCitizen.getValue()=="Regular Citizen") {
						btnRegularCitizen.setVisible(true);
						lblCandidatePoliticalParty.setVisible(false);
						tfCandidatePoliticalParty.setVisible(false);
						lblCandidatePlacement.setVisible(false);
						tfCandidatePlacement.setVisible(false);
						lblSoldierCarryWeapon.setVisible(false);
						chkSoldierCarryWeapon.setVisible(false);
						btnSoldier.setVisible(false);
					}
					if(cmbAddCitizen.getValue()=="Candidate") {
						lblCandidatePoliticalParty.setVisible(true);
						tfCandidatePoliticalParty.setVisible(true);
						lblCandidatePlacement.setVisible(true);
						tfCandidatePlacement.setVisible(true);
						btnCandidate.setVisible(true);
						btnRegularCitizen.setVisible(false);
						lblSoldierCarryWeapon.setVisible(false);
						chkSoldierCarryWeapon.setVisible(false);
						btnSoldier.setVisible(false);

					}
					if(cmbAddCitizen.getValue()=="Soldier") {
						lblSoldierCarryWeapon.setVisible(true);
						chkSoldierCarryWeapon.setVisible(true);
						btnSoldier.setVisible(true);
						lblCandidatePoliticalParty.setVisible(false);
						tfCandidatePoliticalParty.setVisible(false);
						lblCandidatePlacement.setVisible(false);
						tfCandidatePlacement.setVisible(false);
						btnRegularCitizen.setVisible(false);
						btnCandidate.setVisible(false);
					}
				}
			});
			
			Label lblCitizenId= new Label("Enter citizen's id:");
			TextField tfCitizenId= new TextField();
			Label lblCitizenName= new Label("Enter citizen's name:");
			TextField tfCitizenName= new TextField();
			Label lblCitizenYear= new Label("Enter citizen's birth year:");
			TextField tfCitizenYear= new TextField();
			Label lblCitizenAdress= new Label("Enter ballot's address:");
			TextField tfCitizenAdress= new TextField();
			Label lblCitizenSick= new Label("Are you sick babe?");
			CheckBox chkCitizenSick= new CheckBox("I'm sick :(");
			Label lblCitizenProtectiveSuit= new Label("If you wearing protective suit enter the button:");
			CheckBox chkCitizenProtectiveSuit= new CheckBox("I'm wearing :)");
			lblCitizenProtectiveSuit.setVisible(false);
			chkCitizenProtectiveSuit.setVisible(false);
			chkCitizenSick.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					lblCitizenProtectiveSuit.setVisible(chkCitizenSick.isSelected());
					chkCitizenProtectiveSuit.setVisible(chkCitizenSick.isSelected());
				}
			});
			

			gpAddCitizen.add(cmbAddCitizen, 0, 0);
			gpAddCitizen.add(lblCitizenId,0,1);
			gpAddCitizen.add(tfCitizenId, 2, 1);
			gpAddCitizen.add(lblCitizenName, 0, 2);
			gpAddCitizen.add(tfCitizenName, 2, 2);
			gpAddCitizen.add(lblCitizenYear, 0, 3);
			gpAddCitizen.add(tfCitizenYear, 2, 3);
			gpAddCitizen.add(lblCitizenAdress, 0, 4);
			gpAddCitizen.add(tfCitizenAdress, 2, 4);
			gpAddCitizen.add(lblCitizenSick, 0, 5);
			gpAddCitizen.add(chkCitizenSick, 2, 5);
			gpAddCitizen.add(lblCitizenProtectiveSuit, 0, 6);
			gpAddCitizen.add(chkCitizenProtectiveSuit, 2, 6);
			gpAddCitizen.add(btnRegularCitizen, 0, 7);
			gpAddCitizen.add(lblCandidatePoliticalParty, 0, 7);
			gpAddCitizen.add(tfCandidatePoliticalParty, 2, 7);
			gpAddCitizen.add(lblCandidatePlacement, 0, 8);
			gpAddCitizen.add(tfCandidatePlacement, 2, 8);
			gpAddCitizen.add(btnCandidate, 0, 9);
			gpAddCitizen.add(lblSoldierCarryWeapon, 0, 7);
			gpAddCitizen.add(chkSoldierCarryWeapon, 2, 7);
			gpAddCitizen.add(btnSoldier, 0, 8);	
		}
		
		public void AddPoliticalPartyIsSelected() {
			GridPane gpAddPoliticalParty = new GridPane();
			gpAddPoliticalParty.setPadding(new Insets(10));
			gpAddPoliticalParty.setHgap(10);
			gpAddPoliticalParty.setVgap(10);
			
			Label lblPoliticalPartyWing= new Label("Select political party wing:");

			ComboBox<String> cmbPoliticalPartyWing=new ComboBox<String>();
			cmbPoliticalPartyWing.getItems().addAll("Left","Central","Right");
			
			Label lblPoliticalPartyName= new Label("Enter political party name:");
			TextField tfPoliticalPartyName= new TextField();
			
			Button btnPoliticalParty= new Button("Add Political Party !");
			
			gpAddPoliticalParty.add(lblPoliticalPartyWing, 0, 0);
			gpAddPoliticalParty.add(cmbPoliticalPartyWing, 2, 0);
			gpAddPoliticalParty.add(lblPoliticalPartyName, 0, 1);
			gpAddPoliticalParty.add(tfPoliticalPartyName, 2, 1);
			gpAddPoliticalParty.add(btnPoliticalParty, 0, 2);

		}
		
		public void showAllBallotsIsSelected(String str) {
			GridPane gpshowAllBallots = new GridPane();
			gpshowAllBallots.setPadding(new Insets(10));
			gpshowAllBallots.setHgap(10);
			gpshowAllBallots.setVgap(10);
			
			Label lblshowAllBallots= new Label(str);
			
			gpshowAllBallots.add(lblshowAllBallots, 0, 0);
		}
		
		public void showAllCitizensIsSelected(String str) {
			GridPane gpshowAllCitizens = new GridPane();
			gpshowAllCitizens.setPadding(new Insets(10));
			gpshowAllCitizens.setHgap(10);
			gpshowAllCitizens.setVgap(10);
			
			Label lblshowAllCitizens= new Label(str);
			
			gpshowAllCitizens.add(lblshowAllCitizens, 0, 0);
		}
		
		public void showAllPoliticalPartiesIsSelected(String str) {
			GridPane gpshowAllPoliticalParties = new GridPane();
			gpshowAllPoliticalParties.setPadding(new Insets(10));
			gpshowAllPoliticalParties.setHgap(10);
			gpshowAllPoliticalParties.setVgap(10);
			
			Label lblshowAllPoliticalParties= new Label(str);
			
			gpshowAllPoliticalParties.add(lblshowAllPoliticalParties, 0, 0);
		}
		
}
