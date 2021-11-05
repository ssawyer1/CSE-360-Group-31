package application;

import java.io.FileInputStream; 
import java.io.FileNotFoundException;
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
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.control.TableView;

import javafx.scene.text.*;
public class PatientPortal extends Main{
	
	protected Scene patientScene() {		

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
		
		ListView<String> listview = new ListView<String>(); //Listview in middle
		Message m_obj1 = new Message("Message here");
		Message m_obj2 = new Message("Another message here");
		ObservableList<String> msgList = FXCollections.observableArrayList(m_obj1.getMessage(), m_obj2.getMessage());
		listview.setItems(msgList);
		
		Text comp_msg = new Text("Compose Message"); //Text on top
		comp_msg.setFont(Font.font("Courier", FontWeight.MEDIUM, 20)); 
		
		TextArea compose = new TextArea();
		compose.setText("New Message");
		
		BorderPane.setAlignment(listview, Pos.CENTER);
		BorderPane.setMargin(listview, new Insets(100, 50, 100, 50));
		
		Button send = new Button("  Send  ");
		BorderPane.setAlignment(send, Pos.BOTTOM_LEFT);
		BorderPane.setMargin(send, new Insets(20, 0, 20, 0));
		msgPane.setBottom(send);

		vbox.getChildren().addAll(inbox, listview, comp_msg, compose);	

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
		column1.prefWidthProperty().bind(visitsTable.widthProperty().multiply(0.3));
		column1.setResizable(false);
		visitsTable.getColumns().add(column1);
		
		TableColumn <Appointment, String> column2 = new TableColumn<>("Description");
		column2.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		column2.setStyle("-fx-alignment: CENTER;");
		column2.prefWidthProperty().bind(visitsTable.widthProperty().multiply(0.7));
		column2.setResizable(false);	
		visitsTable.getColumns().add(column2);
		
		visitsTable.getItems().add(new Appointment("Date here", "Appointment description here"));
		visitsTable.getItems().add(new Appointment("Another date here", "Another appointment description here"));
		
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
		info.setText("John Doe\nAddress: 1234 W Loma Lane\nMobile Phone: 623-908-0456\nHome Phone: 623-657-0223\nEmail: username@gmail.com"); // set content
		info.setEditable(false); // disallow edit
		info.setMaxSize(550, 200); // set size
		
		// create edit contact title
		Text edit = new Text("Edit Contact Information");
		edit.setFont(Font.font("Courier", FontWeight.MEDIUM, 20)); // set font
		
		// create text areas for editable information
		TextArea address = new TextArea();
		TextArea mobile = new TextArea();
		TextArea home = new TextArea();
		TextArea email = new TextArea();
		address.setMaxSize(400, 20);
		mobile.setMaxSize(400, 20);
		home.setMaxSize(400, 20);
		email.setMaxSize(400, 20);
		
		// create button to submit information
		Button submit = new Button("Submit");
		submit.setMaxSize(100, 20); // set size
		submit.setStyle("-fx-background-radius: 5"); // round button edges
		
		// create vbox to hold left information
		VBox subLeft = new VBox();
		subLeft.setSpacing(20); // spacing between objects in vbox
		subLeft.getChildren().addAll(info,edit, address, mobile, home, email, submit); // add objects
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
			inputstream = new FileInputStream("C:\\Users\\Sam Sawyer\\OneDrive\\Documents\\College Docs\\_Junior Year 2021\\CSE 360\\avatar2.png"); // file location 
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
		VBox box = new VBox(10); // create vbox 
	    box.getChildren().add(tabPane); // add tab pane to vbox
		VBox.setVgrow(tabPane, Priority.ALWAYS);
		box.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + "); -fx-padding: 40;"); // set background of vbox
		
		return new Scene(box);
  }
}

