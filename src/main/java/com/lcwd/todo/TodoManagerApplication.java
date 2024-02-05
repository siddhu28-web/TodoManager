package com.lcwd.todo;

import com.lcwd.todo.dao.TodoDao;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class TodoManagerApplication implements CommandLineRunner {

	Logger loggers = LoggerFactory.getLogger(TodoManagerApplication.class);
	@Autowired
	private TodoDao todoDao;

	public static void main(String[] args) {

		SpringApplication.run(TodoManagerApplication.class, args);
		System.out.println("Start Todos Project");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Application Started");
		//JdbcTemplate jdbcTemplate = todoDao.getTemplate();
		//loggers.info("JdbcTemplateObject : {}",jdbcTemplate);

		//To sove all todo Data
		//Todo todo = new Todo();
		//todo.setId(12349);
		//todo.setTitle("SQL Course");
		//todo.setContent("I have to learn SQL Course");
		//todo.setStatus("PENDING");
		//todo.setAddedDate(new Date());
		//todo.setToDoDate(new Date());
		//todoDao.saveTodo(todo);

		//To get single todo Data
		//Todo todo = todoDao.getTodo(12345);
		//loggers.info("Get Single Todo Data : {}" , todo);

		//todo.setTitle("Embeded Design Course");
		//todo.setContent("I have to learn Embeded Design Course");
		//todo.setStatus("Completed");
		//todo.setAddedDate(new Date());
		//todo.setToDoDate(new Date());
		//Todo todoAll = todoDao.updateData(12345,todo);
		//loggers.info("Get All Todos Data : {}" , todoAll);

		//To get ALL todo Data
		//List<Todo> allTodos =  todoDao.getAllTodo();
		//loggers.info("Get all todos : {}", allTodos);

		//Delete todo from Database
		//todoDao.deleteTodoData(12348);

		//Delete multiple todo Data from Database
		//int[] id;
		//todoDao.deleteMultipleData(new int [] {12346, 12347});

		//By Using Anonymous Class Of Row Mapper Object Mapping
		Todo todo = todoDao.getTodo(2996207);
		loggers.info("Get Single Todo : {}", todo);

		//By Using Lambda Expression Of Row Mapper Object Mapping
		//List<Todo> allTodos = todoDao.getAllTodo();
		//loggers.info("Get All Todos : {}" , allTodos);
	}
}
