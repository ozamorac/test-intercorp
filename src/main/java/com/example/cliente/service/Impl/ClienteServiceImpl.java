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
		BigDecimal mean = new BigDecimal(0);
		Integer sumAge = 0;
		Integer countAge = 0;
		sumAge = clienteRepository.sumAge();
		countAge = clienteRepository.countAge();
		mean = BigDecimal.valueOf(sumAge).divide(BigDecimal.valueOf(countAge));
		
		clienteList = clienteRepository.findAll();
		double stdInput = 0;
		double stdSum = 0;
		for (Cliente cliente : clienteList) {
			stdInput = BigDecimal.valueOf(cliente.getEdad()).subtract(mean).doubleValue();
			stdSum += Math.pow(stdInput,2);
		}
		if (clienteList.size()==1) {
			return std;
		}
		//std = BigDecimal.valueOf(stdSum).divide(BigDecimal.valueOf(countAge));
		std = BigDecimal.valueOf(clienteRepository.std());
		
		return std;
	}

}
