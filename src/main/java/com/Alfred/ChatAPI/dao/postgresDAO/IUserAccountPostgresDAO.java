package com.Alfred.ChatAPI.dao.postgresDAO;

import com.Alfred.ChatAPI.model.UserAccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserAccountPostgresDAO extends JpaRepository<UserAccountModel, Long> {
}
