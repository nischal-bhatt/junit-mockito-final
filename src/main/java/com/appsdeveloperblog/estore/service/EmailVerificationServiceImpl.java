package com.appsdeveloperblog.estore.service;

import com.appsdeveloperblog.estore.model.User;

public class EmailVerificationServiceImpl implements EmailVerificationService {
    @Override
    public void scheduleEmailConfirmation(User user) {
        System.out.println("really inside here");
        //put user details into queue

    }
}
