package com.Alfred.ChatAPI.service;

import com.Alfred.ChatAPI.model.UserAccountModel;

import java.util.List;
import java.util.Optional;

/**
 * Business Logic layer for all user manipulation operations
 *
 * Acts as an intermediate between the API endpoints and data access
 * Responsible for query and database validation
 */

public interface IUserAccountService {
    UserAccountModel addUser(String firstName, String lastName);

    List<UserAccountModel> getUsers(List<String> filters);

    Optional<UserAccountModel> getUserByID(Long userID);

    int deleteUserByID(Long userID);

    UserAccountModel updateUserByID(Long userID, String firstName, String lastName);
}
