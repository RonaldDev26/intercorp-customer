package com.intercorp.ms.customer.business.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class ClienteEntityTest {
    
    @Test
    public void crearClienteEntityTest() {
        log.info("ClienteEntityTest.crearClienteEntityTest");
        ClienteEntity clienteEntity = crearClienteEntity();
        mostrarClienteEntityTest(clienteEntity);
        assertNotNull(clienteEntity);
    }
    
    private void mostrarClienteEntityTest(ClienteEntity clienteEntity) {
        if (clienteEntity != null) {
            log.info("clienteEntity.getId: " + clienteEntity.getId());
            log.info("clienteEntity.getDni: " + clienteEntity.getDni());
            log.info("clienteEntity.getApellido: " + clienteEntity.getApellido());
            log.info("clienteEntity.getEmail: " + clienteEntity.getEmail());
            log.info("clienteEntity.getNombre: " + clienteEntity.getNombre());
            log.info("clienteEntity.getFechaCreacion: " + clienteEntity.getFechaCreacion());
            log.info("clienteEntity.getFechaNacimiento: " + clienteEntity.getFechaNacimiento());
            
        }
    }
    
    private ClienteEntity crearClienteEntity() {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(0L);
        clienteEntity.setApellido("string");
        clienteEntity.setNombre("string");
        clienteEntity.setDni("string");
        clienteEntity.setFechaCreacion(LocalDate.now());
        clienteEntity.setFechaNacimiento(LocalDate.now());
        return clienteEntity;
    }
    
}
