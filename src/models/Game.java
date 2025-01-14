package models;

import java.util.List;
import java.util.Stack;
import java.util.UUID;

public class Game {
    private String id;
    private List<Player> players;
    private Stack<Card> cardsDeck;
    private Stack<Card> returnedCards;
    private List<Card> openCards;
    private Board board;

    public Game(List<Player> players, Stack<Card> cardsDeck, Stack<Card> returnedCards, Board board) {
        this.id = UUID.randomUUID().toString();
        this.players = players;
        this.cardsDeck = cardsDeck;
        this.returnedCards = returnedCards;
        this.board = board;
    }

    public String getId() {
        return id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Stack<Card> getCardsDeck() {
        return cardsDeck;
    }

    public Stack<Card> getReturnedCards() {
        return returnedCards;
    }

    public List<Card> getOpenCards() {
        return openCards;
    }

    public Board getBoard() {
        return board;
    }
// TODO: add builder here
}
