package application; // javaFX

import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.TableView;

import javafx.scene.text.*;
public class PatientPortal extends Main
{
	protected Scene patientScene(Stage stage, Patient curPatient) 
	{		

		//***********************MESSAGE PANE***********************
		BorderPane msgPane = new BorderPane();
		msgPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		msgPane.setPadding(new Insets(0, 50, 50, 50));
		
		VBox vbox = new VBox(8);
		msgPane.setCenter(vbox);
		
		Text msgPhy = new Text("Message Physician"); //Text on top
		msgPhy.setFont(Font.font("Courier", FontWeight.BOLD, 30)); 
		BorderPane.setAlignment(msgPhy, Pos.CENTER);
		BorderPane.setMargin(msgPhy, new Insets(40, 0, 45, 0));
		msgPane.setTop(msgPhy);
		
		Text inbox = new Text("Inbox"); //Text on top
		inbox.setFont(Font.font("Courier", FontWeight.MEDIUM, 30)); 
		
		//ListView<String> listview = new ListView<String>(); //Listview in middle
		//Message m_obj1 = new Message("Message here");
		//Message m_obj2 = new Message("Another message here");
		//ObservableList<String> msgList = FXCollections.observableArrayList(m_obj1.getMessage(), m_obj2.getMessage());
		//listview.setItems(msgList);
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
		
		//adding columns to table
		messageTable.getColumns().add(m_column1);
		messageTable.getColumns().add(m_column2);
		messageTable.getColumns().add(m_column3);
		
		//testing this example
		curPatient.addDoctorMsg(LocalDateTime.now(), curPatient.getDoctorName(), "doctor", "bob smith");
		curPatient.addNurseMsg(LocalDateTime.now(), "Sup", "nurse", "nancy smith");
		
		//traverse through doctors messages
		for(Message m: curPatient.getDoctorMsg())
		{
			if(m.getSendName().equalsIgnoreCase(curPatient.getFullName()) == false) //only displaying incoming messages
			{
				//add message to table
				messageTable.getItems().add(m);
			}
		}
		//traverse through nurse messages
		for(Message x: curPatient.getNurseMsgs())
		{
			if(x.getSendName().equalsIgnoreCase(curPatient.getFullName()) == false) //only displaying incoming messages
			{
				//add message to table
				messageTable.getItems().add(x);
			}
		}
		//sending messages section
		Text to_msg = new Text("To:"); //Text on top
		to_msg.setFont(Font.font("Courier", FontWeight.MEDIUM, 15)); 
		
		TextArea to = new TextArea();//Area to type in receiver's name
		to.setText("Firstname Lastname");
		
		Text comp_msg = new Text("Compose Message"); //Text on top
		comp_msg.setFont(Font.font("Courier", FontWeight.MEDIUM, 20)); 
		
		TextArea compose = new TextArea();
		compose.setText("New Message");
		
		BorderPane.setAlignment(messageTable, Pos.CENTER);
		BorderPane.setMargin(messageTable, new Insets(100, 50, 100, 50));
		
		//button to send message
		Button send = new Button("  Send  ");
		BorderPane.setAlignment(send, Pos.BOTTOM_LEFT);
		BorderPane.setMargin(send, new Insets(20, 0, 20, 0));
		msgPane.setBottom(send);
		
		//creating error text
		Text errorMsg = new Text();
		errorMsg.setFont(Font.font("Courier", 15));
		
		
		send.setOnMouseClicked(e -> 
		{
			if(e.getClickCount() >= 1)
			{
				String senderName = to.getText(); //extracting patient name and message content
				String message = compose.getText();
				int sent = 0;
				if(senderName.equalsIgnoreCase(curPatient.getDoctorName()))
				{
						curPatient.addDoctorMsg(LocalDateTime.now(), message, "Patient", senderName);
						sent = 1;
				}
				if(sent == 0)
				{	
					errorMsg.setFill(Color.DARKRED);
					errorMsg.setText("Doctor not found");
				}
				else
				{
					errorMsg.setFill(Color.GREEN);
					errorMsg.setText("Message Sucessfully Sent");
					to.setText("Firstname Lastname");
					compose.setText("Message Content");
					
				}
			}
		}
		);

		vbox.getChildren().addAll(inbox, messageTable, comp_msg, to_msg, to, compose, errorMsg);	

		//******************VISITS PANE*********************
		BorderPane vPane = new BorderPane();
		vPane.setPadding(new Insets(0, 50, 50, 50)); 
		vPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		
		Text visits = new Text("Patient Vists"); 
		visits.setFont(Font.font("Courier", FontWeight.BOLD, 30)); 
		BorderPane.setAlignment(visits, Pos.CENTER);
		BorderPane.setMargin(visits, new Insets(40, 0, 45, 0));
		vPane.setTop(visits); 
		
		TableView<Appointment> visitsTable = new TableView<Appointment>(); //create table
		BorderPane.setAlignment(visitsTable, Pos.CENTER);
		visitsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		//add columns to table
		TableColumn <Appointment, String> column1 = new TableColumn<>("Date");
		column1.setCellValueFactory(new PropertyValueFactory<>("date"));
		column1.setStyle("-fx-alignment: CENTER;");
		column1.prefWidthProperty().bind(visitsTable.widthProperty().multiply(0.2));
		column1.setResizable(false);
		visitsTable.getColumns().add(column1);
		
		//curPatient.addAppointment(LocalDateTime.now(),"x","y","c","9","9","9","9");
		/*ArrayList<Appointment> tempApt = curPatient.getAptts();
		ObservableList<Appointment> obsApt = FXCollections.<Appointment>observableArrayList();
		obsApt.addAll(tempApt);
		visitsTable.setItems(obsApt);*/
		
		TableColumn <Appointment, String> column2 = new TableColumn<>("Description");
		column2.setCellValueFactory(new PropertyValueFactory<>("reason"));
		column2.setStyle("-fx-alignment: CENTER;");
		column2.prefWidthProperty().bind(visitsTable.widthProperty().multiply(0.4));
		column2.setResizable(false);	
		visitsTable.getColumns().add(column2);
		
		TableColumn <Appointment, String> column3 = new TableColumn<>("Nurse Notes");
		column3.setCellValueFactory(new PropertyValueFactory<>("notes"));
		column3.setStyle("-fx-alignment: CENTER;");
		column3.prefWidthProperty().bind(visitsTable.widthProperty().multiply(0.4));
		column3.setResizable(false);	
		visitsTable.getColumns().add(column3);
		//ArrayList<Appointment> temp
		
		//add the patients previous appointment descriptions to the table
		for (Appointment a: curPatient.getAptts())
		{
			if(a != null)
			{
				visitsTable.getItems().add(a);
			}
		}
		//add immunizations to the table
		/*ArrayList<Prescription> tempPre = curPatient.getPrescription();
		ObservableList<Prescription> obsPre = FXCollections.<Prescription>observableArrayList();
		obsPre.addAll(tempPre);
		visitsTable.setItems(obsPre);*/
		
		//visitsTable.getItems().add(new Appointment("Date here", "Appointment description here", "Patient Notes Here"));
		//visitsTable.getItems().add(new Appointment("Another date here", "Another appointment description here", "Additonal Patient Notes Here"));
		
		vPane.setCenter(visitsTable);
		
	    //*********************INFORMATION PANE*********************************
		// create border pane
		BorderPane infoPane = new BorderPane();
		infoPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");"); // set background color
		
