package model;

import com.google.gson.*;
import java.sql.*;
import java.util.Date;

public class AppointmentInfo {
	//A common method to connect to the DB
			private Connection connect()
			 {
			 Connection con = null;
			 try
			 {
			 Class.forName("com.mysql.jdbc.Driver");

			 //Provide the correct details: DBServer/DBName, username, password
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/appointment", "root", "");
			 }
			 catch (Exception e)
			 {e.printStackTrace();}
			
			 return con;
			 }	
		
			public String insertAppointmentInfo( String patientName, String gender, String contactNo, String hospitalName, String doctorName, String appointmentDate)
			{
				String output = "";
				
				try
				 {
				 
					Connection con = connect();
					
					if (con == null)
					{return "Error while connecting to the database for inserting."; } 
				
					// create a prepared statement
					String query = " insert into appointmenttb (`appointmentID`,`patientName`,`gender`,`contactNo`,`hospitalName`,`doctorName`,`appointmentDate`)"
							+ " values (?, ?, ?, ?, ?, ?, ?)";
				
					PreparedStatement preparedStmt = con.prepareStatement(query);
			
					// binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2,patientName);
					 preparedStmt.setString(3,gender);
					 preparedStmt.setString(4,contactNo);
					 preparedStmt.setString(5,hospitalName);
					//preparedStmt.(7, Date.parse(admit_date));
					preparedStmt.setString(6, doctorName); 
					preparedStmt.setString(7,appointmentDate);	
					
					 
					 
					// execute the statement
					 preparedStmt.execute();
					 con.close(); 
					 
					 output = "Inserted successfully";
				  
					 
				 } 
					 catch (Exception e)
					 {
					
						 output = "Error while inserting the appointment information.";
						 System.err.println(e.getMessage());
					 }
					 	return output;
					
					 	
					 	
					 	
				 } 
			public String readAppointmentInfo() 	 
			{		 
				
				String output = ""; 	 
					 
				try
				 {
				 Connection con = connect();
				
				 if (con == null)
				 {return "Error while connecting to the database for reading."; }  
					 
					 
				// Prepare the html table to be displayed 
				 
				 output = "<table border=\"1\"><tr><th>Appointment ID</th><th>Patient Name</th><th>Gender</th><th>Contact No</th><th>Hospital Name</th><th>Doctor Name</th><th>Appointment Date</><th>Update</th><th>Remove</th></tr>"; 
				 
				 
				 
				 String query = "select * from appointmenttb";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query); 
				 
				 
				// iterate through the rows in the result set
				 
				 while (rs.next()) 
				 {

					 String appointmentID 	= Integer.toString(rs.getInt("appointmentID")); 
					 String patientName		=	rs.getString("patientName"); 
					 String gender			= 	rs.getString("gender");
					 String contactNo		= 	rs.getString("contactNo");
					 String hospitalName    =	rs.getString("hospitalName");
					 String doctorName		=	rs.getString("doctorName");
					 String appointmentDate	=	rs.getString("appointmentDate");
		
				 
					// Add into the html table

					 
					 output += "<tr><td>" + appointmentID + "</td>"; 
					 output += "<td>" + patientName + "</td>"; 
					 output += "<td>" + gender + "</td>"; 
					 output += "<td>" + contactNo+ "</td>"; 
					 output += "<td>" +hospitalName  + "</td>"; 
					 output += "<td>" + doctorName+ "</td>"; 
					 output += "<td>" + appointmentDate + "</td>"; 
					
					 
					 
			
						output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
								+ "<td><form method=\"post\" action=\"patient.jsp\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"RegId\" type=\"hidden\" value=\"" + appointmentID  + "\">" + "</form></td></tr>";
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
			 
			public String updateAppointmentInfo(String ID ,String patientName, String gender, String contactNo, String hospitalName, String doctorName, String appointmentDate)
			{ 
			String output = "";  
				 
				 
			try
			 {
					Connection con = connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
			
					// create a prepared statement
			 String query = "UPDATE appointmenttb SET patientName=?,gender=?,contactNo=?,hospitalName=?,doctorName=?,appointmentDate=? WHERE appointmentID=?";
				 
				 
			 PreparedStatement preparedStmt = con.prepareStatement(query);	 
				 
				 
			// binding values
			 preparedStmt.setString(1, patientName); 
			 preparedStmt.setString(2, gender);  
			 preparedStmt.setString(3,  contactNo); 	
			 preparedStmt.setString(4, hospitalName); 
			 preparedStmt.setString(5, doctorName); 	 
			 preparedStmt.setString(6, appointmentDate);  
			 preparedStmt.setInt(7, Integer.parseInt(ID));

			 
			// execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 output = "Updated successfully";
			 
			 } 
			
			catch (Exception e)
			 {
			 
				output = "Error while updating the appointment information.";
				System.err.println(e.getMessage());
			 } 
			
			
			return output;
			} 

			public String deleteAppointmentInfo(String appointmentID)
			{
				String output = ""; 
				
				try
				 {
				
					Connection con = connect();
				 
				 if (con == null)
				 {return "Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				 String query="delete from appointmenttb  where appointmentID=?";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				 preparedStmt.setInt(1, Integer.parseInt(appointmentID)); 
				 
				// execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 output = "Deleted successfully";
				
				 }
				 
			
			
			catch (Exception e)
			 {
					output = "Error while deleting the appointment.";
					System.err.println(e.getMessage());
			 }
			 
				return output;
			 } 
			
			
	}
