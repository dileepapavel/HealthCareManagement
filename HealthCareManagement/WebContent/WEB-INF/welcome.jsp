<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Appointment Data</title>
</head>
<body>

<h1>login Successfull </h1>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
String id = request.getParameter("userId");
String driverName = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3306/";
String dbName = "appointment";
String userId = "root";
String password = "";

try {
Class.forName(driverName);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}

Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<h2 align="center"><font><strong>Retrieve Appointments from database </strong></font></h2>
<table align="center" cellpadding="5" cellspacing="5" border="1">
<tr>

</tr>
<tr bgcolor="#A52A2A">
<td><b>appointmentDate</b></td>
<td><b>patientName</b></td>
<td><b>gender</b></td>
<td><b>contactNo</b></td>
<td><b>hospitalName</b></td>
<td><b>doctorName</b></td>
<td><b>Update</b></td>
<td><b>Remove</b></td>
</tr>

 

<%
try{ 
connection = DriverManager.getConnection(connectionUrl+dbName, userId, password);
statement=connection.createStatement();
String sql ="SELECT * FROM appointmenttb";

resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<tr bgcolor="#DEB887">

<td><%=resultSet.getString("appointmentDate") %></td>
<td><%=resultSet.getString("patientName") %></td>
<td><%=resultSet.getString("gender") %></td>
<td><%=resultSet.getString("contactNo") %></td>
<td><%=resultSet.getString("hospitalName") %></td>
<td><%=resultSet.getString("doctorName") %></td>

	<td><input name="btnUpdate" type="button"value="Update"></td>	
	<td><input name="btnRemove" type="button"value="Remove"></td>

</tr>
<%




}

} catch (Exception e) {
e.printStackTrace();
}
%>
</table>




<a href="http://localhost:8082/login/logout.jsp">Logout</a>

</body>
</html>