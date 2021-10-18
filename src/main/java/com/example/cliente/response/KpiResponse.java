package com.example.cliente.response;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KpiResponse {

	 @NotNull
	 @ApiModelProperty(allowEmptyValue = false, notes = "Promedio de edad entre todos los clientes")
	 private Integer average;
	  
	 @NotNull
	 @ApiModelProperty(allowEmptyValue = false, notes = "Desviación Estandar entre las edades de todos los clientes")
	 private BigDecimal standardDesviation;
	  
}
