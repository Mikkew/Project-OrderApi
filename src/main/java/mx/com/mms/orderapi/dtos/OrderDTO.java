package mx.com.mms.orderapi.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName(value = "order")
@Relation(collectionRelation = "orders")
public class OrderDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private Double total;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime createdAt;
	
	@JsonIgnore
	private LocalDateTime updatedAt;
	
}
