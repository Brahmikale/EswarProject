package com.adobe.aem.guides.Eswar.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service=EventHandler.class, immediate=true,
			property= {
					EventConstants.EVENT_TOPIC+"=/org/apche/sling/api/resource/Resource/ADDED",
					EventConstants.EVENT_TOPIC+"=/org/apche/sling/api/resource/Resource/REMOVE",
					EventConstants.EVENT_TOPIC+"=/org/apche/sling/api/resource/Resource/CHANGE",
					EventConstants.EVENT_TOPIC+"=/com/day/cq/replicate", // if want tranfer the content from auther to publish instance. 
					EventConstants.EVENT_FILTER+"=(|(|(path=/content/Eswar/us/en/*)(path=/content/Eswar/us/en/eswaredits1/*))(type=activate))"
					//in this event_filer we can write ldap experssion for ex:we want to perform some operations. 
					//we can define here, if we have path1 and path2, on that paths we can perform (or |) and (& and) opetarions
			})
public class EventHandlerExample implements EventHandler{
	public static final Logger  LOG= LoggerFactory.getLogger(EventHandlerExample.class);

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		LOG.info("Event handler is executed....!");
		LOG.info("Topic", event.getTopic());
		
		String[] propertyName= event.getPropertyNames();
		for(String name: propertyName) {
			LOG.info("Property name{}, property value{}", name, event.getProperty(name).toString());
		}
		
		
		
	}

}
