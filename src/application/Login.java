package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Test
{
	private TextArea user;
	private TextArea pass;
	private PatientPortal pp;
	private DoctorPortal dp;
	private NursePortal np;
	private CreateAccount ca;
	private adminGui ag;
	private String usertype;
	private VBox innerPane;
	private Text error;
	
	protected Scene LoginScreen(Stage stage, String userType)
	{			
		usertype = userType;
		
		innerPane = new VBox();
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
		
		error = new Text();
		error.setFont(Font.font("Courier", 15));
		error.setFill(Color.DARKRED);
		
		innerPane.getChildren().addAll(title, username, user, password, pass, login, signup,error);
		
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
			if (usertype.equalsIgnoreCase("patient"))
			{
				// check if username and password is correct
				String fileName = ("Patients/".concat(user.getText()).concat(".txt"));
				File tempFile = new File(fileName);
				boolean exists = tempFile.exists();
				String tempPass = "";
				boolean tempActive = false;
				if(exists == true) // if exists read active and password from file
				{
					FileReader fr = null;
					BufferedReader bf = null;
					try {
						fr = new FileReader(fileName);
						bf = new BufferedReader(fr); // want line 4, 5, 6
						tempActive = Boolean.parseBoolean(loadProperty(bf, "Active"));
						bf.readLine();
						tempPass = loadProperty(bf, "Password");
						bf.close();
					}
					catch(FileNotFoundException ex)
					{
						System.out.println(fileName + " not found.");
					}
					catch(IOException ex)
					{
						System.out.println(ex.toString());
					} catch (Exception e) {
						System.out.print("Problem loading Property");
					}
					// check password
					if(!pass.getText().equals(tempPass))
					{
						error.setText("Invalid password.");
					}
					
					// check if active
					else if(tempActive == false)
					{
						error.setText("Account not activated. Please come back later.");
					}
					else {
						// load patient
						Patient loggedInPatient = new Patient();
						loggedInPatient.load(fileName);
						pp = new PatientPortal();
						Scene patient = pp.patientScene(stage, loggedInPatient);
						stage.setScene(patient);
					}
				}
				else
				{
					error.setText("Invalid username.");
				}
			}
			if (usertype.equalsIgnoreCase("doctor"))
			{
				// check if username and password is correct
				String fileName = ("Doctors/".concat(user.getText()).concat(".txt"));
				File tempFile = new File(fileName);
				boolean exists = tempFile.exists();
				String tempPass = "";
				boolean tempActive = false;
				if(exists == true) // if exists read active and password from file
				{
					FileReader fr = null;
					BufferedReader bf = null;
					try {
						fr = new FileReader(fileName);
						bf = new BufferedReader(fr); // want line 4, 5, 6
						tempActive = Boolean.parseBoolean(loadProperty(bf, "Active"));
						bf.readLine();
						tempPass = loadProperty(bf, "Password");
						bf.close();
					}
					catch(FileNotFoundException ex)
					{
						System.out.println(fileName + " not found.");
					}
					catch(IOException ex)
					{
						System.out.println(ex.toString());
					} catch (Exception e) {
						System.out.print("Problem loading Property");
					}
					// check password
					if(!pass.getText().equals(tempPass))
					{
						error.setText("Invalid password.");
					}
					// check if active
					else if(tempActive == false)
					{
						error.setText("Account not activated. Please come back later.");
					}
					else {
						// load patient
						Doctor loggedInDoctor = new Doctor();
						loggedInDoctor.load(fileName);
						dp = new DoctorPortal();
						Scene doctor = dp.doctorScene(stage, loggedInDoctor);
						stage.setScene(doctor);
					}
				}
				else
				{
					error.setText("Invalid username.");
				}
			}
			if (usertype.equalsIgnoreCase("nurse"))
			{
				// check if username and password is correct
				String fileName = ("Nurses/".concat(user.getText()).concat(".txt"));
				File tempFile = new File(fileName);
				boolean exists = tempFile.exists();
				String tempPass = "";
				boolean tempActive = false;
				if(exists == true) // if exists read active and password from file
				{
					FileReader fr = null;
					BufferedReader bf = null;
					try {
						fr = new FileReader(fileName);
						bf = new BufferedReader(fr); // want line 4, 5, 6
						tempActive = Boolean.parseBoolean(loadProperty(bf, "Active"));
						bf.readLine();
						tempPass = loadProperty(bf, "Password");
						bf.close();
					}
					catch(FileNotFoundException ex)
					{
						System.out.println(fileName + " not found.");
					}
					catch(IOException ex)
					{
						System.out.println(ex.toString());
					} catch (Exception e) {
						System.out.print("Problem loading Property");
					}
					// check password
					if(!pass.getText().equals(tempPass))
					{
						error.setText("Invalid password.");
					}
					
					// check if active
					else if(tempActive == false)
					{
						error.setText("Account not activated. Please come back later.");
					}
					else {
						// load patient
						Nurse loggedInNurse = new Nurse();
						loggedInNurse.load(fileName);
						pp = new PatientPortal();
						Scene nurse = np.NurseScene(stage, loggedInNurse);
						stage.setScene(nurse);
					}
				}
				else
				{
					error.setText("Invalid username.");
				}
			}
			else if (user.getText().equals("Admin") && pass.getText().equals("fkj9728f"))
			{
				//iterate through all objects in doctors folder
				File folder = new File("Doctors/");
				File[] listFiles = folder.listFiles();
				String fileName;
				ArrayList<Doctor> doctors = new ArrayList<Doctor>();
				for(int i = 0; i < listFiles.length; i++)
				{
					if(listFiles[i].isFile())
					{
						fileName = ("Doctors/").concat(listFiles[i].getName()); 
						doctors.add(new Doctor());
						doctors.get(i).load(fileName);
					}
				}					
				// iterate through all object in nurses folder and add to array
				folder = new File("Nurses/");
				listFiles = folder.listFiles();
				ArrayList<Nurse> nurses = new ArrayList<Nurse>();
				for(int i = 0; i < listFiles.length; i++)
				{
					if(listFiles[i].isFile())
					{
						fileName = ("Nurses/").concat(listFiles[i].getName()); 
						nurses.add(new Nurse());
						nurses.get(i).load(fileName);
					}
				}	
				// iterate through all objects in patietns folder and add to array
				folder = new File("Patients/");
				listFiles = folder.listFiles();
				ArrayList<Patient> patients = new ArrayList<Patient>();
				for(int i = 0; i < listFiles.length; i++)
				{
					if(listFiles[i].isFile())
					{
						fileName = ("Patients/").concat(listFiles[i].getName()); 
						patients.add(new Patient());
						patients.get(i).load(fileName);
					}
				}	
				
				ag = new adminGui();
				Scene administrator = ag.adminGuiScene(stage, patients, nurses, doctors);
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
	
	private String loadProperty(BufferedReader bf, String property) throws Exception
	{
		String splitLine[] = (bf.readLine()).split("=");
		if(splitLine[0].equalsIgnoreCase(property)) 
		{
			return splitLine[1];
		}
		throw new Exception("Invalid");
	}
}
