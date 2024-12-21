// package com.service.backend;

// import com.service.backend.controller.TaskController;
// import com.service.backend.entity.Project;
// import com.service.backend.entity.Task;
// import com.service.backend.service.TaskService;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;

// import java.util.Arrays;
// import java.util.List;

// import static org.hamcrest.Matchers.*;
// import static
// org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static
// org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(TaskController.class)
// public class TaskControllerTest {

// @Autowired
// private MockMvc mockMvc;

// @MockBean
// private TaskService taskService;

// @Test
// public void testGetTaskById() throws Exception {
// Task task = new Task();
// task.setId(1L);
// task.setTitle("Sample Task");
// task.setDescription("Sample Description");
// task.setStatus(Task.Status.BACKLOG);

// Mockito.when(taskService.getTaskById(1L)).thenReturn(task);

// mockMvc.perform(get("/tasks/1"))
// .andExpect(status().isOk())
// .andExpect(jsonPath("$.id", is(1)))
// .andExpect(jsonPath("$.title", is("Sample Task")))
// .andExpect(jsonPath("$.description", is("Sample Description")))
// .andExpect(jsonPath("$.status", is(Task.Status.BACKLOG)));
// }

// @Test
// public void testGetTasksByProjectId() throws Exception {
// Task task1 = new Task();
// task1.setId(1L);
// task1.setTitle("Task 1");

// Task task2 = new Task();
// task2.setId(2L);
// task2.setTitle("Task 2");

// List<Task> tasks = Arrays.asList(task1, task2);

// Mockito.when(taskService.getTasksByProjectId(1L)).thenReturn(tasks);

// mockMvc.perform(get("/tasks/project/1"))
// .andExpect(status().isOk())
// .andExpect(jsonPath("$", hasSize(2)))
// .andExpect(jsonPath("$[0].id", is(1)))
// .andExpect(jsonPath("$[0].title", is("Task 1")))
// .andExpect(jsonPath("$[1].id", is(2)))
// .andExpect(jsonPath("$[1].title", is("Task 2")));
// }

// @Test
// public void testCreateTask() throws Exception {
// Project project = new Project();
// project.setId(1L);
// project.setName("Sample Project");

// Task task = new Task();
// task.setId(1L);
// task.setTitle("New Task");
// task.setDescription("New Description");
// task.setStatus(Task.Status.BACKLOG);
// task.setProject(project);

// Mockito.when(taskService.saveTask(Mockito.any(Task.class))).thenReturn(task);

// mockMvc.perform(post("/tasks")
// .contentType(MediaType.APPLICATION_JSON)
// .content(
// "{\"title\":\"New Task\", \"description\":\"New Description\",
// \"status\":\"Backlog\", \"project\":{\"id\":1}}"))
// .andExpect(status().isOk())
// .andExpect(jsonPath("$.id", is(1)))
// .andExpect(jsonPath("$.title", is("New Task")))
// .andExpect(jsonPath("$.description", is("New Description")))
// .andExpect(jsonPath("$.status", is(Task.Status.BACKLOG.toString())))
// .andExpect(jsonPath("$.project.id", is(1)));
// }

// @Test
// public void testUpdateTask() throws Exception {
// Task existingTask = new Task();
// existingTask.setId(1L);
// existingTask.setTitle("Old Task");
// existingTask.setDescription("Old Description");
// existingTask.setStatus(Task.Status.BACKLOG);

// Task updatedTask = new Task();
// updatedTask.setId(1L);
// updatedTask.setTitle("Updated Task");
// updatedTask.setDescription("Updated Description");
// updatedTask.setStatus(Task.Status.IN_PROGRESS);

// Mockito.when(taskService.getTaskById(1L)).thenReturn(existingTask);
// Mockito.when(taskService.saveTask(Mockito.any(Task.class))).thenReturn(updatedTask);

// mockMvc.perform(put("/tasks/1")
// .contentType(MediaType.APPLICATION_JSON)
// .content(
// "{\"title\":\"Updated Task\", \"description\":\"Updated Description\",
// \"status\":\"In Progress\"}"))
// .andExpect(status().isOk())
// .andExpect(jsonPath("$.id", is(1)))
// .andExpect(jsonPath("$.title", is("Updated Task")))
// .andExpect(jsonPath("$.description", is("Updated Description")))
// .andExpect(jsonPath("$.status", is(Task.Status.IN_PROGRESS)));
// }

// @Test
// public void testDeleteTask() throws Exception {
// Mockito.doNothing().when(taskService).deleteTask(1L);

// mockMvc.perform(delete("/tasks/1"))
// .andExpect(status().isOk());
// }
// }
