package com.dev.devfood.api.model;

import java.util.List;

import com.dev.devfood.domain.model.Kitchen;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "kitchens")
@Data
public class KitchenXmlWrapper{
	
	@NonNull
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "kitchen")
	private List<Kitchen> kitchens;
}
