package com.service.goodsteward;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class StartupSQLRunner {

    private final JdbcTemplate jdbcTemplate;

    public StartupSQLRunner(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runSQLQueries() {
        // Insert 5 users
        jdbcTemplate.execute("INSERT INTO users (id, name, email) VALUES (1, 'John Doe', 'john.doe@example.com')");
        jdbcTemplate.execute("INSERT INTO users (id, name, email) VALUES (2, 'Jane Smith', 'jane.smith@example.com')");
        jdbcTemplate
                .execute("INSERT INTO users (id, name, email) VALUES (3, 'Robert Brown', 'robert.brown@example.com')");
        jdbcTemplate
                .execute("INSERT INTO users (id, name, email) VALUES (4, 'Emily Davis', 'emily.davis@example.com')");
        jdbcTemplate.execute(
                "INSERT INTO users (id, name, email) VALUES (5, 'Michael Johnson', 'michael.johnson@example.com')");

        // Insert 2 projects for each user
        jdbcTemplate.execute(
                "INSERT INTO projects (project_owner_id, name, description) VALUES (1, 'Project A - John', 'Description for Project A owned by John')");
        jdbcTemplate.execute(
                "INSERT INTO projects (project_owner_id, name, description) VALUES (1, 'Project B - John', 'Description for Project B owned by John')");

        jdbcTemplate.execute(
                "INSERT INTO projects (project_owner_id, name, description) VALUES (2, 'Project A - Jane', 'Description for Project A owned by Jane')");
        jdbcTemplate.execute(
                "INSERT INTO projects (project_owner_id, name, description) VALUES (2, 'Project B - Jane', 'Description for Project B owned by Jane')");

        jdbcTemplate.execute(
                "INSERT INTO projects (project_owner_id, name, description) VALUES (3, 'Project A - Robert', 'Description for Project A owned by Robert')");
        jdbcTemplate.execute(
                "INSERT INTO projects (project_owner_id, name, description) VALUES (3, 'Project B - Robert', 'Description for Project B owned by Robert')");

        jdbcTemplate.execute(
                "INSERT INTO projects (project_owner_id, name, description) VALUES (4, 'Project A - Emily', 'Description for Project A owned by Emily')");
        jdbcTemplate.execute(
                "INSERT INTO projects (project_owner_id, name, description) VALUES (4, 'Project B - Emily', 'Description for Project B owned by Emily')");

        jdbcTemplate.execute(
                "INSERT INTO projects (project_owner_id, name, description) VALUES (5, 'Project A - Michael', 'Description for Project A owned by Michael')");
        jdbcTemplate.execute(
                "INSERT INTO projects (project_owner_id, name, description) VALUES (5, 'Project B - Michael', 'Description for Project B owned by Michael')");
    }

}
