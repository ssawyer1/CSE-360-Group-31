package application;

import java.time.LocalDateTime;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	    homeGrid.setHgap(50); //horizontal gap in pixels => that's what you are asking for
	    homeGrid.setVgap(20); //vertical gap in pixels
	       
	    CheckBox newPatient = new CheckBox();	    
	    CheckBox olderCheck = new CheckBox();
	    
	    Text home = new Text("Home");
	    Text date = new Text("Date:");
	    Text reason = new Text("Reason:");
	    Text firstName = new Text("First Name: ");	   
	    Text lastName = new Text("Last Name: ");	    
	    Text weightText = new Text("1) Enter the Patient's weight:");
	    Text heightText = new Text("2) Enter the Patient's height:");
	    Text bloodText = new Text("3) Enter the Patient's blood pressure:");
	    Text tempText = new Text("4) Enter the Patient's body temperature:");
	    Text olderTextCheck = new Text("Is the Patient older then 12?");
		Text newPatientText = new Text("Is this a new Patient?");
	    Text notes = new Text("Additional Notes");
	  
	    TextField dateField = new TextField();
	    TextField reasonField = new TextField();
	    TextField lastNameField = new TextField();
	    TextField firstNameField = new TextField();
	    TextField weightField = new TextField();
	    TextField heightField = new TextField();
	    TextField bloodField = new TextField();
	    TextField tempField = new TextField();

	    TextArea noteSpace= new TextArea("Type here");
	    noteSpace.setPrefSize(650,  300);
	    
	    Button submit = new Button("Submit");
	    
	    home.setFont(Font.font("Courier", FontWeight.BOLD, 30)); 
	    date.setFont(Font.font("Courier", 20));
	    reason.setFont(Font.font("Courier", 20));
		firstName.setFont(Font.font("Courier", 20));
		lastName.setFont(Font.font("Courier", 20));
		weightText.setFont(Font.font("Courier", 20));
		heightText.setFont(Font.font("Courier", 20));
		bloodText.setFont(Font.font("Courier", 20));
		tempText.setFont(Font.font("Courier", 20));
		olderTextCheck.setFont(Font.font("Courier", 20));
		notes.setFont(Font.font("Courier", 20));
		noteSpace.setFont(Font.font("Courier", 20));
		newPatientText.setFont(Font.font("Courier", 20));
	      
	    homeGrid.add(home, 1, 0);
	    homeGrid.add(newPatientText, 1, 1);
		homeGrid.add(firstName, 1, 2);
		homeGrid.add(lastName, 1, 3);
		homeGrid.add(weightText, 1, 4);
		homeGrid.add(heightText, 1, 5);
		
		homeGrid.add(newPatient, 2, 1);
		homeGrid.add(firstNameField,2,2);
		homeGrid.add(lastNameField,2,3);
		homeGrid.add(weightField,2,4);
		homeGrid.add(heightField,2,5);
		
		homeGrid.add(olderTextCheck,3,1);
		homeGrid.add(date, 3, 2);
		homeGrid.add(reason, 3, 3);
		homeGrid.add(bloodText, 3, 4);
		homeGrid.add(tempText, 3, 5);
		
		homeGrid.add(olderCheck, 4, 1);
		homeGrid.add(dateField, 4, 2);
		homeGrid.add(reasonField, 4, 3);
		homeGrid.add(bloodField, 4, 4);
		homeGrid.add(tempField, 4, 5);
		
		GridPane bottomPane = new GridPane();
		bottomPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + "); -fx-padding: 40;");
		bottomPane.setHgap(10); //horizontal gap in pixels => that's what you are asking for
		bottomPane.setVgap(20); //vertical gap in pixels
		
		bottomPane.add(notes, 1, 0);
		bottomPane.add(noteSpace, 1, 1);
		bottomPane.add(submit, 1, 2);
	
		homePane.setTop(homeGrid);
		homePane.setCenter(bottomPane);

		//*******************Patient Pane(Tab2)*************************
		BorderPane bp = new BorderPane();
		bp.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		GridPane gp = new GridPane();
		gp.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		
		
		// Formatting for table that goes on the patientInfo tab, this table takes objects from the Patient class
		
		TableView<Patient> patientTable = new TableView<Patient>(); //create table
		patientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		patientTable.setPrefSize(750, 550);
		
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
		
		 Group group = new Group(patientTable);
		 VBox.setVgrow( group, Priority.NEVER );
		 VBox root = new VBox(group);
		 root.setPadding(new Insets(10, 15, 0, 15));
		
		
		GridPane details = new GridPane();
		details.setHgap(15); //horizontal gap in pixels => that's what you are asking for
		details.setVgap(5); //vertical gap in pixels

		ListView<String> prescriptionList = new ListView<String>();
		VBox listBox = new VBox();
		listBox.setPrefSize(350, 200);
		listBox.getChildren().add(prescriptionList);
		
		Text pInfo = new Text("Patient Info");
		Text prescriptions = new Text("Patient's Prescriptions");
		
		Text email = new Text("Email:");
		Text number = new Text("Number:");
		Text birth = new Text("Date of Birth: ");
		Text gender = new Text("Gender: ");
		Text inCo = new Text("Insurance Provider: ");
		Text inNum = new Text("Insurance Number: ");
		Text nurse = new Text("Assigned Nurse: ");
		
		pInfo.setFont(Font.font("Courier", FontWeight.BOLD, 30)); 
		prescriptions.setFont(Font.font("Courier", 20));
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
		details.add(prescriptions, 1, 9);
		details.add(listBox, 1, 10);
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
				prescriptionList.getItems().clear();
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
					for(Prescription prescription : p.getPrescriptions())
					{
						prescriptionList.getItems().addAll(prescription.getType());
					}
				}
			}		
		});
		
		bp.setLeft(root);		
		bp.setCenter(details);

		//************************Nurse Message Pane(Tab 3)*************************
		//can be very heavily based on the DoctorPortal's Message Pane

		BorderPane msgPane = new BorderPane();
		msgPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		msgPane.setPadding(new Insets(0, 50, 50, 50));
		
		VBox vbox = new VBox(8);
		msgPane.setCenter(vbox);
		
		Text msgPhy = new Text("Messages"); //Text on top
		msgPhy.setFont(Font.font("Courier", FontWeight.BOLD, 30)); 
		BorderPane.setAlignment(msgPhy, Pos.CENTER);
		BorderPane.setMargin(msgPhy, new Insets(40, 0, 45, 0));
		msgPane.setTop(msgPhy);
		
		Text inbox = new Text("Inbox"); //Text on top
		inbox.setFont(Font.font("Courier", FontWeight.MEDIUM, 30)); 
		
		TableView<Message> messageTable = new TableView<Message>(); //create table
		messageTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn <Message, String> m_column1 = new TableColumn<>("From"); //From sendName column
		m_column1.setCellValueFactory(new PropertyValueFactory<>("sendName"));
		m_column1.setStyle("-fx-alignment: CENTER;");
	
		TableColumn <Message, String> m_column2 = new TableColumn<>("Message");//Column showing message content
		m_column2.setCellValueFactory(new PropertyValueFactory<>("message"));
		m_column2.setStyle("-fx-alignment: CENTER;");
		
		TableColumn <Message, String> m_column3 = new TableColumn<>("Date");//Column showing message date
		m_column3.setCellValueFactory(new PropertyValueFactory<>("date"));
		m_column3.setStyle("-fx-alignment: CENTER;");
		
		messageTable.getColumns().add(m_column1);
		messageTable.getColumns().add(m_column2);//adding columns to table
		messageTable.getColumns().add(m_column3);
		
		for(Doctor d : curNurse.getDoctors())//iterating through patients and their messages to doctor			
		{
			for(Patient p: d.getPatients())
			{
			    for(Message m: p.getNurseMsgs())
			    {
				    if(m.getSendName().equalsIgnoreCase(curNurse.getFullName()) == false) //only displaying incoming messages(sendName != Doctor name)
				    {
					    messageTable.getItems().add(m);
				    }
			    }
			}
		}
		
		Text comp_msg = new Text("New Message"); //Text on top
		comp_msg.setFont(Font.font("Courier", FontWeight.MEDIUM, 20)); 
		
		Text to_msg = new Text("To"); //Text on top
		to_msg.setFont(Font.font("Courier", FontWeight.MEDIUM, 15)); 
		
		TextArea to = new TextArea();//Area to type in receiver's name
		to.setText("Firstname Lastname");
		
		Text comp_msg1 = new Text("Message Content"); //Text on top
		comp_msg1.setFont(Font.font("Courier", FontWeight.MEDIUM, 20)); 
		
		TextArea compose = new TextArea();//Area to send new message
		compose.setText("New Message");
		
		BorderPane.setAlignment(messageTable, Pos.CENTER);
		BorderPane.setMargin(messageTable, new Insets(100, 50, 100, 50));
		
		Button send = new Button("  Send  ");
		BorderPane.setAlignment(send, Pos.BOTTOM_LEFT);
		BorderPane.setMargin(send, new Insets(20, 0, 20, 0));
		msgPane.setBottom(send);
		
		Text error = new Text(""); //Text on bottom
		error.setFont(Font.font("Courier", 15));
		
		
		
		send.setOnMouseClicked(e -> {
			if (e.getClickCount() >= 1) {
				String senderName = curNurse.getFullName(); //getting doctor name
				String recieverName = to.getText(); //extracting patient name
				String message = compose.getText(); //message content
				LocalDateTime now = LocalDateTime.now(); //getting date of message
				int sent = 0;
				
				for(Doctor d : curNurse.getDoctors())//checking if a patient of the given name exists
					{
					for(Patient p: d.getPatients())
						if(recieverName.equalsIgnoreCase(p.getFullName()))
							{
								p.addNurseMsg(now, message, "Doctor", senderName);
								sent = 1;
							}
					}
				if(sent == 0)
				{	
					error.setFill(Color.RED);
					error.setText("Patient not found");
				}
				else
				{
					error.setFill(Color.GREEN);
					error.setText("Message Sent");
					to.setText("Firstname Lastname");
					compose.setText("New Message");
				}
				
			}		
		});

		vbox.getChildren().addAll(inbox, messageTable, comp_msg, to_msg, to,comp_msg1, compose, error);	



		// *************START OF THE TABS CREATION ************
		Tab portal = new Tab("   Nurse Portal");
		Tab tab1 = new Tab("\t\t\t\t\t    Home");
	    Tab tab2 = new Tab("\t\t\t\t\t   Patient"); // tabbed to center	
	    Tab tab3 = new Tab("\t\t\t\t   Doctor's Messages"); // tabbed to center
	    portal.setDisable(true); // disable portal table 

	    // format tabs
	    portal.setStyle("-fx-pref-width: 120; -fx-pref-height: 30; -fx-background-color: rgb(" + 129 + "," + 138 + ", " + 151 + "); -fx-opacity: 1; -fx-text-base-color: white; -fx-font-weight: bold");
        tab1.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; "); // round tab1
        tab2.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0;"); // round tab2
        tab3.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0;"); // round tab2
        
        tab1.setContent(homePane);
        tab2.setContent(bp);
       tab3.setContent(msgPane);

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