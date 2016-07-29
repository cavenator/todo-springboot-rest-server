package hello.web;

import hello.domain.InMemoryDatabase;
import hello.domain.Todo;
import hello.domain.TodoDto;
import hello.utils.JsonUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodosController {

    InMemoryDatabase inMemoryDatabase;

    public TodosController(){
        inMemoryDatabase = InMemoryDatabase.getInstance();
    }

    @RequestMapping(value="/todo", method=RequestMethod.GET)
    public ResponseEntity<String> getTodos(){
        return ResponseEntity.ok(JsonUtils.toJson(inMemoryDatabase.getTodos()));
    }

    @RequestMapping(value="/todo", method=RequestMethod.POST)
    public ResponseEntity<String> saveTodo(@RequestBody TodoDto dto){
        Todo todo = new Todo(dto);
        inMemoryDatabase.save(todo);
        return ResponseEntity.ok(JsonUtils.toJson(todo));
    }

}

