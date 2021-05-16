package com.Alfred.ChatAPI.service;

import com.Alfred.ChatAPI.dao.IBoardDAO;
import com.Alfred.ChatAPI.dao.IPostDAO;
import com.Alfred.ChatAPI.dao.IUserAccountDAO;
import com.Alfred.ChatAPI.model.BoardModel;
import com.Alfred.ChatAPI.model.PostModel;
import com.Alfred.ChatAPI.model.UserAccountModel;
import com.Alfred.ChatAPI.ProjectConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service("defaultPostService")
public class PostService implements IPostService {
    private final IBoardDAO boardDAO;
    private final IUserAccountDAO userDAO;
    private final IPostDAO postDAO;

    @Autowired
    public PostService(
            @Qualifier(ProjectConfig.DATABASE_LAYER) IBoardDAO boardDAO,
            @Qualifier(ProjectConfig.DATABASE_LAYER) IUserAccountDAO userDAO,
            @Qualifier(ProjectConfig.DATABASE_LAYER) IPostDAO postDAO
        ) {
        this.boardDAO = boardDAO;
        this.userDAO = userDAO;
        this.postDAO = postDAO;
    }

    @Override
    public PostModel addPost(Long userID, Long boardID, String text) {
        Optional<BoardModel> optionalBoard = boardDAO.getBoardByID(boardID);
        if (optionalBoard.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found");

        Optional<UserAccountModel> optionalUser = userDAO.getUserByID(userID);
        if (optionalUser.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");

        PostModel newPost = postDAO.addPost(optionalUser.get(), optionalBoard.get(), text);

        return newPost;
    }

    @Override
    public Optional<PostModel> getPostByID(Long boardId, Long postID) {
        return postDAO.getPostByID(postID);
    }

    @Override
    public int deletePostByID(Long boardID, Long postID) {
        //Check if post exists
        Optional<PostModel> post = postDAO.getPostByID(postID);
        if (post.isEmpty()) return 0;

        //Check if board exists
        Optional<BoardModel> board = boardDAO.getBoardByID(boardID);
        //Remove post from board
        if (board.isPresent()) board.get().removePost(post.get());

        return postDAO.deletePostByID(postID);
    }

    @Override
    public PostModel updatePostByID(Long boardID, Long postID, String text) {
        return postDAO.updatePostByID(postID, text);
    }
}
