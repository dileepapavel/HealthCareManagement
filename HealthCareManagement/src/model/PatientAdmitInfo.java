package model;

import com.google.gson.*;

import dbconnection.DBConnection;

import java.sql.*;
import java.util.Date;

public class PatientAdmitInfo {
		public String insertPatientAdmitInfo( String patientName, String gender, String bloodGroup, String disease, String admit_date, String roomNo, String doctorName)
		{
			String output = "";
			
			try
			 {
			 
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.connect();
				
				if (con == null)
				{return "Error while connecting to the database for inserting."; } 
			
				// create a prepared statement
				String query = " insert into patient_admit_info (`patientAdmitID`,`patientName`,`gender`,`bloodGroup`,`disease`,`admitDate`,`roomNo`,`doctorName`)"
						+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			
				PreparedStatement preparedStmt = con.prepareStatement(query);
		
				// binding values
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2,patientName);
				 preparedStmt.setString(3,gender);
				 preparedStmt.setString(4,bloodGroup);
				 preparedStmt.setString(5,disease);
				//preparedStmt.(7, Date.parse(admit_date));
				preparedStmt.setString(6, admit_date); 
				preparedStmt.setString(7,roomNo);	
				preparedStmt.setString(8,doctorName);
				 
				 
				// execute the statement
				 preparedStmt.execute();
				 con.close(); 
				 
				 output = "Inserted successfully";
			  
				 
			 } 
				 catch (Exception e)
				 {
				
					 output = "Error while inserting the patient admit information.";
					 System.err.println(e.getMessage());
				 }
				 	return output;
				 
			 } 
				 
		
		
		public String readPatientAdmitInfo() 	 
		{		 
			
			String output = ""; 	 
				 
			try
			 {
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.connect();
			
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }  
				 
				 
			// Prepare the html table to be displayed 
			 
			 output = "<table border=\"1\"><tr><th>Patient Admit ID</th><th>Patient Name</th><th>Gender</th><th>Blood Group</th><th>Disease</th><th>Admit Date</th><th>Room No</th><th>Doctor Name</><th>Update</th><th>Remove</th></tr>"; 
			 
			 
			 
			 String query = "select * from patient_admit_info";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 
			// iterate through the rows in the result set
			 
			 while (rs.next()) 
			 {

				 String patientAdmitID 	= Integer.toString(rs.getInt("patientAdmitID")); 
				 String patientName		=	rs.getString("patientName"); 
				 String gender			= 	rs.getString("gender");
				 String bloodGroup		= 	rs.getString("bloodGroup");
				 String disease			=	rs.getString("disease");
				 String admitDate		=	rs.getString("admitDate");
				 String roomNo			=	rs.getString("roomNo");
				 String doctorName		= 	rs.getString("doctorName");
			 
				// Add into the html table

				 
				 output += "<tr><td>" + patientAdmitID + "</td>"; 
				 output += "<td>" + patientName + "</td>"; 
				 output += "<td>" + gender + "</td>"; 
				 output += "<td>" + bloodGroup + "</td>"; 
				 output += "<td>" + disease + "</td>"; 
				 output += "<td>" + admitDate+ "</td>"; 
				 output += "<td>" + roomNo	 + "</td>"; 
				 output += "<td>" + doctorName	 + "</td>"; 
				 
				 
			// buttons
		/*	 output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
				 + "<td><form method=\"post\" action=\"PatientAdmit.jsp\">"
				 + "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"
				 class=\"btn btn-danger\">"
				 + "<input name=\"itemID\" type=\"hidden\" value=\"" + itemID
				 + "\">" + "</form></td></tr>";
				 } 
				 
			*/ 
					output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
							+ "<td><form method=\"post\" action=\"patient.jsp\">"
							+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
							+ "<input name=\"RegId\" type=\"hidden\" value=\"" + patientAdmitID  + "\">" + "</form></td></tr>";
			 }
				 con.close();
				 
				 // Complete the html table
				 output += "</table>";
				 }
		
			 catch (Exception e)
			 {
				 output = "Error while reading the information.";
				 System.err.println(e.getMessage());
			 }
			 	
			 return output;
			 	
		
			 	 
		 } 
			 
		public String updatePatientAdmitInfo(String ID ,String patientName, String gender, String bloodGroup, String disease, String admitdate, String roomNo, String doctorName)
		{ 
		String output = "";  
			 
			 
		try
		 {
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();
				
				if (con == null)
				{return "Error while connecting to the database for updating."; }
		
				// create a prepared statement
		 String query = "UPDATE patient_admit_info SET patientName=?,gender=?,bloodGroup=?,disease=?,admitDate=?,roomNo=?,doctorName=? WHERE patientAdmitID=?";
			 
			 
		 PreparedStatement preparedStmt = con.prepareStatement(query);	 
			 
			 
		// binding values
		 preparedStmt.setString(1, patientName); 
		 preparedStmt.setString(2, gender);  
		 preparedStmt.setString(3,  bloodGroup); 	
		 preparedStmt.setString(4, disease); 
		 preparedStmt.setString(5, admitdate); 	 
		 preparedStmt.setString(6, roomNo);  
		 preparedStmt.setString(7, doctorName);
		 preparedStmt.setInt(8, Integer.parseInt(ID));

		 
		// execute the statement
		 preparedStmt.execute();
		 con.close();
		 
		 output = "Updated successfully";
		 
		 } 
		
		catch (Exception e)
		 {
		 
			output = "Error while updating the admit information.";
			System.err.println(e.getMessage());
		 } 
		
		
		return output;
		} 
		
		




		public String deletePatientAdmitInfo(String patientAdmitID)
		{
			String output = ""; 
			
			try
			 {
			
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.connect();
			 
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			 String query="delete from patient_admit_info where patientAdmitID=?";
			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			 preparedStmt.setInt(1, Integer.parseInt(patientAdmitID)); 
			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Deleted successfully";
			
			 }
			 
		
		
		catch (Exception e)
		 {
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
		 }
		 
			return output;
		 } 
		
	 }
