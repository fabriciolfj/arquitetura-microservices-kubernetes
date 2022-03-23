package com.github.fabriciolfj.customer.infraestrutura;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Cadastro de cliente",
                description = "Crud envolvendo o contexto de clientes",
                contact = @Contact(
                        name = "Fabricio Jacob",
                        url = "https://www.linkedin.com/in/fabricio-jacob-b9495721/",
                        email = "fabricio.jacob@outlook.com"
                ),
                license = @License(
                        name = "Free",
                        url = "www.github.com"
                )
        ),
        servers = @Server(url = "http://localhost:9290")
)
public class OpenApiConfiguration {
}
