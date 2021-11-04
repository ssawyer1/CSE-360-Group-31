package application;
import java.util.ArrayList;

public class Doctor {
	private int phoneNumber;
	private String email;
	private ArrayList<Patient> patientArrayList;
	private ArrayList<Nurse> nurseArrayList;
	
	Doctor()
	{
		this.phoneNumber = 0;
		this.email = "";
		this.patientArrayList = new ArrayList<Patient>();
		this.nurseArrayList = new ArrayList<Nurse>();
	}
	
	Doctor(String email, int phoneNumber)
	{
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.patientArrayList = new ArrayList<Patient>();
	}
	
	//viewPatientInformation searches the doctor's patientArrayList for patient and returns their information. 
	public void viewPatientInformation(Patient patient)
	{
		for (int i = 0; i < patientArrayList.size(); i++)
		{
			if(patientArrayList.get(i) == patient) 
			{
				//patient->getInformation
			}
		}
	}
	
	//viewMedicalRecord searches the doctor's patientArrayList for patient and returns their medical record.
	public void viewMedicalRecord(Patient patient)
	{
		for (int i = 0; i < patientArrayList.size(); i++)
		{
			if(patientArrayList.get(i) == patient) 
			{
				//patient->getMedicalRecord
			}
		}
	}
}
