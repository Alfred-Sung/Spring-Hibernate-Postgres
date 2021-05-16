package com.Alfred.ChatAPI.service;

import com.Alfred.ChatAPI.model.PostModel;

import java.util.Optional;

/**
 * Business Logic layer for all post manipulation operations
 *
 * Acts as an intermediate between the API endpoints and data access
 * Responsible for query and database validation
 */

public interface IPostService {
    PostModel addPost(Long boardID, Long userID, String text);

    Optional<PostModel> getPostByID(Long userID, Long postID);

    int deletePostByID(Long userID, Long postID);

    PostModel updatePostByID(Long boardID, Long postID, String text);
}
