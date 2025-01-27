package manager;

import models.Game;
import models.player.Player;
import models.action.PlayerAction;

import java.util.List;

public interface GameManager {

    Game create(int numPlayer, int numCards);
    Game getGame(String gameId);
    boolean validatePlayerAction(String gameId, PlayerAction playerAction);
    void registerPlayerAction(String gameId, PlayerAction playerAction);
    void checkIfGameCompleted(String gameId);
    void passChanceToNextPlayer(String gameId, List<Player> playerList);
}
