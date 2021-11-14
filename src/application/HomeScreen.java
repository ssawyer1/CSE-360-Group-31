package javaFX;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HomeScreen extends Test
{
	private Login log;
	private Stage stage;
	
	protected Scene firstScreen(Stage mainStage)
	{
		stage = mainStage;
		
		VBox list = new VBox();
		list.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + "); ");
		list.setPadding(new Insets(30, 60, 30, 60));
		list.setSpacing(50);
	    
		Button nurse = new Button("Nurse");
		nurse.setStyle("-fx-background-radius: 5; -fx-background-color: rgb(" + 61 + "," + 138 + "," + 247 + ");; -fx-text-fill: white; ");
		nurse.setMinWidth(300);
		nurse.setMinHeight(70);
		nurse.setFont(Font.font("Courier",20));
		nurse.setOnAction(e->switchScenes("Nurse"));
		
		Button doctor = new Button("Doctor");
		doctor.setStyle("-fx-background-radius: 5; -fx-background-color: rgb(" + 61 + "," + 138 + "," + 247 + ");; -fx-text-fill: white; ");
		doctor.setMinWidth(300);
		doctor.setMinHeight(70);
		doctor.setFont(Font.font("Courier",20));
		doctor.setOnAction(e->switchScenes("Doctor"));
		
		Button patient = new Button("Patient");
		patient.setStyle("-fx-background-radius: 5; -fx-background-color: rgb(" + 61 + "," + 138 + "," + 247 + ");; -fx-text-fill: white; ");
		patient.setMinWidth(300);
		patient.setMinHeight(70);
		patient.setFont(Font.font("Courier",20));
		patient.setOnAction(e->switchScenes("Patient"));
		
		Button admin = new Button("Admin");
		admin.setStyle("-fx-background-radius: 5; -fx-background-color: rgb(" + 61 + "," + 138 + "," + 247 + ");; -fx-text-fill: white; ");
		admin.setMinWidth(300);
		admin.setMinHeight(70);
		admin.setFont(Font.font("Courier",20));
		admin.setOnAction(e->switchScenes("Admin"));
		
		list.getChildren().addAll(nurse, doctor, patient, admin);
		list.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(list);
		return scene;
	}
	
	public void switchScenes(String switchType)
	{
		log = new Login();
		if(switchType.equals("Nurse"))
		{
			Scene login = log.LoginScreen(stage, switchType);
			stage.setScene(login);
		}
		else if(switchType.equals("Doctor"))
		{
			Scene login = log.LoginScreen(stage, switchType);
			stage.setScene(login);
		}
		else if(switchType.equals("Patient"))
		{
			Scene login = log.LoginScreen(stage, switchType);
			stage.setScene(login);
		}
		else if(switchType.equals("Admin"))
		{
			Scene login = log.LoginScreen(stage, switchType);
			stage.setScene(login);
		}
	}	
}
