package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Immunization 
{
	private String type;
	private String date;
	
	Immunization(){}
	
	public Immunization(String type, LocalDateTime taken)
	{
		this.type = type;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
		this.date = dtf.format(taken);
	}
	
	public String getType()
	{
		return this.type;
	}
	public String getDate()
	{
		return this.date;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
}