package com.workout.tracker.controller;

import com.workout.tracker.entity.User;
import com.workout.tracker.model.user.UserBaseDTO;
import com.workout.tracker.model.user.UserCreateDTO;
import com.workout.tracker.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "APIs related to user management")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Adds a new user to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User successfully created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserCreateDTO.class))),
            @ApiResponse(responseCode = "400", description = "Validation error: Missing or invalid name/email",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example =
                                    """
                                            {
                                                "userMessage": "Provide a valid email",
                                                "developerMessage": "Email cannot be empty/null",
                                                "errorCode": "40001"
                                            }
                                            """))),
            @ApiResponse(responseCode = "409", description = "Conflict error: Duplicate email",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example =
                                    """
                                            {
                                                "userMessage": "Email already exists",
                                                "developerMessage": "Email already exists",
                                                "errorCode": "40901"
                                            }
                                            """)))
    })
    public ResponseEntity<User> createUser(@RequestBody @Parameter(description = "User object to be created", required = true) UserCreateDTO userCreateDTO) {
        User user = new User();
        user.setName(userCreateDTO.getName());
        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(userCreateDTO.getPassword());
        User newUser = userService.createUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all users in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of users",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = UserBaseDTO.class)))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example =
                                    """
                                            {
                                                "userMessage": "Oops! Something went wrong !!!",
                                                "developerMessage": "Internal Server error",
                                                "errorCode": "50001"
                                            }
                                            """)))
    })
    public ResponseEntity<List<UserBaseDTO>> getAllUsers() {
        List<UserBaseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Get user by ID", description = "Retrieve details of a user by their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User successfully retrieved",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserBaseDTO.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example =
                                    """
                                            {
                                                "userMessage": "Provide a valid user id",
                                                "developerMessage": "Provide a valid user id",
                                                "errorCode": "40401"
                                            }
                                            """)))
    })
    public ResponseEntity<UserBaseDTO> getUserById(
            @PathVariable @Parameter(description = "Unique ID of the user", required = true) String userId) {
        UserBaseDTO user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    // TODO: Implement update user with Swagger annotations

    // TODO: Implement delete user with Swagger annotations
}
