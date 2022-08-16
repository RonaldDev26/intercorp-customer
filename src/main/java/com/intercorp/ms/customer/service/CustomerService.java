package com.intercorp.ms.customer.service;

import com.intercorp.ms.customer.business.dto.ClienteDto;
import com.intercorp.ms.customer.business.dto.IndicadorDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    
    Mono<ClienteDto> crearCliente(ClienteDto clienteDto);
    
    Flux<ClienteDto> listarCliente();
    
    Mono<ClienteDto> buscarPorDniAndEmail(String dni , String email);
    
    Mono<ClienteDto> buscarPorDni(String dni);
    
    Mono<IndicadorDto> obtenerIndicador(String mes , String anio);
    
}
