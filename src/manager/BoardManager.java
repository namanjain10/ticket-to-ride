package manager;

import models.Board;
import models.City;
import models.Player;

public interface BoardManager {

    Board createBoard(String gameId);
    void addTrainCarConnection(Board board, City source, City destination, Player player);
    boolean checkIfRouteIsComplete(Board board, City source, City destination);
}
