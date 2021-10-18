package com.example.cliente.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.cliente.dto.ClienteDto;
import com.example.cliente.entities.Cliente;

public interface ClienteService {

	List<ClienteDto> findAll();
	
	Cliente save(Cliente cliente);
	
	Integer getAverage();
	
	BigDecimal getStandardDesviation();
	
}
