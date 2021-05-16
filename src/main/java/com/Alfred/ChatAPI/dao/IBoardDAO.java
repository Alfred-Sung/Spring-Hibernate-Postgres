package com.Alfred.ChatAPI.dao;

import com.Alfred.ChatAPI.model.BoardModel;

import java.util.List;
import java.util.Optional;

/**
 * Data access layer for all repository database operations
 *
 * Should support CRUD of only repository objects
 */

public interface IBoardDAO {
    BoardModel addBoard(String boardName);

    List<BoardModel> getBoards();

    Optional<BoardModel> getBoardByID(Long boardID);

    int deleteBoardByID(Long boardID);
}
