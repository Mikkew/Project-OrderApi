package mx.com.mms.orderapi.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import mx.com.mms.orderapi.assembler.OrderLineAssembler;
import mx.com.mms.orderapi.converters.OrderLineConverter;
import mx.com.mms.orderapi.dtos.OrderLineDTO;
import mx.com.mms.orderapi.entity.OrderLine;
import mx.com.mms.orderapi.services.IOrderLineService;
import mx.com.mms.orderapi.utils.WrapperResponse;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
public class OrderLineController {
	
	@Autowired
	private IOrderLineService lineService;
	
	@Autowired
	private OrderLineAssembler lineAssembler;
	
	@Autowired
	private PagedResourcesAssembler<OrderLine> pagedResourcesAssembler;
	
	@Autowired
	private OrderLineConverter converter;
	
	@GetMapping("/lines")
	public ResponseEntity<Object> getAll(
			@PageableDefault (page = 0, sort = {"createdAt"}) Pageable pageable,
			@And({ 
				@Spec(path = "price", params = "price", spec = GreaterThanOrEqual.class) 
			}) Specification<OrderLine> spec) {
		Page<OrderLine> lines = lineService.getAllLines(spec, pageable);
		PagedModel<EntityModel<OrderLineDTO>> pagedLines = pagedResourcesAssembler.toModel(lines, lineAssembler);
		WrapperResponse<Object> response = new WrapperResponse<>(lines, pagedLines, pagedLines.getContent());
		return response.createResponse(HttpStatus.OK);
	}
	
	@GetMapping("/lines/{id}")
	public ResponseEntity<Object> getById(@PathVariable String id) {
		OrderLineDTO lineDTO = converter.fromEntity(lineService.getById(id));
		return ResponseEntity.ok(lineDTO);
	}

	@PostMapping("/lines")
	public ResponseEntity<Object> create(@Valid @RequestBody OrderLineDTO line) {
		OrderLineDTO lineSave = converter.fromEntity(lineService.save(converter.fromDto(line)));
		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(lineSave.getId()).toUri();
		return ResponseEntity.created(uri).body(lineSave);
	}
	
	@PatchMapping("/lines")
	public ResponseEntity<Object> update(@Valid @RequestBody OrderLineDTO line) {
		OrderLineDTO lineUpdate = converter.fromEntity(lineService.update(converter.fromDto(line)));
		return ResponseEntity.accepted().body(lineUpdate);
	}
	
	@PutMapping("/lines/{id}")
	public ResponseEntity<Object> updateById(@Valid @PathVariable String id, @RequestBody OrderLineDTO line) {
		return ResponseEntity.accepted().body(converter.fromEntity(lineService.updateById(id, converter.fromDto(line))));
	}
	
	@DeleteMapping("/lines/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id) {
		return ResponseEntity.accepted().body(converter.fromEntity(lineService.delete(id)));
	}
}
