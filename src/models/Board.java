package models;

import graph.Graph;

import java.util.UUID;

public class Board {

    private String id;

    private String gameId;

    private Graph<City, Connection> cityConnections;

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
