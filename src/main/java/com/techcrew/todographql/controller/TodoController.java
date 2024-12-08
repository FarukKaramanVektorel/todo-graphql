package com.techcrew.todographql.controller;

import com.techcrew.todographql.model.Todo;
import com.techcrew.todographql.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller

public class TodoController {
    private final TodoService service;

    public TodoController(TodoService service){
        this.service = service;
    }

    @QueryMapping
    public List<Todo> todos() {
        return service.getAllTodos();
    }

    @QueryMapping
    public Todo todo(@Argument Long id) {
        return service.getById(id);
    }

    @QueryMapping
    public List<Todo> getCompleted() {
        return service.getCompletedTodos();
    }

    @QueryMapping
    public List<Todo> getUnCompleted() {
        return service.getUnCompletedTodos();
    }

    @MutationMapping
    public Todo createTodo(@Argument String title, @Argument String description) {
        return service.createTodo(title, description);
    }
}
