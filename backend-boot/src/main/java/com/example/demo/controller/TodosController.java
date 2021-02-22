package com.example.demo.controller;

import com.example.demo.auth.security.AppUserDetails;
import com.example.demo.todo.entity.Todo;
import com.example.demo.todo.service.TodoService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodosController {

    private final TodoService todoService;

    public TodosController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    @CrossOrigin(origins = {"http://localhost:3000"})
    public List<Todo> get(@AuthenticationPrincipal AppUserDetails user) {
        return todoService.list(user.getAppUserName());
    }

    @PostMapping
    @CrossOrigin(origins = {"http://localhost:300"})
    public Todo post(@AuthenticationPrincipal AppUserDetails user, @RequestBody Todo todo) {
        return todoService.addTodo(todo.getText(), user.getAppUserName());
    }
}
