package javaFX;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
	private String message;
	private String date;
	
	Message(){}
	
	public Message(String message, LocalDateTime sent)
	{
		this.message = message;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");  
		this.date = dtf.format(sent);
	}
	
	public String getMessage()
	{
		return this.message;
	}
	public String getDate()
	{
		return this.date;
	}
	public void setMessage(String newMsg)
	{
		this.message = newMsg;
	}
	public void setDate(String newDate)
	{
		this.date = newDate;
	}
}