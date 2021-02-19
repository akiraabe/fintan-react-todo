package com.example.demo.controller;

import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PutMapping(value= "/api/todos/{todoId}")
    @CrossOrigin(origins = {"http://localhost:300"}, methods = RequestMethod.PUT)
//    @CrossOrigin(origins = {"http://localhost:3000"})
    public Todo put(@PathVariable Long todoId) {
        return todoService.updateStatus(todoId);
    }

    @DeleteMapping(value = "/api/todos/{todoId}")
    @CrossOrigin
    //@CrossOrigin(origins = {"http://localhost:3000"})
    public void delete(@PathVariable Long todoId) {
        todoService.removeTodo(todoId);
    }
}
