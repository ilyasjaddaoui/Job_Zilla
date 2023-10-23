package com.example.jobzilla_backend;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Spring boot Job_Zilla App REST APIs",
		description = "Spring boot Job_Zilla App REST APIs Documentation",
		version = "v1.0",
		contact = @Contact(
				name = "ilyas",
				email = "ilyass.jaddaoui9@gmail.com"
		)
	),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot Job_Zilla App Documentation"
		)
)
public class JobzillaBackendApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(JobzillaBackendApplication.class, args);
	}

}
