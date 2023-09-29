package com.adobe.aem.guides.Eswar.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;



@Model(adaptables = {SlingHttpServletRequest.class, Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class SlingModelExample{ 
	private static final org.slf4j.Logger log=LoggerFactory.getLogger(SlingModelExample.class);
	
	@ScriptVariable
	Page currentPage;
	
	@Self
	SlingHttpServletRequest slingHttpServletRequest;
	
	
	@ResourcePath(path="/content/Eswar/us/en")
	Resource resource;
	
	@ValueMapValue
	@Default(values="Lord Shiva")
	public String firstname;
	
	@Inject
	Resource resource_1;
	
	@Inject
	@Via("resource")  //we can use this instead of resource.class
	@Optional
	@Default(values="shiva is most powerful god")
	public String text;
	
	@Named("jcr:createdBy")  //we can create variables by using this annotation for some unsupported variables in java.
	public String jcrcreatedby;
	
	@ValueMapValue
	public String fullname;
	
	@ValueMapValue
	public String name;
	
	@ValueMapValue
	public String mobilenumber;
	
	@ValueMapValue
	public String address;
	
	@ValueMapValue
	public String dateofbirth;
	
	@ValueMapValue
	public String pathfield;
	
	@ValueMapValue
	public String pathbrowser;
	
	@ValueMapValue
	public String dropdown;
	
	@ValueMapValue
	public String checkbox;
	
	@ValueMapValue
	public String radiogroup;
	
	@ValueMapValue
	public String text1;
	
	@ValueMapValue
	public String colorpicker;
	
	@ValueMapValue
	public String image;
	
	@ValueMapValue
	public String textarea;
	
	@SlingObject
	ResourceResolver resolver;
	
	@RequestAttribute(name="slingModel")
	public String slingModel;
	
	public String getSlingModel() {
		return slingModel;
	}

	public String getPage() {
		//return resource.getName();
		return resource.getName();
	}
	
	public String getCurrentPage() {
		return currentPage.getPageTitle();
	}
	
	public String getJcrCreatedBy() {
		return jcrcreatedby;
	}
	
	public String getText1() {
		return text1;
	}
	
	public String getColorPicker() {
		return colorpicker;
	}
	
	public String getChildren() {
		return resource.getChildren().toString();
	}
	
	public String getImage() {
		return image;
	}
	
	public String getTextArea() {
		return textarea;
	}
	
	public String getDateOfBirth() {
		return dateofbirth;
	}
	
	public String getPathField() {
		return resource.getPath();
	}
	
	public String getPathBrowser() {
		return pathbrowser;
	}
	
	public String getDropDown() {
		return dropdown;
	}
	
	public String getCheckBox() {
		return checkbox;
	}
	
	public String getRadioGroup() {
		return radiogroup;
	}
	
	
	public String getName() {
		return resource.getName();
	}
	
	public String getMobileNumber() {
		return mobilenumber;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getFullName() {
		return fullname.toUpperCase();
	}
	public String getText() {
		return text;
		
	}
	
	public String getFirstName() {
		return firstname;
		
	}
	@PostConstruct
	public void Start() {
		log.info("this is a logger description...");
	}
		
	
	
}
