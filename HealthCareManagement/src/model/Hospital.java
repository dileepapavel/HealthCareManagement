package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import dbconnection.DBConnection;

public class Hospital {

	public String insertHospital(String name, String address, String contactno, String capacity, String units)
	 {
				String output = "";
				
				try
				{
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
					if (con == null)
					{return "Error while connecting to the database for inserting."; }
	
					// create a prepared statement
					String query = " insert into hospitals(`hosId`,`hosName`,`hosAddress`,`hosContactNo`,`hosCapacity`,`hosUnits`)"
								   + " values (?, ?, ?, ?, ?, ?)";
	 
					PreparedStatement preparedStmt = con.prepareStatement(query);
	 
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, name);
					 preparedStmt.setString(3, address);
					 preparedStmt.setString(4, contactno);
					 preparedStmt.setDouble(5, Double.parseDouble(capacity));
					 preparedStmt.setString(6, units);
	 
	 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 output = "Inserted successfully";
	 }
	 catch (Exception e)
	 {
		 	output = "Error while inserting the hospital.";
		 	System.err.println(e.getMessage());
	 }
	  return output;
}
	public String readHospitals()
	 {
			String output = "";
	 try
	 {
			DBConnection dbconnection = new DBConnection();
			Connection con = dbconnection.connect();
		 		
		 		if (con == null)
		 		{return "Error while connecting to the database for reading."; }
	 
		 		// Prepare the html table to be displayed
		 		output = "<table border=\"1\"><tr><th>Hospital Name</th><th>Hospital Address</th><th>Hospital ContactNumber</th><th>Hospital Capacity</th><th>Hospital Units</th><th>Update</th><th>Remove</th></tr>";
	  
		 		String query = "select * from hospitals";
		 		Statement stmt = con.createStatement();
		 		ResultSet rs = stmt.executeQuery(query);
	 
		 		// iterate through the rows in the result set
		 		while (rs.next())
		 		{
					 String hosId = Integer.toString(rs.getInt("hosId"));
					 String hosName = rs.getString("hosName");
					 String hosAddress = rs.getString("hosAddress");
					 String hosContactNo = Integer.toString(rs.getInt("hosContactNo"));
					 String hosCapacity = Double.toString(rs.getDouble("hosCapacity"));
					 String hosUnits = Integer.toString(rs.getInt("hosUnits"));
	 
					 // Add into the html table
					 output += "<tr><td>" + hosName + "</td>";
					 output += "<td>" + hosAddress + "</td>";
					 output += "<td>" + hosContactNo + "</td>";
					 output += "<td>" + hosCapacity + "</td>";
					 output += "<td>" + hosUnits + "</td>";
	 
					 	// buttons
						output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
								+ "<td><form method=\"post\" action=\"items.jsp\">"
								+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
								+ "<input name=\"itemID\" type=\"hidden\" value=\"" + hosId + "\">" + "</form></td></tr>";
	 }
	 con.close();
	 
	 // Complete the html table
	 	output += "</table>";
	 }
	 catch (Exception e)
	 {
		 output = "Error while reading the hospitals.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	 
	public String updateHospital(String id, String name, String address,  String contactno, String capacity, String units)
	 {
				String output = "";
				try
	 {
					DBConnection dbconnection = new DBConnection();
					Connection con = dbconnection.connect();
					
					if (con == null)
					{return "Error while connecting to the database for updating."; }
	 
					// create a prepared statement
					String query = "UPDATE hospitals SET hosName=?,hosAddress=?,hosContactNo=?,hosCapacity=?,hosUnits=? WHERE hosId=?";
	
				   PreparedStatement preparedStmt = con.prepareStatement(query);
	
					// binding values
					 preparedStmt.setString(1, name);
					 preparedStmt.setString(2, address);
					 preparedStmt.setInt(3, Integer.parseInt(contactno));
					 preparedStmt.setDouble(4, Double.parseDouble(capacity));
					 preparedStmt.setInt(5, Integer.parseInt(units));
					 preparedStmt.setInt(6, Integer.parseInt(id));
	 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 output = "Updated successfully";
	 }
	 catch (Exception e)
	 {
		 			output = "Error while updating the hospital.";
		 			System.err.println(e.getMessage());
	 }
				
	 return output;
	 }
	
	public String deleteHospital(String hosId)
	 {
			String output = "";
			
			try
	 {
				DBConnection dbconnection = new DBConnection();
				Connection con = dbconnection.connect();
				
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
	 
				// create a prepared statement
				String query = "delete from hospitals where hosId=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
	
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(hosId));
	 
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				output = "Deleted successfully";
	 }
	 catch (Exception e)
	 {
		 output = "Error while deleting the hospital.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 }
	} 

