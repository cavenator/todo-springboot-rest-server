package hello.web;

import hello.dao.TodoDao;
import hello.domain.Todo;
import hello.utils.JsonUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.xml.transform.Result;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TodoControllerTest {

    private MockMvc mvc;

    private TodoController controller;

    @Mock
    private TodoDao todoDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new TodoController(todoDao);
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void verifyGetTodoById() throws Exception {
        Todo expectedTodo = new Todo("Clean room", "Because I have to");
        expectedTodo.setId(1);

        Mockito.when(todoDao.findOne(1)).thenReturn(expectedTodo);

        ResultActions response = mvc.perform(MockMvcRequestBuilders.get("/todo/1"));

        response.andExpect(status().isOk());
        response.andExpect(content().string(JsonUtils.toJson(expectedTodo)));
    }

    @Test
    public void testNoTodoFound() throws Exception {
        Mockito.when(todoDao.findOne(1)).thenReturn(null);

        ResultActions response = mvc.perform(MockMvcRequestBuilders.get("/todo/1"));

        response.andExpect(status().isBadRequest());
    }

    @Test
    public void deleteTodoById() throws Exception {
        Mockito.doNothing().when(todoDao);

        ResultActions response = mvc.perform(MockMvcRequestBuilders.delete("/todo/1"));

        response.andExpect(status().isNoContent());
    }

    @Test
    public void testComputeIfPresent() throws Exception {
       Map<String, Integer> map = new HashMap();
       map.put("a", 2);
       map.computeIfPresent("a", (key,val) -> val+4);
       Assert.assertTrue(map.get("a") == 6);
    }
}
