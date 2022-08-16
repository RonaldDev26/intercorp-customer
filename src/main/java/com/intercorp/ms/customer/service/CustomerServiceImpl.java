package com.intercorp.ms.customer.service;

import static com.intercorp.ms.customer.util.CustomerConstantes.MENSAJE_NO_EXISTE_INFORMACION_CLIENTE;

import com.intercorp.ms.customer.business.dao.CustomerRepository;
import com.intercorp.ms.customer.business.dto.ClienteDto;
import com.intercorp.ms.customer.business.dto.FechaDto;
import com.intercorp.ms.customer.business.dto.IndicadorDto;
import com.intercorp.ms.customer.business.entity.ClienteEntity;
import com.intercorp.ms.customer.config.ApplicationProperty;
import com.intercorp.ms.customer.exception.ResourceNotFoundException;
import com.intercorp.ms.customer.util.CustomerUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
/**
 * Clase CustomerService.
 * @author Ronald Baron.
 * @version 1.0
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private ApplicationProperty applicationProperty;
    
    private long tasaNatalidad;
    
    @Override
    public Mono<ClienteDto> crearCliente(ClienteDto clienteDto) {
        log.info("CustomerServiceImpl.crearCliente");
        ClienteEntity clienteEntity = CustomerUtil.mapperClass(ClienteEntity.class, clienteDto);
        
        ClienteEntity nuevoClienteEntity = customerRepository.save(clienteEntity);
        
        // Convertimos de dto a entidad.
        ClienteDto clienteRespuestaDto = CustomerUtil.mapperClass(ClienteDto.class, nuevoClienteEntity);
        
        return Mono.justOrEmpty(clienteRespuestaDto);
        
    }
    
    @Override
    public Flux<ClienteDto> listarCliente() {
        log.info("CustomerServiceImpl.listarCliente");
        List<ClienteEntity> clienteEntityList = customerRepository.findAll();
        
        List<ClienteDto> clienteDtoList = clienteEntityList.stream().map(entity -> {
            ClienteDto clienteDto = CustomerUtil.mapperClass(ClienteDto.class, entity);
            return clienteDto;
            }).collect(Collectors.toList());
        
        return Flux.fromIterable(clienteDtoList);
    }
    
    @Override
    public Mono<ClienteDto> buscarPorDniAndEmail(String dni, String email) {
        log.info("CustomerServiceImpl.buscarPorDniAndEmail");
        
        ClienteDto clienteRespuestaDto = null;
        ClienteEntity clienteEntity = customerRepository.findByDniAndEmail(dni, email);
        
        clienteEntity = customerRepository.findByDniAndEmail(dni, email);
        clienteRespuestaDto = CustomerUtil.mapperClass(ClienteDto.class, clienteEntity);
        
        return Mono.justOrEmpty(clienteRespuestaDto)
                .switchIfEmpty(Mono.error(
                        new RuntimeException(MENSAJE_NO_EXISTE_INFORMACION_CLIENTE)));
        
    }
    
    @Override
    public Mono<ClienteDto> buscarPorDni(String dni) {
        log.info("CustomerServiceImpl.buscarPorDni");
        
        ClienteEntity clienteEntity = customerRepository.findByDni(dni);
        
        ClienteDto clienteRespuestaDto = CustomerUtil.mapperClass(ClienteDto.class, clienteEntity);
        
        return Mono.justOrEmpty(clienteRespuestaDto)
                .switchIfEmpty(Mono.error(() ->
                new ResourceNotFoundException("DNI", "dni",dni )));
        
    }
    
    @Override
    public Mono<IndicadorDto> obtenerIndicador(String mes , String anio) {
       log.info("CustomerServiceImpl.obtenerIndicador");
       
       List<ClienteEntity> clienteEntityList = customerRepository.findAll();
       
       List<ClienteEntity> clienteEntityPorFechaAscList = customerRepository.findByOrderByFechaNacimientoAsc();
       //List<ClienteEntity> clienteEntityPorFechaDescList = customerRepository.findByOrderByFechaNacimientoDesc();
       
       /*Filtro de clientes por mes y año*/
       List<ClienteDto> clienteDtoList = clienteEntityList.stream()
               .filter(p -> p.getFechaNacimiento().getMonthValue() == Integer.parseInt(mes) && 
                            p.getFechaNacimiento().getYear() == Integer.parseInt(anio))
               .map(entity -> {
           ClienteDto clienteDto = CustomerUtil.mapperClass(ClienteDto.class, entity);
           return clienteDto;
           }).collect(Collectors.toList());
       
       List<ClienteDto> clienteDtoPorFechaAscList = clienteEntityPorFechaAscList.stream().map(entity -> {
           ClienteDto clienteDto = CustomerUtil.mapperClass(ClienteDto.class, entity);
           return clienteDto;
           }).collect(Collectors.toList());
       
       
        Map<Object, Map<Object, Long>> result = clienteDtoPorFechaAscList.stream()
                                                 .collect(Collectors.groupingBy(p -> p.getFechaNacimiento().getYear(),
                                                         Collectors.groupingBy(p -> p.getFechaNacimiento().getMonthValue(), 
                                                         Collectors.counting())));
        
        List<String> fechas = new ArrayList<>();
        
        IndicadorDto indicadorDto = new IndicadorDto();
        
        result.forEach((p, sistemaCountMap) -> {
            sistemaCountMap.forEach((m, count) -> {
                fechas.add(p.toString() + "-" + m.toString() + "-" + count.toString());
           });
       });
        
        List<FechaDto> fechaDtoList = new ArrayList<>();
        
        fechas.forEach(p-> {
            log.info(p.toString());
            String[] parts = p.split("-");
            FechaDto fechaDto = new FechaDto();
            fechaDto.setAnio(parts[0]);
            fechaDto.setMes(parts[1]);
            fechaDto.setCantidad(Integer.parseInt(parts[2]));
            fechaDtoList.add(fechaDto);
            
        });
        
        //Ordenando por cantidad de registros de mayor a menor
        fechaDtoList.sort(Comparator.comparing(FechaDto::getCantidad).reversed());
        
        indicadorDto.setMesAnioConMayorClienteNac(fechaDtoList.get(0).getAnio() + "-" + fechaDtoList.get(0).getMes());
        
        //Ordenando por cantidad de registros de menor a mayor
        fechaDtoList.sort(Comparator.comparing(FechaDto::getCantidad));
        
        indicadorDto.setMesAnioConMenorClienteNac(fechaDtoList.get(0).getAnio() + "-" + fechaDtoList.get(0).getMes());
        
        indicadorDto.setCantClienteNacMesAnio(clienteDtoList.size());
        
        /*Calculado tasa de natalidad */
        log.info("Calculando tasa de nataidad");
        Map<Object, Map<Object, Long>> resultFiltradoNatalidad = clienteDtoPorFechaAscList.stream()
                .filter(p -> p.getFechaNacimiento().getMonthValue() == Integer.parseInt(mes))
                .collect(Collectors.groupingBy(p -> p.getFechaNacimiento().getYear(),
                  Collectors.groupingBy(p -> p.getFechaNacimiento().getMonthValue(), 
                  Collectors.counting())));
        
        List<String> fechas2 = new ArrayList<>();
        
        resultFiltradoNatalidad.forEach((p, sistemaCountMap) -> {
            sistemaCountMap.forEach((m, count) -> {
                fechas2.add(p.toString() + "-" + m.toString() + "-" + count.toString());
                });
            });
        
        fechas2.forEach(p -> {
            log.info(p.toString());
            //n= numero de nacimientos por año/mes
            int n = Integer.parseInt(p.split("-")[2]);
            long poblacionTotal = applicationProperty.getPoblacion();
            tasaNatalidad = (Long.valueOf(n)/poblacionTotal)*1000;
         });
        
        indicadorDto.setTasaNatalidadMes(tasaNatalidad);
        
        return Mono.justOrEmpty(indicadorDto)
                .switchIfEmpty(Mono.error(new RuntimeException(MENSAJE_NO_EXISTE_INFORMACION_CLIENTE)));
       
    }
    
}