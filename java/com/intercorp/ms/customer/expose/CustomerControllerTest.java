package com.intercorp.ms.customer.expose;

import com.intercorp.ms.customer.business.dto.ClienteDto;
import com.intercorp.ms.customer.service.CustomerService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient(timeout = "20000")
public class CustomerControllerTest {
    
    @Autowired
    private WebTestClient webClient;
    
    @MockBean
    private CustomerService customerService;
    
    @Test
    public void listarClienteUriTest() {
    	
    }
    
    
    
    
    public List<ClienteDto> createCustomerDtoList() {
        List<ClienteDto> clienteDtoList = new ArrayList<>();
        clienteDtoList.add(createCustomerDto());
        return clienteDtoList;
    }
    
    public ClienteDto createCustomerDto() {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(1);
        clienteDto.setDni("70012821");
        clienteDto.setEmail("baron@gmail.com");
        clienteDto.setNombre("RONALD");
        clienteDto.setApellido("BARON SANCHEZ");
        clienteDto.setFechaCreacion(LocalDate.now());
        clienteDto.setFechaNacimiento(LocalDate.now());
        return clienteDto;
    }
}
