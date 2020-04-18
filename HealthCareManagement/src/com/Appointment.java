package com;

import model.AppointmentInfo;


//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/appointment")

public class Appointment {
AppointmentInfo api=new AppointmentInfo();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	
	public String readItems()
	 {
		return api.readAppointmentInfo();
	 
	 }
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertAppointmentInfo (
		
			@FormParam("patientName") String patientName,
			@FormParam("gender") String gender,
			@FormParam("contactNo") String contactNo,
			@FormParam("hospitalName") String hospitalName,
			@FormParam("doctorName") String doctorName,
			@FormParam("appointmentDate") String appointmentDate)
		
	{
				
	 String output =api.insertAppointmentInfo(patientName, gender, contactNo, hospitalName, doctorName, appointmentDate);
	return output;
	}
	
	//Update API JSON
	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateappointment(String appointmenttData)
		{
		
			//Convert the input string to a JSON object
		 JsonObject paitientObject = new JsonParser().parse(appointmenttData).getAsJsonObject();
		
		 //Read the values from the JSON object
		 String appointmentID =  paitientObject.get("appointmentID").getAsString();
		 String patientName =  paitientObject.get("patientName").getAsString();
		 String gender =  paitientObject.get("gender").getAsString();
		 String contactNo = paitientObject.get("contactNo").getAsString();
		 String hospitalName = paitientObject.get("hospitalName").getAsString();
		 String doctorName= paitientObject.get("doctorName").getAsString();
		 String appointmentDate = paitientObject.get("appointmentDate").getAsString();
		
		
		 String output =  api.updateAppointmentInfo(appointmentID, patientName, gender, contactNo, hospitalName, doctorName, appointmentDate);
		
		 return output;
		}
		

		@DELETE
		@Path("/")
		@Consumes(MediaType.APPLICATION_XML)
		@Produces(MediaType.TEXT_PLAIN)
		
		public String deleteAppointmentInfo(String appointmentdata)
		{
		//Convert the input string to an XML document
		 Document doc = Jsoup.parse( appointmentdata, "", Parser.xmlParser());

		//Read the value from the element 
		 String appointmentID = doc.select("appointmentID").text();
		 String output = api.deleteAppointmentInfo(appointmentID);
		return output;
		}
}