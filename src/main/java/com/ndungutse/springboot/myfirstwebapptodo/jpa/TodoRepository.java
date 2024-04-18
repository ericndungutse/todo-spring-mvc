package com.ndungutse.springboot.myfirstwebapptodo.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.ndungutse.springboot.myfirstwebapptodo.todo.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    public List<Todo> findByUsername(String username);
}
