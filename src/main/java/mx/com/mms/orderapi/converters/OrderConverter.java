package mx.com.mms.orderapi.converters;

import mx.com.mms.orderapi.dtos.OrderDTO;
import mx.com.mms.orderapi.entity.Order;

public class OrderConverter extends AbstractConverter<Order, OrderDTO> {

	@Override
	public OrderDTO fromEntity(Order entity) {
		if (entity == null)
			return null;
		return OrderDTO.builder()
				.id(entity.getOrderId())
				.total(entity.getTotal())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.build();
	}

	@Override
	public Order fromDto(OrderDTO dto) {
		if (dto == null)
			return null;
		return Order.builder()
				.orderId(dto.getId())
				.total(dto.getTotal())
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.build();
	}

	@Override
	public OrderDTO toDto(OrderDTO dto) {
		if (dto == null)
			return null;
		return OrderDTO.builder()
				.id(dto.getId())
				.total(dto.getTotal())
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.build();
	}

	@Override
	public Order toEntity(Order entity) {
		if (entity == null)
			return null;
		return Order.builder()
				.orderId(entity.getOrderId())
				.total(entity.getTotal())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.build();
	}

}
