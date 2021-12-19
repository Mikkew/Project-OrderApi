package mx.com.mms.orderapi.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WrapperAuth <T> {
	private T user;
	private String token;
	
	
	
}
