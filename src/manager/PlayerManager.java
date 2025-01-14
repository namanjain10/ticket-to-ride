package manager;

import models.Player;
import models.action.PlayerAction;

import java.util.List;

public interface PlayerManager {

    List<Player> initPlayers(int playerCount);
    void registerPlayerAction(PlayerAction playerAction);
}
