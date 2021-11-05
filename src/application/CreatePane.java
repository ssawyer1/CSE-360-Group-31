package javaFX;

import java.awt.TextField;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;	//**Need to import
import javafx.event.EventHandler;	//**Need to import
import javafx.geometry.Insets;
import javafx.geometry.Pos;

//import all other necessary javafx classes here
//----

public class CreatePane extends HBox
{
	ArrayList<Club> clubList;
	TextArea text1;
	TextArea text2;
	TextArea text3;
	BorderPane leftPane;
	BorderPane rightPane;
	
	//The relationship between CreatePane and SelectPane is Aggregation
	private SelectPane selectPane;
	
	//constructor
	public CreatePane(ArrayList<Club> list, SelectPane sePane)
	{
		this.clubList = list;
		this.selectPane = sePane;
		
		
		//initialize each instance variable (textfields, labels, textarea, button, etc.)
		//and set up the layout
		
		//create a GridPane hold those labels & text fields.
		//you can choose to use .setPadding() or setHgap(), setVgap()
		//to control the spacing and gap, etc.
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10,10,10,10));

		Label titleLabel = new Label("Title");
		Label numMemLabel = new Label("Number of Members");
		Label uniLabel = new Label("University");
		text1 = new TextArea();
		text2 = new TextArea();
		text3 = new TextArea();
		
		grid.add(titleLabel, 0, 0);
		grid.add(numMemLabel, 0, 1);
		grid.add(uniLabel, 0, 2);
		grid.add(text1, 1, 0);
		grid.add(text2, 1, 1);
		grid.add(text3, 1, 2);
		
		//You might need to create a sub pane to hold the button
		Pane subPane = new Pane();
		Button clubButton = new Button("Create a Club");
		subPane.getChildren().addAll(clubButton);
		
		//Set up the layout for the left half of the CreatePane.
		leftPane = new BorderPane();
		leftPane.setCenter(grid);
		leftPane.setBottom(subPane);
		
		
		//the right half of the CreatePane is simply a TextArea object
		//Note: a ScrollPane will be added to it automatically when there are no
		//enough space
		rightPane = new BorderPane();
		TextArea displayClubs = new TextArea();
		for(int i = 0; i < clubList.size(); i++)
		{
			displayClubs.appendText(clubList.get(i).toString());
		}
		
		rightPane.getChildren().addAll(displayClubs);
		
		//Add the left half and right half to the CreatePane
		//Note: CreatePane extends from HBox
		HBox createPane = new HBox();
		createPane.getChildren().addAll(leftPane, rightPane);
		
		//register/link source object with event handler
		ButtonHandler handler = new ButtonHandler();
		clubButton.setOnAction(handler);
		
	} //end of constructor
		
	//Create a ButtonHandler class
	//ButtonHandler listens to see if the button "Create" is pushed or not,
	//When the event occurs, it get a club's Title, its number of members, and its university
	//information from the relevant text fields, then create a new club and add it inside
	//the clubList. Meanwhile it will display the club's information inside the text area.
	//using the toString method of the Club class.
	//It also does error checking in case any of the textfields are empty,
	//or a non-numeric value was entered for its number of members
	private class ButtonHandler implements EventHandler<ActionEvent>
	{
		//Override the abstact method handle()
		public void handle(ActionEvent event)
		{
			//declare any necessary local variables here
			Text txt;
			String clubName = "";
			int numMembers = 0;
			String university = "";
			boolean notDuplicate = false;
		 
			//when a text field is empty and the button is pushed
			if ((text1.getText() == null) || (text2.getText() == null) || (text3.getText() == null))
		    {
		         //handle the case here
		         txt = new Text("Please fill all fields");
		         txt.setFill(Color.RED);
		         
		         leftPane.setTop(txt);
		    }

			else
			{
		         //when a non-numeric value was entered for its number of members
		         //and the button is pushed
		         //you will need to use try & catch block to catch
		         //the NumberFormatException
		         try
		         {
		        	 clubName = text1.getText();
		        	 numMembers = Integer.parseInt(text2.getText());
		        	 university = text3.getText();
		         }
		         
		         catch(NumberFormatException nfe)
		         {
		        	txt = new Text("Please enter an integer for a number of members");
		        	txt.setFill(Color.RED);
		        	
		        	leftPane.setTop(txt);
		         }
		         
		         //When a club of an existing club name in the list
		         //was attempted to be added, do not add it to the list
		         //and display a message "Club not added - duplicate"
		         for(int i = 0; i < clubList.size(); i++)
		         {
		        	 if(clubName.contentEquals(clubList.get(i).getClubName()))
		        	 {		        		 
		        		 notDuplicate = false;
		        	 }
				         
		         }
		         
		         //at the end, don't forget to update the new arrayList
		         //information on the SelectPanel
		         if(notDuplicate == false)
		         {
	        		 txt = new Text("Club not added - duplicate");
	        		 txt.setFill(Color.RED);
			        	
	        		 leftPane.setTop(txt);
		         }
		         
		         else
		         {
		        	 clubList.add(new Club());
		        	 clubList.get(clubList.size()-1).setClubName(clubName);
		        	 clubList.get(clubList.size()-1).setNumberOfMembers(numMembers);
		        	 clubList.get(clubList.size()-1).setUniversity(university);
		        	
		         }
		         
		     }
		
		} //end of handle() method
	} //end of ButtonHandler class

}
