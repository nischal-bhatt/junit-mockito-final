package com.appsdeveloperblog.estore.service;

import com.appsdeveloperblog.estore.data.UsersRepository;
import com.appsdeveloperblog.estore.data.UsersRepositoryImpl;
import com.appsdeveloperblog.estore.model.User;

import java.util.UUID;

public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;
    EmailVerificationService emailVerificationService;

    public UserServiceImpl(UsersRepository usersRepository,EmailVerificationService emailVerificationService) {
        this.usersRepository = usersRepository;
        this.emailVerificationService = emailVerificationService;
    }

    @Override
    public User createUser(String firstName, String lastName, String email, String password, String repeatPassword) {

        if (firstName == null || firstName.trim().length() == 0)
        {
            throw new IllegalArgumentException("User empty la");
        }

        User user = new User(firstName,lastName,email, UUID.randomUUID().toString());



        boolean isUserCreated;
        try {
            isUserCreated = usersRepository.save(user);
        }catch(RuntimeException e)
        {
            throw new UserServiceException("hey");
        }
        if (!isUserCreated) throw new UserServiceException("could not create user");

        try {
            System.out.println("in here");
            emailVerificationService.scheduleEmailConfirmation(user);
        }catch(RuntimeException ex)
        {
            throw new UserServiceException(ex.getMessage());
        }
        return user;
    }
}
