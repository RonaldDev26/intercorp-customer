package com.intercorp.ms.customer.business.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase FechaDto.
 * @author Ronald Baron.
 * @version 1.0
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FechaDto {
    
    private String anio;
    private String mes;
    private int cantidad;
    
}
