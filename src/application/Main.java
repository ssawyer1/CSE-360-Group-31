package application;
	
import java.util.EventListener;
import javax.swing.event.ChangeListener;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
/*
 * So the main has been modified to accept a return Scene from the GUI classes
 * GUI classes should be protected I think, but mainly just follow the syntax I 
 * have been using and you should be good. 
 * I have coded it so that the button handler class will call the GUI method and
 * get the scene based on what is in the text fields. Right now I have it so if
 * both Password and Username are "Patient" ect... it will bring you to that portal.
 */

public class Main extends Application {
	protected static Stage stage; //local global varables here
	private PatientPortal pp; //Object from the other GUI classes to use the methods 
	private DoctorPortal dp;
	private Button login;
	private TextArea user;
	private TextArea pass;
	@Override
	public void start(Stage primaryStage) {
		stage = primaryStage;
		Scene scene = loginScene();
		primaryStage.setScene(scene);
		primaryStage.show();
	}	
	protected Scene loginScene() { //moved login code to the main
		dp = new DoctorPortal();
		pp = new PatientPortal();
		Pane root = new Pane();
		
		VBox innerPane = new VBox();
		innerPane.setStyle("-fx-background-color: white");
		innerPane.setPadding(new Insets(30, 60, 30, 60));
	    innerPane.setSpacing(25);
			
		Text title = new Text("Login to Patient Portal");
		title.setFont(Font.font("Courier", 30));
		
		Text username = new Text("Username");
		username.setFont(Font.font("Courier", 20));
			
		user = new TextArea();
		user.setFont(Font.font("Courier", 20));
		user.setStyle("-fx-text-fill: gray");
		user.setMaxHeight(20);		
			
		Text password = new Text("Password");
		password.setFont(Font.font("Courier", 20));
			
	    pass = new TextArea();
		pass.setFont(Font.font("Courier", 20));
		pass.setStyle("-fx-text-fill: gray");	
		pass.setMaxHeight(20);	
			
		login = new Button("Login");
		login.setStyle("-fx-background-radius: 5; -fx-background-color: rgb(" + 61 + "," + 138 + "," + 247 + ");; -fx-text-fill: white; ");
		login.setMinWidth(200);
		login.setFont(Font.font("Courier",20));
		//String user_input = user.getText();
		//String pass_input = pass.getText();
		login.setOnAction(new ButtonHandler());
		
		Hyperlink signup = new Hyperlink("Create Account");
		signup.setFont(Font.font("Courier", 20));
		signup.setStyle("-fx-text-fill: rgb(" + 61 + "," + 138 + "," + 247 + ");");
		
		innerPane.getChildren().addAll(title, username, user, password, pass, login, signup);
		
		BorderPane box = new BorderPane();
	    box.setCenter(innerPane); 
		box.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + "); ");
        box.setPadding(new Insets(175, 250, 175, 250));
		root.getChildren().add(box);
		
		return new Scene(root);	
	}
	private class ButtonHandler implements EventHandler<ActionEvent> //handles the button click based on what is in the text areas
	{																 //this is the key to how we will get people into the portal based on their login
																	 //maybe a check box is needed to select who you are? 
		public void handle(ActionEvent e)
		{			
			if (user.getText().equals("Patient") && pass.getText().equals("Patient"))
			{
				stage.setScene(pp.patientScene());
			}
			if (user.getText().equals("Doctor") && pass.getText().equals("Doctor"))
			{
				stage.setScene(dp.doctorScene());
			}			
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}
