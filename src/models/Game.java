package models;

import java.util.List;
import java.util.Stack;

public class Game {
    private List<Player> players;
    private Stack<Card> cardsDeck;
    private Stack<Card> returnedCards;
    private List<Card> openCards;
    private Board board;

    public Game(List<Player> players, Stack<Card> cardsDeck, Stack<Card> returnedCards, Board board) {
        this.players = players;
        this.cardsDeck = cardsDeck;
        this.returnedCards = returnedCards;
        this.board = board;
    }

    // TODO: add builder here
}
