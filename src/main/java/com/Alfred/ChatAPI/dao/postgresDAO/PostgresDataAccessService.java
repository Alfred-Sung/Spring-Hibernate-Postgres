package com.Alfred.ChatAPI.dao.postgresDAO;

import com.Alfred.ChatAPI.dao.IBoardDAO;
import com.Alfred.ChatAPI.dao.IPostDAO;
import com.Alfred.ChatAPI.dao.IUserAccountDAO;
import com.Alfred.ChatAPI.model.BoardModel;
import com.Alfred.ChatAPI.model.PostModel;
import com.Alfred.ChatAPI.model.UserAccountModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("postgresDAO")
public class PostgresDataAccessService implements IUserAccountDAO, IBoardDAO, IPostDAO {
    private IUserAccountPostgresDAO userRepo;
    private IBoardPostgresDAO boardRepo;
    private IPostPostgresDAO postRepo;

    @Autowired
    public PostgresDataAccessService(
            IUserAccountPostgresDAO userRepo,
            IBoardPostgresDAO boardRepo,
            IPostPostgresDAO postRepo
    ) {
        this.userRepo = userRepo;
        this.boardRepo = boardRepo;
        this.postRepo = postRepo;

        this.userRepo.deleteAll();
        this.boardRepo.deleteAll();
        this.postRepo.deleteAll();

        UserAccountModel user = addUser("Alfred", "Sung");
        BoardModel board = addBoard("general");

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
        BoardModel newBoard = new BoardModel(boardRepo.count(), boardName);
        boardRepo.save(newBoard);

        return newBoard;
    }

    @Override
    public List<BoardModel> getBoards() {
        return boardRepo.findAll();
    }

    @Override
    public Optional<BoardModel> getBoardByID(Long boardID) {
        return boardRepo.findById(boardID);
    }

    @Override
    public int deleteBoardByID(Long boardID) {
        boardRepo.deleteById(boardID);
        return 1;
    }

    @Override
    public PostModel addPost(UserAccountModel user, BoardModel board, String text) {
        PostModel newPost = new PostModel(user, board, text);
        postRepo.save(newPost);

        return newPost;
    }

    @Override
    public Optional<PostModel> getPostByID(Long postID) {
        return postRepo.findById(postID);
    }

    @Override
    public int deletePostByID(Long postID) {
        postRepo.deleteById(postID);
        return 1;
    }

    @Override
    public PostModel updatePostByID(Long postID, String text) {
        Optional<PostModel> optionalPost = getPostByID(postID);
        optionalPost.get().setText(text);
        postRepo.save(optionalPost.get());

        return optionalPost.get();
    }

    @Override
    public UserAccountModel addUser(String firstName, String lastName) {
        UserAccountModel newUser = new UserAccountModel(firstName, lastName);
        userRepo.save(newUser);

        return newUser;
    }

    @Override
    public List<UserAccountModel> getUsers(List<String> filters) {
        return userRepo.findAll();
    }

    @Override
    public Optional<UserAccountModel> getUserByID(Long userID) { return userRepo.findById(userID); }

    @Override
    public int deleteUserByID(Long userID) {
        userRepo.deleteById(userID);
        return 1;
    }

    @Override
    public UserAccountModel updateUserByID(Long userID, String firstName, String lastName) {
        Optional<UserAccountModel> optionalUser = getUserByID(userID);
        optionalUser.get().setFirstName(firstName);
        optionalUser.get().setLastName(lastName);
        userRepo.save(optionalUser.get());

        return optionalUser.get();
    }
}
