package com.Alfred.ChatAPI.dao;

import com.Alfred.ChatAPI.model.BoardModel;
import com.Alfred.ChatAPI.model.PostModel;
import com.Alfred.ChatAPI.model.UserAccountModel;

import java.util.Optional;

/**
 * Data access layer for all post database operations
 *
 * Should support CRUD of only post objects
 */

public interface IPostDAO {
    PostModel addPost(UserAccountModel user, BoardModel board, String text);

    Optional<PostModel> getPostByID(Long postID);

    int deletePostByID(Long postID);

    PostModel updatePostByID(Long postID, String text);
}
