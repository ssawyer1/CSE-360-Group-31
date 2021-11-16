package application;

import java.time.LocalDateTime;

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
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
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

public class DoctorPortal extends Main{

	protected Scene doctorScene(Stage stage, Doctor curDoctor) {
    
		//**************** Patient Tab *****************
		BorderPane bp = new BorderPane();
		GridPane gp = new GridPane();
		gp.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		gp.setPadding(new Insets(0, 50, 50, 50)); 
		
		Tab patientInfo = new Tab("\t\t\t\t        Patient Info");
		Tab appointmentHistory= new Tab("\t\t\t\tAppointment History");
		TabPane patientPane = new TabPane();
		patientPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		patientPane.getTabs().addAll(patientInfo, appointmentHistory); // add children
		patientPane.getStyleClass().add(patientPane.STYLE_CLASS_FLOATING); // set tab style to float
		patientPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // don't allow tab closing
		patientInfo.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; "); // round tab1		
		appointmentHistory.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; ");
		
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
		
		for(Patient p : curDoctor.getPatients())
		{
			patientTable.getItems().add(p);
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
		
		ListView<String> prescriptionList = new ListView<String>();
		
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
		bp.setLeft(pBox);
		BorderPane.setAlignment(details, Pos.CENTER_LEFT);
		bp.setCenter(details);
		bp.setRight(prescriptionList);
		patientInfo.setContent(bp);
		
		//************Appointment History Tab**************
		
		TableView <Appointment> apptTable = new TableView <Appointment>();
		
		TableColumn <Appointment, String> a_column1 = new TableColumn<>("Appointment Date");
		a_column1.setCellValueFactory(new PropertyValueFactory<>("date"));
		a_column1.prefWidthProperty().bind(apptTable.widthProperty().multiply(0.2));
		a_column1.setStyle("-fx-alignment: CENTER;");
		apptTable.getColumns().add(a_column1);
		
		TableColumn <Appointment, String> a_column2 = new TableColumn<>("Visit Reason");
		a_column2.setCellValueFactory(new PropertyValueFactory<>("reason"));
		a_column2.prefWidthProperty().bind(apptTable.widthProperty().multiply(0.2));
		a_column2.setStyle("-fx-alignment: CENTER;");
		apptTable.getColumns().add(a_column2);
		
		TableColumn <Appointment, String> a_column3 = new TableColumn<>("Notes");
		a_column3.setCellValueFactory(new PropertyValueFactory<>("doctorNotes"));
		a_column3.prefWidthProperty().bind(apptTable.widthProperty().multiply(0.6));
		a_column3.setStyle("-fx-alignment: center-left;");
		apptTable.getColumns().add(a_column3);
		
		for(Patient p: curDoctor.getPatients())
		{
			for (Appointment a: p.getAptts())
			{
				apptTable.getItems().add(a);
			}
		}

		appointmentHistory.setContent(apptTable);
		
	    //****************MESSAGE TAB *********************
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
		
		for(Patient p : curDoctor.getPatients())//iterating through patients and their messages to doctor
		{
			for(Message m: p.getDoctorMsg())
			{
				if(m.getSendName().equalsIgnoreCase(curDoctor.getFullName()) == false) //only displaying incoming messages(sendName != Doctor name)
				{
					messageTable.getItems().add(m);
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
				String senderName = curDoctor.getFullName(); //getting doctor name
				String recieverName = to.getText(); //extracting patient name
				String message = compose.getText(); //message content
				LocalDateTime now = LocalDateTime.now(); //getting date of message
				int sent = 0;
				
				for(Patient p : curDoctor.getPatients())//checking if a patient of the given name exists
					{
						if(recieverName.equalsIgnoreCase(p.getFullName()))
							{
								p.addDoctorMsg(now, message, "Doctor", senderName);
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
		Tab portal = new Tab("   Doctor Portal");
	    Tab tab1 = new Tab("\t\t\t\t   Patients"); // tabbed to center	
	    Tab tab2 = new Tab("\t\t\t\t\tMessages"); // tabbed to center
		Tab tab3 = new Tab("\t\t\t\t\t    Logout"); // tabbed to center
	    portal.setDisable(true); // disable portal table 
	    tab1.setContent(patientPane); // set pane
		tab2.setContent(msgPane); // set pane

		tab1.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; "); // round tab1
		tab2.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0;"); // round tab2
		portal.setStyle("-fx-pref-width: 120; -fx-pref-height: 30; -fx-background-color: rgb(" + 129 + "," + 138 + ", " + 151 + "); -fx-opacity: 1; -fx-text-base-color: white; -fx-font-weight: bold");
			    
		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(portal, tab1, tab2); // add children
		tabPane.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING); // set tab style to float
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // don't allow tab closing
			  
		Button logout = new Button("Logout");
		logout.setOnAction(e->switchScenes(stage));
		
		VBox box = new VBox(10); // create vbox 
		box.getChildren().addAll(logout,tabPane); // add tab pane to vbox
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