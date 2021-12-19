package mx.com.mms.orderapi.services;

import java.util.List;

import mx.com.mms.orderapi.entity.Role;
import mx.com.mms.orderapi.entity.User;

public interface IAuthService {
	
	public User createUser(User user);
	
	Role saveRole(Role role);
    
    void addRoleToUser(String email, String roleName);

    User getUsuario(String email);

    List<User> getUsers();
    
    List<Role> getRoles();
}
