package com.lcwd.todo.dao;


import com.lcwd.todo.helper.Helper;
import com.lcwd.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TodoDao {

    Logger loggers = LoggerFactory.getLogger(TodoDao.class);

   //@Autowired
    private JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;

        //Create table if not exist
        String CreateTable = "create table IF NOT EXISTS todos(id int primary key,title varchar(100) not null,content varchar(500) null,status varchar(10) not null,addedDate datetime,toDoDate datetime)";
        int update = template.update(CreateTable);
        loggers.info("Todo Table Created : {} " , update);
    }


    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    //save todo in database

    public Todo saveTodo(Todo todo){
        String insertQuery = "insert into todos(id,title,content,status,addedDate,toDoDate) values(?,?,?,?,?,?)";
        int rows = template.update(insertQuery,todo.getId(),todo.getTitle(),todo.getContent(),todo.getStatus(),todo.getAddedDate(),todo.getToDoDate());
        loggers.info("JDBC OPERATION : {} INSERTED " , rows);
        return todo;
    }


    //get single todo from database
    public Todo getTodo(int id) throws ParseException {
        String query = "select * from todos where id = ?";
        Todo todo = template.queryForObject(query, new RowMapper<Todo>() {
            @Override
            public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Todo todo = new Todo();
                todo.setId(( rs.getInt("id")));
                todo.setTitle(( rs.getString("title")));
                todo.setContent(( rs.getString("content")));
                todo.setStatus(( rs.getString("status")));
                try {
                    todo.setAddedDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
                    todo.setToDoDate(Helper.parseDate((LocalDateTime) rs.getObject("toDoDate")));
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                return todo;
            }
        },id);
        //Todo todo = template.queryForObject(query,new TodoRowMapper(),id);
        /*
        Map<String,Object> todoData = template.queryForMap(query,id);

        loggers.info("TODO : {}" , todoData);
        Todo todo = new Todo();
        todo.setId(((int) todoData.get("id")));
        todo.setTitle(((String) todoData.get("title")));
        todo.setContent(((String) todoData.get("content")));
        todo.setStatus(((String) todoData.get("status")));
        todo.setAddedDate(Helper.parseDate((LocalDateTime) todoData.get("addedDate")));
        todo.setToDoDate(Helper.parseDate((LocalDateTime) todoData.get("toDoDate")));
       */
        return todo;
    }
    //get all todo from database
    public List<Todo> getAllTodo(){
        String query = "select * from todos";
        List <Todo> todos = template.query(query,(rs,rowNum) ->{
            Todo todo = new Todo();
            todo.setId(( rs.getInt("id")));
            todo.setTitle(( rs.getString("title")));
            todo.setContent(( rs.getString("content")));
            todo.setStatus(( rs.getString("status")));
            try {
                todo.setAddedDate(Helper.parseDate((LocalDateTime) rs.getObject("addedDate")));
                todo.setToDoDate(Helper.parseDate((LocalDateTime) rs.getObject("toDoDate")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }


            return todo;});
        //List <Todo> todo = template.query(query,new TodoRowMapper());
        return todos;
        /*
        List <Map<String,Object>> maps = template.queryForList(query);
        List <Todo> todos = maps.stream().map((map)->{
            Todo todo = new Todo();
            todo.setId(((int) map.get("id")));
            todo.setTitle(((String) map.get("title")));
            todo.setContent(((String) map.get("content")));
            todo.setStatus(((String) map.get("status")));
            try {
                todo.setAddedDate(Helper.parseDate((LocalDateTime) map.get("addedDate")));
                todo.setToDoDate(Helper.parseDate((LocalDateTime) map.get("toDoDate")));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            return todo;

        }).collect(Collectors.toList());
        return todos;
*/
    }

    //update todo from database
    public Todo updateData(int id ,Todo newData){
        String query = "update todos set title=?,content=?,status=?,addedDate=?,toDoDate=? WHERE id=?";
        int update = template.update(query,newData.getTitle(),newData.getContent(),newData.getStatus(),newData.getAddedDate(),newData
                .getToDoDate(),id);
        loggers.info("Updated Data {}:",update);
        newData.setId(id);
        return newData;
    }

    //delete todo from database
    public void deleteTodoData(int id){
        String query = "Delete from todos WHERE id=?";
        int delete = template.update(query,id);
        loggers.info("Deleted Data : {}",delete);
    }

    public void deleteMultipleData(int [] ids){
        String query = "Delete from todos WHERE id=?";
        int[] ints = template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
              int id = ids[i];
              ps.setInt(1,id);
            }

            @Override
            public int getBatchSize() {
                return ids.length;
            }
        });

        for (int i : ints) {
            loggers.info("Deleted Data {} : " , i );
        }
    }
}
