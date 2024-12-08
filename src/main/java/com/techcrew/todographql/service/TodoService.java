package com.techcrew.todographql.service;

import com.techcrew.todographql.model.Todo;
import com.techcrew.todographql.repository.TodoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class TodoService {
    private final TodoRepository repository;

    public TodoService(TodoRepository repository){
        this.repository = repository;
    }

    public List<Todo> getAllTodos() {
        return repository.findAll();
    }

    public Todo getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException(id + " ID'li Todo bulunamadı"));
    }

    public List<Todo> getCompletedTodos() {
        return repository.findByCompleted(true);
    }

    public List<Todo> getUnCompletedTodos() {
        return repository.findByCompleted(false);
    }

    @Transactional
    public Todo createTodo(String title, String description) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCompleted(false);
        return repository.save(todo);
    }
    @Transactional
    public Todo updateTodo(Long id,String title,String desc,Boolean completed){
        Todo todo=getById(id);
        if(title!=null){
            todo.setTitle(title);
        }
        if(desc!=null){
            todo.setDescription(desc);
        }
        if(completed!=null){
            todo.setCompleted(completed);
        }
        return repository.save(todo);
    }

    public boolean deleteTodo(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }


}
