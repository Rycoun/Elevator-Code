package com.techelevator.dao.kanban;

import com.techelevator.model.kanban.Board;
import com.techelevator.model.kanban.Card;
import com.techelevator.model.kanban.Comment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcKanbanDAO implements KanbanDAO {

    private JdbcTemplate jdbcTemplate;

    public JdbcKanbanDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Board> getAllBoards() {
        List<Board> result = new ArrayList<>();
        String sql = "SELECT id, title, background_color FROM boards;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            Board board = mapRowToBoard(rowSet);
            board.setCards(getCardsForBoardId(board.getId()));
            result.add(board);
        }
        return result;
    }

    @Override
    public Board getBoard(long boardId) {
        String sql = "SELECT id, title, background_color FROM boards WHERE id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, boardId);
        if (rowSet.next()) {
            Board board = mapRowToBoard(rowSet);
            board.setCards(getCardsForBoardId(board.getId()));
            return board;
        } else {
            return null;
        }
    }

    @Override
    public Card getCard(long cardId) {
        String sql = "SELECT id, board_id, title, description, avatar, creation_date, status, tag FROM cards WHERE id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, cardId);
        if (rowSet.next()) {
            Card card = mapRowToCard(rowSet);
            card.setComments(getCommentsForCardId(card.getId()));
            return card;
        } else {
            return null;
        }
    }

    private List<Card> getCardsForBoardId(long boardId) {
        List<Card> result = new ArrayList<>();
        String sql = "SELECT id, board_id, title, description, avatar, creation_date, status, tag FROM cards WHERE board_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, boardId);
        while (rowSet.next()) {
            Card card = mapRowToCard(rowSet);
            card.setComments(getCommentsForCardId(card.getId()));
            result.add(card);
        }
        return result;
    }

    private List<Comment> getCommentsForCardId(long cardId) {
        List<Comment> result = new ArrayList<>();
        String sql = "SELECT id, author, body, posted_on FROM comments WHERE card_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, cardId);
        while (rowSet.next()) {
            Comment comment = mapRowToComment(rowSet);
            result.add(comment);
        }
        return result;
    }


    private Board mapRowToBoard(SqlRowSet rowSet) {
        Board result = new Board();
        result.setId(rowSet.getLong("id"));
        result.setTitle(rowSet.getString("title"));
        result.setBackgroundColor(rowSet.getString("background_color"));
        return result;
    }

    private Card mapRowToCard(SqlRowSet rowSet) {
        Card result = new Card();
        result.setId(rowSet.getLong("id"));
        result.setBoardId(rowSet.getLong("board_id"));
        result.setTitle(rowSet.getString("title"));
        result.setDescription(rowSet.getString("description"));
        result.setAvatar(rowSet.getString("avatar"));
        result.setDate(rowSet.getDate("creation_date").toLocalDate());
        result.setStatus(rowSet.getString("status"));
        result.setTag(rowSet.getString("tag"));
        return result;
    }

    private Comment mapRowToComment(SqlRowSet rowSet) {
        Comment result = new Comment();
        result.setId(rowSet.getLong("id"));
        result.setAuthor(rowSet.getString("author"));
        result.setBody((rowSet.getString("body")));
        result.setPostedOn(rowSet.getTimestamp("posted_on").toLocalDateTime());
        return result;
    }


    @Override
    public Board createBoard(Board board) {
        String sql = "INSERT INTO boards (title, background_color) VALUES (?, ?) RETURNING id;";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class, board.getTitle(), board.getBackgroundColor());
        board.setId(newId);

        for (Card card : board.getCards()) {
            card = createCard(board.getId(), card);
        }

        return board;
    }

    @Override
    public boolean deleteBoard(long boardId) {
        String sql = "DELETE FROM comments WHERE card_id IN (SELECT card_id FROM cards WHERE board_id = ?);";
        jdbcTemplate.update(sql, boardId);
        sql = "DELETE FROM cards WHERE board_id = ?;";
        jdbcTemplate.update(sql, boardId);
        sql = "DELETE FROM boards WHERE id = ?;";
        int count = jdbcTemplate.update(sql, boardId);
        return count == 1;
    }

    @Override
    public Card createCard(long boardId, Card card) {
        String sql = "INSERT INTO cards (board_id, title, description, avatar, creation_date, status, tag) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id;";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class, boardId, card.getTitle(), card.getDescription(),
                card.getAvatar(), card.getDate(), card.getStatus(), card.getTag());
        card.setId(newId);

        for (Comment comment : card.getComments()) {
            comment = createComment(card.getId(), comment);
        }

        return card;
    }

    @Override
    public boolean updateCard(Card card) {
        String sql = "UPDATE cards SET title = ?, description = ?, avatar = ?, creation_date = ?, status = ?, tag = ? " +
                     "WHERE id = ?;";
        int count = jdbcTemplate.update(sql, card.getTitle(), card.getDescription(), card.getAvatar(), card.getDate(),
                                             card.getStatus(), card.getTag(), card.getId());

        return count == 1;
    }

    @Override
    public boolean deleteCard(long cardId) {
        String sql = "DELETE FROM comments WHERE card_id = ?;";
        jdbcTemplate.update(sql, cardId);
        sql = "DELETE FROM cards WHERE id = ?;";
        int count = jdbcTemplate.update(sql, cardId);
        return count == 1;
    }

    @Override
    public Comment createComment(long cardId, Comment comment) {
        String sql = "INSERT INTO comments (card_id, author, body, posted_on) " +
                "VALUES (?, ?, ?, ?) RETURNING id;";
        Long newId = jdbcTemplate.queryForObject(sql, Long.class, cardId, comment.getAuthor(), comment.getBody(), comment.getPostedOn());
        comment.setId(newId);

        return comment;
    }


}
