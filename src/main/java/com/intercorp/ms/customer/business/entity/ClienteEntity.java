package com.intercorp.ms.customer.business.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Clase ClienteEntity.
 * @author Ronald Baron.
 * @version 1.0
 */
@Entity
@Table(name="cliente")
@Getter
@Setter
public class ClienteEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    
    @Column(name="dni",length = 10)
    private String dni;
    
    @Column(name="email",length = 200)
    private String email;
    
    @Column(name="nombre" ,length = 100)
    private String nombre;
    
    @Column(name="apellido" , length = 100)
    private String apellido;
    
    @Column(name="fecha_creacion" , length = 100 , unique = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaCreacion;
    
    @Column(name="fecha_nacimiento" , length = 100)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaNacimiento;
    
    
}
