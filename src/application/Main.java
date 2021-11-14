package application; 

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application 
{
	private Stage stage;
	private Scene main;
	private HomeScreen home;
	
	public void start(Stage primaryStage) throws Exception
	{		
		// load all data from files and add to arrayLists of patients, doctors and nurses
		
		stage = primaryStage;
		stage.setHeight(900);
		stage.setWidth(1300);
		
		home = new HomeScreen();
		main = home.firstScreen(stage);
		
		stage.setScene(main);		
		stage.show();
	}
	
	public static void main(String[] args)
	{
		Application.launch(args);
	}
}
