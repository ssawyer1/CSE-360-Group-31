package javaFX;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.Tab;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane; 
import javafx.beans.value.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.awt.Color;
public class PrototypeCode extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		// set stage size
		stage.setHeight(1000);
		stage.setWidth(1500);
		
		// create tabs and initialize panes
	    Tab tab1 = new Tab("Contact Information");
	    Tab tab2 = new Tab("Patient Visits");
	    Tab tab3 = new Tab("Messages");
	    tab1.setContent(infoPane()); // set pane
	    tab2.setContent(visitsPane()); // set pane
	    tab3.setContent(messagePane()); // set pane
        tab1.setStyle("-fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0;"); // round tab1
        tab2.setStyle("-fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0;"); // round tab2
        tab3.setStyle("-fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0;"); // round tab3
	    
	   // create tab pane
	    TabPane tabPane = new TabPane();
	    tabPane.getTabs().addAll(tab1, tab2, tab3); // add children
	    tabPane.getStyleClass().add(TabPane.STYLE_CLASS_FLOATING); // set tab style to float
	    tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE); // don't allow tab closing
        tabPane.setTabMinWidth(200); // set min tab width
        tabPane.setTabMaxWidth(200); // set max tab width
        tabPane.setTabMinHeight(50); // set min tab height
        tabPane.setTabMaxHeight(50); // set max tab height
        
	    // layout the stage.
	    VBox layout = new VBox(10);
	    layout.getChildren().add(tabPane);
	    VBox.setVgrow(tabPane, Priority.ALWAYS);
	    layout.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + "); -fx-padding: 50;");
	    Scene scene = new Scene(layout);
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
		Pane infoPane = new Pane();
		infoPane.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + ");");
		return infoPane;
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
