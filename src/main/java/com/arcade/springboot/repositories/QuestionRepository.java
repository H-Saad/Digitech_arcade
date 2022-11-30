package com.arcade.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arcade.springboot.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
