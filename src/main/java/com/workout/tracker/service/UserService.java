package com.workout.tracker.service;

import com.workout.tracker.entity.User;
import com.workout.tracker.model.user.UserBaseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<UserBaseDTO> getAllUsers();

    UserBaseDTO getUserById(String userId);

    User updateUserById(String userId);

    User deleteUserById(String userId);
}
