package application; // application

import java.io.File;
import java.io.IOException;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateAccount extends Test
{
	private TextArea user;
	private TextArea pass;
	private TextArea con;
	private TextArea fn;
	private TextArea ln;
	private TextArea em;
	private TextArea phone;
	private TextArea dob;
	private TextArea gen;
	private TextArea addr;
	private TextArea co;
	private TextArea group;
	private TextArea in;
	private TextArea inNum;
	private VBox holder;
	private Text error;
	private String type;
	
	protected Scene createAccountScene(Stage stage, Scene scene1, String usertype) 
	{
		type = usertype;
		
	    ScrollPane create = new ScrollPane();
		create.setStyle("-fx-background: white;"); // set background of vbox
	    create.setHbarPolicy(ScrollBarPolicy.NEVER);
	    create.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
	    holder = new VBox();
	    holder.setStyle("-fx-background-color: white;"); // set background of vbox
	    holder.setPadding(new Insets(30, 60, 30, 60));
	    holder.setSpacing(25);
	    
		Text title = new Text("Create Account");
		title.setFont(Font.font("Courier", 30));
		
		Text username = new Text("Username");
		username.setFont(Font.font("Courier", 15));
		
		user = new TextArea();
		user.setFont(Font.font("Courier", 15));
		user.setStyle("-fx-text-fill: gray");
		user.setMaxHeight(15);
		
		Text password = new Text("Password");
		password.setFont(Font.font("Courier", 15));
		
		pass = new TextArea();
		pass.setFont(Font.font("Courier", 15));
		pass.setStyle("-fx-text-fill: gray");	
		pass.setMaxHeight(15);
		
		Text confirm = new Text("Confirm Password");
		confirm.setFont(Font.font("Courier", 15));
		
		con = new TextArea();
		con.setFont(Font.font("Courier", 15));
		con.setStyle("-fx-text-fill: gray");	
		con.setMaxHeight(15);
		
		GridPane grid = new GridPane();
		grid.setStyle("-fx-background-color: white");
		
		Text fname = new Text("First Name");
		fname.setFont(Font.font("Courier", 15));
		
		fn = new TextArea();
		fn.setFont(Font.font("Courier", 15));
		fn.setStyle("-fx-text-fill: gray");	
		fn.setMaxSize(300, 14);
		
		Text lname = new Text();
		lname.setFont(Font.font("Courier", 15));
		
		ln = new TextArea();
		ln.setFont(Font.font("Courier", 15));
		ln.setStyle("-fx-text-fill: gray");	
		ln.setMaxSize(300, 15);
		
		grid.setHgap(25);
		grid.setVgap(25);
		grid.add(fname, 0, 0);
		grid.add(lname, 1, 0);
		grid.add(fn, 0, 1);
		grid.add(ln, 1, 1);
		
		Text Email = new Text("Email");
		Email.setFont(Font.font("Courier", 15));
		
		em = new TextArea();
		em.setFont(Font.font("Courier", 15));
		em.setStyle("-fx-text-fill: gray");
		em.setMaxHeight(15);
		
		Text PhoneNum = new Text("Phone");
		PhoneNum.setFont(Font.font("Courier", 15));
		
		phone = new TextArea();
		phone.setFont(Font.font("Courier", 15));
		phone.setStyle("-fx-text-fill: gray");
		phone.setMaxHeight(15);
		
		holder.getChildren().addAll(title, username, user, password, pass, confirm, con, grid, Email, em, PhoneNum, phone);
		
		
		if(type.equalsIgnoreCase("patient"))
		{
			Text date = new Text("Date of Birth");
			date.setFont(Font.font("Courier", 15));
			
			dob = new TextArea();
			dob.setFont(Font.font("Courier", 15));
			dob.setStyle("-fx-text-fill: gray");	
			dob.setMaxSize(300, 15);
			
			Text gender = new Text("Gender");
			gender.setFont(Font.font("Courier", 15));
			
			gen = new TextArea();
			gen.setFont(Font.font(15));
			gen.setStyle("-fx-text-fill: gray");	
			gen.setMaxSize(300, 15);
			
			Text addressPharm = new Text("Address of Pharmacy");
			addressPharm.setFont(Font.font("Courier", 15));
			
			addr = new TextArea();
			addr.setFont(Font.font("Courier", 15));
			addr.setStyle("-fx-text-fill: gray");
			addr.setMaxHeight(15);
			
			GridPane grid2 = new GridPane();
			grid2.setStyle("-fx-background-color: white");
			
			Text insuranceCo = new Text("Insurance Company");
			insuranceCo.setFont(Font.font("Courier", 15));
			
			co = new TextArea();
			co.setFont(Font.font("Courier", 15));
			co.setStyle("-fx-text-fill: gray");	
			co.setMaxSize(300, 14);
			
			Text GroupNum = new Text("Group#");
			GroupNum.setFont(Font.font("Courier", 15));
			
			group = new TextArea();
			group.setFont(Font.font("Courier", 15));
			group.setStyle("-fx-text-fill: gray");	
			group.setMaxSize(300, 15);
			
			Text insuredName = new Text("Insured's Name");
			insuredName.setFont(Font.font("Courier", 15));
			
			in = new TextArea();
			in.setFont(Font.font("Courier", 15));
			in.setStyle("-fx-text-fill: gray");	
			in.setMaxSize(300, 15);
			
			Text InsuredNum = new Text("Insurance Number");
			InsuredNum.setFont(Font.font("Courier", 15));
			
			inNum = new TextArea();
			inNum.setFont(Font.font(15));
			inNum.setStyle("-fx-text-fill: gray");	
			inNum.setMaxSize(300, 15);
			
			grid2.setHgap(25);
			grid2.setVgap(25);
			grid2.add(date, 0, 0);
			grid2.add(gender, 1, 0);
			grid2.add(dob, 0, 1);
			grid2.add(gen, 1, 1);
			grid2.add(insuranceCo, 0, 2);
			grid2.add(GroupNum, 1, 2);
			grid2.add(co, 0, 3);
			grid2.add(group, 1, 3);
			grid2.add(insuredName, 0, 4);
			grid2.add(InsuredNum, 1, 4);
			grid2.add(in, 0, 5);
			grid2.add(inNum, 1, 5);
		
			holder.getChildren().addAll(addressPharm, addr, grid2);
			
		}
		
		
		Button submit = new Button("Create Account");
		submit.setStyle("-fx-background-radius: 5; -fx-background-color: rgb(" + 61 + "," + 138 + "," + 247 + ");; -fx-text-fill: white; ");
		submit.setMinSize(200, 50);
		submit.setFont(Font.font("Courier",15));
		submit.setOnAction(e->switchScenes(scene1, stage));
		holder.getChildren().add(submit);
		
		error = new Text();
		error.setFont(Font.font("Courier", 15));
		error.setFill(Color.DARKRED);
		holder.getChildren().add(error);
		
		create.setContent(holder);

		
	    // layout the stage.
	    BorderPane box = new BorderPane(); // create vbox 
	    box.setCenter(create); // add tab pane to vbox
	    box.setStyle("-fx-background-color: rgb(" + 168 + "," + 198 + ", " + 250 + "); ");
	    box.setPadding(new Insets(20, 250, 20, 250));
	    Scene scene = new Scene(box);
	    
	    return scene;
	}
	
	private void switchScenes(Scene scene, Stage stage)
	{		
		// if username exists
		String fileName = ("Patients/".concat(user.getText()).concat(".txt"));
		File tempFile = new File(fileName);
		boolean exists = tempFile.exists();
		if(exists == true)
		{
			error.setText("Username is already in use.");
		}
		// iff paswords dont match
		else if(!pass.getText().equals(con.getText()))
		{
			error.setText("Passwords do not match.");
		}
		else
		{
			if(type.equalsIgnoreCase("patient"))
			{
				// create patient object and save
				Patient newPatient = new Patient(fn.getText(), ln.getText(), user.getText(), pass.getText(), em.getText(), phone.getText(), addr.getText(), dob.getText(), gen.getText(), co.getText(), group.getText(), in.getText(), inNum.getText());
				try {
					newPatient.save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(type.equalsIgnoreCase("doctor"))
			{
				// create doctor object and save
				Doctor newDoc = new Doctor(fn.getText(), ln.getText(), user.getText(), pass.getText(), em.getText(), phone.getText());
				try {
					newDoc.save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(type.equalsIgnoreCase("nurse"))
			{
				// create patient object and save
				Nurse newNurse = new Nurse(fn.getText(), ln.getText(), user.getText(), pass.getText(), em.getText(), phone.getText());
				try {
					newNurse.save();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
			stage.setScene(scene);
		}
	}

}
