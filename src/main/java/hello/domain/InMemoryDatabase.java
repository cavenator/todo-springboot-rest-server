package hello.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDatabase {
    private static InMemoryDatabase ourInstance = new InMemoryDatabase();

    public static InMemoryDatabase getInstance() {
        return ourInstance;
    }

    private Map<Integer, Todo> integerTodoMap;

    private InMemoryDatabase() {
        integerTodoMap = new HashMap<>();
    }

    public Todo getById(Integer id){
        return integerTodoMap.get(id);
    }

    public void save(Todo todo){
        integerTodoMap.put(todo.getId(), todo);
    }

    public Todo remove(Todo todo){
        return integerTodoMap.remove(todo.getId());
    }

    public List<Todo> getTodos(){
        return new ArrayList<Todo>(integerTodoMap.values());
    }
}
