package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NursePortal extends Main{

	protected Scene NurseScene(Stage stage, Nurse curNurse) {
		
		//************************Home Pane (Tab1)***********************
		//Where the nurse takes in the patients information
		BorderPane homePane = new BorderPane();
		homePane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + "); -fx-padding: 40;");
	    GridPane homeGrid = new GridPane(); //goes inside borderpane
	    
	    CheckBox newPatient = new CheckBox();	    
	    CheckBox olderCheck = new CheckBox();
	    Text home = new Text("Home");
	    Text firstName = new Text("First Name: ");	   
	    Text lastName = new Text("Last Name: ");	    
	    Text weightText = new Text("1) Enter the Patient's weight:");
	    Text heightText = new Text("2) Enter the Patient's height:");
	    Text bloodText = new Text("3) Enter the Patient's blood pressure:");
	    Text tempText = new Text("4) Enter the Patient's body temperature:");
	    Text olderTextCheck = new Text("Is the Patient older then 12?");
	    Text notes = new Text("Additional Notes");
	    TextArea noteSpace= new TextArea("Type here");
	    TextField lastNameField = new TextField();
	    TextField firstNameField = new TextField();
	    TextField weightField = new TextField();
	    TextField heightField = new TextField();
	    TextField bloodField = new TextField();
	    TextField tempField = new TextField();
	    Button submit = new Button("Submit");
	    
	    home.setFont(Font.font("Courier", FontWeight.BOLD, 30)); 
		firstName.setFont(Font.font("Courier", 20));
		lastName.setFont(Font.font("Courier", 20));
		weightText.setFont(Font.font("Courier", 20));
		heightText.setFont(Font.font("Courier", 20));
		bloodText.setFont(Font.font("Courier", 20));
		tempText.setFont(Font.font("Courier", 20));
		olderTextCheck.setFont(Font.font("Courier", 20));
		notes.setFont(Font.font("Courier", 20));
		noteSpace.setFont(Font.font("Courier", 20));
	    
	    homePane.setTop(home);
	    homePane.setCenter(homeGrid);
	    homePane.setBottom(submit);

		//*******************Patient Pane(Tab2)*************************
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();
		gp.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		gp.setPadding(new Insets(0, 50, 50, 50)); 
		
		// Formatting for table that goes on the patientInfo tab, this table takes objects from the Patient class
		TableView<Patient> patientTable = new TableView<Patient>(); //create table
		patientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn <Patient, String> p_column1 = new TableColumn<>("First Name");
		p_column1.setCellValueFactory(new PropertyValueFactory<>("fName"));
		p_column1.setStyle("-fx-alignment: CENTER;");
	
		TableColumn <Patient, String> p_column2 = new TableColumn<>("Last Name");
		p_column2.setCellValueFactory(new PropertyValueFactory<>("lName"));
		p_column2.setStyle("-fx-alignment: CENTER;");
		
		patientTable.getColumns().add(p_column1);
		patientTable.getColumns().add(p_column2);
		
		for(Doctor d : curNurse.getDoctors())
		{
			for (Patient p: d.getPatients())
			{
				patientTable.getItems().add(p);
			}
		}
		
		VBox pBox = new VBox();
		pBox.getChildren().addAll(patientTable);
		pBox.setSpacing(10);
		pBox.setAlignment(Pos.TOP_LEFT);
		pBox.setPrefHeight(600);
		pBox.setPrefWidth(600);
		
		GridPane details = new GridPane();
		details.setHgap(5); //horizontal gap in pixels => that's what you are asking for
		details.setVgap(5); //vertical gap in pixels
		
		Text pInfo = new Text("Patient Info");
		
		Text email = new Text("Email:");
		Text number = new Text("Number:");
		Text birth = new Text("Date of Birth: ");
		Text gender = new Text("Gender: ");
		Text inCo = new Text("Insurance Provider: ");
		Text inNum = new Text("Insurance Number: ");
		Text nurse = new Text("Assigned Nurse: ");
		
		pInfo.setFont(Font.font("Courier", FontWeight.BOLD, 30)); 
		email.setFont(Font.font("Courier", 20));
		number.setFont(Font.font("Courier", 20));
		birth.setFont(Font.font("Courier", 20));
		gender.setFont(Font.font("Courier", 20));
		inCo.setFont(Font.font("Courier", 20));
		inNum.setFont(Font.font("Courier", 20));
		nurse.setFont(Font.font("Courier", 20));
		
		Text newEmail = new Text();
		Text newNumber = new Text();
		Text newBirth = new Text();
		Text newGender = new Text();
		Text newInCo = new Text();
		Text newInNum = new Text();
		Text newNurse = new Text();
		
		newEmail.setFont(Font.font("Courier", 20));
		newNumber.setFont(Font.font("Courier", 20));
		newBirth.setFont(Font.font("Courier", 20));
		newGender.setFont(Font.font("Courier", 20));
		newInCo.setFont(Font.font("Courier", 20));
		newInNum.setFont(Font.font("Courier", 20));
		newNurse.setFont(Font.font("Courier", 20));
		
		details.add(pInfo, 1, 1);
		details.add(email, 1, 2);
		details.add(number, 1, 3);
		details.add(birth, 1, 4);
		details.add(gender, 1, 5);
		details.add(inCo, 1, 6);
		details.add(inNum, 1, 7);
		details.add(nurse, 1, 8);
		details.add(newEmail, 3, 2);
		details.add(newNumber, 3, 3);
		details.add(newBirth, 3, 4);
		details.add(newGender, 3, 5);
		details.add(newInCo, 3, 6);
		details.add(newInNum, 3, 7);
		details.add(newNurse, 3, 8);
		details.setAlignment(Pos.TOP_LEFT);
		patientTable.setOnMouseClicked(e -> {
			if (e.getClickCount() >= 1) {
				if (patientTable.getSelectionModel().getSelectedItem() != null) 
				{
					Patient p = patientTable.getSelectionModel().getSelectedItem();
					newEmail.setText(p.getEmail());
					newNumber.setText(p.getPhoneNum());
					newBirth.setText(p.getDOB());
					newGender.setText(p.getGender());
					newInCo.setText(p.getInsurCo());
					newInNum.setText(p.getInsurNum());
					newNurse.setText(p.getNurseName());
					
				}
			}		
		});
		
		bp.setLeft(pBox);
		BorderPane.setAlignment(details, Pos.CENTER_LEFT);
		bp.setCenter(details);
		
		//************************Doctor Message Pane(Tab 3)*************************
		//can be very heavily based on the DoctorPortal's Message Pane
		
		
		
		
		
		
		
		
		
		// *************START OF THE TABS CREATION ************
		Tab portal = new Tab("   Nurse Portal");
		Tab tab1 = new Tab("\t\t\t\t\t\t Home");
	    Tab tab2 = new Tab("\t\t\t\t   Patient"); // tabbed to center	
	    Tab tab3 = new Tab("\t\t\t\t\tDoctor's Messages"); // tabbed to center
	    portal.setDisable(true); // disable portal table 

	    // format tabs
	    portal.setStyle("-fx-pref-width: 120; -fx-pref-height: 30; -fx-background-color: rgb(" + 129 + "," + 138 + ", " + 151 + "); -fx-opacity: 1; -fx-text-base-color: white; -fx-font-weight: bold");
        tab1.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; "); // round tab1
        tab2.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0;"); // round tab2
        tab3.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0;"); // round tab2
        
        tab1.setContent(homePane);
        tab2.setContent(bp);
       // tab3.setContent(value);

	   // create tab pane
	    TabPane tabPane = new TabPane();
	    tabPane.getTabs().addAll(portal, tab1, tab2, tab3); // add children
	    tabPane.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING); // set tab style to float
	    tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // don't allow tab closing
	    
	    Button logout = new Button("Logout");
		logout.setOnAction(e->switchScenes(stage));

	    // layout the stage.
		VBox box = new VBox(10); // create vbox 
		box.getChildren().addAll(logout, tabPane); // add tab pane to vbox
		box.setAlignment(Pos.TOP_RIGHT);
		VBox.setVgrow(tabPane, Priority.ALWAYS);
		
		box.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + "); -fx-padding: 40;"); // set background of vbox
		
		return new Scene(box);
	}

	private void switchScenes(Stage stage)
	{
		HomeScreen home = new HomeScreen();
		Scene h = home.firstScreen(stage);
		stage.setScene(h);
	}
}