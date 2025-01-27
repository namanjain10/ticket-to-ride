package models.cards;

import java.util.UUID;

public class Card {
    private String id;
    private String gameId;
    private Color color;
    private State state;
    private String playerId;

    public Card(String gameId, Color color, State state) {
        this.id = UUID.randomUUID().toString();
        this.gameId = gameId;
        this.color = color;
        this.state = state;
    }

    public Color getColor() {
        return color;
    }

    public State getState() {
        return state;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void passCardToPlayer(String playerId) {
        this.state = State.WITH_PLAYER;
        this.playerId = playerId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", state=" + state +
                ", playerId='" + playerId + '\'' +
                '}';
    }

    public enum State {
        GAME_DECK,
        GAME_OPEN,
        WITH_PLAYER,
        RETURNED;
    }
}
