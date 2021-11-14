package javaFX;

import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;

public class Nurse 
{
	private String phoneNumber;
	private String email;
	private String username;
	private String password;
	private String fName;
	private String lName;
	
	// active
	private boolean active;
	
	// arrays
	private ArrayList<Doctor> doctors;
	private ArrayList<Message> messages;
	
	//Default constructor
	Nurse() {
		this.fName = "";
		this.lName = "";
		this.username = "";
		this.password = "";
		this.phoneNumber = "";
		this.email = "";
		this.active = false;
		doctors = new ArrayList<Doctor>();
		messages = new ArrayList<Message>();
	}
	
	// constructor when registering
	Nurse(String fname, String lname, String user, String pass, String email, String phoneNumber)
	{
		this.fName = fname;
		this.lName = lname;
		this.username = user;
		this.password = pass;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.active = false;
		doctors = new ArrayList<Doctor>();
		messages = new ArrayList<Message>();
	}
	
	public String getFName() {
		return this.fName;
	}
	
	public String getLName() {
		return this.lName;
	}
	
	public String getFullName() {
		return (this.fName.concat(" ").concat(this.lName));
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
	
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public void setPhone(String newPhone) {
		this.phoneNumber = newPhone;
	}
	
	public ArrayList<Doctor> getDoctors() {
		return this.doctors;
	}
	
	public ArrayList<Message> getMsgs() {
		return this.messages;
	}
	
	public void addDoctor(Doctor doctor) {
		this.doctors.add(doctor);
	}
	
	public void addMesaage(LocalDateTime dateCreated, String message) {
		this.messages.add(new Message(message, dateCreated));
	}

	public void save() throws IOException
	{
		String fileName = ("Nurses/").concat(username).concat(".txt");
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter of = new PrintWriter(bw);
		
		// write date
		of.println("Active=" + String.valueOf(this.active));
		of.println("Username=" + this.username);
		of.println("Password=" + this.password);
		of.println("FirstName=" + this.fName);
		of.println("LastName=" + this.lName);
		of.println("Email=" + this.email);
		of.println("Phone=" + this.phoneNumber);

		of.println("MessageNumber=" + this.messages.size());
		for(int i = 0; i < this.messages.size(); i++)
		{
			of.println("Date=" + messages.get(i).getDate());
			of.println("Message=" + messages.get(i).getMessage());
		}
		
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
			this.email = loadProperty(bf, "Email");
			this.phoneNumber = loadProperty(bf, "Phone");
			
			String splitLine[] = (bf.readLine()).split("=");					
			if(splitLine[0].equalsIgnoreCase("DoctorMessageNumber"))
			{
				for(int i = 0; i < Integer.parseInt(splitLine[1]); i++)
				{
					messages.add(new Message());
					messages.get(i).setMessage(loadProperty(bf, "Date"));
					messages.get(i).setDate(loadProperty(bf, "Message"));
				}
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
		
		// iterate through all doctor files
		File folder = new File("Doctors/");
		File[] listFiles = folder.listFiles();
		int count = 0;
		for(int i = 0; i < listFiles.length; i++)
		{
			if(listFiles[i].isFile())
			{
				fileName = ("Doctors/").concat(listFiles[i].getName()); 
				fr = null;
				bf = null;
				try {
					fr = new FileReader(fileName);
					bf = new BufferedReader(fr); // want line 4, 5, 6
					for(int j = 0; j < 5; j++)
					{
						bf.readLine();
					}
					String nurseID = loadProperty(bf, "NurseID"); 
					if(nurseID.equalsIgnoreCase(this.username))
					{
						doctors.add(new Doctor());
						doctors.get(count).load(fileName);
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
