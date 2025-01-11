package models;

import java.util.List;
import java.util.Stack;

public class Board {

    private List<Player> players;
    private Stack<Card> cardsDeck;
    private Stack<Card> returnedCards;
    private CityConnection cityConnection;

    private Board() {
        //TODO: make it singleton
    }

    public static void init() {

    }
}
