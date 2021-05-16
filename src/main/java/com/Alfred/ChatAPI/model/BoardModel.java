package com.Alfred.ChatAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "board")
public class BoardModel {
    @Id
    Long id;
    @NotBlank
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "board")
    private List<PostModel> boardPosts = new ArrayList<>();

    public BoardModel() {}

    public BoardModel(
            Long id,
            String name
    ) {
        this.id = id;
        this.name = name;
    }

    public BoardModel(
            String name
    ) {
        super();
        this.name = name;
    }

    @JsonProperty("id")
    public Long getID() { return id; }

    public void addPost(PostModel postID) { boardPosts.add(postID); }

    public void removePost(PostModel postID) { boardPosts.remove(postID); }

    @JsonProperty("name")
    public String getName() { return name; }

    @JsonProperty("posts")
    public List<PostModel> getPosts() { return boardPosts; }
}
