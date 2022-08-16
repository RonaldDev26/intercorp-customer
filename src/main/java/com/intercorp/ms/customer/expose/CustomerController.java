package com.intercorp.ms.customer.expose;

import com.intercorp.ms.customer.business.dto.ClienteDto;
import com.intercorp.ms.customer.business.dto.IndicadorDto;
import com.intercorp.ms.customer.service.CustomerService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * CustomerController.
 * @author Ronald Baron.
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api/customerInfo")
public class CustomerController {
    
    @Autowired
    private CustomerService customerService;
    
    /**
     * Metodo para crear clientes.
     */
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created", response = ClienteDto.class, responseContainer = "Object"),
        @ApiResponse(code = 400, message = "Error", response = Exception.class),
        @ApiResponse(code = 401, message = "Acceso no autorizado", response = Exception.class),
        @ApiResponse(code = 500, message = "Ocurrio un error", response = Exception.class) })
    @PostMapping
    public ResponseEntity<Mono<ClienteDto>> guardarCliente(@RequestBody ClienteDto clienteDto) {
        log.info("CustomerController.guardarCliente");
        return new ResponseEntity<Mono<ClienteDto>>(customerService.crearCliente(clienteDto), HttpStatus.CREATED);
    }
    
    /**
     * Metodo para listar todos los clientes.
     */
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ok", response = ClienteDto.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Error", response = Exception.class),
        @ApiResponse(code = 401, message = "Acceso no autorizado", response = Exception.class),
        @ApiResponse(code = 500, message = "Ocurrio un error", response = Exception.class) })
    @GetMapping("/clientes")
    public Flux<ClienteDto> listarCliente() {
        log.info("CustomerController.listarCliente");
        return customerService.listarCliente();
    }
    
    /**
     * Metodo para buscar por dni y email de clientes.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = ClienteDto.class, responseContainer = "Object"),
            @ApiResponse(code = 400, message = "Error", response = Exception.class),
            @ApiResponse(code = 401, message = "Acceso no autorizado", response = Exception.class),
            @ApiResponse(code = 500, message = "Ocurrio un error", response = Exception.class) })
    @GetMapping
    public Mono<ClienteDto> buscarPorDniEmailCliente(@RequestParam(name = "dni") String dni , 
            @RequestParam(name = "email") String email) {
        log.info("CustomerController.istarCliente");
        return customerService.buscarPorDniAndEmail(dni, email);
    }
    
    /**
     * Metodo para buscar por dni de cliente.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = ClienteDto.class, responseContainer = "Object"),
            @ApiResponse(code = 400, message = "Error", response = Exception.class),
            @ApiResponse(code = 401, message = "Acceso no autorizado", response = Exception.class),
            @ApiResponse(code = 500, message = "Ocurrio un error", response = Exception.class) })
    @GetMapping("/{dni}")
    public Mono<ClienteDto> buscarPorDniCliente(@PathVariable(name = "dni") String dni) {
        log.info("CustomerController.buscarPorDniCliente");
        return customerService.buscarPorDni(dni);
    }
    
    /**
     * Metodo obtener indicadores.
     */
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok", response = ClienteDto.class, responseContainer = "Object"),
            @ApiResponse(code = 400, message = "Error", response = Exception.class),
            @ApiResponse(code = 401, message = "Acceso no autorizado", response = Exception.class),
            @ApiResponse(code = 500, message = "Ocurrio un error", response = Exception.class) })
    @GetMapping("/indicador")
    public Mono<IndicadorDto> obtenerIndicador(@RequestParam(name = "mes") String mes , 
            @RequestParam(name = "año") String anio) {
        log.info("CustomerController.obtenerIndicador");
        return customerService.obtenerIndicador(mes, anio);
    }
    
}
