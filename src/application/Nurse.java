package application;
import java.util.ArrayList;

public class Nurse {
	private int phoneNumber;
	private String email;
	private String firstName;
	private String lastName;
	private ArrayList<Patient> patientArrayList;
	private ArrayList<Doctor> doctorArrayList;
	private ArrayList<Message> messageArrayList;

	Nurse()

	 {
		 this.phoneNumber = 0;
		 this.email = "";
		 this.firstName = "";
		 this.lastName = "";
		 this.patientArrayList = new ArrayList<Patient>();
		 this.doctorArrayList = new ArrayList<Doctor>();
		 this.messageArrayList = new ArrayList<Message>();
	 }

	Nurse(String email, int phoneNumber, String firstName, String lastName)
	{ this.phoneNumber = phoneNumber;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.patientArrayList = new ArrayList<Patient>();
		this.doctorArrayList = new ArrayList<Doctor>();
		this.messageArrayList = new ArrayList<Message>();
	}

	//methods
	public void addPatient(Patient patient)
	{
		this.patientArrayList.add(patient);
	}

	public void addDoctor(Doctor doctor)
	{
		this.doctorArrayList.add(doctor);
	}

	public void addMessage(Message message)
	{
		this.messageArrayList.add(message);
	}


	public void getMessage()
	{
		for (int i = 0; i < messageArrayList.size(); i++)
		{
			messageArrayList.get(i).getMessage();
		}
	}


}