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
	public String insertDoctor(@FormParam("doctorID") int doctorID,@FormParam("doctorAge") String doctorAge,@FormParam("doctorName") String doctorName,@FormParam("doctorMail") String doctorMail,@FormParam("doctorSpeciality") String doctorSpeciality)
	{
		String output = doctorObj.insertDoctor(doctorID, doctorAge, doctorName, doctorMail, doctorSpeciality);
		return output;
	}
}
