package com.arcade.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcade.springboot.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{

}
