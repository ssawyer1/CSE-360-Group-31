package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Prescription 
{
	private String type;
	private String date;
	private String directions;
	private String stopDate;
	
	Prescription(){}
	
	public Prescription(String type, LocalDateTime taken, String dir, String stopdate)
	{
		this.type = type;
		this.directions = dir;
		this.stopDate = stopdate;
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
	public String getDir()
	{
		return this.directions;
	}
	public String getStopDate()
	{
		return this.stopDate;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public void setDate(String date)
	{
		this.date = date;
	}
	public void setDir(String dir)
	{
		this.directions = dir;
	}
	public void setStopDate(String stop)
	{
		this.stopDate = stop;
	}
}