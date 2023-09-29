package com.adobe.aem.guides.Eswar.core.servlets;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;


//@component is nothing but our osgi framework it will create servelt object automatically. 
//and we can register servlet in this way also.
@Component(service = Servlet.class)
//	property = {
//					"sling.servlet.paths=/hmt/pro", //this path must be insert inside osgi configuration.
//					"sling.servlet.methods=GET",
//					"sling.servlet.selectors= One",
//					"sling.servlet.extensions=txt"
//})

@SlingServletPaths(value = "/hmt/pro") //this is one of the way to register our servlet 

@SuppressWarnings("serial")

//SlingAllMethodsServlet this class is used to convert normal class into a sling servlet class 
public class SlingServletExample extends SlingSafeMethodsServlet  {
	
	//we have created request and response objects by passing parameters
	public void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException{
		
		JsonObjectBuilder obj = Json.createObjectBuilder(); //aem stored data in form of json so we created jsonobject
		obj.add("name", "brahmi"); //json stores data in kay:value pairs
		obj.add("surname", "kale");
		JsonArrayBuilder array = Json.createArrayBuilder();
		array.add(obj);
		res.getWriter().write(array.build().toString());
		
	}
}
