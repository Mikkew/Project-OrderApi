package mx.com.mms.orderapi.controllers;

import java.io.IOException;
import java.net.URI;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import mx.com.mms.orderapi.converters.RoleConverter;
import mx.com.mms.orderapi.converters.UserConverter;
import mx.com.mms.orderapi.dtos.RoleDTO;
import mx.com.mms.orderapi.dtos.UserDTO;
import mx.com.mms.orderapi.entity.Role;
import mx.com.mms.orderapi.entity.User;
import mx.com.mms.orderapi.services.IAuthService;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private IAuthService authService;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private RoleConverter roleConverter;
	
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE, name = "GET USERS")
	public ResponseEntity<Object> getUser() {
		return ResponseEntity.ok(userConverter.fromEntity(authService.getUsers()));
	}
	
	@PostMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveUser(@RequestBody UserDTO user) {
		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(userConverter.fromEntity(authService.createUser(userConverter.fromDto(user))));
    }
	
	@GetMapping("/roles")
    public ResponseEntity<Object> getRoles() {
        return ResponseEntity.ok(roleConverter.fromEntity(authService.getRoles()));
    }
	
	@PostMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveRole(@RequestBody RoleDTO role) {
		URI uri = MvcUriComponentsBuilder.fromController(getClass()).path("/{id}").buildAndExpand(role.getId()).toUri();
        return ResponseEntity.created(uri).body(roleConverter.fromEntity(authService.saveRole(roleConverter.fromDto(role))));
    }
	
	@GetMapping(value = "/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refresh_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String email = decodedJWT.getSubject();
                User user = authService.getUsuario(email);
                
                String access_token = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURI())
                        .withClaim("roles", user.getRoles().stream().map(Role::getName).toList())
                        .sign(algorithm);
                
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			} catch (JWTCreationException | JWTVerificationException | IOException | IllegalArgumentException ex) {
				log.error("Error logging in: {}", ex.getMessage());
				response.setHeader("error", ex.getMessage());
                response.setStatus(HttpStatus.FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", ex.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                try {
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                } catch (IOException ex1) {                
                    error.put("error_message", ex1.getMessage());
                }
			}
		} else {
			 throw new RuntimeException("Refresh token is missing");
		}
	}
}
