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

	public String makePayment(String type, String ammount, String paymentHolder, String payeeId, String date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database.";
			}
			String query = " insert into payments(`PaymentId`,`Type`,`Ammount`,`PaymentHolder`,`date`,`HospitalID`, `DoctorID`,`PharmacyID`,`PatientID`)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, null);
			preparedStmt.setString(2, type);
			preparedStmt.setString(3, ammount);
			preparedStmt.setString(4, paymentHolder);
			preparedStmt.setString(5, date);
			preparedStmt.setString(6, null);
			preparedStmt.setString(7, null);
			preparedStmt.setString(8, null);
			preparedStmt.setString(9, null);
			switch (paymentHolder) {
			case "Doctor":
				preparedStmt.setString(7, payeeId);
				break;
			case "Hospital":
				preparedStmt.setString(6, payeeId);
				break;
			case "Pharmacy":
				preparedStmt.setString(8, payeeId);
				break;
			case "Patient":
				preparedStmt.setString(9, payeeId);
				break;
			}
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database.";
			}

			output = "<table border=\"1\"><tr><th>Payment ID</th><th>Type</th><th>Ammount</th><th>Payment Holder</th><th>Date</th><th>HospitalID</th><th>Update</th><th>Remove</th></tr>";
			String query = "select * from payments";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

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
			output = "Error while reading.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(int PaymentID, String type, String ammount, String paymentHolder, String date,
			String payeeId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database.";
			}

			String query = "UPDATE payments SET Type=?, Ammount=?, PaymentHolder=?, date=?, HospitalID=?, DoctorID=?, PharmacyID=?, PatientID=? WHERE PaymentID = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setInt(9, PaymentID);
			preparedStmt.setString(1, type);
			preparedStmt.setString(2, ammount);
			preparedStmt.setString(3, paymentHolder);
			preparedStmt.setString(4, date);
			preparedStmt.setString(5, null);
			preparedStmt.setString(6, null);
			preparedStmt.setString(7, null);
			preparedStmt.setString(8, null);
			switch (paymentHolder) {
			case "Doctor":
				preparedStmt.setString(6, payeeId);
				break;
			case "Hospital":
				preparedStmt.setString(5, payeeId);
				break;
			case "Pharmacy":
				preparedStmt.setString(7, payeeId);
				break;
			case "Patient":
				preparedStmt.setString(8, payeeId);
				break;
			}

			boolean status = preparedStmt.execute();
			if(status) {
				output = "Updated Failed";
			}else {
				output = "Update successfully";
			}
			con.close();
			
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