		// Title text 
		Text contact = new Text("Contact Information"); // create title
		contact.setFont(Font.font("Courier", FontWeight.BOLD, 30)); // set font
		infoPane.setAlignment(contact, Pos.CENTER); // set alignment to the center
		infoPane.setPadding(new Insets(40,0,0,0)); // pad
		infoPane.setTop(contact); // set title at top of border pane
		
		// create text area to hold information
		TextArea info = new TextArea(); // create
		info.setFont(Font.font("Courier", 25)); // set font
		String user = curPatient.getUsername();
		curPatient.load("Patients/" + user + ".txt");
		info.setText(curPatient.getFullName() + "\nAddress: " + curPatient.getPharm() + "\nMobile Phone: " + curPatient.getPhoneNum() + "\nEmail: " + curPatient.getEmail()); // set content
		info.setEditable(false); // disallow edit
		info.setMaxSize(550, 200); // set size
		
		// create edit contact title
		Text edit = new Text("Edit Contact Information");
		edit.setFont(Font.font("Courier", FontWeight.MEDIUM, 20)); // set font
		
		// create text areas for editable information
		TextArea name = new TextArea();
		TextArea address = new TextArea();
		TextArea mobile = new TextArea();
		TextArea email = new TextArea();
		name.setMaxSize(400, 20);
		address.setMaxSize(400, 20);
		mobile.setMaxSize(400, 20);
		email.setMaxSize(400, 20);
		
