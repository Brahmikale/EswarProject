package com.adobe.aem.guides.Eswar.core.servlets;

import java.io.IOException;
import java.util.Iterator;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@SuppressWarnings("serial")
@Component(service=Servlet.class)
@SlingServletResourceTypes(resourceTypes="Eswar/components/page",
							selectors="one",
							extensions="json")
@SlingServletPaths(value = "/eswar/answer")


public class OSGIDSservlet extends SlingAllMethodsServlet{
	
	
	//SlingHttpServletRequest,SlingHttpServletResponse these two classes contains the some methods and 
	//we can directly use those by importing there two classes.
	public void doGet(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException {
		String parameter = req.getParameter("pageroot");
		
		if(parameter==null) {
			parameter="/content/Eswar/us/en";  //if the pageroot has null value, by default it will assign.
		}
	
		//for example resourceresolver is a method we can get it from this class SlingHttpServletRequest. 
		//it will help for getting connectivity and configuration of the page. inside crxde.
		//similarly we can use for tagmanager aslo
		ResourceResolver resourceResolver = req.getResourceResolver(); 
		//for performing the operation on page we need to import pagemanager class.
		//and also perfomimg the crud operations on page.
		//resolver.adaptTo(PageManager.class);with the adaptto method we can easily get the object of the pagemanager.
		PageManager pagemanager=resourceResolver.adaptTo(PageManager.class);
		Page page = pagemanager.getPage(parameter);
		//inside page many items are there it will iterate and read all items in side the page.
		Iterator<Page> listChildren = page.listChildren();
		JsonArrayBuilder createArrayBuilder = Json.createArrayBuilder();
		
		while(listChildren.hasNext()) {
			Page next = listChildren.next();
			JsonObjectBuilder obj = Json.createObjectBuilder();
			obj.add("Title", next.getTitle());
			//obj.add("PageTitle", next.getPageTitle());
			//obj.add("Path", next.getPath());
			ValueMap properties = next.getProperties();
			obj.add("sling:resourceType",properties.get("sling:resourceType", String.class));
			createArrayBuilder.add(obj);
		}
		res.getWriter().write(createArrayBuilder.build().toString());
	
	}
	


}
