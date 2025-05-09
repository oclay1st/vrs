package io.github.oclay1st.vrs.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Vehicle Registration System", description = "API for a vehicle registration system", contact = @Contact(name = "oclay1st", url = "https://github.com/oclay1st"), license = @License(name = "Apache 2 License", url = "https://github.com/oclay1st/vehicle-registration-system/LICENSE.txt")), servers = @Server(url = "http://localhost:8080"))
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer")
@Configuration
public class OpenApiConfig {

}
