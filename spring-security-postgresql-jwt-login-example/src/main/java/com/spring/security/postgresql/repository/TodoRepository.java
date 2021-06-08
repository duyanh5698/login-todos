package com.spring.security.postgresql.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.postgresql.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
