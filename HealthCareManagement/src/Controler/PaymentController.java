package Controler;

import java.sql.*;
import java.util.Date;

public class PaymentController {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcaresystem", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertItem(String code, String name, String price, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into items(`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, desc);
//execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String makePayment(String type, double ammount, String paymentHolder, int payeeId, Date date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			String query = " insert into payments(`PaymentId`,`Type`,`Ammount`,`PaymentHolder`,`date`,`HospitalID`, `DoctorID`,`PharmacyID`,`PatientID`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, null);
			preparedStmt.setString(2, type);
			preparedStmt.setDouble(3, ammount);
			preparedStmt.setString(4, paymentHolder);
			preparedStmt.setString(5, "");
			preparedStmt.setString(6, null);
			preparedStmt.setString(7, null);
			preparedStmt.setString(8, null);
			preparedStmt.setString(9, null);
			switch (paymentHolder) {
			case "Doctor":
				preparedStmt.setInt(7, payeeId);
				break;
			case "Hospital":
				preparedStmt.setInt(6, payeeId);
				break;
			case "Pharmacy":
				preparedStmt.setInt(8, payeeId);
				break;
			case "Patient":
				preparedStmt.setInt(9, payeeId);
				break;
			}
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Payment ID</th><th>Type</th><th>Ammount</th><th>Payment Holder</th><th>Date</th><th>HospitalID</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
			while (rs.next()) {
				String paymentID = Integer.toString(rs.getInt("PaymentID"));
				String type = rs.getString("Type");
				String ammount = Integer.toString(rs.getInt("Ammount"));
				String paymentHolder = rs.getString("PaymentHolder");
				String date = rs.getString("Date");
				String hospitalID = rs.getString("HospitalID");

				output += "<tr><td>" + paymentID + "</td>";
				output += "<td>" + type + "</td>";
				output += "<td>" + ammount + "</td>";
				output += "<td>" + paymentHolder + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + hospitalID + "</td>";
// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + paymentID + "\">" + "</form></td></tr>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateItem(String ID, String code, String name, String price, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
// create a prepared statement
			String query = "UPDATE items SET itemCode=?,itemName=?,itemPrice=?,itemDesc=?WHERE itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteItem(String itemID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from items where itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
