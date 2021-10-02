package com.dev.devfood.api.model;

import java.util.List;

import com.dev.devfood.domain.model.City;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "cities")
@Data
public class CityXmlWrapper {
	
	@NonNull
	@JacksonXmlProperty(localName = "city")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<City> cities;
}
