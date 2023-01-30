package com.retail.demo;

import com.retail.demo.model.User;
import com.retail.demo.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    private Long testId = -1L;

    private User testUser = new User(null, "test", "test");

    private User updateTestUser = new User(null, "username", "password");

    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println("Start..." + testInfo.getDisplayName());
        if(!userService.isDaoEmpty()) {
            testId = userService.getLastUser().getId();
        }
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        System.out.println("Finished..." + testInfo.getDisplayName());
    }

    @Test
    @Order(1)
    void createUser() throws Exception {
        assertNotNull(userService.createUser(testUser));
    }

    @Test
    @Order(2)
    void getUserById() throws Exception {
        assertNotNull(userService.getUserByUsername(testUser.getUsername()));
    }

    @Test
    @Order(3)
    void Login() throws Exception {
        assertTrue(userService.checkLogin(testUser.getUsername(), testUser.getPassword()));
    }
    @Test
    @Order(4)
    void getBadLogin() throws Exception {
        assertTrue(!userService.checkLogin(updateTestUser.getUsername(), updateTestUser.getPassword()));
    }

    @Test
    @Order(5)
    void badCreateUser() throws Exception {
        assertNull(userService.createUser(testUser));
    }

    @Test
    @Order(6)
    void currentUser() throws Exception {
        userService.setCurrentUser(testUser);
        assertNotNull(userService.getCurrentUser());
    }

    @Test
    @Order(7)
    void resetCurrentUser() throws Exception {
        userService.setCurrentUser(testUser);
        assertTrue(userService.resetCurrentUser());
    }

    @Test
    @Order(8)
    void deleteUser() throws Exception {
        assertTrue(userService.deleteUser(testId));
    }
}
