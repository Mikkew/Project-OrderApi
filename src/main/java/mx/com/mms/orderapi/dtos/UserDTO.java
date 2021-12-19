package mx.com.mms.orderapi.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String firstName;
	private String lastName;
	private String username;
	@JsonProperty(required = true)
	@Email(message = "El correo es obligatorio")
	private String email;
	@Size(min = 6)
	@JsonProperty(access = Access.WRITE_ONLY, required = true)
	private String password;
}
