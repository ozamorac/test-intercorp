package com.example.cliente.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cliente.dto.ClienteDto;
import com.example.cliente.entities.Cliente;
import com.example.cliente.response.KpiResponse;
import com.example.cliente.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		List<ClienteDto> clienteList = null;
		clienteList = clienteService.findAll();
		return new ResponseEntity<List<ClienteDto>>(clienteList, HttpStatus.OK);
	}
	
	@GetMapping("/kpi")
	public ResponseEntity<?> getKpiClientes(){
		
		KpiResponse kpiResponse = new KpiResponse();
		Integer average = 0;
		BigDecimal standardDesviation = new BigDecimal(0);
		average = clienteService.getAverage();
		standardDesviation = clienteService.getStandardDesviation();
		kpiResponse.setAverage(average);
		kpiResponse.setStandardDesviation(standardDesviation);
		
		return new ResponseEntity<KpiResponse>(kpiResponse, HttpStatus.OK);
	}
	
	@PostMapping("/crear")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		
		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try { 
			clienteNew = clienteService.save(cliente);
			
		} catch(DataAccessException e) { 
			response.put("mensaje", "Error al realizar el insert en la Base de datos");
			response.put("Error",  e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
		
		response.put("mensaje", "El Cliente ha sido creado con éxito!");	
		response.put("cliente", clienteNew);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
}
