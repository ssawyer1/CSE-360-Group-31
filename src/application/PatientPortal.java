package application; // javaFX

import java.io.FileInputStream; 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.TableView;

import javafx.scene.text.*;
public class PatientPortal extends Main
{
	private Patient curPatient;
	
	protected Scene patientScene(Stage stage, Patient CurPatient) 
	{		
		curPatient = CurPatient; 
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
		
		//traverse through doctors messages
		for(Message m: curPatient.getDoctorMsg())
		{
//			if(m.getSendName().equalsIgnoreCase(curPatient.getFullName()) == false) //only displaying incoming messages
//			{
				//add message to table
				messageTable.getItems().add(m);
//			}
		}
		//traverse through nurse messages
		for(Message x: curPatient.getNurseMsgs())
		{
//			if(x.getSendName().equalsIgnoreCase(curPatient.getFullName()) == false) //only displaying incoming messages
//			{
				//add message to table
				messageTable.getItems().add(x);
//			}
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
		
		
		send.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				String senderName = to.getText(); //extracting patient name and message content
				String message = compose.getText();
				int sent = 0;
				if(senderName.equalsIgnoreCase(curPatient.getDoctorName()))
				{
					curPatient.addDoctorMsg(LocalDateTime.now(), message, "Patient", curPatient.getFullName());
					messageTable.getItems().add(curPatient.getDoctorMsg().get(curPatient.getDoctorMsg().size()-1));
					sent = 1;
				}
				else if(senderName.equalsIgnoreCase(curPatient.getNurseName()))
				{
					curPatient.addNurseMsg(LocalDateTime.now(), message, "Patient", curPatient.getFullName());
					messageTable.getItems().add(curPatient.getNurseMsgs().get(curPatient.getNurseMsgs().size()-1));
					sent = 1;
				}
				if(sent == 0)
				{	
					errorMsg.setFill(Color.DARKRED);
					errorMsg.setText("Doctor or Nurse not found");
				}
				else
				{
					errorMsg.setFill(Color.GREEN);
					errorMsg.setText("Message Sucessfully Sent");
					to.setText("Firstname Lastname");
					compose.setText("Message Content");
				}
			}
		});
		
	

		vbox.getChildren().addAll(inbox, messageTable, comp_msg, to_msg, to, compose, errorMsg);	

		//******************VISITS PANE*********************
		BorderPane vPane = new BorderPane();
		vPane.setPadding(new Insets(10, 10, 10, 10)); 
		vPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		
		Text visits = new Text("Patient Vists"); 
		visits.setFont(Font.font("Courier", FontWeight.BOLD, 30)); 
		BorderPane.setAlignment(visits, Pos.CENTER);
		BorderPane.setMargin(visits, new Insets(30, 0, 30, 0));
		vPane.setTop(visits); 
		
		TableView<Appointment> visitsTable = new TableView<Appointment>(); //create table
		BorderPane.setAlignment(visitsTable, Pos.CENTER);
		//visitsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		visitsTable.setMaxSize(550, 550);
		visitsTable.setMinSize(550, 550);
				
		//add columns to table
		TableColumn <Appointment, String> column1 = new TableColumn<>("Date");
		column1.setCellValueFactory(new PropertyValueFactory<>("date"));
		column1.setStyle("-fx-alignment: CENTER;");
		column1.prefWidthProperty().bind(visitsTable.widthProperty().multiply(0.2));
		column1.setResizable(false);
		visitsTable.getColumns().add(column1);
		
		TableColumn <Appointment, String> column2 = new TableColumn<>("Description");
		column2.setCellValueFactory(new PropertyValueFactory<>("reason"));
		column2.setStyle("-fx-alignment: CENTER;");
		column2.prefWidthProperty().bind(visitsTable.widthProperty().multiply(0.4));
		column2.setResizable(false);	
		visitsTable.getColumns().add(column2);
		
		TableColumn <Appointment, String> column3 = new TableColumn<>("Nurse Notes");
		column3.setCellValueFactory(new PropertyValueFactory<>("nurseNotes"));
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
		
		visitsTable.getSelectionModel().selectFirst();
		
		visitsTable.setOnMouseClicked(e -> {
			if (e.getClickCount() >= 1) {
				if (visitsTable.getSelectionModel().getSelectedItem() != null) 
				{
					vPane.setCenter(getInfo(visitsTable.getSelectionModel().getSelectedItem()));
				}
			}		
		});
		
		vPane.setLeft(visitsTable);
		if (visitsTable.getSelectionModel().getSelectedItem() != null) 
		{
			vPane.setCenter(getInfo(visitsTable.getSelectionModel().getSelectedItem()));
		}
		
	    //*********************INFORMATION PANE*********************************
		// create border pane
		BorderPane infoPane = new BorderPane();
		infoPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");"); // set background color
		
		// Title text 
		Text contact = new Text("Contact Information"); // create title
		contact.setFont(Font.font("Courier", FontWeight.BOLD, 30)); // set font
		infoPane.setAlignment(contact, Pos.CENTER); // set alignment to the center
		infoPane.setPadding(new Insets(30,0,0,0)); // pad
		infoPane.setTop(contact); // set title at top of border pane
		
		// create text area to hold information
		Text infoName = new Text("Name: " + curPatient.getFullName());
		infoName.setFont(Font.font("Courier", 20)); // set font
		
		Text infoPharm = new Text("Pharmacy: " + curPatient.getPharm()); // create
		infoPharm.setFont(Font.font("Courier", 20)); // set font
		
		Text infoPhone = new Text("Phone Number: " + curPatient.getPhoneNum()); // create
		infoPhone.setFont(Font.font("Courier", 20)); // set font
		
		Text infoEmail = new Text("Email: " + curPatient.getEmail()); // create
		infoEmail.setFont(Font.font("Courier", 20)); // set font
		
		// create edit contact title
		Text edit = new Text("Edit Contact Information: ");
		edit.setFont(Font.font("Courier", FontWeight.MEDIUM, 30)); // set font
		
		// create text areas for editable information
		
		Text nameQuery = new Text("Edit Name:");
		nameQuery.setFont(Font.font("Courier", 15)); // set font
		TextArea name = new TextArea();
		
		Text addrQuery = new Text("Edit Address:");
		addrQuery.setFont(Font.font("Courier", 15)); // set font
		TextArea address = new TextArea();
		
		Text phoneQuery = new Text("Edit Phone Number:");
		phoneQuery.setFont(Font.font("Courier", 15)); // set font
		TextArea mobile = new TextArea();
		
		Text emailQuery = new Text("Edit Email:");
		emailQuery.setFont(Font.font("Courier", 15)); // set font
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
		submit.setOnAction(new EventHandler<ActionEvent>() 
		{
			//check that button was pressed once
			public void handle(ActionEvent e)
			{
				//error catching to prevent repeats
				if(!name.getText().equalsIgnoreCase(""))
				{
					infoName.setText("Name: " + name.getText());
					String tempName = name.getText();
					String[] splitFullName = tempName.split("\\s+");
					if(splitFullName[0] != null)
					{
						curPatient.setFName(splitFullName[0]);
					}
					else
					{
						curPatient.setFName("");
					}
					if(splitFullName[1] != null)
					{
						curPatient.setLName(splitFullName[1]);
					}
					else
					{
						curPatient.setLName("");
					}
				}
				if(!address.getText().equalsIgnoreCase(""))
				{
					infoPharm.setText("Pharmacy: " + address.getText());
					curPatient.setPharm(address.getText());
				}
				if(!mobile.getText().equalsIgnoreCase(""))
				{
					infoPhone.setText("Phone Number: " + mobile.getText());
					curPatient.setPhoneNum(mobile.getText());
				}
				if(!email.getText().equalsIgnoreCase(""))
				{
					infoEmail.setText("Email: " + email.getText());
					curPatient.setEmail(email.getText());
				}
				//otherwise update the contact information				
				name.clear();
				address.clear();
				mobile.clear();
				email.clear();
			}
			
		});
		
		
		// create vbox to hold left information
		VBox subLeft = new VBox();
		subLeft.setSpacing(10); // spacing between objects in vbox
		subLeft.getChildren().addAll(infoName, infoPharm, infoPhone, infoEmail ,edit, nameQuery,name, addrQuery, address, phoneQuery, mobile, emailQuery, email, submit, error); // add objects
		//subLeft.setAlignment(Pos.TOP_CENTER); // align at center
		
		// create hbox to hold left information and right picture
		HBox infoBox = new HBox();
		infoBox.getChildren().add(subLeft); // add object
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

		stage.setOnCloseRequest(event ->
		{
			try {
				curPatient.save();
			} catch (IOException x) {
				// TODO Auto-generated catch block
				x.printStackTrace();
			}
		});
		
		return new Scene(box);
  }
	
	private TabPane getInfo(Appointment appt)
	{
		Tab history = new Tab("Appintment History");
		Tab prescrip = new Tab("Prescriptions");
		Tab immun = new Tab("Immunizations");		
		
		ScrollPane scrollAppts = new ScrollPane();
		scrollAppts.setStyle("-fx-background: white;"); // set background of vbox
		scrollAppts.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollAppts.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollAppts.setMaxSize(550,525);
		scrollAppts.setMinSize(550,525);
		scrollAppts.setPadding(new Insets(10,10,10,10));
		
		VBox holder = new VBox();
		//holder.setPadding(new Insets(20,20,20,20));
		holder.setSpacing(15);
		
		Text apptTitle = new Text("Appointment: " + appt.getDate());
		apptTitle.setFont(Font.font("Courier", 20));
		Text vitals = new Text("Vitals:");
		vitals.setFont(Font.font("Courier", 15));
		
		Text height = new Text("Height: " + appt.getHeight());
		height.setFont(Font.font("Courier", 15));
		Text Weight = new Text("Weight: " + appt.getWeight());
		Weight.setFont(Font.font("Courier", 15));
		Text BP = new Text("Blood Pressure: " + appt.getBP());
		BP.setFont(Font.font("Courier", 15));
		Text temp = new Text("Temperature: " + appt.getTemp());
		temp.setFont(Font.font("Courier", 15));
		
		GridPane grid = new GridPane();
		grid.setVgap(15);
		grid.setHgap(40);
		grid.add(height, 0, 0);
		grid.add(Weight, 0, 1);
		grid.add(BP, 1, 0);
		grid.add(temp, 1, 1);

		Text n_notes = new Text("Nurse Notes: ");
		n_notes.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea nnotes = new TextArea(appt.getNurseNotes());
		nnotes.setMinSize(500, 100);
		nnotes.setMaxSize(500, 100);
		nnotes.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		nnotes.setEditable(false);
		
		Text d_notes = new Text("Doctor Notes: ");
		d_notes.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea dnotes = new TextArea(appt.getDoctorNotes());
		dnotes.setMinSize(500, 100);
		dnotes.setMaxSize(500, 100);
		dnotes.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		dnotes.setEditable(false);
		
		Text meds = new Text("Prescribed Medication: ");
		meds.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea medsPrescribed = new TextArea();
		medsPrescribed.setEditable(false);
		medsPrescribed.setMinSize(500, 100);
		medsPrescribed.setMaxSize(500, 100);
		medsPrescribed.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		for(int i = 0; i < curPatient.getPrescriptions().size(); i++)
		{
			if(curPatient.getPrescriptions().get(i).getDate().equalsIgnoreCase(appt.getPrescipTime()))
			{
				medsPrescribed.appendText(curPatient.getPrescriptions().get(i).getType());
				medsPrescribed.appendText("\n\tDirections: " + curPatient.getPrescriptions().get(i).getDir());
				medsPrescribed.appendText("\n\tStop Date: " + curPatient.getPrescriptions().get(i).getStopDate() + "\n\n");
			}
		}
		
		Text vaxs = new Text("Vaccines Given: ");
		vaxs.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea vaxPrescribed = new TextArea();
		vaxPrescribed.setEditable(false);
		vaxPrescribed.setMinSize(500, 100);
		vaxPrescribed.setMaxSize(500, 100);
		vaxPrescribed.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		for(int i = 0; i < curPatient.getImmunizations().size(); i++)
		{
			if(curPatient.getImmunizations().get(i).getDate().equalsIgnoreCase(appt.getDate()))
			{
				vaxPrescribed.appendText(curPatient.getImmunizations().get(i).getType() + "\n");
			}
		}
		
		holder.getChildren().addAll(apptTitle, vitals, grid, n_notes, nnotes, d_notes, dnotes, meds, medsPrescribed, vaxs, vaxPrescribed);
		scrollAppts.setContent(holder);
		
		TabPane newTabs = new TabPane();
		newTabs.setMinSize(550, 550);
		newTabs.setMaxSize(550, 550);
		newTabs.getTabs().addAll(history, prescrip, immun);
		newTabs.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING); // set tab style to float
		newTabs.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // don't allow tab closing
		history.setContent(scrollAppts);
		prescrip.setContent(scrollPrescrip(curPatient.getPrescriptions()));
		immun.setContent(scrollImmun(curPatient.getImmunizations()));
		
		return newTabs;
	}
	
	private ScrollPane scrollPrescrip(ArrayList<Prescription> prescrips)
	{
		ScrollPane scrollP = new ScrollPane();
		scrollP.setStyle("-fx-background: white;"); // set background of vbox
		scrollP.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollP.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		VBox prescripHolder = new VBox();
		prescripHolder.setStyle("-fx-background-color: white;"); // set background of vbox
		prescripHolder.setPadding(new Insets(20, 20, 20, 20));
		prescripHolder.setSpacing(20);
		
		Text p = new Text("Prescriptions: ");
		prescripHolder.getChildren().add(p);
		
		for(int k = 0; k < prescrips.size(); k++)
		{
			VBox temp = new VBox();
			temp.setSpacing(15);
			
			Text date = new Text("\n\tDate Prescribed: " +  prescrips.get(k).getDate());
			Text type = new Text("\n\t\tPrescription Name: "+ prescrips.get(k).getType());
			Text dir = new Text("\n\t\tDirections: "+ prescrips.get(k).getDir());
			Text stopDate = new Text("\n\t\tStop Date: "+ prescrips.get(k).getStopDate());
			
			temp.getChildren().addAll(date, type, dir, stopDate);			
			prescripHolder.getChildren().add(temp);
		}
		
		scrollP.setContent(prescripHolder);
		return scrollP;
	}
	
	private ScrollPane scrollImmun(ArrayList<Immunization> shots)
	{
		ScrollPane scrollI = new ScrollPane();
		
		scrollI.setStyle("-fx-background: white;"); // set background of vbox
		scrollI.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollI.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		VBox immunHolder = new VBox();
		immunHolder.setStyle("-fx-background-color: white;"); // set background of vbox
		immunHolder.setPadding(new Insets(20, 20, 20, 20));
		immunHolder.setSpacing(20);
		
		Text i = new Text("Immunizations: ");
		immunHolder.getChildren().add(i);
		
		for(int k = 0; k < shots.size(); k++)
		{
			HBox temp = new HBox();
			temp.setSpacing(15);
			
			Text date = new Text("\n\tDate Taken: " +  shots.get(k).getDate());
			Text type = new Text("\n\t\tImmunization Name: "+ shots.get(k).getType());
			
			temp.getChildren().addAll(date, type);			
			immunHolder.getChildren().add(temp);
		}
		
		scrollI.setContent(immunHolder);
		return scrollI;
	}
	
	private void switchScenes(Stage stage)
	{
		try {
			curPatient.save();
		} catch (IOException x) {
			// TODO Auto-generated catch block
			x.printStackTrace();
		}
		HomeScreen home = new HomeScreen();
		Scene h = home.firstScreen(stage);
		stage.setScene(h);
	}
	
}