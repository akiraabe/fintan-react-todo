package com.example.demo.controller;

import com.example.demo.auth.security.AppUserDetails;
import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;
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
    @CrossOrigin
    //@CrossOrigin(origins = {"http://localhost:3000"})
    public List<Todo> get(@AuthenticationPrincipal AppUserDetails user) {
        System.out.println("**** user -> " + user);
        return todoService.list(user.getAppUserName());
    }

    @PostMapping
    @CrossOrigin(origins = {"http://localhost:300"})
    //@CrossOrigin(origins = {"http://localhost:3000"})
    public Todo post(@RequestBody Todo todo) {
        return todoService.addTodo(todo.getText());
    }
}
