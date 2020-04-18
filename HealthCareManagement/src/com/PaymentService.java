package com;

import Controler.PaymentController;
import model.Payment;

import java.io.Console;
import java.util.Date;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.RequestWrapper;

//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentService {
	PaymentController paymentObj = new PaymentController();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems() {
		return paymentObj.readPayment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String makePayment(@FormParam("type") String type,
	 @FormParam("ammount") String ammount ,
	 @FormParam("paymentHolder") String paymentHolder,
	 @FormParam("payeeId") String payeeId,
	 @FormParam("date") String date)
	{
	 String output = paymentObj.makePayment(type, ammount, paymentHolder, payeeId, date);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
	JsonObject paymentObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	 int PaymentID = Integer.parseInt(paymentObject.get("PaymentID").getAsString());
	 String type = paymentObject.get("type").getAsString();
	 String ammount = paymentObject.get("ammount").getAsString();
	 String paymentHolder = paymentObject.get("paymentHolder").getAsString();
	 String date = paymentObject.get("date").getAsString();
	 String payeeId = paymentObject.get("payeeId").getAsString();
	 
	 String output = paymentObj.updatePayment(PaymentID, type, ammount, paymentHolder, date, payeeId);
	return output;
	}
}
