package hello.web;

import hello.dao.TodoDao;
import hello.domain.InMemoryDatabase;
import hello.domain.Todo;
import hello.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/todo")
public class TodoController {

    @Autowired
    TodoDao todoDao;

    public TodoController(){}

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getTodoById(@PathVariable(value="id") Integer id) {
        Todo todo = todoDao.findOne(id);
        if (todo == null){
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.ok(JsonUtils.toJson(todo));
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTodoById(@PathVariable(value="id") Integer id){
        Todo todo = todoDao.findOne(id);
        if (todo == null){
            return ResponseEntity.badRequest().body("Todo does not exists");
        }
        todoDao.delete(todo);
        return ResponseEntity.badRequest().body("Todo was successfully deleted");
    }

}

