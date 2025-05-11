package org.TP3P1;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        // Initialiser les mocks
        MockitoAnnotations.openMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void testGetUserById() {
        // Arrange (préparation des données)
        long userId =2001L;
        User mockUser = new User(userId, "Kalakhi Cheikh Abdelmadjid");

        when(userRepository.findUserById(userId)).thenReturn(mockUser);

        // Act (appel de la méthode à tester)
        User result = userService.getUserById(userId);

        // Assert (vérifications)
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Kalakhi Cheikh Abdelmadjid", result.getName());

        // Vérifier que findUserById a été appelé avec le bon ID
        verify(userRepository, times(1)).findUserById(userId);
    }
}
