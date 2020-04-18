<%@ page import ="model.Hospital"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%
 //Insert item----------------------------------
if (request.getParameter("hosId") != null)
 {
		 Hospital hospitalObj = new Hospital();
		 String stsMsg = hospitalObj.insertHospital(request.getParameter("hosName"),
		 request.getParameter("hosAddress"),
		 request.getParameter("hosContactNo"),
		 request.getParameter("hosCapacity"),
		 request.getParameter("hosUnits"));
		 
		 session.setAttribute("statusMsg", stsMsg);
 }
%>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hospital Management</title>
</head>
<body>
	<h1>Hospital Management</h1>
	<form method="post" action="hospitals.jsp">
		 Hospital Name: <input name="hosName" type="text"><br> 
		 Hospital Address: <input name="hosAddress" type="text"><br> 
		 Hospital ContactNo:<input name="hosContactNo" type="text"><br> 
		 Hospital Capacity: <input name="hosCapacity" type="text"><br>
		 Hospital Units: <input name="hosUnits" type="text"><br>
		  <input name="btnSubmit" type="submit" value="Save">
   </form>
 <% out.print(session.getAttribute("statusMsg")); %>
<br>


</body>
</html>