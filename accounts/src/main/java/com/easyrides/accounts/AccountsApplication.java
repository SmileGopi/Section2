package com.easyrides.accounts;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*
@ComponentScans({@ComponentScan("com.easyrides.accounts.controller) })
@EnableJpaRepositories("com.easyrides.accounts.model")
 */
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Microservice REST API Documentation",
				description = "EasyRidesBank Accounts microservices REST API documentation",
				version =  "v1",
				contact = @Contact(
						name = "gK Technologies",
						email = "rgkrishna007@outlook.com",
						url = "https://easyridersbank.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://easyridersbank.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Accounts microservice API Documentation",
				url = "https://easyridersbank.com/swagger-ui.html"
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
