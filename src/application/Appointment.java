package application;

public class Appointment {
	private String date = " ";
	private String description = " ";
	Appointment(){}
	public Appointment(String date, String description)
	{
		this.date = date;
		this.description = description;
	}
	public String getDate()
	{
		return date;
	}
	public String getDescription()
	{
		return description;
	}

}
