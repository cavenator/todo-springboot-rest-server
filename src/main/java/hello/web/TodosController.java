package hello.web;

import hello.dao.TodoDao;
import hello.domain.Todo;
import hello.domain.TodoDto;
import hello.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
public class TodosController {

    @Autowired
    TodoDao todoDao;

    public TodosController(){}

    public TodosController(TodoDao dao){
        this.todoDao = dao;
    }

    @RequestMapping(value="/todo", method=RequestMethod.GET)
    public ResponseEntity<String> getTodos(){
        List<Todo> allTodos = (List<Todo>) todoDao.findAll();
        return ResponseEntity.ok(JsonUtils.toJson(allTodos));
    }

    @RequestMapping(value="/todo", method=RequestMethod.POST)
    public ResponseEntity<String> saveTodo(@RequestBody TodoDto dto){
        if (!dto.isValid()){
            return new ResponseEntity<String>("new todo should have both a title and a description", HttpStatus.BAD_REQUEST);
        }
        Todo newTodo = new Todo(dto);
        Todo savedTodo = todoDao.save(newTodo);
        return ResponseEntity.ok(JsonUtils.toJson(savedTodo));
    }

}

