package com.service.goodsteward.repository;

import com.service.goodsteward.entity.Project;
import com.service.goodsteward.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    // Find all projects for a specific user
    List<Project> findByProjectOwner(User projectOwner);
}
