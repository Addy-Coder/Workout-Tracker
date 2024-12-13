package com.workout.tracker.service.validation.impl.user;

import com.workout.tracker.entity.User;
import com.workout.tracker.exception.APIException;
import com.workout.tracker.repository.UserRepository;
import com.workout.tracker.service.validation.Validator;
import com.workout.tracker.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CreateUserValidator extends UserServiceValidator {

    @Override
    public void validate(User validator) {
        if(Utility.isStringEmptyOrNull(validator.getName())){
            throw new APIException(
                    "Provide a valid name",
                    "Name cannot be empty/null",
                    HttpStatus.BAD_REQUEST
            );
        }

        if(Utility.isStringEmptyOrNull(validator.getEmail())){
            throw new APIException(
                    "Provide a valid email",
                    "Email cannot be empty/null",
                    HttpStatus.BAD_REQUEST
            );
        }

        User existingUser = userRepository.findByEmail(validator.getEmail());
        if (existingUser != null) {
            throw new APIException(
                    "Email already exists",
                    "Email already exists",
                    HttpStatus.CONFLICT
            );
        }
    }
}
