package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class adminGui extends Main
{
	private ArrayList<Patient> Patients;
	private ArrayList<Nurse> Nurses;
	private ArrayList<Doctor> Doctors;
	protected Scene adminGuiScene(Stage stage, ArrayList<Patient> patients, ArrayList<Nurse> nurses, ArrayList<Doctor> doctors)
	{
		Patients = patients;
		Nurses = nurses;
		Doctors = doctors;
		
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
		
		// for all patients add their name and activity status (check box), doctor(drop down)
		ArrayList<CheckBox> statuses = new ArrayList<CheckBox>(); // array of all active statuses of patients
		ArrayList<ComboBox> doctorSelected = new ArrayList<ComboBox>(); // array of all assigned doctors of patients
		ArrayList<HBox> patientsAll = new ArrayList<HBox>();
		ObservableList<String> doctorsAll = FXCollections.observableArrayList("unassigned");
		for(int i = 0; i < Doctors.size(); i++)
		{
			String temp = (Doctors.get(i).getFullName());
			doctorsAll.add(temp);
		}
		for(int i = 0; i<Patients.size(); i++) // example
		{
			Text temp = new Text(Patients.get(i).getFullName());
			Pane tempTextPane = new Pane(temp);
			tempTextPane.setMaxWidth(442);
			tempTextPane.setMinWidth(442);
			
			statuses.add(new CheckBox("active"));
			statuses.get(i).setSelected(Patients.get(i).getActive());
			Pane tempCheckPane = new Pane(statuses.get(i));
			tempCheckPane.setMaxWidth(445);
			tempCheckPane.setMinWidth(445);
			
			doctorSelected.add(new ComboBox(doctorsAll));
			doctorSelected.get(i).setValue(Patients.get(i).getDoctorName());
			
			patientsAll.add(new HBox());
			patientsAll.get(i).getChildren().addAll(tempTextPane,tempCheckPane,doctorSelected.get(i));
			
			rows.getChildren().add(patientsAll.get(i));
		}
		
		Button save = new Button("Save"); // set handling, set all patient statuses to check selected and dropdown
		save.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				for(int i = 0; i < Patients.size(); i++)
				{
					Patients.get(i).setActive(statuses.get(i).isSelected());
					if(doctorSelected.get(i).getSelectionModel().getSelectedIndex() == 0)
					{
						Patients.get(i).setDoctorID("unassigned");
					}
					else
					{
						Patients.get(i).setDoctorID(Doctors.get(doctorSelected.get(i).getSelectionModel().getSelectedIndex()-1).getuser());
					}
					try {
						Patients.get(i).save();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
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
		
		for(int i = 0; i<Nurses.size(); i++) // example
		{
			Text temp = new Text(Nurses.get(i).getFullName());
			Pane tempTextPane = new Pane(temp);
			tempTextPane.setMaxWidth(442);
			tempTextPane.setMinWidth(442);
			
			statuses.add(new CheckBox("active"));
			statuses.get(i).setSelected(Nurses.get(i).getActive()); // .isSelected() to get val
			
			nurses.add(new HBox());
			nurses.get(i).getChildren().addAll(tempTextPane,statuses.get(i));
			
			rows.getChildren().add(nurses.get(i));
		}
		
		Button save = new Button("Save"); // set handling, set all patient statuses to check selected and dropdown
		save.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				for(int i = 0; i < Nurses.size(); i++)
				{
					Nurses.get(i).setActive(statuses.get(i).isSelected());
					try
					{
						Nurses.get(i).save();
					}
					catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});		
		
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
		ArrayList<HBox> doctorsAll = new ArrayList<HBox>();
		ObservableList<String> nursesAll = FXCollections.observableArrayList("unassigned");
		for(int i = 0; i<Nurses.size(); i++)
		{
			String temp = (Nurses.get(i).getFullName());
			nursesAll.add(temp);
			
		}
		for(int i = 0; i<Doctors.size(); i++) // example
		{
			Text temp = new Text(Doctors.get(i).getFullName());
			Pane tempTextPane = new Pane(temp);
			tempTextPane.setMaxWidth(442);
			tempTextPane.setMinWidth(442);
			
			statuses.add(new CheckBox("active"));
			statuses.get(i).setSelected(Doctors.get(i).getActive());
			Pane tempCheckPane = new Pane(statuses.get(i));
			tempCheckPane.setMaxWidth(445);
			tempCheckPane.setMinWidth(445);
			
			nurseSelected.add(new ComboBox(nursesAll));	
			nurseSelected.get(i).setValue(Doctors.get(i).getnurseName());
			
			doctorsAll.add(new HBox());
			doctorsAll.get(i).getChildren().addAll(tempTextPane,tempCheckPane,nurseSelected.get(i));
			
			rows.getChildren().add(doctorsAll.get(i));
		}
		
		Button save = new Button("Save"); // set handling, set all patient statuses to check selected and dropdown
		save.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				for(int i = 0; i < Doctors.size(); i++)
				{
					Doctors.get(i).setActive(statuses.get(i).isSelected());
					if(nurseSelected.get(i).getSelectionModel().getSelectedIndex() == 0)
					{
						Doctors.get(i).setNurseID("unassigned");
					}
					else
					{
						Doctors.get(i).setNurseID(Nurses.get(nurseSelected.get(i).getSelectionModel().getSelectedIndex()-1).getuser());
					}
					try {
						Doctors.get(i).save();
					} catch (IOException e1) 
					{
						e1.printStackTrace();
					}
				}
			}
		});		
		
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
