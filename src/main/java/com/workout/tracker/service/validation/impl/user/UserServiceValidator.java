package com.workout.tracker.service.validation.impl.user;

import com.workout.tracker.entity.User;
import com.workout.tracker.repository.UserRepository;
import com.workout.tracker.service.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class UserServiceValidator implements Validator<User> {
    @Autowired
    protected UserRepository userRepository;
}
