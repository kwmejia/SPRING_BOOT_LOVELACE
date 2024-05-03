package com.riwi.vacants.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

//Para configurar beans dentro de spring 
@Configuration
@OpenAPIDefinition(info = @Info(title = "Api para administración de compañias y vacantes", version = "1.0", description = "Esta api fue creada para aprender los fundamentos de spring boot y spring jpa"))
public class OpenApiConfig {

}
