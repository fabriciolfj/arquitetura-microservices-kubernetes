package com.github.fabriciolfj.inventario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.inventario.domain.integration.OrderBinder;
import com.github.fabriciolfj.inventario.domain.integration.StatusBinder;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Role;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@EnableR2dbcRepositories
@SpringBootApplication
@EnableBinding({OrderBinder.class, StatusBinder.class})
public class InventarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarioApplication.class, args);
	}

	@Bean(BeanDefinition.SCOPE_PROTOTYPE)
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
