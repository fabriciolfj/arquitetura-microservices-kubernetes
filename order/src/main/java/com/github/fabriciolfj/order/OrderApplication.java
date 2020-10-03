package com.github.fabriciolfj.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.order.domain.integracao.http.CustomerClient;
import com.github.fabriciolfj.order.domain.integracao.http.ProductClient;
import com.github.fabriciolfj.order.domain.integracao.message.binders.EstoqueMessage;
import com.github.fabriciolfj.order.domain.integracao.message.binders.StatusMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableBinding({EstoqueMessage.class, StatusMessage.class})
@EnableFeignClients(clients = {CustomerClient.class, ProductClient.class})
@EntityScan(basePackages = {"com.github.fabriciolfj.order.domain.entity"})
@EnableJpaRepositories(basePackages = {"com.github.fabriciolfj.order.domain.repository"})
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

}
