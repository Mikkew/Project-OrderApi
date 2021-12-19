package mx.com.mms.orderapi.converters;

import mx.com.mms.orderapi.dtos.OrderDTO;
import mx.com.mms.orderapi.entity.Order;

public class OrderConverter extends AbstractConverter<Order, OrderDTO> {

	@Override
	public OrderDTO fromEntity(Order entity) {
		return (entity != null) ? (OrderDTO.builder()
				.id(entity.getOrderId())
				.total(entity.getTotal())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.build()) : null;
	}

	@Override
	public Order fromDto(OrderDTO dto) {
		return (dto != null) ? (Order.builder()
				.orderId(dto.getId())
				.total(dto.getTotal())
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.build()) : null;
	}

	public OrderDTO toDto(OrderDTO dto) {
		return (dto != null) ? (OrderDTO.builder()
				.id(dto.getId())
				.total(dto.getTotal())
				.createdAt(dto.getCreatedAt())
				.updatedAt(dto.getUpdatedAt())
				.build()) : null;
	}

	public Order toEntity(Order entity) {
		return (entity != null) ? (Order.builder()
				.orderId(entity.getOrderId())
				.total(entity.getTotal())
				.createdAt(entity.getCreatedAt())
				.updatedAt(entity.getUpdatedAt())
				.build() ) : null;
	}

}
