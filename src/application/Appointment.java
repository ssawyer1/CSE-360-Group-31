package application;

public class Appointment {
	private String date;
	private String reason;
	private String notes;
	Appointment(){}
	public Appointment(String date, String reason, String notes)
	{
		this.date = date;
		this.reason = reason;
		this.notes = notes;
	}
	public String getDate()
	{
		return this.date;
	}
	public String getReason()
	{
		return this.reason;
	}
	public String getNotes()
	{
		return this.notes;
	}
}
