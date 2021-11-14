package mx.com.mms.orderapi.validators;

import mx.com.mms.orderapi.entity.Product;

public class ProductValidator {

	public static void save(Product product) {
		if (product.getName().isBlank() || product.getName().trim().isEmpty()) {
			throw new RuntimeException("El nombre es requerido");
		}
		if (product.getName().length() > 100) {
			throw new RuntimeException("El nombre es muy largo");
		}
		if(product.getPrice().isNaN()) {
			throw new RuntimeException("El precio es requerido");
		}
		if(product.getPrice() < 0) {
			throw new RuntimeException("El precio es invalido");
		}
	}
}
