package com.example.cliente.service.Impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cliente.dto.ClienteDto;
import com.example.cliente.entities.Cliente;
import com.example.cliente.repository.ClienteRepository;
import com.example.cliente.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<ClienteDto> findAll() {
		Integer average = 0;
		average = clienteRepository.average();
		
		return clienteRepository.getAll(average);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Cliente save(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer getAverage() {
		return clienteRepository.average();
	}

	@Override
	@Transactional(readOnly = true)
	public BigDecimal getStandardDesviation() {
		
		List<Cliente> clienteList = null;
		BigDecimal std = new BigDecimal(0);
		clienteList = clienteRepository.findAll();
		if (clienteList.size()==1) {
			return std;
		}
		std = BigDecimal.valueOf(clienteRepository.std());
		
		return std;
	}

}
