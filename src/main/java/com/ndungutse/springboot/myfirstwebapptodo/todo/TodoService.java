package com.ndungutse.springboot.myfirstwebapptodo.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ndungutse.springboot.myfirstwebapptodo.jpa.TodoRepository;

import jakarta.validation.Valid;

// Business Logic
@Service
public class TodoService {
    private TodoRepository todoRepository;
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 3;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // To initialize static variables, use static block
    static {
        todos.add(new Todo(1, "Eric", "Learn AWS", LocalDate.now().plusWeeks(3), false));
        todos.add(new Todo(2, "James", "Learn DEVOPS", LocalDate.now().plusWeeks(5), false));
        todos.add(new Todo(3, "Lona", "Learn Azure", LocalDate.now().plusWeeks(5), false));
        todos.add(new Todo(4, "eric", "Learn K8s", LocalDate.now().plusWeeks(5), false));
    }

    // ***** Find All ****
    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    // ***** Find todos of a user
    public List<Todo> findByUsername(String username) {
        return todoRepository.findByUsername(username);
    }

    // ***** Add new Todo
    public void addTodo(Todo todo) {
        todo.setUsername(getLoggedInUser());
        todoRepository.save(todo);
    }

    // **** Find todo by id
    public Todo findTodoById(int id) {
        // return todos.stream().filter(todo -> todo.getId() == id).findFirst().get();

        return todoRepository.findById(id).get();

    }

    // ***** Delete a todo by id
    public void deleteTodoById(int id) {
        // // Predicate<? super Todo> predicate = todos -> todo.getId() == id;
        // todos.removeIf(todo -> todo.getId() == id);
        todoRepository.deleteById(id);
    }

    public void updateTodo(@Valid Todo todo) {
        // deleteTodoById(todo.getId());
        // todos.add(todo);
        todo.setUsername(getLoggedInUser());
        todoRepository.save(todo);

    }

    public String getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
