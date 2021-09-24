package com.dev.devfood.api.model;

import java.util.List;

import com.dev.devfood.domain.model.Estado;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "estados")
@Data
public class EstadoXmlWrapper {
	
	@NonNull
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "estado")
	private List<Estado> estados;
}
