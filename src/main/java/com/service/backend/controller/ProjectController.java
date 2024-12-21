package com.service.backend.controller;

import com.service.backend.DTO.ProjectRequest;
import com.service.backend.entity.Project;
import com.service.backend.entity.User;
import com.service.backend.service.ProjectService;
import com.service.backend.service.UserService;

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
    public List<Project> getProjectsByUserId(@PathVariable Long userId) {
        return projectService.getProjectsByUserId(userId);
    }

    @PostMapping
    public Project createProject(@RequestBody ProjectRequest projectRequest) {
        User projectOwner = userService.getUserById(projectRequest.getProjectOwnerId());

        Project project = new Project(
                projectRequest.getName(),
                projectRequest.getDescription(),
                projectOwner);

        return projectService.saveProject(project);
    }

    @PutMapping("/{id}")
    public Project updateProject(@PathVariable Long id, @RequestBody Project updatedProject) {
        Project project = projectService.getProjectById(id);
        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        return projectService.saveProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}
