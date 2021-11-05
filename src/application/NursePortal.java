package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NursePortal extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		// set stage size
		stage.setHeight(900);
		stage.setWidth(1300);
		
		// create tabs and initialize panes
		Tab portal = new Tab("   Nurse Portal");
	    Tab tab1 = new Tab("\t\t\t\t   Patient"); // tabbed to center	
	    Tab tab2 = new Tab("\t\t\t\t\tDoctor's Messages"); // tabbed to center
	    Tab tab3 = new Tab("\t\t\t\t\t    Logout"); // tabbed to center
	    portal.setDisable(true); // disable portal table 
	    tab1.setContent(patientPane()); // set pane
	    tab2.setContent(messagePane()); // set pane
	    tab3.setContent(logoutPane()); // set pane
	    
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
	    Scene scene = new Scene(box);
	    stage.setScene(scene);
	    stage.show();
	}
	
	private TabPane patientPane()
	{
		TabPane patientPane = new TabPane();

		Tab nurseTab1 = new Tab("Patient History");
		Tab nurseTab2 = new Tab("Perscription History");
		
		patientPane.getTabs().addAll(nurseTab1, nurseTab2);
		
		return patientPane;
	}
	private Pane messagePane() //the article I am referencing here: http://tutorials.jenkov.com/javafx/tableview.html
	{	
		BorderPane msgPane = new BorderPane();
		msgPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		msgPane.setPadding(new Insets(0, 50, 50, 50));
		
		VBox vbox = new VBox(8);
		msgPane.setCenter(vbox);
		
		Text msgPhy = new Text("Messages from Patient"); //Text on top
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
		
		return msgPane;
	}
	private Pane logoutPane()
	{
		BorderPane logoutPane = new BorderPane();
		logoutPane.setPadding(new Insets(0, 50, 50, 50)); 
		logoutPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		
		Text logout = new Text("Logout"); 
		logout.setFont(Font.font("Courier", FontWeight.BOLD, 30)); 
		BorderPane.setAlignment(logout, Pos.CENTER);
		BorderPane.setMargin(logout, new Insets(40, 0, 45, 0));
		logoutPane.setTop(logout);
		
		Button send = new Button("  Logout  ");
		BorderPane.setAlignment(send, Pos.CENTER);
		BorderPane.setMargin(send, new Insets(20, 0, 20, 0));
		logoutPane.setBottom(send);
		
		return logoutPane;
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
	

