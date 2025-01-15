package manager;

import models.Game;
import models.action.PlayerAction;

public interface GameManager {

    Game create(int numPlayer, int numCards);
    Game getGame(String gameId);
    void registerPlayerAction(Game game, PlayerAction playerAction);
}
