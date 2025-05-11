package org.TP3P2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Tp3IntegrationTestApplication.class)
public class UserServiceTest {

    @Container
    static MySQLContainer<?> mysqlContainer = new MySQLContainer<>("mysql:8.0.30")
            .withDatabaseName("testdb")
            .withUsername("root")
            .withPassword("password");

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll(); // Nettoyage avant chaque test
    }

    @Test
    void testCreateAndGetUser() {
        User user = new User("Alice", "alice@example.com");
        User savedUser = userService.createUser(user);

        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getName()).isEqualTo("Alice");

        Optional<User> found = userService.getUserById(savedUser.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getEmail()).isEqualTo("alice@example.com");
    }

    @Test
    void testGetAllUsers() {
        userService.createUser(new User("Bob", "bob@example.com"));
        userService.createUser(new User("Charlie", "charlie@example.com"));

        List<User> users = userService.getAllUsers();
        assertThat(users).hasSize(2);
    }

    @Test
    void testDeleteUser() {
        User user = userService.createUser(new User("Dave", "dave@example.com"));
        userService.deleteUser(user.getId());

        Optional<User> deleted = userService.getUserById(user.getId());
        assertThat(deleted).isNotPresent();
    }
}

