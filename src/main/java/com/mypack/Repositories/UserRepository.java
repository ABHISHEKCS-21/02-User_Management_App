package com.mypack.Repositories;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mypack.Entity.User;
@Configuration
@Repository
public interface UserRepository extends JpaRepository<User,Serializable> {

	
	public User findByEmailAndPassword(String email, String pws);
	
	public User findByEmail(String email);

}
