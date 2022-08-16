package com.intercorp.ms.customer.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase ClienteDto.
 * @author Ronald Baron.
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ClienteDto {
    
    private long id;
    private String dni;
    private String email;
    private String nombre;
    private String apellido;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaCreacion;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;
    
}
