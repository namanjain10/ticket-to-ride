package models;

import models.board.City;

import java.util.UUID;

public class Ticket {
    private final String id;
    private final String gameId;
    private final City from;
    private final City to;
    private final int points;
    private State state;
    private String playerId;

    public Ticket(String gameId, City from, City to, int points, State state, String playerId) {
        this.id = UUID.randomUUID().toString();
        this.gameId = gameId;
        this.from = from;
        this.to = to;
        this.points = points;
        this.state = state;
        this.playerId = playerId;
    }

    public String getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getPoints() {
        return points;
    }

    public State getState() {
        return state;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void addCardToPlayer(String playerId) {
        this.state = State.WITH_PLAYER;
        this.playerId = playerId;
    }

    public enum State {
        IN_DECK,
        WITH_PLAYER
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "from=" + from +
                ", to=" + to +
                ", points=" + points +
                ", state=" + state +
                ", id='" + id + '\'' +
                ", gameId='" + gameId + '\'' +
                ", playerId='" + playerId + '\'' +
                '}';
    }
}
