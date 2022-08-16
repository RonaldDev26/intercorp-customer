package com.intercorp.ms.customer;

import com.tdp.ms.cobertura.TestingConfiguration;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebTestClient
@ImportAutoConfiguration(classes = TestingConfiguration.class)
@TestPropertySource(properties = {
        "application.storage.connection=DefaultEndpointsProtocol=https;AccountName=scvisordatadev;AccountKey=n7AzXQ+OONO870oSYfzueL6euGpwwdfhtlYqQy6XxzrEtKDow2oevwaYn28FvmBEyrZ7UB6zbSGNrXIJIJHA5w==;EndpointSuffix=core.windows.net",
        "application.storage.tables.cobertura.name=VOMCobertura",
        "application.storage.tables.cobertura.partitionKey=pk_cobertura",
        "application.storage.tables.cobertura.maxRegister=50",
        "application.storage.tables.coberturaTambo.name=VOMCobTambo",
        "application.storage.tables.coberturaTambo.partitionKey=pk_cob_tambo",
        "application.storage.tables.coberturaTambo.maxRegister=45",
        "application.storage.tables.zonaSinCobertura.name=VOMZonSinCob",
        "application.storage.tables.zonaSinCobertura.partitionKey=pk_zon_sin_cob",
        "application.storage.tables.zonaSinCobertura.maxRegister=55",
        "application.data.formatoIso=true",
        "microservices.errores.url=https://aks-visor-ingress-cert.eastus2.cloudapp.azure.com/",
        "microservices.errores.id=1234567890"})
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
class CustomerApplicationTests {

	@Test
	void main() {
		CustomerApplication.main(new String[] {});
	}

}
