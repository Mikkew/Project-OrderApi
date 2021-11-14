package mx.com.mms.orderapi.converters;

import org.springframework.beans.factory.annotation.Autowired;

import mx.com.mms.orderapi.dtos.OrderLineDTO;
import mx.com.mms.orderapi.entity.OrderLine;
import mx.com.mms.orderapi.services.IOrderService;
import mx.com.mms.orderapi.services.IProductService;

public class OrderLineConverter extends AbstractConverter<OrderLine, OrderLineDTO> {
	
	@Autowired
	private IProductService productService;
	
	@Autowired
	private IOrderService orderService;
	
	@Autowired
	private OrderConverter orderConverter;
	
	@Autowired
	private ProductConverter productConverter;
	
	@Override
	public OrderLineDTO fromEntity(OrderLine entity) {
		if(entity == null) 
			return null;
		return OrderLineDTO.builder()
				.id(entity.getId())
				.order(orderConverter.fromEntity(entity.getOrder()))
				.product(productConverter.fromEntity(entity.getProduct()))
				.price(entity.getPrice())
				.quantity(entity.getQuantity())
				.total(entity.getTotal())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.build();
	}

	@Override
	public OrderLine fromDto(OrderLineDTO dto) {
		if(dto == null) 
			return null;		
		return OrderLine.builder()
				.id(dto.getId())
				.order(orderService.getById(dto.getOrderId()))
				.product(productService.findById(dto.getProductId()))
				.price(dto.getPrice())
				.quantity(dto.getQuantity())
				.total(dto.getTotal())
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.build();
	}

	@Override
	public OrderLineDTO toDto(OrderLineDTO dto) {
		if(dto == null) 
			return null;
		return OrderLineDTO.builder()
				.id(dto.getId())
				.order(dto.getOrder())
				.product(dto.getProduct())
				.price(dto.getPrice())
				.quantity(dto.getQuantity())
				.total(dto.getTotal())
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.build();
	}

	@Override
	public OrderLine toEntity(OrderLine entity) {
		if(entity == null) 
			return null;		
		return OrderLine.builder()
				.id(entity.getId())
				.order(entity.getOrder())
				.product(entity.getProduct())
				.price(entity.getPrice())
				.quantity(entity.getQuantity())
				.total(entity.getTotal())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.build();
	}

}
