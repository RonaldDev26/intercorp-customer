package com.intercorp.ms.customer.config;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Properties para utilizar en el microservicio.
 * @author Ronald Baron.
 * @version 1.0
 */
@Configuration
@Getter
public class ApplicationProperty {
    
    @Value("${application.data.poblacion}")
    private long poblacion;
    
}
