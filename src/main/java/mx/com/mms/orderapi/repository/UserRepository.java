package mx.com.mms.orderapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import mx.com.mms.orderapi.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
	
	 public Optional<User> findByUsername(String username);
	    
	 public Optional<User> findByEmail(String email);
}
