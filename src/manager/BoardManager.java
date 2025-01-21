package manager;

import models.Board;
import models.City;
import models.Player;

public interface BoardManager {

    Board createBoard(String gameId);
    Board getBoardForGame(String gameId);
    void addTrainCarConnection(String gameId, City source, City destination, Player player);
    boolean checkIfRouteIsComplete(String boardId, City source, City destination);
}
