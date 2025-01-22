package manager;

import models.Board;
import models.City;
import models.Player;
import models.cards.Card;

import java.util.List;

public interface BoardManager {

    Board createBoard(String gameId);
    Board getBoardForGame(String gameId);
    void addTrainCarConnection(String gameId, City source, City destination, Player player, List<Card> cards);
    boolean checkIfRouteIsComplete(String boardId, City source, City destination);
}
