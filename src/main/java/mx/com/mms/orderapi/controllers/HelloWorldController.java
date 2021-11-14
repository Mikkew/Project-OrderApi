package mx.com.mms.orderapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//import mx.com.mms.orderapi.entity.Product;

@RestController
public class HelloWorldController {

	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
//	@GetMapping("/product")
//	public Product findProduct() {
//		Product product = Product.builder().id(1L).name("Producto 1").build();
//		return product;
//	}
}
