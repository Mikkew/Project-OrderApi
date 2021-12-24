package mx.com.mms.orderapi.config;

import org.springframework.context.annotation.*;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Order API", version = "1.0", description = "Order Information"))
public class SwaggerConfig {

}
