package com.appsdeveloperblog.estore.service;

import com.appsdeveloperblog.estore.model.User;
import com.appsdeveloperblog.estore.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserServiceTest {

    UserService userService;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;

    @BeforeEach
    void init()
    {
        userService = new UserServiceImpl();

        firstName="nish";
        lastName="nish";
        email="nish";
        password="nish";
        repeatPassword="nish";
    }

    @DisplayName("user object created")
    @Test
    void testCreateUser_whenUserDetailsProvided_returnUserObject()
    {
        //Arrange


        //Act

        User user = userService.createUser(firstName,lastName,email,password,repeatPassword);
        //Assert
        assertNotNull(user);
        assertEquals(firstName,user.getFirstName());
        assertEquals(lastName,user.getLastName());
        assertEquals(email,user.getEmail());
        assertNotNull(user.getId());
    }

    /*
    @Test
    void testCreateUser_whenUserCreated_returnedUserObjectContainsSameFirstName() {

        //Arrange
        UserService userService = new UserServiceImpl();

        String firstName="nish";
        String lastName="nish";
        String email="nish";
        String password="nish";
        String repeatPassword="nish";


        //Act

        User user = userService.createUser(firstName,lastName,email,password,repeatPassword);

        //Assert
        assertEquals(firstName,user.getFirstName());
    }

     */

    @DisplayName("empty first name causes correct exception")
    @Test
    void testCreateUser_whenFirstNameIsEmpty_throwsIllegalArgumentException() {
        //Arrange
        UserService userService = new UserServiceImpl();

        String firstName="";

        //Act && Assert

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(firstName,lastName,email,password,repeatPassword);
        });




        //Assert
        assertEquals("User empty la",thrown.getMessage());



    }
}
