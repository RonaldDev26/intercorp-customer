package com.intercorp.ms.customer;

import com.intercorp.ms.customer.config.ApplicationProperty;

import lombok.extern.slf4j.Slf4j;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class CustomerApplicationTest {
    
    @Autowired
    private ApplicationProperty applicationProperty;
    
    @Test
    void main() {
       CustomerApplication.main(new String[] {});
    }
    
    @Test
    public void ApplicationTest(){
        log.info("CustomerApplicationTest.ApplicationTest");
        mostrarApplicationProperty();
        Assertions.assertTrue(true);
    }
    
    private void mostrarApplicationProperty() {
        log.info("applicationProperty.getTipoDescontar: " + applicationProperty.getPoblacion());
    }
    
}
