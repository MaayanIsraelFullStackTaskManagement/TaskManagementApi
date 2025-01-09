package com.service.goodsteward.controller;

import com.service.goodsteward.DTO.TaskEditRequest;
import com.service.goodsteward.DTO.TaskRequest;
import com.service.goodsteward.entity.Project;
import com.service.goodsteward.entity.Task;
import com.service.goodsteward.service.ProjectService;
import com.service.goodsteward.service.TaskService;
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
    public Task createTask(@RequestBody TaskRequest taskRequest) {
        if (taskRequest.getProjectId() == null) {
            throw new IllegalArgumentException("Task must be associated with a valid project.");
        }

        String title = taskRequest.getTitle();
        String description = taskRequest.getDescription();
        LocalDateTime createdAt = LocalDateTime.now();

        Project project = projectService.getProjectById(taskRequest.getProjectId());

        Task task = new Task(title, description, createdAt, project);

        return taskService.saveTask(task);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody TaskEditRequest updatedTask) {
        Task task = taskService.getTaskById(id);
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        Task.Status status = updatedTask.getStatus().equals("BACKLOG") ? Task.Status.BACKLOG
                : updatedTask.getStatus().equals("IN_PROGRESS") ? Task.Status.IN_PROGRESS : Task.Status.COMPLETED;
        task.setStatus(status);
        LocalDateTime now = LocalDateTime.now();
        task.setUpdatedAt(now);
        return taskService.saveTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }
}
