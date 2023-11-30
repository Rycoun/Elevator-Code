package com.techelevator.controller;

import com.techelevator.dao.kanban.KanbanDAO;
import com.techelevator.model.kanban.Board;
import com.techelevator.model.kanban.Card;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class KanbanController {

    private final KanbanDAO kanbanDAO;

    public KanbanController(KanbanDAO kanbanDAO) {
        this.kanbanDAO = kanbanDAO;
    }

    @GetMapping("/boards")
    public List<Board> getBoards() {
        return kanbanDAO.getAllBoards();
    }

    @GetMapping("/boards/{id}")
    public Board getBoard(@PathVariable long id) {
        Board result = kanbanDAO.getBoard(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No board with that id.");
        } else {
            return result;
        }
    }

    @GetMapping("/cards/{id}")
    public Card getCard(@PathVariable long id) {
        Card result = kanbanDAO.getCard(id);
        if (result == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No card with that id.");
        } else {
            return result;
        }
    }

    @PostMapping("/boards")
    @ResponseStatus(HttpStatus.CREATED)
    public Board postBoard(@RequestBody Board newBoard) {
        return kanbanDAO.createBoard(newBoard);
    }

    @PostMapping("/cards")
    @ResponseStatus(HttpStatus.CREATED)
    public Card postCard(@Valid @RequestBody Card newCard) {
        return kanbanDAO.createCard(newCard.getBoardId(), newCard);
    }

    @PutMapping("/cards/{id}")
    public Card putCard(@PathVariable long id, @RequestBody Card updatedCard) {
        updatedCard.setId(id);
        if (kanbanDAO.updateCard(updatedCard)) {
            return updatedCard;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found to update.");
        }
    }

    @DeleteMapping("/boards/{id}")
    @PreAuthorize("isAuthenticated()")
    public void deleteBoard(@PathVariable long id) {
        if (!kanbanDAO.deleteBoard(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Board not found to delete.");
        }
    }

    @DeleteMapping("/cards/{id}")
    public void deleteCard(@PathVariable long id) {
        if (!kanbanDAO.deleteCard(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card not found to delete.");
        }
    }
}
