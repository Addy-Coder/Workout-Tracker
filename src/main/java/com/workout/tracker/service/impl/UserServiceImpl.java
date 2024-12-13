package com.workout.tracker.service.impl;

import com.workout.tracker.entity.User;
import com.workout.tracker.exception.APIException;
import com.workout.tracker.exception.ExceptionUtility;
import com.workout.tracker.model.user.UserBaseDTO;
import com.workout.tracker.repository.UserRepository;
import com.workout.tracker.service.UserService;
import com.workout.tracker.service.validation.ValidateInput;
import com.workout.tracker.service.validation.impl.user.CreateUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @ValidateInput(validator = CreateUserValidator.class)
    public User createUser(User user) {
        long currentTimeMillis = System.currentTimeMillis();
        user.setCt(currentTimeMillis);
        user.setLu(currentTimeMillis);
        return userRepository.save(user);
    }

    @Override
    public List<UserBaseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw ExceptionUtility.noDataAvailable();
        }
        List<UserBaseDTO> userBaseDTOs = new LinkedList<>();
        for (User user : users) {
            userBaseDTOs.add(new UserBaseDTO(user.getName(), user.getEmail()));
        }
        return userBaseDTOs;
    }

    @Override
    public UserBaseDTO getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new APIException(
                "Provide a valid user id",
                "Provide a valid user id",
                HttpStatus.NOT_FOUND
        ));
        return new UserBaseDTO(user.getName(), user.getEmail());
    }

    @Override
    public User updateUserById(String userId) {
        //TODO : Implement
        return null;
    }

    @Override
    public User deleteUserById(String userId) {
        //TODO : Implement
        return null;
    }
}
