package com.realitybrackets.controllers;

import com.realitybrackets.beans.User;
import com.realitybrackets.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reality-tracker/api/v1/user")
@Tag(name = "Reality-Tracker Comp: User Controller", description = "User Endpoints")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/", produces = "application/json")
    @Operation(summary = "getUserList", description = "Get User List", security = @SecurityRequirement(name = "bearerAuth"))
    public List<User> getUserList() {
        return this.userService.getUserList();
    }

    @GetMapping(value = "/{userKey}", produces = "application/json")
    @Operation(summary = "getUserByUserKey", description = "Get User by User Key", security = @SecurityRequirement(name = "bearerAuth"))
    public User getUserByUserKey(@Parameter(description = "User Key", example = "key_user1") @PathVariable("userKey") String userKey) {
        return this.userService.getUserByUserKey(userKey);
    }

    @GetMapping(value = "/team/{teamKey}", produces = "application/json")
    @Operation(summary = "getUserListByTeamKey", description = "Get User List by Team Key", security = @SecurityRequirement(name = "bearerAuth"))
    public List<User> getUserListByTeamKey(@Parameter(description = "Team Key", example = "key_team1") @PathVariable("teamKey") String teamKey) {
        return this.userService.getUserListByTeamKey(teamKey);
    }
}
