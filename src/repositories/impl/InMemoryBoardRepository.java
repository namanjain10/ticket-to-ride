package repositories.impl;

import exception.DataGetException;
import models.Board;
import repositories.BoardRepository;

import java.util.Map;
import java.util.Objects;

public class InMemoryBoardRepository implements BoardRepository {

    private Map<String, Board> boardMap;
    private Map<String, Board> gameVsBoardMap;

    @Override
    public String save(Board board) {
        boardMap.put(board.getId(), board);
        gameVsBoardMap.put(board.getGameId(), board);
        return board.getId();
    }

    @Override
    public Board get(String id) {
        Board board = boardMap.get(id);
        if (Objects.isNull(board)) {
            throw new DataGetException("Board with id not found");
        }
        return board;
    }

    @Override
    public Board getBoardForGame(String gameId) {
        return gameVsBoardMap.get(gameId);
    }

    @Override
    public void update(String id, Board board) {
        Board boardOld = get(id);
        boardMap.put(id, board);
        gameVsBoardMap.put(board.getGameId(), board);
    }
}
