package manager.impl;

import manager.PlayerManager;
import models.Player;
import models.action.PlayerAction;

import java.util.List;

public class PlayerManagerImpl implements PlayerManager {

    @Override
    public List<Player> initPlayers(int playerCount) {
        return List.of();
    }

    @Override
    public void registerPlayerAction(PlayerAction playerAction) {

    }
}
