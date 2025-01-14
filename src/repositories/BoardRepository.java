package repositories;

import models.Board;

public interface BoardRepository {
    String save(Board board);
    Board get(String id);
    Board getBoardForGame(String gameId);
    void update(String id, Board board);
}
