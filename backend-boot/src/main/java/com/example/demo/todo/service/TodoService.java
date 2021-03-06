package com.example.demo.todo.service;

import com.example.demo.auth.repository.UserProfileRepository;
import com.example.demo.todo.entity.Todo;
import com.example.demo.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserProfileRepository userProfileRepository;

    public TodoService(TodoRepository todoRepository, UserProfileRepository userProfileRepository) {
        this.todoRepository = todoRepository;
        this.userProfileRepository = userProfileRepository;
    }

    public List<Todo> list(String userId) {
        List<Todo> todos = todoRepository.findByUserId(userId);
        //List<Todo> todos = todoRepository.findAll();
        return todos;
    }

    public Todo addTodo(String text, String userId) {
        Todo todo = new Todo(text, userId);
        todoRepository.save(todo);
        return todo;
    }

    public Todo updateStatus(Long todoId) {
        Todo todo = todoRepository.getOne(todoId);
        todo.changeStatus();
        todoRepository.save(todo);
        return todo;
    }

    public void removeTodo(Long todoId) {
        todoRepository.deleteById(todoId);
    }

    public Todo getOne(Long todoId) {
        return todoRepository.getOne(todoId);
    }
}
