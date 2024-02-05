package com.lcwd.todo.services.impl;

import com.lcwd.todo.exceptions.ResourceNotFoundException;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Component
@Service
public class TodoServiceImpl implements com.lcwd.todo.services.TodoService {

    Logger loggers = LoggerFactory.getLogger(TodoServiceImpl.class);
    List<Todo> todos = new ArrayList<>();


    //create todo method
    public Todo createTodo(Todo todo){
        //create
        //change the logic
        todos.add(todo);
        loggers.info("Todos {} :",this.todos);
        return todo;
    }

    public List<Todo> getAllTodos() {
        return todos;
    }

    public Todo getTodo(int todoId) {
        Todo todo = todos.stream().filter(t->todoId==t.getId()).findAny().orElseThrow(()-> new ResourceNotFoundException("Todo Not Found With Given Id",HttpStatus.NOT_FOUND));
        loggers.info("TODO {} :" , todo);
        return todo;
    }

    public Todo updateTodo(int todoId, Todo todo) {
        List<Todo> newUpdatedList = todos.stream().map(t->{
            if (t.getId() == todoId){
                //perform update logic
                t.setTitle(todo.getTitle());
                t.setContent(todo.getContent());
                t.setStatus((todo.getStatus()));
                return t;
            }else {
                return t;
            }
        }).collect(Collectors.toList());
        todos = newUpdatedList;
        todo.setId(todoId);
        return todo;
    }

    //public void deleteTodoId(int todoId) {
    public void deleteTodoData(int todoId) {
        List<Todo> newTodoId = todos.stream().filter(t->t.getId()!=todoId).collect(Collectors.toList());
        todos = newTodoId;
    }
}
