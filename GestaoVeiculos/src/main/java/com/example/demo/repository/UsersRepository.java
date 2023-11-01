package com.example.demo.repository;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.domain.Users;


public interface UsersRepository extends JpaRepository<Users, Integer>{
	UserDetails findByLogin(String username);
	
}
