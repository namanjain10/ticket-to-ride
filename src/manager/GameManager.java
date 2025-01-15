package manager;

import models.Game;
import models.action.PlayerAction;

public interface GameManager {

    Game create(int numPlayer, int numCards);
    Game getGame(String gameId);
    boolean validatePlayerAction(String gameId, PlayerAction playerAction);
    void registerPlayerAction(String gameId, PlayerAction playerAction);
    void checkIfGameCompleted(String gameId);
}
