package com.example.demo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * description    :
 * packageName    : com.example.demo.config
 * fileName       : SwaggerConfig
 * author         : cho
 * date           : 2025. 3. 15.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025. 3. 15.        cho       최초 생성
 */

@OpenAPIDefinition(
        info = @Info(
                title = "demo API",
                version = "0.0.1",
                contact = @Contact(
                        name = "SungMin Cho",
                        url = "demo.com",
                        email = "anisia1124@gmail.com"),
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/licenses/MIT")
        )
)
public class SwaggerConfig {
}
