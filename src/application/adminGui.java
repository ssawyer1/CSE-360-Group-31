package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class adminGui extends Test
{
	protected Scene adminGuiScene(Stage stage)
	{
		BorderPane fullPane = new BorderPane();
		
		Button logout = new Button("Logout");
		logout.setOnAction(e->switchScenes(stage));
		
		TabPane tabpane = new TabPane();
		Tab nurse = new Tab("Nurse");
		Tab doctor = new Tab("Doctor");
		Tab patient = new Tab("Patient");
		
		nurse.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; "); // round tab1
	    doctor.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; "); // round tab1
		patient.setStyle("-fx-pref-width: 356; -fx-pref-height: 50; -fx-border-radius: 10 10 0 0; -fx-background-radius: 10 10 0 0; "); // round tab1
		  
		patient.setContent(patientScreen());
		nurse.setContent(nurseScreen());
		doctor.setContent(doctorScreen());
		
		tabpane.getTabs().addAll(nurse, doctor, patient);
		tabpane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		
		fullPane.setTop(logout);
		fullPane.setAlignment(logout, Pos.TOP_RIGHT);
		fullPane.setCenter(tabpane);
		
		Scene scene = new Scene(fullPane);
		return scene;
	}
	
	private ScrollPane patientScreen()
	{
		ScrollPane scrollPatient = new ScrollPane();
		VBox complete = new VBox();
		complete.setPadding(new Insets(50,50,50,50));
		
		scrollPatient.setStyle("-fx-background: white;"); // set background of vbox
		scrollPatient.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPatient.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		VBox rows = new VBox();
		rows.setPadding(new Insets(20,50,20,0));
		rows.setSpacing(20);
		
		HBox titles = new HBox();
		titles.setSpacing(400);
		
		Text nameTitle = new Text("Name:");
		nameTitle.setFont(Font.font("Courier", 15));
		Text activeTitle = new Text("Active:");
		activeTitle.setFont(Font.font("Courier", 15));	
		Text assignTitle = new Text("Doctor:");
		assignTitle.setFont(Font.font("Courier", 15));	
		
		titles.getChildren().addAll(nameTitle, activeTitle, assignTitle);
		rows.getChildren().add(titles);
		
		// for all patients add their name and activity status (checkbox), doctor(dropdown)
		ArrayList<CheckBox> statuses = new ArrayList<CheckBox>();
		ArrayList<ComboBox> doctorSelected = new ArrayList<ComboBox>();
		ArrayList<HBox> patients = new ArrayList<HBox>();
		ObservableList<String> doctors = FXCollections.observableArrayList("Not Assigned", "Doctor A", "Doctor B");
		
		for(int i = 0; i<2; i++) // example
		{
			statuses.add(new CheckBox("active"));
			
			doctorSelected.add(new ComboBox(doctors));	
			doctorSelected.get(i).getSelectionModel().clearAndSelect(0);
			
			patients.add(new HBox());
			patients.get(i).getChildren().addAll(new Text("John Doe"),statuses.get(i),doctorSelected.get(i));
			patients.get(i).setSpacing(400);
			
			rows.getChildren().add(patients.get(i));
		}
		
		Button save = new Button("Save"); // set handling, set all patient statuses to check selected and dropdown
		complete.getChildren().addAll(rows, save);		
		
		scrollPatient.setContent(complete);
		return scrollPatient;
	}

	private ScrollPane nurseScreen()
	{
		ScrollPane scrollNurse = new ScrollPane();
		VBox complete = new VBox();
		complete.setPadding(new Insets(50,50,50,50));
		
		scrollNurse.setStyle("-fx-background: white;"); // set background of vbox
		scrollNurse.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollNurse.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		VBox rows = new VBox();
		rows.setPadding(new Insets(20,50,20,0));
		rows.setSpacing(20);
		
		HBox titles = new HBox();
		titles.setSpacing(400);
		
		Text nameTitle = new Text("Name:");
		nameTitle.setFont(Font.font("Courier", 15));
		Text activeTitle = new Text("Active:");
		activeTitle.setFont(Font.font("Courier", 15));	
		
		titles.getChildren().addAll(nameTitle, activeTitle);
		rows.getChildren().add(titles);
		
		// for all patients add their name and activity status (checkbox), doctor(dropdown)
		ArrayList<CheckBox> statuses = new ArrayList<CheckBox>();
		ArrayList<HBox> nurses = new ArrayList<HBox>();
		
		for(int i = 0; i<2; i++) // example
		{
			statuses.add(new CheckBox("active"));
			statuses.get(i).setSelected(true); // .isSelected() to get val
			
			nurses.add(new HBox());
			nurses.get(i).getChildren().addAll(new Text("Nancy Brown"),statuses.get(i));
			nurses.get(i).setSpacing(400);
			
			rows.getChildren().add(nurses.get(i));
		}
		
		Button save = new Button("Save"); // set handling, set all patient statuses to check selected and dropdown
		complete.getChildren().addAll(rows, save);
		
		scrollNurse.setContent(complete);
		return scrollNurse;
	}
	
	private ScrollPane doctorScreen()
	{
		ScrollPane scrollDoctor = new ScrollPane();
		VBox complete = new VBox();
		complete.setPadding(new Insets(50,50,50,50));
		
		scrollDoctor.setStyle("-fx-background: white;"); // set background of vbox
		scrollDoctor.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollDoctor.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		VBox rows = new VBox();
		rows.setPadding(new Insets(20,50,20,0));
		rows.setSpacing(20);
		
		HBox titles = new HBox();
		titles.setSpacing(400);
		
		Text nameTitle = new Text("Name:");
		nameTitle.setFont(Font.font("Courier", 15));
		Text activeTitle = new Text("Active:");
		activeTitle.setFont(Font.font("Courier", 15));	
		Text assignTitle = new Text("Nurse:");
		assignTitle.setFont(Font.font("Courier", 15));	
		
		titles.getChildren().addAll(nameTitle, activeTitle, assignTitle);
		rows.getChildren().add(titles);
		
		// for all patients add their name and activity status (checkbox), doctor(dropdown)
		ArrayList<CheckBox> statuses = new ArrayList<CheckBox>();
		ArrayList<ComboBox> nurseSelected = new ArrayList<ComboBox>();
		ArrayList<HBox> doctors = new ArrayList<HBox>();
		ObservableList<String> nurses = FXCollections.observableArrayList("Not Assigned", "Nancy Brown", "Nurse B");
		
		for(int i = 0; i<2; i++) // example
		{
			statuses.add(new CheckBox("active"));
			statuses.get(i).setSelected(true); // .isSelected() to get val
			
			nurseSelected.add(new ComboBox(nurses));	
			nurseSelected.get(i).getSelectionModel().clearAndSelect(1);
			
			doctors.add(new HBox());
			doctors.get(i).getChildren().addAll(new Text("Doctor A"),statuses.get(i),nurseSelected.get(i));
			doctors.get(i).setSpacing(400);
			
			rows.getChildren().add(doctors.get(i));
		}
		
		Button save = new Button("Save"); // set handling, set all patient statuses to check selected and dropdown
		
		complete.getChildren().addAll(rows, save);
		
		scrollDoctor.setContent(complete);
		return scrollDoctor;
	}
	
	private void switchScenes(Stage stage)
	{
		HomeScreen home = new HomeScreen();
		Scene h = home.firstScreen(stage);
		stage.setScene(h);
	}
}
