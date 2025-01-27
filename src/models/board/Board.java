package models.board;

import graph.Graph;

import java.util.UUID;

public class Board {

    private final String id;

    private final String gameId;

    private final Graph<City, Connection> cityConnections;

    public Board(String gameId, Graph<City, Connection> cityConnections) {
        this.id = UUID.randomUUID().toString();
        this.gameId = gameId;
        this.cityConnections = cityConnections;
    }

    public String getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public Graph<City, Connection> getCityConnection() {
        return cityConnections;
    }
}