package mx.com.mms.orderapi.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;

import mx.com.mms.orderapi.controllers.ProductController;
import mx.com.mms.orderapi.converters.ProductConverter;
import mx.com.mms.orderapi.dtos.ProductDTO;
import mx.com.mms.orderapi.entity.Product;

@Component
public class ProductAssembler implements RepresentationModelAssembler<Product, EntityModel<ProductDTO>> {

	@Autowired
	private ProductConverter converter;

	@Override
	public EntityModel<ProductDTO> toModel(Product entity) {
		EntityModel<ProductDTO> model = EntityModel.of(converter.fromEntity(entity));
		Link link = linkTo(methodOn(ProductController.class).findById(entity.getProductId())).withSelfRel();
		model.add(link);
		return model;
	}

	public CollectionModel<EntityModel<ProductDTO>> toCollectionModel(
			Pageable pageable,
			Specification<Product> spec,
			List<Product> entities) {
		List<ProductDTO> productsDTOs = converter.fromEntity(entities);
		List<EntityModel<ProductDTO>> model = productsDTOs.stream()
				.map( prod -> { 
					return EntityModel.of(prod); 
				}).toList();
		Link link = linkTo(methodOn(ProductController.class).findAll(pageable, spec)).withSelfRel();
		return CollectionModel.of(model, link);
	}

}
