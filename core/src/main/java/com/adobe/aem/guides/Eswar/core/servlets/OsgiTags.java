package com.adobe.aem.guides.Eswar.core.servlets;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.Iterator;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.commons.json.JSONArray;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import com.day.cq.tagging.InvalidTagFormatException;
import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;

@SuppressWarnings("serial")
@Component(service=Servlet.class)
@SlingServletPaths(value = "/bin/tags")
public class OsgiTags extends SlingAllMethodsServlet{
	

	@SuppressWarnings("deprecation")
	public void doget(SlingHttpServletRequest req, SlingHttpServletResponse res) throws IOException {
		String pathInfo = req.getPathInfo();
		
		Tag createTag = null;
		ResourceResolver resolver = req.getResourceResolver();
		TagManager tagMngr = resolver.adaptTo(TagManager.class);
		Tag tag = tagMngr.resolve("/content/cq:tags/septtag/countries");
		try {
			createTag =tagMngr.createTag("/content/cq:tags/septtag/kale", "title", "description");
		} catch (AccessControlException | InvalidTagFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unused")
		Iterator<Tag> listChildren = tag.listChildren();
		JSONObject obj = new JSONObject();
		@SuppressWarnings("unused")
		JSONArray array = new JSONArray();
		
		while(listChildren.hasNext()) {
			Tag next = listChildren.next();
			next.getTitle();
			next.getPath().toString();
			
			try {
				obj.put("Title", next.getTitle());
				obj.put("Path", next.getPath());
				obj.put("Createtag", createTag);
				array.put(obj);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			res.getWriter().write(createTag.toString());
			res.getWriter().write(array.toString());
		}
		
		
	}

}
