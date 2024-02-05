package com.lcwd.todo.services;

import com.lcwd.todo.models.Todo;

import java.text.ParseException;
import java.util.List;

public interface TodoService {

    public Todo createTodo(Todo todo);
    public Todo getTodo(int todoId) throws ParseException;
    public List<Todo> getAllTodos();
    public Todo updateTodo(int todoId, Todo todo);
    public void deleteTodoData(int todoId);

    //void deleteTodoId(int todoId);
}
