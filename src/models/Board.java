package models;

public class Board {

    private String id;

    private String gameId;

    private CityConnection cityConnection;

    private Board() {
    }

    public String getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public CityConnection getCityConnection() {
        return cityConnection;
    }
}
