package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;

public class Patient 
{
	// variables sent in when create patient object
	private String username;
	private String password;
	private String fName;
	private String lName;
	private String email;
	private String phoneNum;
	private String pharmacy;
	private String dob;
	private String gender;
	private String insuranceCo;
	private String insuranceGroup;
	private String insuredName;
	private String insuranceNum;
	
	// variable initialized
	private boolean active;
	
	// variables added
	private String doctorName;
	private String doctorUser;
	private String nurseName;
	private String nurseUser;
	private int age;
	
	// arrays
	private ArrayList<Appointment> appointments;
	private ArrayList<Message> doctorMsgs;
	private ArrayList<Message> nurseMsgs;
	private ArrayList<Prescription> prescriptions;
	private ArrayList<Immunization> immunizations;
	
	// create default constructor!!
	Patient() {
		this.fName = "";
		this.lName = "";
		this.username = "";
		this.password = "";
		this.email = "";
		this.phoneNum = "";
		this.pharmacy = "";
		this.dob = "";
		this.gender = "";
		this.insuranceCo = "";
		this.insuranceGroup = "";
		this.insuredName = "";
		this.insuranceNum = "";	
		this.active = false;
		this.doctorName = "unassigned";
		this.nurseName = "unassigned";
		this.nurseUser = "unassigned";
		this.doctorUser = "unassigned";
		appointments = new ArrayList<Appointment>();
		doctorMsgs = new ArrayList<Message>();
		nurseMsgs = new ArrayList<Message>();
		prescriptions = new ArrayList<Prescription>();
		immunizations = new ArrayList<Immunization>();
	}
	
