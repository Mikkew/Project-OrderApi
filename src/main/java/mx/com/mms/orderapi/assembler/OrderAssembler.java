package mx.com.mms.orderapi.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;

import mx.com.mms.orderapi.controllers.OrderController;
import mx.com.mms.orderapi.converters.OrderConverter;
import mx.com.mms.orderapi.dtos.OrderDTO;
import mx.com.mms.orderapi.entity.Order;

@Component
public class OrderAssembler implements RepresentationModelAssembler<Order, EntityModel<OrderDTO>> {

	@Autowired
	private OrderConverter converter = new OrderConverter();
	
	@Override
	public EntityModel<OrderDTO> toModel(Order entity) {
		EntityModel<OrderDTO> model = EntityModel.of(converter.fromEntity(entity));
		Link link = linkTo(methodOn(OrderController.class).getById(entity.getOrderId())).withSelfRel();
		model.add(link);
		return model;
	}

	public CollectionModel<EntityModel<OrderDTO>> toCollectionModel(
			Pageable pageable,
			Specification<Order> spec,
			List<Order> entities) {
		List<OrderDTO> ordersDTO = converter.fromEntity(entities);
		List<EntityModel<OrderDTO>> model = ordersDTO.stream()
				.map(EntityModel::of).toList();
		Link link = linkTo(methodOn(OrderController.class).getAll(pageable, spec)).withSelfRel();
		return CollectionModel.of(model, link);
	}
	
}
