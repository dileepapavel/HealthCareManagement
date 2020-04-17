package com;

import model.Pharmacy;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;
@Path("/PharmacyService") 

public class PharmacyService {
	Pharmacy phrObj = new Pharmacy();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPharmacy()
	 {
	 return phrObj.readPharmacy(); 
	 } 
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPharmacy(@FormParam("phName") String phName,
	 @FormParam("phAddr") String phAddr,
	 @FormParam("phOwner") String phOwner,
	 @FormParam("contact") String contact,
	 @FormParam("regDate") String regDate,
	 @FormParam("email") String email)
	{
	 String output = phrObj.insertPharmacy(phName, phAddr, phOwner, contact,regDate,email);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePharmacy(String pharmacyData)
	{
	//Convert the input string to a JSON object
	 JsonObject phrObject = new JsonParser().parse(pharmacyData).getAsJsonObject();
	//Read the values from the JSON object
	 String RegId = phrObject.get("RegId").getAsString();
	 String phName = phrObject.get("phName").getAsString();
	 String phAddr = phrObject.get("phAddr").getAsString();
	 String phOwner = phrObject.get("phOwner").getAsString();
	 String contact = phrObject.get("contact").getAsString();
	 String regDate = phrObject.get("regDate").getAsString();
	 String email = phrObject.get("email").getAsString();
	 String output = phrObj.updatePharmacy(RegId, phName, phAddr, phOwner, contact,regDate,email);
	return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePharmacy(String pharmacyData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(pharmacyData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 //Integer RegId = doc.select("RegId").size();
	 String  RegId = doc.select("RegId").text();
	 String output = phrObj.deletePharmacy(RegId);
	return output;
	}


	
}

