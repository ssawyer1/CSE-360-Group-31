package application;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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

	private Doctor curDoctor;
	private BorderPane bp;
	
	protected Scene doctorScene(Stage stage, Doctor CurDoctor) 
	{
		curDoctor = CurDoctor;
		
	   
	   
		// *************START OF THE TABS CREATION ************
		Tab portal = new Tab("   Doctor Portal");
	    Tab tab1 = new Tab("\t\t\t\t   Patients"); // tabbed to center	
	    Tab tab2 = new Tab("\t\t\t\t\tMessages"); // tabbed to center
	    portal.setDisable(true); // disable portal table 
	    tab1.setContent(patientPane()); // set pane
		tab2.setContent(doctorMsgPane()); // set pane

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
	
		stage.setOnCloseRequest(event ->
		{
			try
			{
				curDoctor.save();
				for(Patient p : curDoctor.getPatients())
				{
					p.save();
				}
			}
			catch (IOException x)
			{
				x.printStackTrace();
			}
		});
		
	    return new Scene(box);
  }
	
	private BorderPane doctorMsgPane()
	{
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
//				if(m.getSendName().equalsIgnoreCase(curDoctor.getFullName()) == false) //only displaying incoming messages(sendName != Doctor name)
//				{
					messageTable.getItems().add(m);
//				}
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
								messageTable.getItems().add(p.getDoctorMsg().get(p.getDoctorMsg().size()-1));
								sent = 1;
								 try 
			    		    	    {
			    		    	    	p.save();
			    		    	    } 
			    		    	    catch (IOException x) 
			    		    	    {
			    		    	    	// TODO Auto-generated catch block
			    		    	    	x.printStackTrace();
			    		    	    }
							}
					}
				if(sent == 0)
				{	
					error.setFill(Color.DARKRED);
					error.setText("Patient not found");
				}
				else
				{
					error.setFill(Color.DARKGREEN);
					error.setText("Message Sent");
					to.setText("Firstname Lastname");
					compose.setText("New Message");
				}
				
			}		
		});

		vbox.getChildren().addAll(inbox, messageTable, comp_msg, to_msg, to,comp_msg1, compose, error);	
		return msgPane;
	}
	
	private BorderPane patientPane()
	{
		//**************** Patient Tab *****************
		LocalDateTime curTime = LocalDateTime.now();
		bp = new BorderPane();
		bp.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		GridPane gp = new GridPane();
		gp.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		
		// Formatting for table that goes on the patientInfo tab, this table takes objects from the Patient class
		TableView<Patient> patientTable = new TableView<Patient>(); //create table
		patientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		patientTable.setMaxSize(200, 650);
		patientTable.setMinSize(200, 650);
		
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
		patientTable.getSelectionModel().selectFirst();
		
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
		
		pInfo.setFont(Font.font("Courier", FontWeight.BOLD, 20)); 
		email.setFont(Font.font("Courier", 15));
		number.setFont(Font.font("Courier", 15));
		birth.setFont(Font.font("Courier", 15));
		gender.setFont(Font.font("Courier", 15));
		inCo.setFont(Font.font("Courier", 15));
		inNum.setFont(Font.font("Courier", 15));
		nurse.setFont(Font.font("Courier", 15));
		
		Text newEmail = new Text();
		Text newNumber = new Text();
		Text newBirth = new Text();
		Text newGender = new Text();
		Text newInCo = new Text();
		Text newInNum = new Text();
		Text newNurse = new Text();
		
		newEmail.setFont(Font.font("Courier", 15));
		newNumber.setFont(Font.font("Courier", 15));
		newBirth.setFont(Font.font("Courier", 15));
		newGender.setFont(Font.font("Courier", 15));
		newInCo.setFont(Font.font("Courier", 15));
		newInNum.setFont(Font.font("Courier", 15));
		newNurse.setFont(Font.font("Courier", 15));
		
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
		details.setMaxWidth(325);
		details.setMinWidth(325);
		
				
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
					bp.setCenter(displayPatientInfo(p));
					if(patientTable.getSelectionModel().getSelectedItem() != null)
					{
						bp.setCenter(displayPatientInfo(p));
					}
				}				
			}		
		});
		
		bp.setLeft(root);		
		if(patientTable.getSelectionModel().getSelectedItem() != null)
		{
			bp.setCenter(displayPatientInfo(patientTable.getSelectionModel().getSelectedItem()));
		}
		bp.setRight(details);
		return bp;
	}
	
	private TabPane displayPatientInfo(Patient patientSelected)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
		String now = dtf.format(LocalDateTime.now());
		
		Tab history = new Tab("Appintment History");
		Tab prescrip = new Tab("Prescriptions");
		Tab immun = new Tab("Immunizations");
		Tab pend = new Tab("Pending Appointments");
		
		//addNew.setContent(createApptPane(patientSelected));
		history.setContent(appointmentTab(patientSelected, patientSelected.getAptts()));
		prescrip.setContent(scrollPrescrip(patientSelected, patientSelected.getPrescriptions()));
		immun.setContent(scrollImmun(patientSelected, patientSelected.getImmunizations()));
		if(patientSelected.getAptts().size() > 0)
		{
			if(patientSelected.getAptts().get(patientSelected.getAptts().size()-1).getDate().contains(now))
			{
				if(patientSelected.getAptts().get(patientSelected.getAptts().size()-1).getDoctorNotes().equalsIgnoreCase(" "))
				{
					pend.setContent(pendAppt(patientSelected, patientSelected.getAptts().get(patientSelected.getAptts().size()-1)));
				}
			}	
		}
		
		TabPane appTab = new TabPane();
		appTab.setMinSize(650, 650);
		appTab.setMaxSize(650, 650);
		appTab.getTabs().addAll(history, prescrip, immun, pend);
		appTab.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING); // set tab style to float
		appTab.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // don't allow tab closing
		
		return appTab;
	}
	
	private ScrollPane appointmentTab(Patient patientSelected, ArrayList<Appointment> appts)
	{
		ScrollPane scrollAppts = new ScrollPane();
		scrollAppts.setStyle("-fx-background: white;"); // set background of vbox
		//scrollAppts.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollAppts.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		VBox apptHolder = new VBox();
		apptHolder.setStyle("-fx-background-color: white;"); // set background of vbox
		apptHolder.setPadding(new Insets(20, 20, 20, 40));
		apptHolder.setSpacing(15);
		
		for(int k = 0; k < appts.size(); k++)
		{
			apptHolder.getChildren().add(newAppt(patientSelected, appts.get(k)));
		}
		
		scrollAppts.setContent(apptHolder);
		return scrollAppts;
	}
	
	private VBox newAppt(Patient patientSelected, Appointment appt)
	{
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
		nnotes.setMinSize(550, 100);
		nnotes.setMaxSize(550, 100);
		nnotes.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		nnotes.setEditable(false);
		
		Text d_notes = new Text("Doctor Notes: ");
		d_notes.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea dnotes = new TextArea(appt.getDoctorNotes());
		dnotes.setMinSize(550, 100);
		dnotes.setMaxSize(550, 100);
		dnotes.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		dnotes.setEditable(false);
		
		Text meds = new Text("Prescribed Medication: ");
		meds.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea medsPrescribed = new TextArea();
		medsPrescribed.setEditable(false);
		medsPrescribed.setMinSize(550, 100);
		medsPrescribed.setMaxSize(550, 100);
		medsPrescribed.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		for(int i = 0; i < patientSelected.getPrescriptions().size(); i++)
		{
			if(patientSelected.getPrescriptions().get(i).getDate().equalsIgnoreCase(appt.getPrescipTime()))
			{
				medsPrescribed.appendText(patientSelected.getPrescriptions().get(i).getType());
				medsPrescribed.appendText("\n\tDirections: " + patientSelected.getPrescriptions().get(i).getDir());
				medsPrescribed.appendText("\n\tStop Date: " + patientSelected.getPrescriptions().get(i).getStopDate() + "\n\n");
			}
			
		}
		
		Text vaxs = new Text("Vaccines Given: ");
		vaxs.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea vaxPrescribed = new TextArea();
		vaxPrescribed.setEditable(false);
		vaxPrescribed.setMinSize(550, 100);
		vaxPrescribed.setMaxSize(550, 100);
		vaxPrescribed.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		for(int i = 0; i < patientSelected.getImmunizations().size(); i++)
		{
			if(patientSelected.getImmunizations().get(i).getDate().equalsIgnoreCase(appt.getDate()))
			{
				vaxPrescribed.appendText(patientSelected.getImmunizations().get(i).getType() + "\n");
			}
		}
		
		holder.getChildren().addAll(apptTitle, vitals, grid, n_notes, nnotes, d_notes, dnotes, meds, medsPrescribed, vaxs, vaxPrescribed);
		return holder;
	}
	
	private ScrollPane scrollPrescrip(Patient patientSelected, ArrayList<Prescription> prescrips)
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
	
	private ScrollPane scrollImmun(Patient patientSelected, ArrayList<Immunization> shots)
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
	
	private ScrollPane pendAppt(Patient patientSelected, Appointment appt)
	{
		ScrollPane scrollA = new ScrollPane();
		scrollA.setStyle("-fx-background: white;"); // set background of vbox
		scrollA.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollA.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		scrollA.setPadding(new Insets(20,20,20,40));
		
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
		nnotes.setMinSize(550, 100);
		nnotes.setMaxSize(550, 100);
		nnotes.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		nnotes.setEditable(false);
		
		Text d_notes = new Text("Doctor Notes: ");
		d_notes.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea dnotes = new TextArea(appt.getDoctorNotes());
		dnotes.setMinSize(550, 100);
		dnotes.setMaxSize(550, 100);
		dnotes.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		dnotes.setEditable(true);
			
		
		Text meds = new Text("Prescribed Medications (comma separated): ");
		meds.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea medsPrescribed = new TextArea();
		medsPrescribed.setEditable(true);
		medsPrescribed.setMinSize(550, 25);
		medsPrescribed.setMaxSize(550, 25);
		medsPrescribed.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		Text medsDir = new Text("Directions: ");
		medsDir.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea medsDirPrescribed = new TextArea();
		medsDirPrescribed.setEditable(true);
		medsDirPrescribed.setMinSize(550, 50);
		medsDirPrescribed.setMaxSize(550, 50);
		medsDirPrescribed.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		Text medsStop = new Text("Stop Date: ");
		medsStop.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea medsStopDirPrescribed = new TextArea();
		medsStopDirPrescribed.setEditable(true);
		medsStopDirPrescribed.setMinSize(550, 25);
		medsStopDirPrescribed.setMaxSize(550, 25);
		medsStopDirPrescribed.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		
		Button sendToPharm = new Button("Send to Pharmacy");
		sendToPharm.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
				appt.setPrescripDate(dtf.format( LocalDateTime.now()));
				patientSelected.addPrescription(medsPrescribed.getText(), LocalDateTime.now(), medsDirPrescribed.getText(), medsStopDirPrescribed.getText());
			}
			
		});
		
		Button submitNotes = new Button("Save notes");
		submitNotes.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				appt.setDocNotes(dnotes.getText());
				bp.setCenter(displayPatientInfo(patientSelected));
			}
		});
		
		
		Text vaxs = new Text("Vaccines Given: ");
		vaxs.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		TextArea vaxPrescribed = new TextArea();
		vaxPrescribed.setEditable(false);
		vaxPrescribed.setMinSize(550, 100);
		vaxPrescribed.setMaxSize(550, 100);
		vaxPrescribed.setFont(Font.font("Courier", FontWeight.MEDIUM, 15));
		for(int i = 0; i < patientSelected.getImmunizations().size(); i++)
		{
			if(patientSelected.getImmunizations().get(i).getDate().equalsIgnoreCase(appt.getDate()))
			{
				vaxPrescribed.appendText(patientSelected.getImmunizations().get(i).getType() + "\n");
			}
		}
		
		holder.getChildren().addAll(apptTitle, vitals, grid, n_notes, nnotes,  vaxs, vaxPrescribed, d_notes, dnotes, meds, medsPrescribed, medsDir, medsDirPrescribed, medsStop,medsStopDirPrescribed, sendToPharm, submitNotes );
		scrollA.setContent(holder);
		
		return scrollA;
	}
	
	private void switchScenes(Stage stage)
	{
		try
		{
			curDoctor.save();
			for(Patient p : curDoctor.getPatients())
			{
				p.save();
			}
		}
		catch (IOException x)
		{
			x.printStackTrace();
		}
		
		HomeScreen home = new HomeScreen();
		Scene h = home.firstScreen(stage);
		stage.setScene(h);
	}
}