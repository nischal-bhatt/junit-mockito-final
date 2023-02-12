package com.appsdeveloperblog.estore.service;

import com.appsdeveloperblog.estore.data.UsersRepository;
import com.appsdeveloperblog.estore.model.User;
import com.appsdeveloperblog.estore.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UsersRepository usersRepository;

    @Mock
    EmailVerificationServiceImpl emailVerificationService;
    String firstName;
    String lastName;
    String email;
    String password;
    String repeatPassword;

    @BeforeEach
    void init()
    {


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

        Mockito.when(usersRepository.save(any(User.class))).thenReturn(true);


        //Act

        User user = userService.createUser(firstName,lastName,email,password,repeatPassword);
        //Assert
        assertNotNull(user);
        assertEquals(firstName,user.getFirstName());
        assertEquals(lastName,user.getLastName());
        assertEquals(email,user.getEmail());
        assertNotNull(user.getId());
        Mockito.verify(usersRepository,times(1)).save(Mockito.any(User.class));

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
       // UserService userService = new UserServiceImpl();

        String firstName="";

        //Act && Assert

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            userService.createUser(firstName,lastName,email,password,repeatPassword);
        });




        //Assert
        assertEquals("User empty la",thrown.getMessage());



    }

    @DisplayName("aaaa")
    @Test
    void testCreateUser_whenSaveMethodThrowsException_thenThrowsUserServiceException() {
        //arrange
        when(usersRepository.save(any(User.class))).thenThrow(RuntimeException.class);
        //act
        assertThrows(UserServiceException.class, () -> {
            userService.createUser(firstName,lastName,email,password,repeatPassword);
        });
        //assert

    }

    @Test
    @DisplayName("bbbbbb")
    void testCreated_whenEmailNotificationExceptionThrown_throwsUserServiceException() {
        //arrange
        when(usersRepository.save(any(User.class))).thenReturn(true);

        doThrow(EmailNotificationServiceException.class).when(emailVerificationService).scheduleEmailConfirmation(any(User.class));

       // doNothing().when(emailVerificationService).scheduleEmailConfirmation(any(User.class));

        //act
        assertThrows(UserServiceException.class, () ->  userService.createUser(firstName,lastName,email,password,repeatPassword));
        //

        verify(emailVerificationService,times(1)).scheduleEmailConfirmation(any(User.class));

    }

    @Test
    void testCreateUser_whenUserCreated_schedulesEmailConfirmation() {
        when(usersRepository.save(any(User.class))).thenReturn(true);
        doCallRealMethod().when(emailVerificationService).scheduleEmailConfirmation(any(User.class));
        userService.createUser(firstName,lastName,email,password,repeatPassword);
        verify(emailVerificationService, times(1)).scheduleEmailConfirmation(any(User.class));
    }
}
