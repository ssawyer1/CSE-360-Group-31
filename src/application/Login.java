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

public class Login extends Application
{
	@Override
	public void start(Stage stage) throws Exception
	{
		// set stage size
		stage.setHeight(900);
		stage.setWidth(1300);
		
		// create tabs and initialize panes
		Tab portal = new Tab("   Patient Portal");
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
}
