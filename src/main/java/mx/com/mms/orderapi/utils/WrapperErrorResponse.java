package mx.com.mms.orderapi.utils;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WrapperErrorResponse<T> {
	private int status;

	@JsonFormat(pattern = "dd-MM-yyyy hh:mm:ss a", timezone = "America/Mexico_City")
	private LocalDateTime time;
	private String path;
	private T errors;
	
	public ResponseEntity<Object> createResponse(HttpStatus status) {
		return new ResponseEntity<>(this, status);
	}
}
