package com.service.backend.controller;

import com.service.backend.entity.Project;
import com.service.backend.entity.Task;
import com.service.backend.service.ProjectService;
import com.service.backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/project/{projectId}")
    public List<Task> getTasksByProjectId(@PathVariable Long projectId) {
        return taskService.getTasksByProjectId(projectId);
    }

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public Task createTask(@RequestBody Task taskRequest) {
        if (taskRequest.getProject() == null || taskRequest.getProject().getId() == null) {
            throw new IllegalArgumentException("Task must be associated with a valid project.");
        }

        // Extract fields from the request
        String title = taskRequest.getTitle();
        String description = taskRequest.getDescription();
        LocalDateTime createdAt = taskRequest.getCreatedAt();
        LocalDateTime updatedAt = taskRequest.getUpdatedAt();
        String status = taskRequest.getStatus().name();

        // Fetch the project using ProjectService
        Project project = projectService.getProjectById(taskRequest.getProject().getId());

        // Manually create a new Task object and set the fields
        Task task = new Task(title, description, createdAt, updatedAt, project, Task.Status.valueOf(status));

        // Save the task using the task service
        return taskService.saveTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.getTaskById(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        return taskService.saveTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
