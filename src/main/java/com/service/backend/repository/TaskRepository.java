package com.service.backend.repository;

import com.service.backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    // Find all tasks for a specific project
    List<Task> findByProjectId(Long projectId);

    // Optional: Find tasks containing a keyword in their title or description
    List<Task> findByTitleContainingOrDescriptionContaining(String titleKeyword, String descriptionKeyword);
}
