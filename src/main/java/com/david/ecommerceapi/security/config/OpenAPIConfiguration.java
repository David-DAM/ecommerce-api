package com.david.ecommerceapi.security.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
//@OpenAPIDefinition(
//        info =@Info(
//                title = "User API",
//                version = "${api.version}",
//                contact = @Contact(
//                        name = "Baeldung", email = "user-apis@baeldung.com", url = "https://www.baeldung.com"
//                ),
//                license = @License(
//                        name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"
//                ),
//                termsOfService = "${tos.uri}",
//                description = "${api.description}"
//        ),
//        servers = @Server(
//                url = "${api.server.url}",
//                description = "Production"
//        )
//)
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenAPIConfiguration {
}
