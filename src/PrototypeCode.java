package javaFX;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

import javafx.scene.text.*;
public class PrototypeCode extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		// set stage size
		stage.setHeight(900);
		stage.setWidth(1300);
		
		// create tabs and initialize panes
		Tab portal = new Tab("Patient Portal");
	    Tab tab1 = new Tab("\t\t\t\t   Contact Information"); // tabbed to center	
	    Tab tab2 = new Tab("\t\t\t\t\tPatient Visits"); // tabbed to center
	    Tab tab3 = new Tab("\t\t\t\t\t    Messages"); // tabbed to center
	    portal.setDisable(true); // disable portal table 
	    tab1.setContent(infoPane()); // set pane
	    tab2.setContent(visitsPane()); // set pane
	    tab3.setContent(messagePane()); // set pane
	    
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

	// content for messagePane
	private Pane messagePane()
	{
		Pane msgPane = new Pane();
		msgPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		
		return msgPane;
	}

	// content for visitsPane
	private Pane visitsPane()
	{
		Pane visitPane = new Pane();
		visitPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		return visitPane;
	}

	// content for infoPane
	private Pane infoPane()
	{
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
		edit.setFont(Font.font("Courier", FontWeight.BOLD, 20)); // set font
		
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
			inputstream = new FileInputStream("C:\\Users\\Maya\\Data\\System\\Installations\\Eclipse\\javaFX\\avatar2.png"); // file location 
			image = new Image(inputstream);  // image
			ImageView view = new ImageView(image); // view image
			view.setFitHeight(475);  // set size
			view.setFitWidth(450); // set size
			infoBox.getChildren().add(view);  // add to infoBox
			infoBox.setAlignment(Pos.TOP_CENTER); // set at center
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	    
		// add infoBox to center of border pane
		infoPane.setCenter(infoBox);
		return infoPane; // return pane
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
