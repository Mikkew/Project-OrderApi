package mx.com.mms.orderapi.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import mx.com.mms.orderapi.entity.Product;

public interface IProductService {
	public Page<Product> findAll(Specification<Product> spec, Pageable pageable);
	
	public Product findById(String id);
	
	public Product save(Product product);
	
	public Product update(Product product);
	
	public Product updateById(String id, Product product);
	
	public Product delete(String id);
	
}
