package mx.com.mms.orderapi.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.com.mms.orderapi.converters.ProductConverter;
import mx.com.mms.orderapi.entity.Product;
import mx.com.mms.orderapi.exceptions.GeneralException;
import mx.com.mms.orderapi.exceptions.ResourceNotFoundException;
import mx.com.mms.orderapi.repository.ProductRepository;
import mx.com.mms.orderapi.services.IProductService;

@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements IProductService {
	
	@Autowired
	public ProductRepository productRepository;
	
	@Autowired
	private ProductConverter converter;

	@Override
	public Page<Product> findAll(Specification<Product> spec, Pageable pageable) {
		Page<Product> pagedResult = productRepository.findAll(spec, pageable);
		try {
			if(pagedResult.hasContent()) {
				return pagedResult;
			}
			throw new ResourceNotFoundException("No products found");
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public Product findById(String id) {
		try {
			Product product = productRepository
					.findById(id)
					.orElseThrow( () -> new ResourceNotFoundException("Product ID: " + id + " not found") );
			return product;	
		} catch(ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public Product save(Product product) {
		//ProductValidator.save(product);
		Product productSave = productRepository.save(product);
		return productSave;
	}

	@Override
	public Product delete(String id) {
		try {
			Product productDeleted = productRepository.findById(id)
					.orElseThrow( () -> new ResourceNotFoundException("Product ID: " + id + " not found") );
			
			productRepository.delete(productDeleted);	
			return productDeleted;
		}catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public Product update(Product product) {
		try {
			Product productUpdate = productRepository.findById(product.getProductId())
					.orElseThrow( () -> new ResourceNotFoundException("Product ID: " + product.getProductId() + " not found") );
			productUpdate = converter.toEntity(product);
			return productUpdate;
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}
	
	@Override
	public Product updateById(String id, Product product) {
		try {
			Product productUpdate = productRepository.findById(id)
					.orElseThrow( () -> new ResourceNotFoundException("Product ID: " + product.getProductId() + " not found") );
			productUpdate = converter.toEntity(product);
			return productUpdate;
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}
}
