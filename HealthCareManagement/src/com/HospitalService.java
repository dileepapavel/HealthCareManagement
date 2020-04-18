package com;

import model.Hospital;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document; 

@Path("/Hospitals") 
public class HospitalService 
{
	 Hospital hospitalObj = new Hospital();
	 
	 @GET
	 @Path("/")
	 @Produces(MediaType.TEXT_HTML)
	 public String readHospitals()
	  {
		 return hospitalObj.readHospitals();
	  }
	 
	 @POST
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String insertHospital(@FormParam("hosName") String hosName,
	  @FormParam("hosAddress") String hosAddress,
	  @FormParam("hosContactNo") String hosContactNo,
	  @FormParam("hosCapacity") String hosCapacity,
	  @FormParam("hosUnits") String hosUnits)
	 {
	  String output = hospitalObj.insertHospital(hosName, hosAddress, hosContactNo, hosCapacity, hosUnits);
	  return output;
	 }

	 @PUT
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_JSON)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String updateHospital(String hospitalData)
	 {
	 //Convert the input string to a JSON object
	  JsonObject hospitalObject = new JsonParser().parse(hospitalData).getAsJsonObject();
	 
	  //Read the values from the JSON object
	  String hosId = hospitalObject.get("hosId").getAsString();
	  String hosName = hospitalObject.get("hosName").getAsString();
	  String hosAddress = hospitalObject.get("hosAddress").getAsString();
	  String hosContactNo = hospitalObject.get("hosContactNo").getAsString();
	  String hosCapacity = hospitalObject.get("hosCapacity").getAsString();
	  String hosUnits = hospitalObject.get("hosUnits").getAsString();
	  
	  String output = hospitalObj.updateHospital(hosId, hosName, hosAddress, hosContactNo, hosCapacity, hosUnits);
	  return output;
	 }

	 @DELETE
	 @Path("/")
	 @Consumes(MediaType.APPLICATION_XML)
	 @Produces(MediaType.TEXT_PLAIN)
	 public String deleteHospital(String hospitalData)
	 {
	 //Convert the input string to an XML document
	  Document doc = Jsoup.parse(hospitalData, "", Parser.xmlParser());

	 //Read the value from the element <itemID>
	  String hosId = doc.select("hosId").text();
	  String output = hospitalObj.deleteHospital(hosId);
	 return output;
	 }
	 
	 
	 }

