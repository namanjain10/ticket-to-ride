package manager;

import models.player.Player;
import models.action.PlayerAction;

import java.util.List;

public interface PlayerManager {

    List<Player> initPlayers(String gameId, int playerCount);
    List<Player> getPlayersForGame(String gameId);
    void registerPlayerAction(PlayerAction playerAction);
}
