package com.appsdeveloperblog.service;

import com.appsdeveloperblog.io.UsersDatabase;
import com.appsdeveloperblog.io.UsersDatabaseMapImpl;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceImplTest {

    UsersDatabase usersDatabase;
    UserService userService;
    String createdUserId = "";

    @BeforeAll
    //no longer static
    void setup() {
         usersDatabase = new UsersDatabaseMapImpl();
         usersDatabase.init();
         userService = new UserServiceImpl(usersDatabase);
    }

    @AfterAll
    void cleanup() {
        // Close connection
        // Delete database
        usersDatabase.close();
    }

    @Test
    @Order(1)
    @DisplayName("Create User works")
    void testCreateUser_whenProvidedWithValidDetails_returnsUserId() {

        //Arrange
        Map<String,String> user = new HashMap<>();
        user.put("firstName","Sergey");
        user.put("lastName","Bhatt");
        //Act
        createdUserId = userService.createUser(user);
        //Assert
        assertNotNull(createdUserId,"User ID should not be null");


    }


    @Test
    @Order(2)
    @DisplayName("Update user works")
    void testUpdateUser_whenProvidedWithValidDetails_returnsUpdatedUserDetails() {

        //Arrange
        Map<String,String> newUerDetails = new HashMap<>();
        newUerDetails.put("firstName","a");
        newUerDetails.put("lastName","Bhatt");
        //Act
        Map updated = userService.updateUser(createdUserId,newUerDetails);

        //Assert
        assertEquals(newUerDetails.get("firstName"),updated.get("firstName"));
    }

    @Test
    @Order(3)
    @DisplayName("Find user works")
    void testGetUserDetails_whenProvidedWithValidUserId_returnsUserDetails() {

        //Act
        Map userDet = userService.getUserDetails(createdUserId);

        //Assert
        assertNotNull(userDet);
        assertEquals(createdUserId,userDet.get("userId"));
    }

    @Test
    @Order(4)
    @DisplayName("Delete user works")
    void testDeleteUser_whenProvidedWithValidUserId_returnsUserDetails() {

        //Act
        userService.deleteUser(createdUserId);
        assertNull(userService.getUserDetails(createdUserId));

    }

}
