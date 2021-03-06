package com;

import model.Doctor;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/Doctor")

public class DoctorService {
	Doctor doctorObj = new Doctor();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctor()
	{
	return doctorObj.readDoctor();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("doctorID") int doctorID,@FormParam("doctorAge") String doctorAge,@FormParam("doctorName") String doctorName,@FormParam("doctorMail") String doctorMail,@FormParam("doctorSpeciality") String doctorSpeciality,@FormParam("MediRegNo") String MediRegNo,@FormParam("workedHospital") String workedHospital)
	{
		String output = doctorObj.insertDoctor(doctorID, doctorAge, doctorName, doctorMail, doctorSpeciality,MediRegNo, workedHospital);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String doctorData)
	{
	//Convert the input string to a JSON object
	JsonObject doctorObject = new JsonParser().parse(doctorData).getAsJsonObject();
	//Read the values from the JSON object
	String doctorID = doctorObject.get("doctorID").getAsString();
	String doctorAge = doctorObject.get("doctorAge").getAsString();
	String doctorName = doctorObject.get("doctorName").getAsString();
	String doctorMail = doctorObject.get("doctorMail").getAsString();
	String doctorSpeciality = doctorObject.get("doctorSpeciality").getAsString();
	String MediRegNo = doctorObject.get("MediRegNo").getAsString();
	String workedHospital = doctorObject.get("workedHospital").getAsString();
	String output = doctorObj.updateDoctor(doctorID, doctorAge, doctorName, doctorMail, doctorSpeciality,MediRegNo,workedHospital);
	return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(String doctorData)
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(doctorData, "", Parser.xmlParser());
	//Read the value from the element <itemID>
	String doctorID = doc.select("doctorID").text();
	String output = doctorObj.deleteDoctor(doctorID);
	return output;
	}
}
