package com.dev.devfood.api.model;

import java.util.List;

import com.dev.devfood.domain.model.State;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "states")
@Data
public class StateXmlWrapper {
	
	@NonNull
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "state")
	private List<State> states;
}
