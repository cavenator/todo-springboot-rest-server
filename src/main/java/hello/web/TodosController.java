package hello.web;

import hello.dao.TodoDao;
import hello.domain.Todo;
import hello.domain.TodoDto;
import hello.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value="/todo", method=RequestMethod.GET)
    public ResponseEntity<String> getTodos(){
        Iterator<Todo> iterator = todoDao.findAll().iterator();
        List<Todo> allTodos = new ArrayList<>();
        iterator.forEachRemaining((todo)-> allTodos.add(todo));
        return ResponseEntity.ok(JsonUtils.toJson(allTodos));
    }

    @RequestMapping(value="/todo", method=RequestMethod.POST)
    public ResponseEntity<String> saveTodo(@RequestBody TodoDto dto){
        Todo newTodo = new Todo(dto);
        Todo savedTodo = todoDao.save(newTodo);
        return ResponseEntity.ok(JsonUtils.toJson(savedTodo));
    }

}

