package model;

import java.sql.Connection;
import java.sql.DriverManager;
import dbconnection.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Doctor 
{ 	

	public String insertDoctor(int ID,String age, String name, String mail, String speciality,String MediRegNo, String workedHospital)
	{
		String output = "";
		try
		{
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();
			
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
				// create a prepared statement
				String query = " insert into doctor(`doctorID`,`doctorAge`,`doctorName`,`doctorMail`,`doctorSpeciality`,`MediRegNo`,`workedHospital`)"
						+ " values (?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, ID);
				preparedStmt.setString(2, age);
				preparedStmt.setString(3, name);
				preparedStmt.setString(4, mail);
				preparedStmt.setString(5, speciality);
				preparedStmt.setString(6, MediRegNo);
				preparedStmt.setString(7, workedHospital);

				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Inserted successfully";
			}
			catch (Exception e)
			{
				output = "Error while inserting the doctor details.";
				System.err.println(e.getMessage());
			}
			
			return output;
		}
		
		public String readDoctor()
		{
			String output = "";
				
			try
			{
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Age</th><th>Doctor Name</th><th>Doctor Mail</th><th>Doctor Speciality</th><th>Medical Registration No</th><th>Hospitals worked in</th><th>Update</th><th>Remove</th></tr>";
				
				String query = "select * from doctor";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String doctorID = Integer.toString(rs.getInt("doctorID"));
					String doctorAge = rs.getString("doctorAge");
					String doctorName = rs.getString("doctorName");
					String doctorMail =rs.getString("doctorMail");
					String doctorSpeciality = rs.getString("doctorSpeciality");
					String MediRegNo = rs.getString("MediRegNo");
					String workedHospital = rs.getString("workedHospital");
				
					// Add into the html table
					output += "<tr><td>" + doctorAge + "</td>";
					output += "<td>" + doctorName + "</td>";
					output += "<td>" + doctorMail + "</td>";
					output += "<td>" + doctorSpeciality + "</td>";
					output += "<td>" + MediRegNo + "</td>";
					output += "<td>" + workedHospital + "</td>";
				
					// buttons
					output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
									+ "<td><form method=\"post\" action=\"doctor.jsp\">"+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
											+ "<input name=\"doctorID\" type=\"hidden\" value=\"" + doctorID
											+ "\">" + "</form></td></tr>";
				}
				
				con.close();
				
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the doctor details.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String updateDoctor(String ID, String age, String name, String mail, String speciality , String MediRegNo, String workedHospital)
		{
		String output = "";
		
		try
		{
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();
				
				if (con == null)
				{return "Error while connecting to the database for updating."; }
		
				// create a prepared statement
				String query = "UPDATE doctor SET doctorAge=?,doctorName=?,doctorMail=?,doctorSpeciality=?,MediRegNo=?,workedHospital=? WHERE doctorID=?";
								
				PreparedStatement preparedStmt = con.prepareStatement(query);
		
				// binding values
				preparedStmt.setString(1, age);
				preparedStmt.setString(2, name);
				preparedStmt.setString(3, mail);
				preparedStmt.setString(4, speciality);
				preparedStmt.setString(5, MediRegNo);
				preparedStmt.setString(6, workedHospital);
				preparedStmt.setInt(7, Integer.parseInt(ID));
		
				// execute the statement
				preparedStmt.execute();
				con.close();
		
				output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the doctor details.";
			System.err.println(e.getMessage());
		}
			return output;
	}
	
	public String deleteDoctor(String doctorID)
	{
		String output = "";
		
		try
		{
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();
			
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
		
			// create a prepared statement
			String query = "delete from doctor where doctorID=?";
		
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(doctorID));
		
			// execute the statement
			preparedStmt.execute();
			con.close();
		
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the doctor details.";
			System.err.println(e.getMessage());
		}
		
		return output;
		}
}
	
