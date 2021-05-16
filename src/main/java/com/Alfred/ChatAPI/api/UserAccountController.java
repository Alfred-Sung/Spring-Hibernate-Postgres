package com.Alfred.ChatAPI.api;

import com.Alfred.ChatAPI.model.UserAccountModel;
import com.Alfred.ChatAPI.service.IUserAccountService;
import com.Alfred.ChatAPI.ProjectConfig;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

/**
 * Exposes API endpoints related to user manipulation
 * Requires an IUserService that is injected by Spring
 *
 * All URI path variables are defined by the ProjectConfig in order
 * to ensure that variable naming is consistent across all controllers
 */

@RequestMapping("api/v1/user")
@RestController
public class UserAccountController {
    private final IUserAccountService userService;

    @Autowired
    public UserAccountController(@Qualifier("defaultUserService") IUserAccountService userService) { this.userService = userService; }

    @PostMapping
    public UserAccountModel addUser(@Valid @NonNull @RequestBody ObjectNode jsonNodes) {
        if (!jsonNodes.has("first-name")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'first-name' field not found");
        if (!jsonNodes.has("last-name")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'last-name' field not found");

        return userService.addUser(jsonNodes.get("first-name").asText(), jsonNodes.get("last-name").asText());
    }

    @GetMapping(path = "{" + ProjectConfig.URIPath.USER + "}")
    public UserAccountModel getFileByID(@PathVariable(ProjectConfig.URIPath.USER) Long userID) {
        return userService.getUserByID(userID)
                .orElse(null);
    }

    @DeleteMapping(path = "{" + ProjectConfig.URIPath.USER + "}")
    public void deleteUserByID(@PathVariable(ProjectConfig.URIPath.USER) Long userID) { userService.deleteUserByID(userID); }

    @PutMapping(path = "{" + ProjectConfig.URIPath.USER + "}")
    public void updateUserByID(@PathVariable(ProjectConfig.URIPath.USER) Long userID, @Valid @NonNull @RequestBody ObjectNode jsonNodes) {
        if (!jsonNodes.has("first-name")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'first-name' field not found");
        if (!jsonNodes.has("last-name")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'last-name' field not found");

        userService.updateUserByID(userID, jsonNodes.get("first-name").asText(), jsonNodes.get("last-name").asText());
    }
}
