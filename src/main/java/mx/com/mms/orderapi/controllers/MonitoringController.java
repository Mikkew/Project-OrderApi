package mx.com.mms.orderapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
public class MonitoringController {

	@Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	@GetMapping("/")
    public ResponseEntity<List<String>> getEndpoints() {
		
        return new ResponseEntity<>(
                requestMappingHandlerMapping
                        .getHandlerMethods()
                        .keySet()
                        .stream()
                        .map( RequestMappingInfo::toString )
                        .toList(),
                HttpStatus.OK
        );
	}
}
