package com.arcade.springboot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arcade.springboot.model.Users;
public interface UsersRepository extends JpaRepository<Users, Integer>{
	@Query(value = "SELECT * FROM Users WHERE email = ?", nativeQuery = true)
	List<Users> findAllByEmail(String email);
}
