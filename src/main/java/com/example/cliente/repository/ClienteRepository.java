package com.example.cliente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.cliente.dto.ClienteDto;
import com.example.cliente.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query(value = "select id, Nombre as nombre, Apellido as apellido, Edad as edad, Fecha_Nacimiento as fechaNacimiento, DATEADD(yyyy,?1, Fecha_Nacimiento) as fechaDead from Cliente "
			+ " GROUP BY id", nativeQuery = true)
	public List<ClienteDto> getAll(Integer average);
	
	@Query("select avg(c.edad) from Cliente c")
	public Integer average();
	
	@Query("select sum(c.edad) from Cliente c")
	public Integer sumAge();
	
	@Query("select count(c.edad) from Cliente c")
	public Integer countAge();
	
	@Query(value = "select STDDEV(Edad) as stdev from Cliente ", nativeQuery = true)
	public Double std();
}
