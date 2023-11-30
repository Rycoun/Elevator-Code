package com.techelevator.dao.kanban;

import com.techelevator.model.kanban.Board;
import com.techelevator.model.kanban.Card;
import com.techelevator.model.kanban.Comment;

import java.util.List;

public interface KanbanDAO {

    public List<Board> getAllBoards();

    public Board getBoard(long boardId);

    public Card getCard(long cardId);

    public Board createBoard(Board board);

    public Card createCard(long boardId, Card card);

    public Comment createComment(long cardId, Comment comment);

    public boolean updateCard(Card card);

    public boolean deleteBoard(long boardId);

    public boolean deleteCard(long cardId);

}
