package com.dev.devfood.api.model;

import java.util.List;

import com.dev.devfood.domain.model.Restaurant;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "restaurants")
@Data
public class RestaurantXmlWrapper {
	
	@NonNull
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "restaurant")
	private List<Restaurant> restaurants;
}
