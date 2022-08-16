package com.intercorp.ms.customer.business.dao;

import com.intercorp.ms.customer.business.entity.ClienteEntity;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository  extends JpaRepository<ClienteEntity, Long> {
    
    ClienteEntity findByDniAndEmail(String dni , String email);
    
    ClienteEntity findByDni(String dni);
    
    List<ClienteEntity> findByOrderByFechaNacimientoAsc();
    
    List<ClienteEntity> findByOrderByFechaNacimientoDesc();
    
}
