package com.Alfred.ChatAPI.service;

import com.Alfred.ChatAPI.model.BoardModel;

import java.util.List;
import java.util.Optional;

/**
 * Business Logic layer for all board manipulation operations
 *
 * Acts as an intermediate between the API endpoints and data access
 * Responsible for query and database validation
 */

public interface IBoardService {
    BoardModel addBoard(String boardName);

    List<BoardModel> getBoards();

    Optional<BoardModel> getBoardByID(Long boardID);

    int deleteBoardByID(Long boardID);
}
