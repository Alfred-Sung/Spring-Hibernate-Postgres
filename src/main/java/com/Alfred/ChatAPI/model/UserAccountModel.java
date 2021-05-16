package com.Alfred.ChatAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_account")
public class UserAccountModel extends BaseModel {
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<PostModel> userPosts = new ArrayList<>();

    public UserAccountModel() {}

    public UserAccountModel(
            Long id,
            String firstName,
            String lastName
    ) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserAccountModel(
            String firstName,
            String lastName
    ) {
        super();

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean addPost(PostModel post) { return userPosts.add(post); }

    public boolean removePost(PostModel post) { return userPosts.remove(post); }

    @JsonProperty("first-name")
    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    @JsonProperty("last-name")
    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    @JsonProperty("posts")
    public List<PostModel> getPosts() { return userPosts; }
}
