package mx.com.mms.orderapi.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName(value = "line")
@Relation(collectionRelation = "lines")
public class OrderLineDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	@JsonProperty(access = Access.READ_ONLY)
	private OrderDTO order;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String orderId;
	
	@JsonProperty(access = Access.READ_ONLY)
	private ProductDTO product;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String productId;
	
	private Double price;
	private Double quantity;
	private Double total;
	
	@JsonIgnore
	private LocalDateTime createdAt;
	
	@JsonIgnore
	private LocalDateTime updatedAt;
}
