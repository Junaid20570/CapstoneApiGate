package com.example.nessApiGatewayjwt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.nessApiGatewayjwt.model.User;

@Repository 
public interface NewRepo extends JpaRepository<User,Integer>{
	public User findByEmailAndPwd(String email, String pwd);
	
	@Query("from User u where u.email=:email")
	Optional<User> findById(String email);
	
	@Query("delete from  User u where u.email=:email")
	void deleteById(String email);
}
