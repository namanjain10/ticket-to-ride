package models.action;

import models.Player;

public abstract class PlayerAction {
    private final PlayerActionType playerActionType;
    private final Player player;

    PlayerAction(PlayerActionType playerActionType, Player player) {
        this.playerActionType = playerActionType;
        this.player = player;
    }

    public PlayerActionType getPlayerActionType() {
        return playerActionType;
    }

    public Player getPlayer() {
        return player;
    }

    public abstract <T> T accept(PlayerActionVisitor<T> visitor);
}
