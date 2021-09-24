package com.dev.devfood.api.model;

import java.util.List;

import com.dev.devfood.domain.model.Restaurante;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "restaurantes")
@Data
public class RestauranteXmlWrapper {
	
	@NonNull
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "restaurante")
	private List<Restaurante> restaurantes;
}
