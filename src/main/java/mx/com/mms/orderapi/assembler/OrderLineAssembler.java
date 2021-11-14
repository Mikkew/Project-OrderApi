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

import mx.com.mms.orderapi.controllers.OrderLineController;
import mx.com.mms.orderapi.converters.OrderLineConverter;
import mx.com.mms.orderapi.dtos.OrderLineDTO;
import mx.com.mms.orderapi.entity.OrderLine;

@Component
public class OrderLineAssembler implements RepresentationModelAssembler<OrderLine, EntityModel<OrderLineDTO>> {
	
	@Autowired
	private OrderLineConverter converter;

	@Override
	public EntityModel<OrderLineDTO> toModel(OrderLine entity) {
		EntityModel<OrderLineDTO> model = EntityModel.of(converter.fromEntity(entity));
		Link link = linkTo(methodOn(OrderLineController.class).getById(entity.getId())).withSelfRel();
		model.add(link);
		return model;
	}


	public CollectionModel<EntityModel<OrderLineDTO>> toCollectionModel(
			Pageable pageable,
			Specification<OrderLine> spec,
			List<OrderLine> entities) {
		List<OrderLineDTO> lineDTOs = converter.fromEntity(entities);
		List<EntityModel<OrderLineDTO>> model = lineDTOs.stream().map(EntityModel::of).toList();
		Link link = linkTo(methodOn(OrderLineController.class).getAll(pageable, spec)).withSelfRel();
		return CollectionModel.of(model, link);
	}

	
}
