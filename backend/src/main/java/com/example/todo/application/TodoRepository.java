package com.example.todo.application;

import com.example.todo.domain.Todo;
import com.example.todo.domain.TodoId;
import com.example.todo.domain.UserId;

import java.util.List;

public interface TodoRepository {

    List<Todo> list(UserId userid);

    TodoId nextId();

    void add(UserId userId, Todo todo);
}
