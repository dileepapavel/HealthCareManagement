package com;

import model.PatientAdmitInfo;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/paitent") 

public class PatientAdmit {
PatientAdmitInfo pai=new PatientAdmitInfo();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
	 {
		return pai.readPatientAdmitInfo();
	 
	 } 
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatientAdmitInfo(
		
			@FormParam("patientName") String patientName,
			@FormParam("gender") String gender,
			@FormParam("bloodGroup") String bloodGroup,
			@FormParam("disease") String disease,
			@FormParam("admitDate") String admitDate,
			@FormParam("roomNo") String roomNo,
			@FormParam("doctorName") String doctorName)
	{
				
	 String output =pai.insertPatientAdmitInfo(patientName, gender, bloodGroup, disease, admitDate, roomNo, doctorName);
	return output;
	}

	
	//Update API JSON
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String paitientData)
	{
	
		//Convert the input string to a JSON object
	 JsonObject paitientObject = new JsonParser().parse(paitientData).getAsJsonObject();
	
	 //Read the values from the JSON object
	 String patientAdmitID =  paitientObject.get("patientAdmitID").getAsString();
	 String patientName =  paitientObject.get("patientName").getAsString();
	 String gender =  paitientObject.get("gender").getAsString();
	 String bloodGroup = paitientObject.get("bloodGroup").getAsString();
	 String disease = paitientObject.get("disease").getAsString();
	 String admitDate = paitientObject.get("admitDate").getAsString();
	 String roomNo = paitientObject.get("roomNo").getAsString();
	 String doctorName= paitientObject.get("doctorName").getAsString();
	
	 String output =  pai.updatePatientAdmitInfo(patientAdmitID , patientName, gender, bloodGroup, disease, admitDate, roomNo, doctorName);
	return output;
	
	}
	
	/////test

	/*	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatientAdmitInfo( 
	
		@FormParam("patientAdmitID ") String patientAdmitID ,
		@FormParam("patientName") String patientName,
		@FormParam("gender") String gender,
		@FormParam("bloodGroup") String bloodGroup,
		@FormParam("disease") String disease,
		@FormParam("admitDate") String admitDate,
		@FormParam("roomNo") String roomNo,
		@FormParam("doctorName") String doctorName)
{
			
 String output =pai.updatePatientAdmitInfo(patientAdmitID, patientName, gender, bloodGroup, disease, admitDate, roomNo, doctorName);
return output;
}
	*/
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatientAdmitInfo(String patientdata)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(patientdata, "", Parser.xmlParser());

	//Read the value from the element 
	 String patientAdmitID = doc.select("patientAdmitID ").text();
	 String output = pai.deletePatientAdmitInfo(patientAdmitID);
	return output;
	}
	
	
}