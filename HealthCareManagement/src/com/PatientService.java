package com;

import model.Patient;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Patients")
public class PatientService {
	Patient patientObj = new Patient();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPatient() {
		
		return patientObj.readPatients();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPatient(@FormParam("patientName") String patientName,
			@FormParam("age") String age, @FormParam("nic") String nic, @FormParam("phoneNo") String phoneNo) {
		String output = patientObj.insertPatient( patientName, age, nic, phoneNo);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatient(String patientData) {
		// Convert the input string to a JSON object
		JsonObject patient = new JsonParser().parse(patientData).getAsJsonObject();
		// Read the values from the JSON object
		 String userID = patient.get("userID").getAsString();
		 String patientName = patient.get("patientName").getAsString();
		 String age = patient.get("age").getAsString();
		 String nic = patient.get("nic").getAsString();
		 String phoneNo = patient.get("phoneNo").getAsString();
		String output = patientObj.updatePatient(userID, patientName, age, nic, phoneNo);
		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientData) {
		// Convert the input string to an XML document
		Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());

		// Read the value from the element <userID>
		String userID = doc.select("userID").text();
		String output = patientObj.deletePatients(userID);
		return output;
	}
	
}