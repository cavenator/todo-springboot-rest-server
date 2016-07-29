package hello.web;

import hello.domain.InMemoryDatabase;
import hello.domain.Todo;
import hello.utils.JsonUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {

    private InMemoryDatabase inMemoryDatabase;

    public TodoController(){
        inMemoryDatabase = InMemoryDatabase.getInstance();
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getTodoById(@PathVariable(value="id") Integer id) {
        Todo todo = inMemoryDatabase.getById(id);
        if (todo == null){
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.ok(JsonUtils.toJson(todo));
        }

    }

    @RequestMapping(value = "todo/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTodoById(@PathVariable(value="id") Integer id){
        Todo todo = inMemoryDatabase.getById(id);
        if (todo == null){
            return ResponseEntity.badRequest().body("Todo does not exists");
        }
        inMemoryDatabase.remove(todo);
        return ResponseEntity.badRequest().body("Todo was successfully deleted");
    }

}

