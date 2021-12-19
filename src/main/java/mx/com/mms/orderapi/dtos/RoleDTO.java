package mx.com.mms.orderapi.dtos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	@JsonProperty(required = true)
	private String name;
}
