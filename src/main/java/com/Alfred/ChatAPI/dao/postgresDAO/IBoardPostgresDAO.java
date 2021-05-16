package com.Alfred.ChatAPI.dao.postgresDAO;

import com.Alfred.ChatAPI.model.BoardModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBoardPostgresDAO extends JpaRepository<BoardModel, Long> {
}
