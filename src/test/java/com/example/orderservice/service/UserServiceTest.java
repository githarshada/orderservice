package com.example.orderservice.service;

import com.example.orderservice.model.User;
import com.example.orderservice.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserRepository repo = Mockito.mock(UserRepository.class);
    private final UserService service = new UserService(repo);

    @Test
    void testSaveUser() {
        User user = new User();
        user.setName("John");
        user.setEmail("john@test.com");

        when(repo.save(any(User.class))).thenReturn(user);

        User result = service.save(user);

        assertNotNull(result);
        assertEquals("John", result.getName());
    }

    @Test
    void testGetById() {
        User user = new User();
        user.setName("Alice");

        when(repo.findById(1L)).thenReturn(Optional.of(user));

        User result = service.getById(1L);

        assertNotNull(result);
        assertEquals("Alice", result.getName());
    }
}