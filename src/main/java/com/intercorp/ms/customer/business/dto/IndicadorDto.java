package com.intercorp.ms.customer.business.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase IndicadorDto.
 * @author Ronald Baron.
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class IndicadorDto {
    
    private int cantClienteNacMesAnio;
    private String mesAnioConMayorClienteNac;
    private String mesAnioConMenorClienteNac;
    private long tasaNatalidadMes;
    
}
