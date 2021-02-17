package com.example.demo.controller;

import com.example.demo.auth.entity.AppUserDetails;
import com.example.demo.entity.Todo;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodosController {

    @Autowired
    private UserSession userSession;

    private final TodoService todoService;

    public TodosController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    @CrossOrigin
    //@CrossOrigin(origins = {"http://localhost:3000"})
    public List<Todo> get(@AuthenticationPrincipal AppUserDetails user) {
        System.out.println("**** user -> " + user);
        System.out.println("**** " + userSession.getUserId());
        if (userSession.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "forbidden");
        }
        return todoService.list(userSession.getUserId());
    }

    @PostMapping
    @CrossOrigin(origins = {"http://localhost:300"})
    //@CrossOrigin(origins = {"http://localhost:3000"})
    public Todo post(@RequestBody Todo todo) {
        if (userSession.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "forbidden");
        }
        return todoService.addTodo(todo.getText(), userSession.getUserId());
    }
}
