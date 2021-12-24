package mx.com.mms.orderapi.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.*;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import mx.com.mms.orderapi.assembler.OrderAssembler;
import mx.com.mms.orderapi.converters.OrderConverter;
import mx.com.mms.orderapi.dtos.OrderDTO;
import mx.com.mms.orderapi.entity.Order;
import mx.com.mms.orderapi.services.IOrderService;
import mx.com.mms.orderapi.utils.WrapperResponse;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
public class OrderController {

	@Autowired
	private IOrderService orderService;

	@Autowired
	private OrderAssembler orderAssembler;

	@Autowired
	private PagedResourcesAssembler<Order> pagedResourcesAssembler;

	@Autowired
	private OrderConverter converter;

	@Operation(summary = "Get All Orders")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the order", 
				    content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = OrderDTO.class)) }),
	})
	@GetMapping("/orders")
	public ResponseEntity<Object> getAll(@PageableDefault(page = 0, sort = { "createdAt" }) Pageable pageable, @And({
			@Spec(path = "totalGreaterThanOrEqual", params = "GreaterThanOrEqual", spec = GreaterThanOrEqual.class) }) Specification<Order> spec) {
		Page<Order> orders = orderService.getAllOrders(spec, pageable);
		PagedModel<EntityModel<OrderDTO>> pagedOrder = pagedResourcesAssembler.toModel(orders, orderAssembler);
		WrapperResponse<Object> response = new WrapperResponse<>(orders, pagedOrder, pagedOrder.getContent());
		return response.createResponse(HttpStatus.OK);
	}

	@GetMapping("/orders/{id}")
	public ResponseEntity<Object> getById(@PathVariable String id) {
		OrderDTO orderDTO = converter.fromEntity(orderService.getById(id));
		return ResponseEntity.ok(orderDTO);
	}

	@PostMapping("/orders")
	public ResponseEntity<Object> create(@Valid @RequestBody OrderDTO order) {
		OrderDTO orderSave = converter.fromEntity(orderService.save(converter.fromDto(order)));
		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(orderSave.getId())
				.toUri();
		return ResponseEntity.created(uri).body(orderSave);
	}

	@PatchMapping("/orders")
	public ResponseEntity<Object> update(@Valid @RequestBody OrderDTO order) {
		Order orderUpdate = converter.fromDto(order);
		orderUpdate = orderService.update(orderUpdate);
		return ResponseEntity.accepted().body(converter.fromEntity(orderUpdate));
	}

	@PutMapping("/orders/{id}")
	public ResponseEntity<Object> updateById(@Valid @PathVariable String id, @RequestBody OrderDTO order) {
		Order orderUpdate = converter.fromDto(order);
		orderUpdate = orderService.updateById(id, orderUpdate);
		return ResponseEntity.accepted().body(converter.fromEntity(orderUpdate));
	}

	@DeleteMapping("/orders/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		Order orderDelete = orderService.delete(id);
		return ResponseEntity.accepted().body(converter.fromEntity(orderDelete));
	}
}
