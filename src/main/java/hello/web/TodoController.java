package hello.web;

import hello.dao.TodoDao;
import hello.domain.InMemoryDatabase;
import hello.domain.Todo;
import hello.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping(value="/todo")
public class TodoController {

    @Autowired
    TodoDao todoDao;

    public TodoController(){}

    public TodoController(TodoDao todoDao){
        this.todoDao = todoDao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getTodoById(@PathVariable(value="id") Integer id) {
        Optional<Todo> maybeTodo = Optional.ofNullable(todoDao.findOne(id));
        ResponseEntity<String> badRequest = ResponseEntity.badRequest().body(String.format("No todo found with id %s", id));
        return maybeTodo.flatMap( todo -> Optional.of(ResponseEntity.ok(JsonUtils.toJson(todo)))).orElse(badRequest);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTodoById(@PathVariable(value="id") Integer id){
        todoDao.delete(id);
        return ResponseEntity.noContent().build();
    }

}

