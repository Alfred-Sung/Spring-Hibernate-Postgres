package com.Alfred.ChatAPI.dao;

import com.Alfred.ChatAPI.model.UserAccountModel;

import java.util.List;
import java.util.Optional;

/**
 * Data access layer for all user database operations
 *
 * Should support CRUD of only user objects
 */

public interface IUserAccountDAO {
    UserAccountModel addUser(String firstName, String lastName);

    List<UserAccountModel> getUsers(List<String> filters);

    Optional<UserAccountModel> getUserByID(Long userID);

    int deleteUserByID(Long userID);

    UserAccountModel updateUserByID(Long userID, String firstName, String lastName);
}
