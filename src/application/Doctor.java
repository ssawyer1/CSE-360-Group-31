package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Doctor 
{
	private String phoneNumber;
	private String email;
	private String username;
	private String password;
	private String fName;
	private String lName;
	private String nurseName;
	private String nurseUser;
	
	// active
	private boolean active;
	
	// arrays
	private ArrayList<Patient> patients;
	
	//Default constructor
	Doctor() {
		this.fName = "";
		this.lName = "";
		this.username = "";
		this.password = "";
		this.phoneNumber = "";
		this.email = "";
		this.active = false;	
		this.nurseName = "unassigned";
		this.nurseUser = "unassigned";
		patients = new ArrayList<Patient>();
	}
	
	// constructor when registering
	Doctor(String fname, String lname, String user, String pass, String email, String phoneNumber)
	{
		this.fName = fname;
		this.lName = lname;
		this.username = user;
		this.password = pass;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.active = false;	
		this.nurseName = "unassigned";
		this.nurseUser = "unassigned";
		patients = new ArrayList<Patient>();
	}
	
	public String getFName() {
		return this.fName;
	}
	
	public String getLName() {
		return this.lName;
	}
	
	public String getFullName(){
		String fullName = this.fName.concat(" ").concat(this.lName);
		return fullName;
	}
	
	public String getuser() {
		return this.username;
	}
	
	public String getPhone() {
		return this.phoneNumber;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	public String getnurseName() {
		return this.nurseName;
	}
	
	public String getnurseID() {
		return this.nurseUser;
	}
	
	public String getPass() {
		return this.password;
	}
	
	public void setFName(String newName) {
		this.fName = newName;
	}
	
	public void setLName(String newName) {
		this.lName = newName;
	}
	
	public void setUser(String newUser) {
		this.username = newUser;
	}
	
	public void setPass(String newPass) {
		this.password = newPass;
	}
	
	public void setNurseName(String newName) {
		this.nurseName = newName;
	}
	
	public void setNurseID(String newID) {
		this.nurseUser = newID;
	}
	
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public void setPhone(String newPhone) {
		this.phoneNumber = newPhone;
	}
	
	public void setActive(boolean status) {
		this.active = status;
	}
	
	
	public ArrayList<Patient> getPatients() {
		return this.patients;
	}

	public void addPatient(Patient patient) {
		this.patients.add(patient);
	}
	
	public void save() throws IOException
	{
		String fileName = ("Doctors/").concat(username).concat(".txt");
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter of = new PrintWriter(bw);
		
		// write date
		of.println("Active=" + String.valueOf(this.active));
		of.println("Username=" + this.username);
		of.println("Password=" + this.password);
		of.println("FirstName=" + this.fName);
		of.println("LastName=" + this.lName);
		of.println("NurseID=" + this.nurseUser);
		of.println("Email=" + this.email);
		of.println("Phone=" + this.phoneNumber);

		// close file
		of.close();
	}
	
	public void load(String file)
	{
		String fileName = file; 
		FileReader fr = null;
		BufferedReader bf = null;
		try {
			fr = new FileReader(fileName);
			bf = new BufferedReader(fr); // want line 4, 5, 6
			this.active = Boolean.parseBoolean(loadProperty(bf, "Active"));
			this.username = loadProperty(bf, "Username");
			this.password = loadProperty(bf, "Password");
			this.fName = loadProperty(bf, "FirstName");
			this.lName = loadProperty(bf, "LastName");
			this.nurseUser = loadProperty(bf, "NurseID");
			this.email = loadProperty(bf, "Email");
			this.phoneNumber = loadProperty(bf, "Phone");
			
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
		//use nurse id to get nurse name
		if(!this.nurseUser.equalsIgnoreCase("unassigned"))
		{
			fileName = ("Nurses/").concat(this.nurseUser).concat(".txt"); 
			fr = null;
			bf = null;
			try {
				fr = new FileReader(fileName);
				bf = new BufferedReader(fr); // want line 4, 5, 6
				bf.readLine(); 
				bf.readLine();
				bf.readLine();
				String tempfName = loadProperty(bf, "FirstName");
				String tempLName = loadProperty(bf, "LastName");
				this.nurseName = tempfName.concat(" ").concat(tempLName);
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
		}
		
		// iterate through all  files in the patient folder, find those that match doctor user
		File folder = new File("Patients/");
		File[] listFiles = folder.listFiles();
		int count = 0;
		for(int i = 0; i < listFiles.length; i++)
		{
			if(listFiles[i].isFile())
			{
				fileName = ("Patients/").concat(listFiles[i].getName()); 
				fr = null;
				bf = null;
				try {
					fr = new FileReader(fileName);
					bf = new BufferedReader(fr); // want line 4, 5, 6
					for(int j = 0; j < 5; j++)
					{
						bf.readLine();
					}
					String doctorID = loadProperty(bf, "doctorID"); 
					if(doctorID.equalsIgnoreCase(this.username))
					{
						patients.add(new Patient());
						patients.get(count).load(fileName);;
						count++;
					}
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
			}
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
