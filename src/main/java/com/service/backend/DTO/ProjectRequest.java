package com.service.backend.DTO;

public class ProjectRequest {
    private String name;
    private String description;
    private Long projectOwnerId;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProjectOwnerId() {
        return projectOwnerId;
    }

    public void setProjectOwnerId(Long projectOwnerId) {
        this.projectOwnerId = projectOwnerId;
    }
}
