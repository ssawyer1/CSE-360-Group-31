package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {
	private String message;
	private String date;
	private String sendType; 
	private String sendName;
	
	Message(){}
	
	public Message(String message, LocalDateTime sent, String senderType, String senderName)
	{
		this.message = message;
		this.sendType = senderType;
		this.sendName = senderName;
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
	public String getSendType()
	{
		return this.sendType;
	}
	public String getSendName()
	{
		return this.sendName;
	}
	public void setMessage(String newMsg)
	{
		this.message = newMsg;
	}
	public void setDate(String newDate)
	{
		this.date = newDate;
	}
	public void setSendType(String type)
	{
		this.sendType = type;
	}
	public void setSendName(String name)
	{
		this.sendName = name;
	}
}