package com.intercorp.ms.customer.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    private String nombreDelRecurso;
    private String nombreDelCampo;
    private String valorDelCampo;
    
    public ResourceNotFoundException(String nombreDelRecurso, String nombreDelCampo, String valorDelCampo) {
        super(String.format("%s no encontrado  con : %s : '%S'", nombreDelRecurso, nombreDelCampo, valorDelCampo));
        this.nombreDelRecurso = nombreDelRecurso;
        this.nombreDelCampo = nombreDelCampo;
        this.valorDelCampo = valorDelCampo;
    }
    
}
