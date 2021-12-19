package mx.com.mms.orderapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import mx.com.mms.orderapi.converters.OrderConverter;
import mx.com.mms.orderapi.converters.OrderLineConverter;
import mx.com.mms.orderapi.converters.ProductConverter;
import mx.com.mms.orderapi.converters.RoleConverter;
import mx.com.mms.orderapi.converters.UserConverter;

@Configuration
public class ConverterConfig {

	@Bean
	public ProductConverter getProductConverter() {
		return new ProductConverter();
	}
	
	@Bean
	public OrderConverter getOrderConverter() {
		return new OrderConverter();
	}
	
	@Bean
	public OrderLineConverter getOrderLineConverter() {
		return new OrderLineConverter();
	}
	
	@Bean
	public UserConverter getUserConverter() {
		return new UserConverter();
	}
	
	@Bean
	public RoleConverter getRoleConverter() {
		return new RoleConverter();
	}
}
