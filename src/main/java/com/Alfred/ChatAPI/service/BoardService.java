package com.Alfred.ChatAPI.service;

import com.Alfred.ChatAPI.dao.IBoardDAO;
import com.Alfred.ChatAPI.ProjectConfig;
import com.Alfred.ChatAPI.dao.IPostDAO;
import com.Alfred.ChatAPI.model.BoardModel;
import com.Alfred.ChatAPI.model.PostModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("defaultBoardService")
public class BoardService implements IBoardService {
    private final IBoardDAO boardDAO;
    private final IPostDAO postDAO;

    @Autowired
    public BoardService(
            @Qualifier(ProjectConfig.DATABASE_LAYER) IBoardDAO boardDAO,
            @Qualifier(ProjectConfig.DATABASE_LAYER) IPostDAO postDAO
    ) {
        this.boardDAO = boardDAO;
        this.postDAO = postDAO;
    }

    @Override
    public BoardModel addBoard(String boardName) {
        return boardDAO.addBoard(boardName);
    }

    @Override
    public List<BoardModel> getBoards() { return boardDAO.getBoards(); }

    @Override
    public Optional<BoardModel> getBoardByID(Long boardID) { return boardDAO.getBoardByID(boardID); }

    @Override
    public int deleteBoardByID(Long boardID) {
        Optional<BoardModel> board = boardDAO.getBoardByID(boardID);
        if (board.isEmpty()) return 0;

        for (PostModel postID : board.get().getPosts())
            postDAO.deletePostByID(postID.getID());

        return boardDAO.deleteBoardByID(boardID);
    }
}
