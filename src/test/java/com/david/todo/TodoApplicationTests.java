package com.david.todo;

import com.david.todo.controllers.TaskController;
import com.david.todo.models.Task;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc // Allows the mockmvc to be autowired for us
class TodoApplicationTests {

	@Autowired
	private TaskController taskcontroller;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(taskcontroller).isNotNull();
	}

	@Test
	public void getAllTodoTest() throws Exception {
		this.mockMvc.perform(get("/tasks")).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString("demo description")))
			.andExpect(content().string(containsString("all tasks retrieved successfully")));
	}

	public static String asJsonString(final Object obj) {
    try {
        return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
	}


	@Test
	public void createNewTodoTest() throws Exception {
		Task t = new Task("demo1", "demo1 description", "pending");
		this.mockMvc.perform(post("/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(t)))
				.andDo(print()).andDo(print()).andExpect(status().isCreated())
			.andExpect(content().string(containsString("demo1 description")));
	}

	@Test
	public void getOneTodoTest() throws Exception {
		this.mockMvc.perform(get("/tasks/1"))
				.andDo(print()).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString("\"id\":1,\"status\":")));
	}

	@Test
	public void getNonExistentTodoTest() throws Exception {
		this.mockMvc.perform(get("/tasks/100"))
				.andDo(print()).andDo(print()).andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("unable to find the task")));
	}

	@Test
	public void getPendingTodoTest() throws Exception {
		String status = "pending";
		this.mockMvc.perform(get("/tasks/status/"+status))
				.andDo(print()).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString(status + " tasks retrieved successfully")));
	}

	@Test
	public void getDoneTodoTest() throws Exception {
		String status = "done";
		this.mockMvc.perform(get("/tasks/status/"+status))
				.andDo(print()).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString(status + " tasks retrieved successfully")));
	}

	@Test
	public void getInProgressTodoTest() throws Exception {
		String status = "inprogress";
		this.mockMvc.perform(get("/tasks/status/"+status))
				.andDo(print()).andDo(print()).andExpect(status().isOk())
			.andExpect(content().string(containsString(status + " tasks retrieved successfully")));
	}

	@Test
	public void getInvalidStatusTodoTest() throws Exception {
		String status = "invalid";
		this.mockMvc.perform(get("/tasks/status/"+status))
				.andDo(print()).andDo(print()).andExpect(status().isBadRequest())
			.andExpect(content().string(containsString("status must be one of pending, done, or inprogress")));
	}



}
