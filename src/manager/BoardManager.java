package manager;

import models.board.Board;
import models.board.City;
import models.player.Player;
import models.cards.Card;

import java.util.List;

public interface BoardManager {

    Board createBoard(String gameId);
    Board getBoardForGame(String gameId);
    void addTrainCarConnection(String gameId, City source, City destination, Player player, List<Card> cards);
    boolean checkIfRouteIsComplete(String boardId, City source, City destination);
}
