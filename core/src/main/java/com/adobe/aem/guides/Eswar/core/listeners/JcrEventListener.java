package com.adobe.aem.guides.Eswar.core.listeners;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventListener.class)

public class JcrEventListener implements EventListener {
	private static final Logger Log = LoggerFactory.getLogger(JcrEventListener.class);
	private Session session;

	@Reference
	SlingRepository slingRepo;

	@Activate
	public void activate() {

		try {
			// all the content will fetch and store in session from the workspace.
			// "eswarsubservoce" this is our user it will contain all permission, null is
			// our workspace
			session = slingRepo.loginService("eswarsubservoce", null);
			// form the session we are getting current workspace from workspace we are
			// getting observation
			// manager after that we are perfoming the event listener.
			session.getWorkspace().getObservationManager().addEventListener( // this method excepts 7 parameters
					this, // handler, it will onevent object.
					Event.NODE_ADDED | Event.PROPERTY_ADDED, // event type if some one added node or property it will execute
					"/content/Eswar/us/en", // path
					true, // is deep? if we want everything in that path we have to write true
					null, // uuid unique name
					null, // nodetype filter
					true); //it will used for trigger same event again and again, if dont want we can use false.
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			Log.info("\n error while adding event listener: {}", e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void onEvent(EventIterator events) {
		// TODO Auto-generated method stub
		try {
			while (events.hasNext()) {

				Log.info("\n path:{}", events.nextEvent().getPath());
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
