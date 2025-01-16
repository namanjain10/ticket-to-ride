package manager;


import models.cards.Card;

import java.util.List;
import java.util.Stack;

public interface CardManager {
    void initCardsDeck(String gameId, int numCards);
    Stack<Card> getCardsDeckForGame(String gameId);
    List<Card> getCards(String gameId, Card.State state);
}
