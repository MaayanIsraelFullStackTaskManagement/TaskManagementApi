package com.service.goodsteward.controller;

import com.service.goodsteward.DTO.ProjectRequest;
import com.service.goodsteward.entity.Project;
import com.service.goodsteward.entity.User;
import com.service.goodsteward.service.ProjectService;
import com.service.goodsteward.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @GetMapping("/user/{userId}")
    public List<Project> getProjectsByUserId(@PathVariable String userId) {
        return projectService.getProjectsByUserId(userId);
    }

    @PostMapping
    public Project createProject(@RequestBody ProjectRequest projectRequest) {
        User projectOwner = userService.getUserById(projectRequest.getProjectOwnerId());
        if (projectOwner == null) {
            throw new RuntimeException("Project owner not found with ID: " + projectRequest.getProjectOwnerId());
        }

        Project project = new Project(
                projectRequest.getName(),
                projectRequest.getDescription(),
                projectOwner);

        return projectService.saveProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, ProjectRequest projectRequest) {
        Project project = projectService.getProjectById(id);
        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        return projectService.saveProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
