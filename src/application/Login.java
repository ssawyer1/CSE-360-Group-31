package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application
{
	public void start(Stage stage) throws Exception
	{
		// set stage size
		stage.setHeight(900);
		stage.setWidth(1300);
		
		VBox innerPane = new VBox();
		innerPane.setStyle("-fx-background-color: white"); // set background of vbox
	    innerPane.setPadding(new Insets(30, 60, 30, 60));
	    innerPane.setSpacing(25);
		
		Text title = new Text("Login to Patient Portal");
		title.setFont(Font.font("Courier", 30));
		
		Text username = new Text("Username");
		username.setFont(Font.font("Courier", 20));
		
		TextArea user = new TextArea();
		user.setFont(Font.font("Courier", 20));
		user.setStyle("-fx-text-fill: gray");
		user.setMaxHeight(20);
		
		Text password = new Text("Password");
		password.setFont(Font.font("Courier", 20));
		
		TextArea pass = new TextArea();
		pass.setFont(Font.font("Courier", 20));
		pass.setStyle("-fx-text-fill: gray");	
		pass.setMaxHeight(20);
		
		Button submit = new Button("Login");
		submit.setStyle("-fx-background-radius: 5; -fx-background-color: rgb(" + 61 + "," + 138 + "," + 247 + ");; -fx-text-fill: white; ");
		submit.setMinWidth(200);
		submit.setFont(Font.font("Courier",20));
		
		Hyperlink signup = new Hyperlink("Create Account");
		signup.setFont(Font.font("Courier", 20));
		signup.setStyle("-fx-text-fill: rgb(" + 61 + "," + 138 + "," + 247 + ");");
		
		innerPane.getChildren().addAll(title, username, user, password, pass, submit, signup);
		
	    // layout the stage.
	    BorderPane box = new BorderPane(); // create vbox 
	    box.setCenter(innerPane); // add tab pane to vbox
	    box.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + "); ");
	    box.setPadding(new Insets(175, 250, 175, 250));
	    Scene scene = new Scene(box);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
