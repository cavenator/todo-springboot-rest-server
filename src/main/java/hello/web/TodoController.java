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

    public TodoController(TodoDao todoDao){
        this.todoDao = todoDao;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> getTodoById(@PathVariable(value="id") Integer id) {
        System.out.println(id);
        Todo todo = todoDao.findOne(id);
        if (todo == null){
            return ResponseEntity.badRequest().body(String.format("No todo found with id %s", id));
        } else {
            return ResponseEntity.ok(JsonUtils.toJson(todo));
        }

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteTodoById(@PathVariable(value="id") Integer id){
        todoDao.delete(id);
        return ResponseEntity.noContent().build();
    }

}

