package com.example.cliente.dto;

import java.util.Date;

public interface ClienteDto {
	
	Long getId();
	
	String getNombre();
	
	String getApellido();
	
	Integer getEdad();
	
	Date getFechaNacimiento();
	
	Date getFechaDead();
	
}
