package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment 
{
	
	private String date;
	private String reason;
	private String height;
	private String weight;
	private String bloodPressure;
	private String temp;
	private String doctorNotes;
	private String nurseNotes;
	private String prescripTime;
	
	Appointment(){  
		this.date = " ";
		this.reason = " ";
		this.doctorNotes = " ";
		this.nurseNotes = " ";
		this.height = " ";
		this.weight = " ";
		this.bloodPressure = " ";
		this.temp = " ";
		this.prescripTime = " ";
	}
	
	public Appointment(LocalDateTime dateCreated, String reason, String docNotes, String nNotes, String hght, String wght, String bp, String temperature, String prescripGiven)
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
		this.date = dtf.format(dateCreated);
		this.prescripTime = " ";
		this.reason = reason;
		this.doctorNotes = docNotes;
		this.nurseNotes = nNotes;
		this.height = hght;
		this.weight = wght;
		this.bloodPressure = bp;
		this.temp = temperature;
	}
	
	public String getDate()
	{
		return this.date;
	}
	
	public String getPrescipTime()
	{
		return this.prescripTime;
	}
	
	public String getReason()
	{
		return this.reason;
	}
	
	public String getDoctorNotes()
	{
		return this.doctorNotes;
	}
	
	public String getNurseNotes()
	{
		return this.nurseNotes;
	}
	
	public String getHeight()
	{
		return this.height;
	}
	
	public String getWeight()
	{
		return this.weight;
	}
	
	public String getBP()
	{
		return this.bloodPressure;
	}
	
	public String getTemp()
	{
		return this.temp;
	}
	public void setDate(String newDate)
	{
		this.date = newDate;
	}
	
	public void setPrescripDate(String newDate)
	{
		this.prescripTime = newDate;
	}
	
	public void setReason(String newReason)
	{
		this.reason = newReason;
	}
	
	public void setDocNotes(String newNotes)
	{
		this.doctorNotes = newNotes;
	}
	
	public void setNNotes(String newNotes)
	{
		this.nurseNotes = newNotes;
	}
	
	public void setHeight(String newHeight)
	{
		this.height = newHeight;
	}
	
	public void setWeight(String newWeight)
	{
		this.weight = newWeight;
	}
	
	public void setBP(String newBP)
	{
		this.bloodPressure =newBP;
	}
	
	public void setTemp(String newTemp)
	{
		this.temp = newTemp;
	}
}
