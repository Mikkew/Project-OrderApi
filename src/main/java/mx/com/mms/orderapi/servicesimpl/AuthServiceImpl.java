package mx.com.mms.orderapi.servicesimpl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mx.com.mms.orderapi.entity.Role;
import mx.com.mms.orderapi.entity.User;
import mx.com.mms.orderapi.exceptions.BadRequestException;
import mx.com.mms.orderapi.exceptions.GeneralException;
import mx.com.mms.orderapi.exceptions.ResourceNotFoundException;
import mx.com.mms.orderapi.repository.RoleRepository;
import mx.com.mms.orderapi.repository.UserRepository;
import mx.com.mms.orderapi.services.IAuthService;

@Slf4j
@Service
@Transactional
public class AuthServiceImpl implements IAuthService, UserDetailsService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User createUser(User user) {
		try {
			User userExist = userRepository.findByEmail(user.getEmail()).orElse(null);		
			if(userExist != null ) {
				new ResourceNotFoundException("User exist");
			} 
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return userRepository.save(user);
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public Role saveRole(Role role) {
		try {
			Role roleExist = roleRepository.findByName(role.getName()).orElse(null);
			if (roleExist != null) {
				new BadRequestException("Role Exist");
			} 
			return roleRepository.save(role);	
		} catch (ResourceNotFoundException | BadRequestException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public void addRoleToUser(String email, String roleName) {
		try {
			User user = userRepository.findByEmail(email)
					.orElseThrow(() -> new ResourceNotFoundException("User "+ email +" no exist"));
			Role role = roleRepository.findByName(roleName)
					.orElseThrow(() -> new ResourceNotFoundException("Role " + roleName +"no exist"));
			user.getRoles().add(role);
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public User getUsuario(String email) {
		try {
			User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User no exist"));
			return user;
		} catch (ResourceNotFoundException ex) {
			log.info(ex.getMessage(), ex);
			throw ex;
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
	}

	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			User usuario = userRepository.findByEmail(username)
					.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			usuario.getRoles().forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			});
			return new org.springframework.security.core.userdetails.User(usuario.getEmail(), usuario.getPassword(),
					authorities);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			throw new GeneralException("Error al realizar la consulta a la base de datos");
		}
		
		
	}

//	@Override
//	public User loginUser(User user) {
//		try {
//			User userLogged = userRepository.findByEmail(user.getEmail())
//					.orElseThrow(() -> new ResourceNotFoundException("Usuario o contraseña incorrectos"));	
//			log.info(userLogged.getEmail());
//			return userLogged;
//		} catch (ResourceNotFoundException ex) { 
//			log.info(ex.getMessage(), ex);
//			throw ex;
//		} catch (Exception ex) {
//			log.error(ex.getMessage(), ex);
//			throw new GeneralException("Error al realizar la consulta a la base de datos");
//		}
//	}

}
