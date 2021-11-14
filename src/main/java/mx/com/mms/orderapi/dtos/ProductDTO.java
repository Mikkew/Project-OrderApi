package mx.com.mms.orderapi.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.*;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonRootName(value = "product")
@Relation(collectionRelation = "products")
public class ProductDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;

	@NotBlank(message = "El nombre es requerido")
	@Size(max = 100, message = "El nombre es muy largo (min 100)")
	private String name;

	@Min(value = 0)
	private Double price;
	
	@JsonIgnore
	private LocalDateTime createdAt;
	
	@JsonIgnore
	private LocalDateTime updatedAt;
}
