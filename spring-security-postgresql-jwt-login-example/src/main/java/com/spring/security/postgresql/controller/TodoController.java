package com.spring.security.postgresql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.security.postgresql.model.Todo;
import com.spring.security.postgresql.security.service.TodoService;

@CrossOrigin(origins = "", maxAge = 3600)
@RestController
@RequestMapping("/todos")
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER')")
	public List<Todo> getToDoList(){
		return todoService.getAll();
	}
	
	@PostMapping("/add")
	@PreAuthorize("hasRole('USER')")
	public Todo addTodo(@RequestBody Todo todo) {
		return this.todoService.saveTodo(todo);
	}
	
	@PutMapping("/edit/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Todo> editTodo(@PathVariable (value = "id") long toDoId, @RequestBody Todo todo){
		return ResponseEntity.ok(this.todoService.ediTodo(toDoId, todo));
	}
	
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> deleteTodoItem(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.deleteTodo(id));
    }
	
	@PutMapping("/state/{id}")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<Todo> changeDoneState(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.changeStateForTodo(id));
    }

}