package com.Alfred.ChatAPI.service;

import com.Alfred.ChatAPI.ProjectConfig;
import com.Alfred.ChatAPI.dao.IUserAccountDAO;
import com.Alfred.ChatAPI.model.UserAccountModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("defaultUserService")
public class UserAccountService implements IUserAccountService {
    private final IUserAccountDAO userDAO;

    public UserAccountService(@Qualifier(ProjectConfig.DATABASE_LAYER) IUserAccountDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserAccountModel addUser(String firstName, String lastName) {
        return userDAO.addUser(firstName, lastName);
    }

    public List<UserAccountModel> getUsers(List<String> filters) { return null; }

    @Override
    public Optional<UserAccountModel> getUserByID(Long userID) {
        return userDAO.getUserByID(userID);
    }

    @Override
    public int deleteUserByID(Long userID) {
        return userDAO.deleteUserByID(userID);
    }

    @Override
    public UserAccountModel updateUserByID(Long userID, String firstName, String lastName) {
        return userDAO.updateUserByID(userID, firstName, lastName);
    }
}
