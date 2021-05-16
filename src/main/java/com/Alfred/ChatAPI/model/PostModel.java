package com.Alfred.ChatAPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "post")
public class PostModel extends BaseModel {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_account")
    private UserAccountModel user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "board")
    private BoardModel board;
    @NotNull private LocalDateTime dateCreated;
    @NotNull private LocalDateTime dateModified;
    private String text;

    public PostModel() {}

    public PostModel(
            Long id,
            UserAccountModel user,
            BoardModel board,
            String text
    ) {
        super(id);

        this.user = user;
        this.board = board;
        this.text = text;
        this.dateCreated = LocalDateTime.now();
        this.dateModified = LocalDateTime.now();
        this.text = text;
    }

    public PostModel(
            UserAccountModel user,
            BoardModel board,
            String text
    ) {
        super();

        this.user = user;
        this.board = board;
        this.text = text;
        this.dateCreated = LocalDateTime.now();
        this.dateModified = LocalDateTime.now();
        this.text = text;
    }

    @JsonIgnore
    public UserAccountModel getUser() { return user; }

    @JsonIgnore
    public BoardModel getBoard() { return board; }

    @JsonProperty("date-created")
    public LocalDateTime getDateCreated() { return dateCreated; }

    @JsonProperty("date-modified")
    public LocalDateTime getDateModified() { return dateModified; }

    @JsonProperty("text")
    public String getText() { return text; }

    public void setText(String text) {
        this.dateModified = LocalDateTime.now();
        this.text = text;

    }
}
