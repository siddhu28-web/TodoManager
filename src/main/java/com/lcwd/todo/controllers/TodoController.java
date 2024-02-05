package com.lcwd.todo.controllers;

import com.lcwd.todo.models.Todo;
import com.lcwd.todo.services.TodoService;
import com.lcwd.todo.services.impl.TodoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoController {

    Logger loggers = LoggerFactory.getLogger(TodoController.class);

    @Autowired
    private TodoService todoservice;

    Random random = new Random();
    //create Todo method
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){
//
       // String str = null;
      //  loggers.info("Null Pointer Exception : {}", str.length());
        //int x = 10;
      // int y = x/0;
      // System.out.println(y);
       // Integer.parseInt("sl1232dd");
        //create todo
        int id = random.nextInt(9999999);
        todo.setId(id);
        //Create date with system default current date
        Date currentDate = new Date();
        loggers.info("Current Date: {}",currentDate);
        loggers.info("ToDo Date: {}",todo.getToDoDate());
        todo.setAddedDate(currentDate);
        loggers.info("Create Todo");
        //Call service to create todo
        Todo todo1 = todoservice.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }
//6993150 , 3616437
    //get all todo method
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(){
        List<Todo> allTodos = todoservice.getAllTodos();
        return new ResponseEntity<>(allTodos,HttpStatus.OK);
    }

    //get single todo method
    @GetMapping("/{todoId}")
     public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId) throws ParseException {
        Todo todo = todoservice.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }

    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails , @PathVariable int todoId){
        Todo todo = todoservice.updateTodo(todoId,todoWithNewDetails);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoHandler(@PathVariable int todoId){
        //todoservice.deleteTodoId(todoId);
        todoservice.deleteTodoData(todoId);
        return ResponseEntity.ok("TodoId Deleted Succesfully");
    }

    //Exception Handler

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex){
        System.out.println(ex.getMessage());
        System.out.println("Null Pointer Exception Generated");
        return new ResponseEntity<>("Null pointer exception generated" + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
/*
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> nullPointerExceptionHandler(NumberFormatException ex){
        System.out.println(ex.getMessage());
        System.out.println("Number Format Exception Generated");
        return new ResponseEntity<>("Null format exception generated" + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> nullPointerExceptionHandler(ArithmeticException ex){
        System.out.println(ex.getMessage());
        System.out.println("Arithmetic Exception Generated");
        return new ResponseEntity<>("Arithmetic exception generated" + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
    */

    /*
    @ExceptionHandler(value = {ArithmeticException.class,NumberFormatException.class,NullPointerException.class})
    public ResponseEntity<String> ExceptionHandler(Exception ex){
        System.out.println(ex.getMessage());
        System.out.println("Exception Generated");
        return new ResponseEntity<>("Exception generated" + ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
   */
}
