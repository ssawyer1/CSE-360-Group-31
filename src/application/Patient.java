package application;

public class Patient {
	private Appointment appt;
	private String name;
	private String prescriptions;
	private String weight;
	private String height;
	private String bloodPressure;
	private String bodyTemp;
	private String age;
	
	Patient() {}
	public Patient(String name, String prescriptions, String weight, String height, String bloodPressure, String bodyTemp, String age)
	{
		this.name = name;
		this.prescriptions = prescriptions;
		this.weight = weight;
		this.height = height;
		this.bloodPressure = bloodPressure;
		this.bodyTemp = bodyTemp;
		this.age = age;
	}
	public String getName()
	{
		return this.name;
	}
	public String getPrescriptions()
	{
		return this.prescriptions;
	}
	public String getWeight()
	{
		return this.weight;
	}
	public String getHeight()
	{
		return this.height;
	}
	public String getBloodPressure()
	{
		return this.bloodPressure;
	}
	public String getBodyTemp()
	{
		return this.bodyTemp;
	}
	public String getAge()
	{
		return this.age;
	}
}
