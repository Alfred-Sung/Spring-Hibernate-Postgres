package com.Alfred.ChatAPI.api;

import com.Alfred.ChatAPI.service.IPostService;
import com.Alfred.ChatAPI.ProjectConfig;
import com.Alfred.ChatAPI.model.PostModel;
import com.Alfred.ChatAPI.model.UserAccountModel;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * Exposes API endpoints related to post manipulation
 * Requires an IPostService that is injected by Spring
 *
 * All URI path variables are defined by the ProjectConfig in order
 * to ensure that variable naming is consistent across all controllers
 */

@RequestMapping("api/v1/post")
@RestController
public class PostController {
    private final IPostService postService;

    @Autowired
    public PostController(@Qualifier("defaultPostService") IPostService postService) {
        this.postService = postService;
    }

    @PostMapping(path = "{" + ProjectConfig.URIPath.USER + "}/" +
            "{" + ProjectConfig.URIPath.BOARD + "}")
    public PostModel addPost(
            @PathVariable(ProjectConfig.URIPath.USER) Long userID,
            @PathVariable(ProjectConfig.URIPath.BOARD) Long boardID,
            @Valid @NonNull @RequestBody ObjectNode jsonNodes
    ) {
        if (!jsonNodes.has("text")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'text' field not found");

        return postService.addPost(userID, boardID, jsonNodes.get("text").asText());
    }

    @GetMapping(path =
            "{" + ProjectConfig.URIPath.BOARD + "}/" +
            "{" + ProjectConfig.URIPath.POST + "}"
    )
    public PostModel getPostByID(
            @PathVariable(ProjectConfig.URIPath.BOARD) Long boardID,
            @PathVariable(ProjectConfig.URIPath.POST) Long postID
    ) {
        return postService.getPostByID(boardID, postID)
                .orElse(null);
    }

    @GetMapping(path =
            "{" + ProjectConfig.URIPath.BOARD + "}/" +
            "{" + ProjectConfig.URIPath.POST + "}/" +
            "author"
    )
    public UserAccountModel getPostUserByID(
            @PathVariable(ProjectConfig.URIPath.BOARD) Long boardID,
            @PathVariable(ProjectConfig.URIPath.POST) Long postID
    ) {
        return getPostByID(boardID, postID).getUser();
    }

    @GetMapping(path =
            "{" + ProjectConfig.URIPath.BOARD + "}/" +
            "{" + ProjectConfig.URIPath.POST + "}/" +
            "date-created"
    )
    public LocalDateTime getPostDateCreatedByID(
            @PathVariable(ProjectConfig.URIPath.BOARD) Long boardID,
            @PathVariable(ProjectConfig.URIPath.POST) Long postID
    ) {
        return getPostByID(boardID, postID).getDateCreated();
    }

    @GetMapping(path =
            "{" + ProjectConfig.URIPath.BOARD + "}/" +
                    "{" + ProjectConfig.URIPath.POST + "}/" +
                    "date-modified"
    )
    public LocalDateTime getPostDateModifiedByID(
            @PathVariable(ProjectConfig.URIPath.BOARD) Long boardID,
            @PathVariable(ProjectConfig.URIPath.POST) Long postID
    ) {
        return getPostByID(boardID, postID).getDateModified();
    }

    @GetMapping(path = {
            "{" + ProjectConfig.URIPath.BOARD + "}/" +
            "{" + ProjectConfig.URIPath.POST + "}/" +
            "text"
    })
    public String getPostTextByID(
            @PathVariable(ProjectConfig.URIPath.BOARD) Long boardID,
            @PathVariable(ProjectConfig.URIPath.POST) Long postID
    ) {
        return getPostByID(boardID, postID).getText();
    }

    @DeleteMapping(path =
            "{" + ProjectConfig.URIPath.BOARD + "}/" +
            "{" + ProjectConfig.URIPath.POST + "}"
    )
    public void deletePostByID(
            @PathVariable(ProjectConfig.URIPath.BOARD) Long boardID,
            @PathVariable(ProjectConfig.URIPath.POST) Long postID
    ) {
        postService.deletePostByID(boardID, postID);
    }

    @PutMapping(path =
            "{" + ProjectConfig.URIPath.BOARD + "}/" +
                    "{" + ProjectConfig.URIPath.POST + "}"
    )
    public PostModel updatePostByID(
            @PathVariable(ProjectConfig.URIPath.BOARD) Long boardID,
            @PathVariable(ProjectConfig.URIPath.POST) Long postID,
            @Valid @NonNull @RequestBody ObjectNode jsonNodes
    ) {
        if (!jsonNodes.has("text")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'text' field not found");

        return postService.updatePostByID(boardID, postID, jsonNodes.get("text").asText());
    }
}
