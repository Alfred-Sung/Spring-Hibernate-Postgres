package com.Alfred.ChatAPI.dao.postgresDAO;

import com.Alfred.ChatAPI.model.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostPostgresDAO extends JpaRepository<PostModel, Long> {
}