		//creating error text
		Text error = new Text();
		error.setFont(Font.font("Courier", 15));
		error.setFill(Color.DARKRED);
		
		// create button to submit information
		Button submit = new Button("Submit");
		submit.setMaxSize(100, 20); // set size
		submit.setStyle("-fx-background-radius: 5"); // round button edges
		//if button is clicked update the patient info
		submit.setOnAction(e-> {
			if(update(name, address, mobile, email, error, curPatient, info))
			{
				String tempName = name.getText();
				String[] splitFullName = tempName.split("\\s+");
				curPatient.setFName(splitFullName[0]);
				curPatient.setLName(splitFullName[1]);
				curPatient.setPharm(address.getText());
				curPatient.setPhoneNum(mobile.getText());
				curPatient.setEmail(email.getText());
				//trying to save updated info but it didn't work, will come back
				try {
					curPatient.save();
				} catch (IOException x) {
					// TODO Auto-generated catch block
					x.printStackTrace();
				}
			}
			
		});
		
		
		// create vbox to hold left information
		VBox subLeft = new VBox();
		subLeft.setSpacing(20); // spacing between objects in vbox
		subLeft.getChildren().addAll(info,edit, name, address, mobile, email, submit, error); // add objects
		subLeft.setAlignment(Pos.TOP_CENTER); // align at center
		
		// create hbox to hold left information and right picture
		HBox infoBox = new HBox();
		infoBox.getChildren().add(subLeft); // add object
	    HBox.setHgrow(info, Priority.ALWAYS); 
	    infoBox.setSpacing(50); // set spacing between Hbox objects
	    infoBox.setStyle("-fx-padding: 50;"); // set background color
	    
		// create image
	    FileInputStream inputstream; 
		Image image; 
		try 
		{
			inputstream = new FileInputStream("C:\\Users\\Maya\\Data\\System\\Installations\\Eclipse\\javaFX\\avatar2.png"); // file location 
			image = new Image(inputstream);  // image
			ImageView view = new ImageView(image); // view image
			view.setFitHeight(475);  // set size
			view.setFitWidth(450); // set size
			infoBox.getChildren().add(view);  // add to infoBox
			infoBox.setAlignment(Pos.TOP_CENTER); // set at center
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
	    
		// add infoBox to center of border pane
		infoPane.setCenter(infoBox);
		
	    //***************************Tabs (used to be at the beginning)**********************************
		Tab portal = new Tab("   Patient Portal");
		Tab tab1 = new Tab("\t\t\t\t   Contact Information"); // tabbed to center	
		Tab tab2 = new Tab("\t\t\t\t\tPatient Visits"); // tabbed to center
		Tab tab3 = new Tab("\t\t\t\t\t    Messages"); // tabbed to center
		portal.setDisable(true); // disable portal table 
		tab1.setContent(infoPane); // set pane
		tab2.setContent(vPane); // set pane
		tab3.setContent(msgPane); // set pane
		    
		    // format tabs
	    tab1.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; "); // round tab1
	    tab2.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0;"); // round tab2
	    tab3.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; "); // round tab3
		portal.setStyle("-fx-pref-width: 120; -fx-pref-height: 30; -fx-background-color: rgb(" + 129 + "," + 138 + ", " + 151 + "); -fx-opacity: 1; -fx-text-base-color: white; -fx-font-weight: bold");
		    
		   // create tab pane
		TabPane tabPane = new TabPane();
		tabPane.getTabs().addAll(portal, tab1, tab2, tab3); // add children
		tabPane.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING); // set tab style to float
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // don't allow tab closing
			
		    // layout the stage.
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
	//method to update the information displayed
	private boolean update(TextArea name, TextArea address, TextArea mobile, TextArea email, Text error, Patient patient, TextArea info)
	{
		boolean test = false;
		if(name.getText().equals(patient.getFullName()))
		{
			error.setText("Name is already the same");
		}
		else if(address.getText().equals(patient.getPharm()))
		{
			error.setText("Address is already the same");
		}
		else if(mobile.getText().equals(patient.getPhoneNum()))
		{
			error.setText("Phone number is already the same");
		}
		else if(email.getText().equals(patient.getEmail()))
		{
			error.setText("Email is already the same");
		}
		else
		{
			info.setText(name.getText() + "\nAddress: " + address.getText() + "\nMobile Phone: " + mobile.getText() + "\nEmail: " + email.getText()); // set content
			test = true;
		}
		return test;
	}
}