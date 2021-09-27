package com.dev.devfood.api.model;

import java.util.List;

import com.dev.devfood.domain.model.Cidade;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "cidades")
@Data
public class CidadeXmlWrapper {
	
	@NonNull
	@JacksonXmlProperty(localName = "cidade")
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Cidade> cidades;
}
