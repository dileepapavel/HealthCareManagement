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
		return paymentObj.readItems();
	}
	
	@POST
	@Path("/makePayment")
	@Produces(MediaType.APPLICATION_JSON)
	public String  makePayment(Payment payment) {
		Payment payment2;
		paymentObj.makePayment("cash", 150.00, "Doctor", 1, new Date());
		return "{sample: 'true'}";
		
	}
}
