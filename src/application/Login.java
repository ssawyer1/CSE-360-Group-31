package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Test
{
	private TextArea user;
	private TextArea pass;
	private PatientPortal pp;
	private DoctorPortal dp;
	private CreateAccount ca;
	private adminGui ag;
	private String usertype;
	
	protected Scene LoginScreen(Stage stage, String userType)
	{			
		usertype = userType;
		
		VBox innerPane = new VBox();
		innerPane.setStyle("-fx-background-color: white");
		innerPane.setPadding(new Insets(30, 60, 30, 60));
	    innerPane.setSpacing(25);
			
	    String portalName = "Login to ";
	    portalName = portalName.concat(usertype);
	    portalName = portalName.concat(" Portal");
		Text title = new Text(portalName);
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
			
		Button login = new Button("Login");
		login.setStyle("-fx-background-radius: 5; -fx-background-color: rgb(" + 61 + "," + 138 + "," + 247 + ");; -fx-text-fill: white; ");
		login.setMinWidth(200);
		login.setFont(Font.font("Courier",20));
		login.setOnAction(e->switchScenes(stage, "login"));
		
		Hyperlink signup = new Hyperlink("Register");
		signup.setFont(Font.font("Courier", 20));
		signup.setStyle("-fx-text-fill: rgb(" + 61 + "," + 138 + "," + 247 + ");");
		signup.setOnAction(e->switchScenes(stage, "create"));
		
		if(usertype.equals("Admin"))
		{
			signup.setVisible(false);
		}
		
		innerPane.getChildren().addAll(title, username, user, password, pass, login, signup);
		
		BorderPane box = new BorderPane();
	    box.setCenter(innerPane); 
		box.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + "); ");
        box.setPadding(new Insets(175, 250, 175, 250));
        
        Button back = new Button("Back to Home");
        back.setOnAction(e->switchScenes(stage, "back"));
        box.setTop(back);
        box.setAlignment(back, Pos.TOP_LEFT);
        
        Scene loginScene = new Scene(box);
		
		return loginScene;	
	}
	
	private void switchScenes(Stage stage, String switchType)
	{
		if(switchType.equals("login"))
		{
			if (user.getText().equals("Patient") && pass.getText().equals("Patient"))
			{
				// check if user type s patient, if it it, check entered text against passwors and usernames of all patients in array (or unil fouond). if not found return invalid. if found but not activated, return, else open 
				pp = new PatientPortal();
				Scene patient = pp.patientScene(stage);
				stage.setScene(patient);
			}
			else if (user.getText().equals("Doctor") && pass.getText().equals("Doctor"))
			{
				dp = new DoctorPortal();
				Scene doctor = dp.doctorScene();
				stage.setScene(doctor);
			}
			else if (user.getText().equals("Admin") && pass.getText().equals("Admin"))
			{
				ag = new adminGui();
				Scene administrator = ag.adminGuiScene(stage);
				stage.setScene(administrator);
			}	
		}
		else if(switchType.equals("back"))
		{
			HomeScreen home = new HomeScreen();
			Scene h = home.firstScreen(stage);
			stage.setScene(h);
		}
		else
		{
			ca = new CreateAccount();	
			Scene account = ca.createAccountScene(stage, LoginScreen(stage,usertype), usertype);
			stage.setScene(account);
		}
	}
}
