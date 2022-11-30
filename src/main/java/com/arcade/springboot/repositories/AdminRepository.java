package com.arcade.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcade.springboot.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

}