	// constructor for initial creation when registering
	public Patient(String fname, String lname, String user, String pass, String Email, String phone, String pharmacyLoc, String  DOB, String Gender, String insurCo, String insurGr, String insurName, String insurNum)
	{
		this.fName = fname;
		this.lName = lname;
		this.username = user;
		this.password = pass;
		this.email = Email;
		this.phoneNum = phone;
		this.pharmacy = pharmacyLoc;
		this.dob = DOB;
		this.gender = Gender;
		this.insuranceCo = insurCo;
		this.insuranceGroup = insurGr;
		this.insuredName = insurName;
		this.insuranceNum = insurNum;		
		this.age = calcAge(DOB);
		this.active = false;
		this.doctorName = "unassigned";
		this.nurseName = "unassigned";
		this.nurseUser = "unassigned";
		this.doctorUser = "unassigned";
		appointments = new ArrayList<Appointment>();
		doctorMsgs = new ArrayList<Message>();
		nurseMsgs = new ArrayList<Message>();
		prescriptions = new ArrayList<Prescription>();
		immunizations = new ArrayList<Immunization>();
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	public String getDoctorName() {
		return this.doctorName;
	}
	
	public String getDoctorID() {
		return this.doctorUser;
	}
	
	public String getNurseName() {
		return this.nurseName;
	}
	
	public void setActive(boolean status) {
		this.active = status;
	}
	
	public void setDoctorName(String newName) {
		this.doctorName = newName;
	}
	
	public void setDoctorID(String newID) {
		this.doctorUser = newID;
	}
	
	public void setNurseName(String newName) {
		this.nurseName = newName;
	}
	
	public String getFName() {
		return this.fName;
	}

	public String getLName() {
		return this.lName;
	}
	
	public String getFullName()
	{
		return (this.fName.concat(" ").concat(this.lName));
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPhoneNum() {
		return this.phoneNum;
	}
	
	public String getPharm() {
		return this.pharmacy;
	}
	
	public String getDOB() {
		return this.dob;
	}
	
	public int getAge() {
		return calcAge(this.dob);
	}
	
	public String getGender() {
		return this.gender;
	}
	
	public String gerInsurCo() {
		return this.insuranceCo;
	}
	
	public String getInsurGroup() {
		return this.insuranceGroup;
	}
	
	public String getInsurName() {
		return this.insuredName;
	}
	
	public String getInsurNum() {
		return this.insuranceNum;
	}
	
	public String getUsername() {
		return this.username; 
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setFName(String newName) {
		this.fName = newName;
	}

	public void setLName(String newName) {
		this.lName = newName;
	}
	
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	public void setPhoneNum(String newNum) {
		this.phoneNum = newNum;
	}
	
	public void setPharm(String newAddr) {
		this.pharmacy = newAddr;
	}
	
	public void setDOB(String newDOB) {
		this.dob = newDOB;
	}
	
	public void setGender(String newGender) {
		this.gender = newGender;
	}
	
	public void serInsurCo(String newCo) {
		this.insuranceCo = newCo;
	}
	
	public void setInsurGroup(String newGr) {
		this.insuranceGroup = newGr;
	}
	
	public void setInsurName(String newName) {
		this.insuredName = newName;
	}
	
	public void setInsurNum(String newNum) {
		this.insuranceNum = newNum;
	}
	
	public void setUsername(String newUser) {
		this.username = newUser; 
	}
	
	public void setPassword(String newPass) {
		this.password = newPass;
	}
	
	public ArrayList<Appointment> getAptts() {
		return this.appointments;
	}
	
	public ArrayList<Message> getDoctorMsg() {
		return this.doctorMsgs;
	}
	
	public ArrayList<Message> getNurseMsgs() {
		return this.nurseMsgs;
	}
	
	public ArrayList<Immunization> getImmunizations() {
		return this.immunizations;
	}
	
	public ArrayList<Prescription> getPrescriptions() {
		return this.prescriptions;
	}
	
	// (LocalDateTime dateCreated, String reason, String docNotes, String nNotes, String hght, String wght, String bp, String temperature)
	public void addDoctorMsg(LocalDateTime dateCreated, String message, String senderType, String senderName) {
		this.doctorMsgs.add(new Message(message, dateCreated, senderType, senderName));
	}
	
	public void addNurseMsg(LocalDateTime dateCreated, String message, String senderType, String senderName) {
		this.nurseMsgs.add(new Message(message, dateCreated, senderType, senderName));
	}
	
	public void addImmunization(String type, LocalDateTime taken) {
		this.immunizations.add(new Immunization(type, taken));
	}
	
	public void addPrescription(String type, LocalDateTime taken, String dir, String stopdate) {
		this.prescriptions.add(new Prescription(type,taken, dir, stopdate));
	}
	
	public void addAppointment(LocalDateTime dateCreated, String reason, String docNotes, String nNotes, String hght, String wght, String bp, String temperature) {
		this.appointments.add(new Appointment(dateCreated, reason, docNotes, nNotes, hght, wght, bp, temperature));
	}
	
	private int calcAge(String dob)
	{
		String[] splitString = dob.split("/");
		int month = Integer.parseInt(splitString[0]);
		int day = Integer.parseInt(splitString[1]);
		int year = Integer.parseInt(splitString[2]);
		LocalDate currentDay = LocalDate.now();
		LocalDate birthday = LocalDate.of(year, month, day);
		Period patientAge = Period.between(birthday, currentDay);
		return patientAge.getYears();
	}
	
	public void save() throws IOException
	{
		String fileName = ("Patients/").concat(username).concat(".txt");
		FileWriter fw = new FileWriter(fileName);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter of = new PrintWriter(bw);
		
		// write date
		of.println("Active=" + String.valueOf(this.active));
		of.println("Username=" + this.username);
		of.println("Password=" + this.password);
		of.println("FirstName=" + this.fName);
		of.println("LastName=" + this.lName);
		of.println("doctorID=" + this.doctorUser);
		of.println("Email=" + this.email);
		of.println("Phone=" + this.phoneNum);
		of.println("pharmacy=" + this.pharmacy);
		of.println("dob="+this.dob);
		of.println("gender=" + this.gender);
		of.println("insuranceCo=" + this.insuranceCo);
		of.println("insuranceGroup=" + this.insuranceGroup);
		of.println("insuranceName=" + this.insuredName);
		of.println("insuranceNum=" + this.insuranceNum);

		of.println("DoctorMessageNumber=" + this.doctorMsgs.size());
		for(int i = 0; i < this.doctorMsgs.size(); i++)
		{
			of.println("Date=" + doctorMsgs.get(i).getDate());
			of.println("Message=" + doctorMsgs.get(i).getMessage());
			of.println("SendName=" + doctorMsgs.get(i).getSendName());
			of.println("SendType=" + doctorMsgs.get(i).getSendType());
		}
		of.println("NurseMessageNumber=" + this.nurseMsgs.size());
		for(int i = 0; i < this.nurseMsgs.size(); i++)
		{
			of.println("Date=" + nurseMsgs.get(i).getDate());
			of.println("Message=" + nurseMsgs.get(i).getMessage());
			of.println("SendName=" + nurseMsgs.get(i).getSendName());
			of.println("SendType=" + nurseMsgs.get(i).getSendType());
		}
		
		// appointments
		of.println("AppointmentNum=" + this.appointments.size());
		for(int i = 0; i < this.appointments.size(); i++)
		{
			of.println("Date=" + appointments.get(i).getDate());
			of.println("Reason=" + appointments.get(i).getReason());
			of.println("Weight=" + appointments.get(i).getHeight());
			of.println("Height=" + appointments.get(i).getWeight());
			of.println("BP=" + appointments.get(i).getBP());
			of.println("Temp=" + appointments.get(i).getTemp());
			of.println("DoctorNotes=" + appointments.get(i).getDocNotes());
			of.println("NurseNotes=" + appointments.get(i).getNNotes());
		}
		
		// immunizations
		of.println("ImmunizationNum=" + this.immunizations.size());
		for(int i = 0; i < this.immunizations.size(); i++)
		{
			of.println("Date=" + immunizations.get(i).getDate());
			of.println("Type=" + immunizations.get(i).getType());
		}
		
		// prescriptions
		of.println("PrescriptionNum=" + this.prescriptions.size());
		for(int i = 0; i < this.prescriptions.size(); i++)
		{
			of.println("Date=" + prescriptions.get(i).getDate());
			of.println("Type=" + prescriptions.get(i).getType());
			of.println("Directions=" + prescriptions.get(i).getDir());
			of.println("StopDate=" + prescriptions.get(i).getStopDate());
		}
		of.close(); 
	}
	
	public void load(String file)
	{
		// load
		String fileName = file;
		FileReader fr = null;
		BufferedReader bf = null;
		try {
			fr = new FileReader(fileName);
			bf = new BufferedReader(fr);
			this.active = Boolean.parseBoolean(loadProperty(bf, "Active"));
			this.username = loadProperty(bf, "Username"); 
			this.password = loadProperty(bf, "Password"); 
			this.fName = loadProperty(bf, "FirstName"); 
			this.lName = loadProperty(bf, "LastName"); 
			this.doctorUser = loadProperty(bf, "doctorID"); 
			this.email = loadProperty(bf, "Email"); 
			this.phoneNum = loadProperty(bf, "Phone"); 
			this.pharmacy = loadProperty(bf, "Pharmacy"); 
			this.dob = loadProperty(bf, "dob"); 
			this.gender = loadProperty(bf, "gender"); 
			this.insuranceCo = loadProperty(bf, "insuranceCo"); 
			this.insuranceGroup = loadProperty(bf, "insuranceGroup"); 
			this.insuredName = loadProperty(bf, "insuranceName"); 
			this.insuranceNum = loadProperty(bf, "insuranceNum"); 
			// load doctor messages
			String[] splitLine = (bf.readLine()).split("=");					
			if(splitLine[0].equalsIgnoreCase("DoctorMessageNumber"))
			{
				for(int i = 0; i < Integer.parseInt(splitLine[1]); i++)
				{
					doctorMsgs.add(new Message());
					doctorMsgs.get(i).setMessage(loadProperty(bf, "Date"));
					doctorMsgs.get(i).setDate(loadProperty(bf, "Message"));
					doctorMsgs.get(i).setSendName(loadProperty(bf, "SendName"));
					doctorMsgs.get(i).setSendType((loadProperty(bf, "SendType")));
				}
			}
			// load nurse messages	
			splitLine = (bf.readLine()).split("=");					
			if(splitLine[0].equalsIgnoreCase("NurseMessageNumber"))
			{
				for(int i = 0; i < Integer.parseInt(splitLine[1]); i++)
				{
					nurseMsgs.add(new Message());
					nurseMsgs.get(i).setMessage(loadProperty(bf, "Date"));
					nurseMsgs.get(i).setDate(loadProperty(bf, "Message"));
					nurseMsgs.get(i).setSendName(loadProperty(bf, "SendName"));
					nurseMsgs.get(i).setSendType((loadProperty(bf, "SendType")));
				}
			}
			// load appointments
			splitLine = (bf.readLine()).split("=");		
			if(splitLine[0].equalsIgnoreCase("AppointmentNum")) {
				for(int i = 0; i < Integer.parseInt(splitLine[1]); i++)
				{
					appointments.add(new Appointment());
					appointments.get(i).setDate(loadProperty(bf, "Date"));
					appointments.get(i).setReason(loadProperty(bf, "Reason"));
					appointments.get(i).setWeight(loadProperty(bf, "Weight"));
					appointments.get(i).setHeight(loadProperty(bf, "Height"));
					appointments.get(i).setBP(loadProperty(bf, "BP"));
					appointments.get(i).setTemp(loadProperty(bf, "Temp"));
					appointments.get(i).setDocNotes(loadProperty(bf, "DoctorNotes"));
					appointments.get(i).setNNotes(loadProperty(bf, "NurseNotes"));
				}
			}
			// load immunizations
			splitLine = (bf.readLine()).split("=");					
			if(splitLine[0].equalsIgnoreCase("ImmunizationNum"))
			{
				for(int i = 0; i < Integer.parseInt(splitLine[1]); i++)
				{
					immunizations.add(new Immunization());
					immunizations.get(i).setDate(loadProperty(bf, "Date"));
					immunizations.get(i).setType(loadProperty(bf, "Type"));
				}
			}
			// load prescriptions
			splitLine = (bf.readLine()).split("=");					
			if(splitLine[0].equalsIgnoreCase("PrescriptionNum"))
			{
				for(int i = 0; i < Integer.parseInt(splitLine[1]); i++)
				{
					prescriptions.add(new Prescription());
					prescriptions.get(i).setDate(loadProperty(bf, "Date"));
					prescriptions.get(i).setType(loadProperty(bf, "Type"));
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
			// TODO Auto-generated catch block
			System.out.print("Problem loading Property");
		}
		
		// open doctor file for doctor user , get name and nurse id
		if(!this.doctorUser.equals("unassigned"))
		{
			fileName = ("Doctors/").concat(this.doctorUser).concat(".txt");
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
				this.doctorName = tempfName.concat(" ").concat(tempLName);
				this.nurseUser = loadProperty(bf, "NurseID");
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
		// open nurse file for nurse id, get name and store
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