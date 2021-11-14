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
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NursePortal extends Main{

	protected Scene NurseScene(Stage stage, Nurse curNurse) {
    
		//**************** Patient Tab *****************
	/*	BorderPane patientPane = new BorderPane();
		patientPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		patientPane.setPadding(new Insets(0, 50, 50, 50)); */
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
	//	BorderPane.setAlignment(patientTable, Pos.CENTER);
		patientTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		TableColumn <Patient, String> p_column1 = new TableColumn<>("Name");
		p_column1.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		p_column1.setStyle("-fx-alignment: CENTER;");
		patientTable.getColumns().add(p_column1);
		
		TableColumn <Patient, String> p_column2 = new TableColumn<>("Prescriptions");
		p_column2.setCellValueFactory(new PropertyValueFactory<>("prescriptions"));
		
		p_column2.setStyle("-fx-alignment: CENTER;");
		patientTable.getColumns().add(p_column2);
		
		TableColumn <Patient, String> p_column3 = new TableColumn<>("Weight");
		p_column3.setCellValueFactory(new PropertyValueFactory<>("weight"));
		
		p_column3.setStyle("-fx-alignment: CENTER;");	
		patientTable.getColumns().add(p_column3);
		
		TableColumn <Patient, String> p_column4 = new TableColumn<>("Height");
		p_column4.setCellValueFactory(new PropertyValueFactory<>("height"));
		
		p_column4.setStyle("-fx-alignment: CENTER;");	
		patientTable.getColumns().add(p_column4);
		
		TableColumn <Patient, String> p_column5 = new TableColumn<>("Blood Pressure");
		p_column5.setCellValueFactory(new PropertyValueFactory<>("bloodPressure"));
		
		p_column5.setStyle("-fx-alignment: CENTER;");	
		patientTable.getColumns().add(p_column5);
		
		TableColumn <Patient, String> p_column6 = new TableColumn<>("Body Temperature");
		p_column6.setCellValueFactory(new PropertyValueFactory<>("bodyTemp"));
		
		p_column6.setStyle("-fx-alignment: CENTER;");	
		patientTable.getColumns().add(p_column6);
		
		TableColumn <Patient, String> p_column7 = new TableColumn<>("Age");
		p_column7.setCellValueFactory(new PropertyValueFactory<>("age"));
		
		p_column7.setStyle("-fx-alignment: CENTER;");
		patientTable.getColumns().add(p_column7);
		
		//patientTable.getItems().add(new Patient("Ricky Gervais", "Hydrocodone", "69 lbs", "5 ft 10 inches", "120/80", "97 f", "45 years"));
		//patientTable.getItems().add(new Patient("Rick James", "Adderall", "2 lbs", "5 ft 7 inches", "1210/80", "99 f", "60 years"));
		
		patientInfo.setContent(patientTable);
		
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
		a_column3.setCellValueFactory(new PropertyValueFactory<>("notes"));
		a_column3.prefWidthProperty().bind(apptTable.widthProperty().multiply(0.6));
		a_column3.setStyle("-fx-alignment: center-left;");
		apptTable.getColumns().add(a_column3);
		
		//apptTable.getItems().add(new Appointment("11/11/2011", "Knee Issues", "Knees weak"));
		
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
		
		ListView<String> listview = new ListView<String>(); //Listview in middle
		//Message m_obj1 = new Message("Message here");
		//Message m_obj2 = new Message("Another message here");
		//ObservableList<String> msgList = FXCollections.observableArrayList(m_obj1.getMessage(), m_obj2.getMessage());
		//listview.setItems(msgList);
		
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
		// **********************Logout Pane*********************

		BorderPane logOutPane = new BorderPane();
		logOutPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		Button logOut = new Button("Log Out");
		logOutPane.setCenter(logOut);
		
		
	   
		
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
		tabPane.getTabs().addAll(portal, tab1, tab2, tab3); // add children
		tabPane.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING); // set tab style to float
		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // don't allow tab closing
			  
		Button logout = new Button("Logout");
		logout.setOnAction(e->switchScenes(stage));
		
		VBox box = new VBox(10); // create vbox 
		box.getChildren().addAll(logout,tabPane); // add tab pane to vbox
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