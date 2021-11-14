package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Test 
{
	public static void main(String[] args) throws IOException
	{ //	public Patient(String fname, String lname, String user, String pass, String Email, String phone, String pharmacyLoc, String  DOB, String Gender, String insurCo, String insurGr, String insurName, String insurNum)

		// HOW TO USE PATIENT OBJEC FOR PATIENT PORTAL
		 Patient me = new Patient();
		 me.load("Patients/MayaEleff.txt");
		 System.out.println(me.getAge()); 
		 System.out.println(me.getDOB());
		 System.out.println(me.getEmail()); 
		 me.setEmail("meleff@gmail.com");
		 System.out.println(me.getEmail()); 
		 ArrayList<Appointment> appts = me.getAptts();
		 for(int i = 0; i < appts.size(); i++) 
		 {
			 System.out.println(appts.get(i).getDate());
			 System.out.println(appts.get(i).getTemp());
			 System.out.println(appts.get(i).getNNotes()); 
		}
		me.save();	
		
		// HOW TO USE NURSE OBJECT FOR NURSE PORTAL
		System.out.println();
		Nurse nurse1 = new Nurse();
		nurse1.load("Nurses/NancyBrown.txt");
		for(int i = 0; i < nurse1.getDoctors().size(); i++)
		{
			for(int j = 0; j < nurse1.getDoctors().get(i).getPatients().size(); j++)
			{
				System.out.println(nurse1.getDoctors().get(i).getPatients().get(j).getFullName());
			}
		}
		
		// HOW TO USE DOCTOR OBJECT FOR DOCTOR PORTAL
		System.out.println();
		Doctor doc1 = new Doctor();
		doc1.load("Doctors/JohnSmith.txt");
		for(int i = 0; i < doc1.getPatients().size(); i++)
		{
			System.out.println(doc1.getPatients().get(i).getAge());
		}
		
		// ADDING MESSAGE FROMD DOCTOR SIDE
		//LocalDateTime now = LocalDateTime.now();
		//doc1.getPatients().get(0).addDoctorMsg(now, "Hi patient, ths is your doctor.", "doctor", doc1.getFullName()); // either doctor or patient
		//doc1.getPatients().get(0).save(); // any information have to save for specifc patient
		//me.save();
		
		// ADDING MESSAGES FROM PATIENT SIDE
		//me.addDoctorMsg(now,"Hi doctor, this is your patient.", "patient", me.getFullName()); 
		//me.save();
				
	}
}
