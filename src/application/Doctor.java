package application;
import java.util.ArrayList;

public class Doctor {
	private int phoneNumber;
	private String email;
	private ArrayList<Patient> patientArrayList;
	private ArrayList<Nurse> nurseArrayList;
	private ArrayList<Message> messageArrayList;
	
	//Default constructor
	Doctor()
	{
		this.phoneNumber = 0;
		this.email = "";
		this.patientArrayList = new ArrayList<Patient>();
		this.nurseArrayList = new ArrayList<Nurse>();
		this.messageArrayList = new ArrayList<Message>();
	}
	
	Doctor(String email, int phoneNumber)
	{
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.patientArrayList = new ArrayList<Patient>();
		this.nurseArrayList = new ArrayList<Nurse>();
		this.messageArrayList = new ArrayList<Message>();
	}
	
	//Methods:
	
	public void addPatient(Patient patient)
	{
		this.patientArrayList.add(patient);
	}
	
	public void addNurse(Nurse nurse)
	{
		this.nurseArrayList.add(nurse);
	}
	
	public void addMessage(Message message)
	{
		this.messageArrayList.add(message);
	}
	
	public void addNote(String note, Patient patient) 
	{
		for (int i = 0; i < patientArrayList.size(); i++)
		{
			if(patientArrayList.get(i) == patient) 
			{
				//patientArrayList.get(i).addNote(note)
			}
		}
	}
	
	public void getMessage() 
	{
		for (int i = 0; i < messageArrayList.size(); i++)
		{
			messageArrayList.get(i).getMessage();
		}
	}
	
	//viewPatientInformation iterates through the doctor's patientArrayList for patient and returns their information. 
	public void viewPatientInformation(Patient patient)
	{
		for (int i = 0; i < patientArrayList.size(); i++)
		{
			if(patientArrayList.get(i) == patient) 
			{
				//patientArrayList.get(i).getInformation()
			}
		}
	}
	
	//viewAllPatientInformation iterates through the doctor's patientArrayList and prints the patients information. 
	public void viewAllPatientInformation()
		{
			for (int i = 0; i < patientArrayList.size(); i++)
			{
				//patientArrayList.get(i).getInformation()
			}
		}
	
	//viewMedicalRecord searches the doctor's patientArrayList for patient object and returns their medical record.
	public void viewMedicalRecord(Patient patient)
	{
		for (int i = 0; i < patientArrayList.size(); i++)
		{
			if(patientArrayList.get(i) == patient) 
			{
				//patient.get(i).getMedicalRecord()
			}
		}
	}
	
	public void prescribeMedication(Patient patient, String medication)
	{
		for (int i = 0; i < patientArrayList.size(); i++)
		{
			if(patientArrayList.get(i) == patient)
			{
				//patientArrayList.get(i).addMedication(medication);
			}
		}
	}
	
}

