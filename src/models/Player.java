package models;

import java.util.List;

public class Player {
    private String id;
    private String name;
    private String gameId;
    private PlayerColor playerColor;
    private List<Ticket> tickets;
    private List<Card> cards;
    private int trainCarCount;
    private int sequenceId;

    public String getId() {
        return id;
    }

    public String getGameId() {
        return gameId;
    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getTrainCarCount() {
        return trainCarCount;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public Player(String id, String name, String gameId, PlayerColor playerColor, List<Ticket> tickets,
                  List<Card> cards, int trainCarCount, int sequenceId) {
        this.id = id;
        this.name = name;
        this.gameId = gameId;
        this.playerColor = playerColor;
        this.tickets = tickets;
        this.cards = cards;
        this.trainCarCount = trainCarCount;
        this.sequenceId = sequenceId;
    }
}
