package mx.com.mms.orderapi.converters;

import mx.com.mms.orderapi.dtos.ProductDTO;
import mx.com.mms.orderapi.entity.Product;

public class ProductConverter extends AbstractConverter<Product, ProductDTO> {

	@Override
	public ProductDTO fromEntity(Product entity) {
		if(entity == null) 
			return null;
		return ProductDTO.builder()
				.id(entity.getProductId())
				.name(entity.getName())
				.price(entity.getPrice())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.build();
	}

	@Override
	public Product fromDto(ProductDTO dto) {
		if(dto == null) 
			return null;
		return Product.builder()
				.productId(dto.getId())
				.name(dto.getName())
				.price(dto.getPrice())
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.build();
	}

	public ProductDTO toDto(ProductDTO dto) {
		if(dto == null) 
			return null;
		return ProductDTO.builder()
				.id(dto.getId())
				.name(dto.getName())
				.price(dto.getPrice())
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.build();
	}

	public Product toEntity(Product entity) {
		if(entity == null) 
			return null;
		return Product.builder()
				.productId(entity.getProductId())
				.name(entity.getName())
				.price(entity.getPrice())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.build();
	}
	
}
