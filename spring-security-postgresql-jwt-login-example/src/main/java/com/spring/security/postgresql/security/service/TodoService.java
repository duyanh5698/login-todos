package com.spring.security.postgresql.security.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.postgresql.model.Todo;
import com.spring.security.postgresql.repository.TodoRepository;

@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	
	public List<Todo> getAll(){
		return this.todoRepository.findAll();
	}
	
	public Todo saveTodo(Todo todo) {
		return todoRepository.save(todo);
	}
	
	public Todo ediTodo(long id, Todo editTodo) {
//		Todo todo = todoRepository.findById(editTodo.getId()).orElse(null);
		Todo todo = todoRepository.findById(id).orElse(null);
		todo.setWhatToDo(editTodo.getWhatToDo());
		return todoRepository.save(todo);
	}
	
	public Boolean deleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            todoRepository.delete(todo);
            return true;
        }
        return false;
    }
	
	public Todo changeStateForTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
        	todo.setCompleted(!todo.isCompleted());
            todoRepository.save(todo);
            return todo;
        }
        return null;
    }
}