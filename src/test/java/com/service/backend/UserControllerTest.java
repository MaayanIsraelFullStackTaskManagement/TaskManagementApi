package com.service.backend;

import com.service.backend.entity.User;
import com.service.backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.service.backend.controller.UserController;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        user1 = new User("john.doe@example.com", "John Doe");
        user1.setId(1L);

        user2 = new User("jane.doe@example.com", "Jane Doe");
        user2.setId(2L);
    }

    @Test
    void testGetUserById() throws Exception {
        when(userService.getUserById(1L)).thenReturn(user1);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")));

        verify(userService, times(1)).getUserById(1L);
    }

    @Test
    void testGetAllUsers() throws Exception {
        List<User> users = Arrays.asList(user1, user2);
        when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Jane Doe")));

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    void testCreateUser() throws Exception {
        User newUser = new User("alice@example.com", "Alice");

        when(userService.saveUser(Mockito.any(User.class))).thenReturn(newUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Alice\", \"email\": \"alice@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Alice")))
                .andExpect(jsonPath("$.email", is("alice@example.com")));

        verify(userService, times(1)).saveUser(Mockito.any(User.class));
    }

    @Test
    void testUpdateUser() throws Exception {
        User updatedUser = new User("john.smith@example.com", "John Smith");

        when(userService.getUserById(1L)).thenReturn(user1);
        when(userService.saveUser(Mockito.any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"John Smith\", \"email\": \"john.smith@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("John Smith")))
                .andExpect(jsonPath("$.email", is("john.smith@example.com")));

        verify(userService, times(1)).getUserById(1L);
        verify(userService, times(1)).saveUser(Mockito.any(User.class));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteUser(1L);
    }
}
