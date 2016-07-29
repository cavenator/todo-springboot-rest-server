package hello.domain;

import hello.web.TodosController;

public class Todo {
    private Integer id;
    private String title;
    private String description;

    private static Integer nextId = 1;

    public Todo(){}; //for Jackson

    public Todo(TodoDto dto){
        this(dto.title, dto.description);
    }

    public Todo(String title, String description){
        id = nextId++;
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    //for testing!
    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Todo todo = (Todo) o;

        if (id != null ? !id.equals(todo.id) : todo.id != null) return false;
        if (title != null ? !title.equals(todo.title) : todo.title != null) return false;
        return !(description != null ? !description.equals(todo.description) : todo.description != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
