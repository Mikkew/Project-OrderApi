package mx.com.mms.orderapi.controllers;

import java.net.URI;
import java.util.Collection;

import javax.validation.Valid;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

//import lombok.extern.slf4j.Slf4j;
import mx.com.mms.orderapi.assembler.ProductAssembler;
import mx.com.mms.orderapi.converters.ProductConverter;
import mx.com.mms.orderapi.dtos.ProductDTO;
import mx.com.mms.orderapi.entity.Product;
import mx.com.mms.orderapi.services.IProductService;
import mx.com.mms.orderapi.utils.WrapperResponse;

//@Slf4j
@RestController
public class ProductController {

	@Autowired
	private IProductService productService;

	@Autowired
	private ProductAssembler productAssembler;

	@Autowired
	private PagedResourcesAssembler<Product> pagedResourcesAssembler;

	@Autowired
	private ProductConverter converter;

	@GetMapping("/products")
	public ResponseEntity<Object> findAll(
//			@RequestParam(defaultValue = "0") Integer page,
//			@RequestParam(defaultValue = "10") Integer size, 
//			@RequestParam(defaultValue = "createdAt") String sort,
			@PageableDefault(page = 0, sort = {"createdAt"}) Pageable pageable,
			@And({ 
				@Spec(path = "total", params = "name", spec = Equal.class) 
			}) Specification<Product> spec) {
//		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		Page<Product> products = productService.findAll(spec, pageable);
		PagedModel<EntityModel<ProductDTO>> pagedProduct = pagedResourcesAssembler.toModel(products, productAssembler);
		WrapperResponse<Collection<EntityModel<ProductDTO>>> response = new WrapperResponse<>(products, pagedProduct, pagedProduct.getContent());
		return response.createResponse(HttpStatus.OK);
	}

	@GetMapping("/products/{productId}")
	public ResponseEntity<Object> findById(@PathVariable String productId) {
		ProductDTO productDTO = converter.fromEntity(productService.findById(productId));
		return ResponseEntity.ok(productDTO);
	}

	@PostMapping("/products")
	public ResponseEntity<?> create(@Valid @RequestBody ProductDTO product) {
		Product productSave = converter.fromDto(product);
		productSave = productService.save(productSave);
		product = converter.fromEntity(productSave);
		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(product);
	}

	@PatchMapping("/products")
	public ResponseEntity<?> update(@Valid @RequestBody ProductDTO product) {
		Product productUpdate = converter.fromDto(product);
		productUpdate = productService.update(productUpdate);
		product = converter.fromEntity(productUpdate);
		return ResponseEntity.accepted().body(product);
	}
	
	@PutMapping("/products/{id}")
	public ResponseEntity<?> updateById(@Valid @PathVariable String id, @RequestBody ProductDTO product) {
		Product productUpdate = converter.fromDto(product);
		productUpdate = productService.updateById(id, productUpdate);
		product = converter.fromEntity(productUpdate);
		return ResponseEntity.accepted().body(product);
	}

	@DeleteMapping("/products/{id}")
	public ResponseEntity<?> delete(@PathVariable String id) {
		Product productDeleted = productService.delete(id);
		ProductDTO product = converter.fromEntity(productDeleted);
		return ResponseEntity.accepted().body(product);
	}
}
