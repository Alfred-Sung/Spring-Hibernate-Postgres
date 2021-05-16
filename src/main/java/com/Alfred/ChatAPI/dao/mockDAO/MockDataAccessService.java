package com.Alfred.ChatAPI.dao.mockDAO;

import com.Alfred.ChatAPI.dao.IBoardDAO;
import com.Alfred.ChatAPI.dao.IPostDAO;
import com.Alfred.ChatAPI.dao.IUserAccountDAO;
import com.Alfred.ChatAPI.model.BoardModel;
import com.Alfred.ChatAPI.model.PostModel;
import com.Alfred.ChatAPI.model.UserAccountModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Mock database used to test back-end functionalities
 *
 * Adds an user, a board, and a few posts and comments upon initialization
 */

@Repository("mockDAO")
public class MockDataAccessService implements IUserAccountDAO, IBoardDAO, IPostDAO {
    private static List<UserAccountModel> userDatabase = new ArrayList<>();
    private static List<BoardModel> boardDatabase = new ArrayList<>();
    private static List<PostModel> postDatabase = new ArrayList<>();

    public MockDataAccessService() {
        UserAccountModel user = addUser("Alfred", "Sung");
        BoardModel board = addBoard("mock");

        PostModel post = addPost(user, board,
                "Lorem ipsum dolor sit amet"
        );
        board.addPost(post);
        user.addPost(post);

        post = addPost(user, board,
                "Scornfully mallard crud"
        );
        board.addPost(post);
        user.addPost(post);
    }

    @Override
    public BoardModel addBoard(String boardName) {
        BoardModel newBoard = new BoardModel((long) boardDatabase.size(), boardName);
        boardDatabase.add(newBoard);

        return newBoard;
    }

    @Override
    public List<BoardModel> getBoards() {
        return boardDatabase;
    }

    @Override
    public Optional<BoardModel> getBoardByID(Long boardID) {
        return boardDatabase.stream()
                .filter(b -> b.getID() == boardID)
                .findFirst();
    }

    @Override
    public int deleteBoardByID(Long boardID) {
        Optional<BoardModel> optionalBoard = getBoardByID(boardID);
        if (optionalBoard.isEmpty()) return 0;

        boardDatabase.remove(optionalBoard.get());
        return 1;
    }

    @Override
    public PostModel addPost(UserAccountModel user, BoardModel board, String text) {
        PostModel newPost = new PostModel((long) postDatabase.size(), user, board, text);
        postDatabase.add(newPost);

        return newPost;
    }

    @Override
    public Optional<PostModel> getPostByID(Long postID) {
        return postDatabase.stream()
                .filter(p -> p.getID() == postID)
                .findFirst();
    }

    @Override
    public int deletePostByID(Long postID) {
        Optional<PostModel> optionalPost = getPostByID(postID);
        if (optionalPost.isEmpty()) return 0;

        postDatabase.remove(optionalPost.get());
        return 1;
    }

    @Override
    public PostModel updatePostByID(Long postID, String text) {
        Optional<PostModel> optionalPost = getPostByID(postID);

        return null;
    }

    @Override
    public UserAccountModel addUser(String firstName, String lastName) {
        UserAccountModel newUser = new UserAccountModel((long) userDatabase.size(), firstName, lastName);
        userDatabase.add(newUser);

        return newUser;
    }

    @Override
    public List<UserAccountModel> getUsers(List<String> filters) {
        return null;
    }

    @Override
    public Optional<UserAccountModel> getUserByID(Long userID) {
        return userDatabase.stream()
                .filter(u -> u.getID() == userID)
                .findFirst();
    }

    @Override
    public int deleteUserByID(Long userID) {
        Optional<UserAccountModel> optionalUser = getUserByID(userID);
        if (optionalUser.isEmpty()) return  0;

        userDatabase.remove(optionalUser.get());
        return 1;
    }

    @Override
    public UserAccountModel updateUserByID(Long userID, String firstName, String lastName) {
        Optional<UserAccountModel> optionalUser = getUserByID(userID);
        optionalUser.get().setFirstName(firstName);
        optionalUser.get().setLastName(lastName);

        return null;
    }
}
