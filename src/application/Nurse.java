package application;

public class Nurse {
  
  private int phoneNumber;
	private String email;
  private String name;
	private ArrayList<Patient> patientArrayList;
	private ArrayList<Doctor> doctorArrayList;
	private ArrayList<Message> messageArrayList;
  
  Nurse()
  {
    this.phoneNumber = 0;
		this.email = "";
    this.name = "";
		this.patientArrayList = new ArrayList<Patient>();
		this.doctorArrayList = new ArrayList<Doctor>();
		this.messageArrayList = new ArrayList<Message>();
	}
  	
  Nurse(String email, int phoneNumber, String name)
	{
		this.phoneNumber = phoneNumber;
		this.email = email;
    this.name = name;
		this.patientArrayList = new ArrayList<Patient>();
		this.doctorArrayList = new ArrayList<Doctor>();
		this.messageArrayList = new ArrayList<Message>();
	}
	
  //Methods:

 
  
}
