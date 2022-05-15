package com.feedback;

import model.Feedback;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Feedback")
public class FeedbackService {
	Feedback FeedbackObj = new Feedback();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFeedback() {
		return FeedbackObj.readFeedback();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFeedback(
			@FormParam("CostomerName") String CostomerName,
			@FormParam("Branch") String Branch,
			@FormParam("Review") String Review,
			@FormParam("Rate") String Rate) {
		String output = FeedbackObj.insertFeedback(CostomerName, Branch, Review, Rate);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateFeedback(String feedbackData) {
		
		JsonObject FeedbackObject = new JsonParser().parse(feedbackData).getAsJsonObject();
		
		String FEEDBACKID = FeedbackObject.get("FEEDBACKID").getAsString();
		String CostomerName = FeedbackObject.get("CostomerName").getAsString();
		String Branch = FeedbackObject.get("Branch").getAsString();
		String Review = FeedbackObject.get("Review").getAsString();
		String Rate = FeedbackObject.get("Rate").getAsString();
		
		String output = FeedbackObj.updateFeedback(FEEDBACKID, CostomerName, Branch, Review, Rate);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFeedback(String feedbackData) {
		
		Document doc = Jsoup.parse(feedbackData, "", Parser.xmlParser());

		String FEEDBACKID = doc.select("FEEDBACKID").text();
		String output = FeedbackObj.deleteFeedback(FEEDBACKID);
		return output;
		
	}
}
