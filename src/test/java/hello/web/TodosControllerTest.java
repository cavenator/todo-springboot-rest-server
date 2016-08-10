package hello.web;

import hello.dao.TodoDao;
import hello.domain.Todo;
import hello.domain.TodoDto;
import hello.utils.JsonUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TodosControllerTest {

    @Mock
    TodoDao todoDao;

    MockMvc mvc;

    TodosController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new TodosController(todoDao);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testGetAllTodos() throws Exception {
        Todo todo = new Todo("Clean room", "Because its dirty");
        todo.setId(2);
        List<Todo> todos = Arrays.asList(todo);
        Mockito.when(todoDao.findAll()).thenReturn(todos);

        ResultActions response = mvc.perform(MockMvcRequestBuilders.get("/todo"));

        response.andExpect(status().isOk());
        response.andExpect(content().string(JsonUtils.toJson(todos)));

    }

    @Test
    public void testNoTodos() throws Exception {
        List<Todo> todos = Collections.emptyList();
        Mockito.when(todoDao.findAll()).thenReturn(todos);

        ResultActions response = mvc.perform(MockMvcRequestBuilders.get("/todo"));

        response.andExpect(status().isOk());
        response.andExpect(content().string(JsonUtils.toJson(todos)));
    }

    @Test
    public void testAddingNewTodo() throws Exception {
        TodoDto dto = new TodoDto("Clean room", "Because Mom said so");
        Todo todo = new Todo(dto);
        Todo savedTodo = new Todo(dto);
        savedTodo.setId(3);

        Mockito.when(todoDao.save(todo)).thenReturn(savedTodo);

        ResultActions response = mvc.perform(MockMvcRequestBuilders.post("/todo").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(dto)));

        response.andExpect(status().isOk());
        response.andExpect(content().string(JsonUtils.toJson(savedTodo)));
    }

    @Test
    public void testInvalidPayload() throws Exception {
        TodoDto dto = new TodoDto();
        ResultActions response = mvc.perform(MockMvcRequestBuilders.post("/todo").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(dto)));

        response.andExpect(status().isBadRequest());
    }

}
