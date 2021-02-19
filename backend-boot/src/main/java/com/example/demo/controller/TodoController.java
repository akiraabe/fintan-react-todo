package com.example.demo.controller;

import com.example.demo.todo.entity.Todo;
import com.example.demo.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(value="/")
    @CrossOrigin(origins = {"http://localhost:300"}, methods = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @PutMapping(value= "/api/todos/{todoId}")
    @CrossOrigin(origins = {"http://localhost:300"}, methods = RequestMethod.PUT)
    public Todo put(@PathVariable Long todoId) {
        return todoService.updateStatus(todoId);
    }

    @DeleteMapping(value = "/api/todos/{todoId}")
    @CrossOrigin(origins = {"http://localhost:300"}, methods = RequestMethod.DELETE)
    public void delete(@PathVariable Long todoId) {
        todoService.removeTodo(todoId);
    }
}
