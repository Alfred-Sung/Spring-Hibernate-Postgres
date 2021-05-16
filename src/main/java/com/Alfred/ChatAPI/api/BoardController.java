package com.Alfred.ChatAPI.api;

import com.Alfred.ChatAPI.ProjectConfig;
import com.Alfred.ChatAPI.model.BoardModel;
import com.Alfred.ChatAPI.service.IBoardService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

/**
 * Exposes API endpoints related to repository manipulation
 * Requires an IBoardService that is injected by Spring
 *
 * All URI path variables are defined by the ProjectConfig in order
 * to ensure that variable naming is consistent across all controllers
 */

@RequestMapping("api/v1/board")
@RestController
public class BoardController {
    private final IBoardService boardService;

    @Autowired
    public BoardController(@Qualifier("defaultBoardService") IBoardService boardService) { this.boardService = boardService; }

    @PostMapping
    public BoardModel addBoard(@Valid @NonNull @RequestBody ObjectNode jsonNodes) {
        if (!jsonNodes.has("name")) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "'name' field not found");
        return boardService.addBoard(jsonNodes.get("name").asText());
    }

    @GetMapping
    public List<BoardModel> getBoards() { return boardService.getBoards(); }

    @GetMapping(path = "{" + ProjectConfig.URIPath.BOARD + "}")
    public BoardModel getFileByID(@PathVariable(ProjectConfig.URIPath.BOARD) Long repoID) {
        return boardService.getBoardByID(repoID)
                .orElse(null);
    }

    @DeleteMapping(path = "{" + ProjectConfig.URIPath.BOARD + "}")
    public void deleteFileByID(@PathVariable(ProjectConfig.URIPath.BOARD) Long repoID) { boardService.deleteBoardByID(repoID); }
}
