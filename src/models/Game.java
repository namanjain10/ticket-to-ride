package models;

import java.util.List;
import java.util.Stack;

public class Game {
    private String id;
    private Stack<Card> cardsDeck;
    private Stack<Card> returnedCards;
    private List<Card> openCards;
    private String nextChance;
    private boolean isGameComplete = false;

    public Game(String id, Stack<Card> cardsDeck, Stack<Card> returnedCards) {
        this.id = id;
        this.cardsDeck = cardsDeck;
        this.returnedCards = returnedCards;
    }

    public String getId() {
        return id;
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

    public String getNextChance() {
        return nextChance;
    }

    public void setNextChance(String nextChance) {
        this.nextChance = nextChance;
    }

    public boolean isGameComplete() {
        return isGameComplete;
    }

    public void setGameComplete(boolean gameComplete) {
        isGameComplete = gameComplete;
    }

    public void printOpenCards() {
        if (openCards == null) {
            System.out.println("No Cards Open Currently!!");
            return;
        }
        for (int i=0; i<openCards.size(); i++) {
            System.out.print(String.format("%d: %s  ", i, openCards.get(i)));
        }
    }
// TODO: add builder here
}
