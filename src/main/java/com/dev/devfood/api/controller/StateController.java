package com.dev.devfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.devfood.api.model.StateXmlWrapper;
import com.dev.devfood.domain.exception.ResourceInUseException;
import com.dev.devfood.domain.exception.ResourceNotFoundException;
import com.dev.devfood.domain.model.State;
import com.dev.devfood.domain.service.StateRegistrationService;

@RestController
@RequestMapping("/states")
public class StateController {
	
	@Autowired
	private StateRegistrationService stateRegistrationService;
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<StateXmlWrapper> getWrapper() {
		return ResponseEntity.ok(new StateXmlWrapper(stateRegistrationService.list()));
	}
	
	@PostMapping
	public ResponseEntity<State> save(@RequestBody State state){
		return ResponseEntity.ok(stateRegistrationService.save(state));
	}
	
	@GetMapping
	public ResponseEntity<List<State>> list(){
		return ResponseEntity.ok(stateRegistrationService.list());		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(stateRegistrationService.findByIdOrThrowsResourceNotFoundException(id));
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody State state){
		try{
			State currentState = stateRegistrationService.findByIdOrThrowsResourceNotFoundException(id);
			BeanUtils.copyProperties(state, currentState, "id");
			return ResponseEntity.ok(stateRegistrationService.save(currentState));
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		try {
			stateRegistrationService.deleteOrThrowsException(id);
			return ResponseEntity.noContent().build();
		}catch(ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch(ResourceInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}
}
