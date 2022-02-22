package elections.view;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import election.controller.ElectionsController;
import election.listeners.ElectionEventsListener;
import election.listeners.ElectionsUIListener;
import elections.objects.Ballot;
import elections.objects.Citizen;
import elections.objects.PoliticalParty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class view_menu implements ElectionsUIListener{
	private ComboBox<Integer> cmbAllElectors = new ComboBox<Integer>();
	private ComboBox<String> cmbAllParties = new ComboBox<String>();
	private ComboBox<String> cmbAllPartiesCandidate = new ComboBox<String>();
	private ComboBox<String> cmbRegularCitizensBallot = new ComboBox<String>();
	private ComboBox<String> cmbSickCitizensBallot = new ComboBox<String>();
	private ComboBox<String> cmbSoldiersBallot = new ComboBox<String>();
	private ComboBox<String> cmbSickSoldiersBallot = new ComboBox<String>();

	private GridPane gpMenu;
	private GridPane gpRoot;
	private GridPane gpSelect;
	private GridPane gpAddBallot = new GridPane();
	private GridPane gpAddCitizen = new GridPane();
	private GridPane gpAddPoliticalParty = new GridPane();
	private Label lblPrintDetails = new Label();
	private GridPane gpGoVote = new GridPane();
	private Vector<ElectionsUIListener> listenerUI = new Vector<ElectionsUIListener>();
	private Citizen voter = null;

	public void registerListenerUI(ElectionsUIListener listener) {
		listenerUI.add(listener);
	}

	public view_menu(Stage theStage) {
		theStage.setTitle("Election Program || By - Roni Aronson & Idan Ezer");

		gpRoot = new GridPane();
		gpRoot.setPadding(new Insets(10));
		gpRoot.setHgap(10);
		gpRoot.setVgap(10);
		gpMenu = new GridPane();
		gpMenu.setPadding(new Insets(10));
		gpMenu.setHgap(10);
		gpMenu.setVgap(10);
		gpSelect = new GridPane();
		gpSelect.setPadding(new Insets(10));
		gpSelect.setHgap(10);
		gpSelect.setVgap(10);

		ToggleGroup tglSelect = new ToggleGroup();
		RadioButton rdoAddBallot = new RadioButton("Add new ballot box");
		RadioButton rdoAddCitizen = new RadioButton("Add new citizen");
		RadioButton rdoAddPoliticalParty = new RadioButton("Add new political party");
		RadioButton rdoShowBallot = new RadioButton("View all ballots details");
		RadioButton rdoShowCitizen = new RadioButton("View all citizens details");
		RadioButton rdoShowPoliticalParty = new RadioButton("View all political parties details");
		RadioButton rdoGoVote = new RadioButton("Go vote");
		RadioButton rdoShowResults = new RadioButton("View results");
		RadioButton rdoExit = new RadioButton("Exit");

		rdoAddBallot.setToggleGroup(tglSelect);
		rdoAddCitizen.setToggleGroup(tglSelect);
		rdoAddPoliticalParty.setToggleGroup(tglSelect);
		rdoShowBallot.setToggleGroup(tglSelect);
		rdoShowCitizen.setToggleGroup(tglSelect);
		rdoShowPoliticalParty.setToggleGroup(tglSelect);
		rdoGoVote.setToggleGroup(tglSelect);
		rdoShowResults.setToggleGroup(tglSelect);
		rdoExit.setToggleGroup(tglSelect);

		rdoAddBallot.setTextFill(Color.DEEPPINK);
		rdoAddCitizen.setTextFill(Color.ORCHID);
		rdoAddPoliticalParty.setTextFill(Color.BLUEVIOLET);
		rdoShowBallot.setTextFill(Color.BLUE);
		rdoShowCitizen.setTextFill(Color.GREEN);
		rdoShowPoliticalParty.setTextFill(Color.PERU);
		rdoGoVote.setTextFill(Color.SALMON);
		rdoShowResults.setTextFill(Color.TOMATO);
		rdoExit.setTextFill(Color.BLACK);
		
		gpMenu.add(rdoAddBallot, 0, 0);
		gpMenu.add(rdoAddCitizen, 0, 1);
		gpMenu.add(rdoAddPoliticalParty, 0, 2);
		gpMenu.add(rdoShowBallot, 0, 3);
		gpMenu.add(rdoShowCitizen, 0, 4);
		gpMenu.add(rdoShowPoliticalParty, 0, 5);
		gpMenu.add(rdoGoVote, 0, 6);
		gpMenu.add(rdoShowResults, 0, 7);
		gpMenu.add(rdoExit, 0, 8);

		gpAddBallot.setPadding(new Insets(10));
		gpAddBallot.setHgap(10);
		gpAddBallot.setVgap(10);

		ComboBox<String> cbAddBallot = new ComboBox<String>();
		cbAddBallot.getItems().addAll("Citizens", "Sick Citizens", "Soldiers", "Sick Soldiers");
		Label lblBallotType = new Label("Select ballot's type:");
		Label lblBallotAdress = new Label("Enter ballot's address:");
		TextField tfBallotAdress = new TextField();
		Button btnAddBallot = new Button("Add new ballot box");
		Label lblExsBallot = new Label();
		lblExsBallot.setVisible(false);

		gpAddBallot.add(lblBallotType, 0, 0);
		gpAddBallot.add(cbAddBallot, 0, 1);
		gpAddBallot.add(tfBallotAdress, 1, 2);
		gpAddBallot.add(lblBallotAdress, 0, 2);
		gpAddBallot.add(btnAddBallot, 0, 3);
		gpAddBallot.add(lblExsBallot, 0, 5);

		gpAddBallot.setVisible(false);

		gpAddCitizen.setPadding(new Insets(10));
		gpAddCitizen.setHgap(10);
		gpAddCitizen.setVgap(10);

		ComboBox<String> cmbAddCitizen = new ComboBox<String>();
		cmbAddCitizen.getItems().addAll("Regular Citizen", "Candidate", "Soldier");

		Button btnRegularCitizen = new Button("Add Regular Citizen !");

		ComboBox<String> cmbAddCitizenParties = cmbAllParties;

		Label lblCandidatePoliticalParty = new Label("Enter political party name:");
		// TextField tfCandidatePoliticalParty= new TextField();
		Label lblCandidatePlacement = new Label("Enter your placement:");
		TextField tfCandidatePlacement = new TextField();
		Button btnCandidate = new Button("Add Candidate !");

		Label lblSoldierCarryWeapon = new Label("If you carry weapon enter the button:");
		CheckBox chkSoldierCarryWeapon = new CheckBox("I'm carrying a weapon");
		Button btnSoldier = new Button("Add Soldier !");
		Label lblCitizenId = new Label("Enter citizen's id:");
		TextField tfCitizenId = new TextField();
		Button btnCheckId = new Button("Check Id");
		Label lblCitizenName = new Label("Enter citizen's name:");
		TextField tfCitizenName = new TextField();
		Label lblCitizenYear = new Label("Enter citizen's birth year:");
		TextField tfCitizenYear = new TextField();
		Button btnCheckYear = new Button("Check Year");
		Label lblCitizenAdress = new Label("Enter ballot's address:");
		TextField tfCitizenAdress = new TextField();
		Label lblCitizenSick = new Label("Are you sick babe?");
		CheckBox chkCitizenSick = new CheckBox("I'm sick :(");
		Label lblCitizenProtectiveSuit = new Label("If you wearing protective suit enter the button:");
		CheckBox chkCitizenProtectiveSuit = new CheckBox("I'm wearing :)");
		Label lblCitizenHowManyDays = new Label("for how long do you sick? (enter number of days)");
		TextField tfCitizenHowManyDays = new TextField();
		Label lblExsAddCitizen = new Label();
		lblExsAddCitizen.setTextFill(Color.RED);

		gpAddCitizen.add(cmbAddCitizen, 0, 0);
		gpAddCitizen.add(lblCitizenId, 0, 1);
		gpAddCitizen.add(tfCitizenId, 2, 1);
		gpAddCitizen.add(btnCheckId, 3, 1);
		gpAddCitizen.add(lblCitizenName, 0, 2);
		gpAddCitizen.add(tfCitizenName, 2, 2);
		gpAddCitizen.add(lblCitizenYear, 0, 3);
		gpAddCitizen.add(tfCitizenYear, 2, 3);
		gpAddCitizen.add(btnCheckYear, 3, 3);
		gpAddCitizen.add(lblCitizenAdress, 0, 4);
		gpAddCitizen.add(tfCitizenAdress, 2, 4);

		gpAddCitizen.add(lblCitizenSick, 0, 5);
		gpAddCitizen.add(chkCitizenSick, 2, 5);
		gpAddCitizen.add(lblCitizenProtectiveSuit, 0, 6);
		gpAddCitizen.add(chkCitizenProtectiveSuit, 2, 6);
		gpAddCitizen.add(lblCitizenHowManyDays, 0, 7);
		gpAddCitizen.add(tfCitizenHowManyDays, 2, 7);
		gpAddCitizen.add(btnRegularCitizen, 0, 8);
		gpAddCitizen.add(lblCandidatePoliticalParty, 0, 8);
		gpAddCitizen.add(cmbAllPartiesCandidate, 2, 8);
		gpAddCitizen.add(lblCandidatePlacement, 0, 9);
		gpAddCitizen.add(tfCandidatePlacement, 2, 9);
		gpAddCitizen.add(btnCandidate, 0, 10);
		gpAddCitizen.add(lblSoldierCarryWeapon, 0, 8);
		gpAddCitizen.add(chkSoldierCarryWeapon, 2, 8);
		gpAddCitizen.add(btnSoldier, 0, 9);
		gpAddCitizen.add(lblExsAddCitizen, 0, 11);

		gpAddCitizen.setVisible(false);

		Label lblEnterId = new Label("Enter your id:");
		TextField tfEnterId = new TextField();
		Button btnEnterId = new Button("Submit");
		Label lblDoYouVote = new Label();
		CheckBox chkDoYouVote = new CheckBox("Yes I Do !!!!");
		Button btnGoVote = new Button("Go Vote");
		Label lblGoVoteMess = new Label();

		gpGoVote.add(lblEnterId, 0, 0);
		gpGoVote.add(tfEnterId, 1, 0);
		gpGoVote.add(btnEnterId, 2, 0);
		gpGoVote.add(lblDoYouVote, 1, 1);
		gpGoVote.add(chkDoYouVote, 2, 1);
		gpGoVote.add(cmbAllParties, 0, 2);
		gpGoVote.add(btnGoVote, 0, 3);
		gpGoVote.add(lblGoVoteMess, 0, 4);

		gpGoVote.setVisible(false);

		rdoAddBallot.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				lblPrintDetails.setVisible(false);
				gpAddPoliticalParty.setVisible(false);
				gpAddCitizen.setVisible(false);
				gpAddBallot.setVisible(true);
				gpGoVote.setVisible(false);

				btnAddBallot.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						if (cbAddBallot.getValue() == "Citizens") {
							for (ElectionsUIListener l : listenerUI)
								l.addCitizenBallotToModel(tfBallotAdress.getText(), cbAddBallot.getValue());
						}

						else if (cbAddBallot.getValue() == "Sick Citizens") {
							for (ElectionsUIListener l : listenerUI)
								l.addSickCitizenBallotToModel(tfBallotAdress.getText(), cbAddBallot.getValue());
						} else if (cbAddBallot.getValue() == "Soldiers") {
							for (ElectionsUIListener l : listenerUI)
								l.addSoldiersBallotToModel(tfBallotAdress.getText(), cbAddBallot.getValue());
						} else if (cbAddBallot.getValue() == "Sick Soldier") {
							for (ElectionsUIListener l : listenerUI)
								l.addSickSoldiersBallotToModel(tfBallotAdress.getText(), cbAddBallot.getValue());
						} else {
							lblExsBallot.setText("Please Select Ballot's Box Type");
							lblExsBallot.setVisible(true);
						}
						lblExsBallot.setVisible(false);
						tfBallotAdress.clear();
						cbAddBallot.getSelectionModel().clearSelection();
					}
				});

			}
		});

		rdoAddCitizen.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				cmbRegularCitizensBallot.setVisible(false);
				cmbSickCitizensBallot.setVisible(false);
				cmbSoldiersBallot.setVisible(false);
				cmbSickSoldiersBallot.setVisible(false);
				lblCandidatePlacement.setVisible(false);
				tfCandidatePlacement.setVisible(false);
				lblCandidatePoliticalParty.setVisible(false);
				cmbAllPartiesCandidate.setVisible(false);
				cmbAddCitizenParties.setVisible(false);
				btnCandidate.setVisible(false);
				btnRegularCitizen.setVisible(false);
				lblSoldierCarryWeapon.setVisible(false);
				chkSoldierCarryWeapon.setVisible(false);
				btnSoldier.setVisible(false);

				cmbAddCitizen.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						if (cmbAddCitizen.getValue() == "Regular Citizen") {
							btnRegularCitizen.setVisible(true);
							cmbAddCitizenParties.setVisible(false);
							lblCandidatePoliticalParty.setVisible(false);
							cmbAllPartiesCandidate.setVisible(false);
							lblCandidatePlacement.setVisible(false);
							tfCandidatePlacement.setVisible(false);
							btnCandidate.setVisible(false);
							lblSoldierCarryWeapon.setVisible(false);
							chkSoldierCarryWeapon.setVisible(false);
							btnSoldier.setVisible(false);

						} else if (cmbAddCitizen.getValue() == "Candidate") {
							cmbAddCitizenParties.setVisible(true);
							lblCandidatePoliticalParty.setVisible(true);
							cmbAllPartiesCandidate.setVisible(true);
							lblCandidatePlacement.setVisible(true);
							tfCandidatePlacement.setVisible(true);
							btnCandidate.setVisible(true);
							btnRegularCitizen.setVisible(false);
							lblSoldierCarryWeapon.setVisible(false);
							chkSoldierCarryWeapon.setVisible(false);
							btnSoldier.setVisible(false);

						} else if (cmbAddCitizen.getValue() == "Soldier") {
							lblSoldierCarryWeapon.setVisible(true);
							chkSoldierCarryWeapon.setVisible(true);
							btnSoldier.setVisible(true);
							cmbAddCitizenParties.setVisible(false);
							lblCandidatePoliticalParty.setVisible(false);
							cmbAllPartiesCandidate.setVisible(false);
							lblCandidatePlacement.setVisible(false);
							tfCandidatePlacement.setVisible(false);
							btnRegularCitizen.setVisible(false);
							btnCandidate.setVisible(false);

						}
					}
				});

				lblExsAddCitizen.setVisible(false);
				lblCitizenHowManyDays.setVisible(false);
				tfCitizenHowManyDays.setVisible(false);
				lblCitizenProtectiveSuit.setVisible(false);
				chkCitizenProtectiveSuit.setVisible(false);
				chkCitizenSick.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						lblCitizenProtectiveSuit.setVisible(chkCitizenSick.isSelected());
						chkCitizenProtectiveSuit.setVisible(chkCitizenSick.isSelected());
						lblCitizenHowManyDays.setVisible(chkCitizenSick.isSelected());
						tfCitizenHowManyDays.setVisible(chkCitizenSick.isSelected());
					}
				});

				lblPrintDetails.setVisible(false);
				gpAddCitizen.setVisible(true);
				gpAddPoliticalParty.setVisible(false);
				gpAddBallot.setVisible(false);
				gpGoVote.setVisible(false);
				btnCheckId.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						try {
							lblExsAddCitizen.setVisible(false);
							int id = Integer.parseInt(tfCitizenId.getText());

							if (id < 100000000 || id > 999999999)
								throw new Exception("please enter 9 digits");
						} catch (InputMismatchException j) {
							lblExsAddCitizen.setText("please enter only digits");
							tfCitizenId.clear();
							lblExsAddCitizen.setVisible(true);

						} catch (Exception j) {
							lblExsAddCitizen.setText(j.getMessage());
							tfCitizenId.clear();
							lblExsAddCitizen.setVisible(true);

						}

						btnCheckYear.setOnAction(new EventHandler<ActionEvent>() {
							@Override
							public void handle(ActionEvent e) {
								try {
									lblExsAddCitizen.setVisible(false);
									int year = Integer.parseInt(tfCitizenYear.getText());
									if (year >= 2002)
										throw new Exception("you are too young darling");
								} catch (InputMismatchException j) {
									lblExsAddCitizen.setText("please enter only digits");
									tfCitizenYear.clear();
									lblExsAddCitizen.setVisible(true);
								} catch (Exception j) {
									lblExsAddCitizen.setText(j.getMessage());
									tfCitizenYear.clear();
									lblExsAddCitizen.setVisible(true);
								}

								btnRegularCitizen.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent e) {
										if (!chkCitizenSick.isSelected()) {
											for (ElectionsUIListener l : listenerUI)
												l.addRegularCitizenToModel(tfCitizenName.getText(),
														tfCitizenId.getText(), tfCitizenYear.getText(),
														tfCitizenAdress.getText());
										} else {
											for (ElectionsUIListener l : listenerUI)
												l.addSickCitizenToModel(tfCitizenName.getText(), tfCitizenId.getText(),
														tfCitizenYear.getText(), tfCitizenAdress.getText(),
														chkCitizenProtectiveSuit.isSelected(),
														tfCitizenHowManyDays.getText());
										}

										chkCitizenSick.setSelected(false);
										tfCitizenName.clear();
										tfCitizenId.clear();
										tfCitizenYear.clear();
										tfCitizenAdress.clear();
										chkCitizenProtectiveSuit.setSelected(false);
										tfCitizenHowManyDays.clear();
										chkSoldierCarryWeapon.setSelected(false);
										chkCitizenProtectiveSuit.setSelected(false);
										tfCitizenHowManyDays.clear();
										tfCandidatePlacement.clear();
										cmbAddCitizen.getSelectionModel().clearSelection();

										btnCandidate.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent e) {
												if (!chkCitizenSick.isSelected()) {
													for (ElectionsUIListener l : listenerUI) {
														l.addCandidateToModel(tfCitizenName.getText(),
																tfCitizenId.getText(), tfCitizenYear.getText(),
																tfCitizenAdress.getText(),
																tfCandidatePlacement.getText(),
																cmbAllPartiesCandidate.getValue());
													}
												} else {
													for (ElectionsUIListener l : listenerUI)
														l.addSickCandidateToModel(tfCitizenName.getText(),
																tfCitizenId.getText(), tfCitizenYear.getText(),
																tfCitizenAdress.getText(),
																tfCandidatePlacement.getText(),
																cmbAllPartiesCandidate.getValue(),
																chkCitizenProtectiveSuit.isSelected(),
																tfCitizenHowManyDays.getText());
												}
												chkCitizenSick.setSelected(false);
												tfCitizenName.clear();
												tfCitizenId.clear();
												tfCitizenYear.clear();
												tfCitizenAdress.clear();
												chkCitizenProtectiveSuit.setSelected(false);
												tfCitizenHowManyDays.clear();
												chkSoldierCarryWeapon.setSelected(false);
												chkCitizenProtectiveSuit.setSelected(false);
												tfCitizenHowManyDays.clear();
												tfCandidatePlacement.clear();
												cmbAddCitizen.getSelectionModel().clearSelection();
											}
										});
										btnSoldier.setOnAction(new EventHandler<ActionEvent>() {

											@Override
											public void handle(ActionEvent event) {
												if (!chkCitizenSick.isSelected()) {
													for (ElectionsUIListener l : listenerUI)
														l.addSoldierToModel(tfCitizenName.getText(),
																tfCitizenId.getText(), tfCitizenYear.getText(),
																tfCitizenAdress.getText(),
																chkSoldierCarryWeapon.isSelected());
												} else {
													for (ElectionsUIListener l : listenerUI)
														l.addSickSoldierToModel(tfCitizenName.getText(),
																tfCitizenId.getText(), tfCitizenYear.getText(),
																tfCitizenAdress.getText(),
																chkSoldierCarryWeapon.isSelected(),
																chkCitizenProtectiveSuit.isSelected(),
																tfCitizenHowManyDays.getText());
												}
												chkCitizenSick.setSelected(false);
												tfCitizenName.clear();
												tfCitizenId.clear();
												tfCitizenYear.clear();
												tfCitizenAdress.clear();
												chkCitizenProtectiveSuit.setSelected(false);
												tfCitizenHowManyDays.clear();
												chkSoldierCarryWeapon.setSelected(false);
												chkCitizenProtectiveSuit.setSelected(false);
												tfCitizenHowManyDays.clear();
												tfCandidatePlacement.clear();
												cmbAddCitizen.getSelectionModel().clearSelection();
											}
										});

									}
								});
							}

						});
					}
				});
			}
		});

		rdoAddPoliticalParty.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				gpAddPoliticalParty.setPadding(new Insets(10));
				gpAddPoliticalParty.setHgap(10);
				gpAddPoliticalParty.setVgap(10);

				Label lblPoliticalPartyWing = new Label("Select political party wing:");

				ComboBox<String> cmbPoliticalPartyWing = new ComboBox<String>();
				cmbPoliticalPartyWing.getItems().addAll("Left", "Central", "Right");

				Label lblPoliticalPartyName = new Label("Enter political party name:");
				TextField tfPoliticalPartyName = new TextField();

				DatePicker datePicker = new DatePicker();
				Label lblSelectDate = new Label("Please pick a creation date:");

				Button btnPoliticalParty = new Button("Add Political Party !");

				gpAddPoliticalParty.add(lblPoliticalPartyWing, 0, 0);
				gpAddPoliticalParty.add(cmbPoliticalPartyWing, 1, 0);
				gpAddPoliticalParty.add(lblPoliticalPartyName, 0, 1);
				gpAddPoliticalParty.add(tfPoliticalPartyName, 1, 1);
				gpAddPoliticalParty.add(lblSelectDate, 0, 2);
				gpAddPoliticalParty.add(datePicker, 1, 2);
				gpAddPoliticalParty.add(btnPoliticalParty, 0, 4);

				lblPrintDetails.setVisible(false);
				gpAddPoliticalParty.setVisible(true);
				gpAddCitizen.setVisible(false);
				gpAddBallot.setVisible(false);
				gpGoVote.setVisible(false);

				btnPoliticalParty.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						for (ElectionsUIListener l : listenerUI) {
							l.addPoliticalPartyToModel(tfPoliticalPartyName.getText(), cmbPoliticalPartyWing.getValue(),
									datePicker.getValue());
						}

						tfPoliticalPartyName.clear();
						cmbPoliticalPartyWing.getSelectionModel().clearSelection();
						datePicker.getEditor().clear();

					}

				});
			}
		});
		rdoShowBallot.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lblPrintDetails.setText(listenerUI.elementAt(0).viewAsksForBallotsDetails());
				lblPrintDetails.setVisible(true);
				gpAddPoliticalParty.setVisible(false);
				gpAddCitizen.setVisible(false);
				gpAddBallot.setVisible(false);
				gpGoVote.setVisible(false);

			}
		});
		rdoShowCitizen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lblPrintDetails.setText(listenerUI.elementAt(0).viewAsksForCitizensDetails());
				lblPrintDetails.setVisible(true);
				gpAddPoliticalParty.setVisible(false);
				gpAddCitizen.setVisible(false);
				gpAddBallot.setVisible(false);
				gpGoVote.setVisible(false);

			}
		});
		rdoShowPoliticalParty.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lblPrintDetails.setText(listenerUI.elementAt(0).viewAsksForPoliticalPartyDetails());
				lblPrintDetails.setVisible(true);
				gpAddPoliticalParty.setVisible(false);
				gpAddCitizen.setVisible(false);
				gpAddBallot.setVisible(false);
				gpGoVote.setVisible(false);

			}
		});
		rdoGoVote.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				gpGoVote.setPadding(new Insets(10));
				gpGoVote.setHgap(10);
				gpGoVote.setVgap(10);

				lblDoYouVote.setVisible(false);
				chkDoYouVote.setVisible(false);
				cmbAllParties.setVisible(false);
				btnGoVote.setVisible(false);
				lblGoVoteMess.setVisible(false);

				lblPrintDetails.setVisible(false);
				gpAddPoliticalParty.setVisible(false);
				gpAddCitizen.setVisible(false);
				gpAddBallot.setVisible(false);
				gpGoVote.setVisible(true);

				btnEnterId.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {

						voter = listenerUI.elementAt(0).viewAsksForCitizenById(Integer.parseInt(tfEnterId.getText()));
						if (voter == null) {
							lblGoVoteMess.setText(tfEnterId.getText() + " is not exist");
							lblGoVoteMess.setVisible(true);
						} else {
							lblGoVoteMess.setText("");
							lblDoYouVote.setVisible(true);
							chkDoYouVote.setVisible(true);
							lblDoYouVote.setText("Hi " + voter.getName() + ", if you want please check the box:");
							chkDoYouVote.setOnAction(new EventHandler<ActionEvent>() {

								@Override
								public void handle(ActionEvent e) {
									cmbAllParties.setVisible(true);
									btnGoVote.setVisible(chkDoYouVote.isSelected());

									btnGoVote.setOnAction(new EventHandler<ActionEvent>() {
										@Override
										public void handle(ActionEvent e) {
											lblGoVoteMess.setText(listenerUI.elementAt(0)
													.viewAsksToSetVoteToCitizen(voter, cmbAllParties.getValue()));
											lblGoVoteMess.setVisible(true);
											// cmbAllParties.getSelectionModel().clearSelection();
											chkDoYouVote.setSelected(false);
											chkDoYouVote.setVisible(false);
											lblDoYouVote.setVisible(false);
											cmbAllParties.setVisible(false);
											tfEnterId.clear();
										}
									});
								}
							});
						}
					}

				});
			}

		});
		rdoShowResults.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				lblPrintDetails.setText(listenerUI.elementAt(0).viewAsksForResults());
				lblPrintDetails.setVisible(true);
				gpAddPoliticalParty.setVisible(false);
				gpAddCitizen.setVisible(false);
				gpAddBallot.setVisible(false);
				gpGoVote.setVisible(false);

			}

		});

		rdoExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				theStage.close();
			}

		});

		gpSelect.add(gpGoVote, 0, 0);
		gpSelect.add(lblPrintDetails, 0, 0);
		gpSelect.add(gpAddBallot, 0, 0);
		gpSelect.add(gpAddPoliticalParty, 0, 0);
		gpSelect.add(gpAddCitizen, 0, 0);

		gpRoot.add(gpMenu, 0, 0);
		gpRoot.add(gpSelect, 1, 0);

		theStage.setScene(new Scene(gpRoot, 1222, 500));
		theStage.show();
	}

	public void addCitizenToGoVote(Citizen citizen) {
		cmbAllElectors.getItems().add(citizen.getId());
	}

	public void addPartyToGoVote(PoliticalParty party) {
		cmbAllParties.getItems().add(party.getName());
		cmbAllPartiesCandidate.getItems().add(party.getName());
	}

	public void addBallotCitizenToCmb(Ballot newBallot) {
		cmbRegularCitizensBallot.getItems().add(newBallot.getAddress());
	}

	public void addBallotSickCitizenToCmb(Ballot newBallot) {
		cmbSickCitizensBallot.getItems().add(newBallot.getAddress());
	}

	public void addBallotSoldiersToCmb(Ballot newBallot) {
		cmbSoldiersBallot.getItems().add(newBallot.getAddress());
	}

	public void addBallotSickSoldiersToCmb(Ballot newBallot) {
		cmbSickSoldiersBallot.getItems().add(newBallot.getAddress());
	}

	@Override
	public String viewAsksForBallotsDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viewAsksForCitizensDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viewAsksForPoliticalPartyDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Citizen viewAsksForCitizenById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viewAsksToSetVoteToCitizen(Citizen voter, String vote) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCitizenBallotToModel(String address, String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSickCitizenBallotToModel(String address, String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSoldiersBallotToModel(String address, String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSickSoldiersBallotToModel(String address, String type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRegularCitizenToModel(String name, String id, String year, String address) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSickCitizenToModel(String name, String id, String year, String address, boolean protectiveSuit,
			String howManyDays) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCandidateToModel(String name, String id, String year, String address, String placement,
			String politicalPartyName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSickCandidateToModel(String name, String id, String year, String address, String placement,
			String politicalPartyName, boolean protectiveSuit, String howManyDays) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSoldierToModel(String name, String id, String year, String address, boolean carryWeapon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSickSoldierToModel(String name, String id, String year, String address, boolean carryWeapon,
			boolean protectiveSuit, String howManyDays) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addPoliticalPartyToModel(String name, String wing, LocalDate date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String viewAsksForResults() {
		// TODO Auto-generated method stub
		return null;
	}

}
