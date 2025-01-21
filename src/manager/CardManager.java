package manager;


import models.cards.Card;

import java.util.List;
import java.util.Stack;

public interface CardManager {
    void initCardsDeck(String gameId, int numCards);
//    List<Card> getCardsFromDeck(String gameId, int numCards);
    void addCardsToPlayerFromDeck(String gameId, String playerId, int numCards);
    void addCardsToOpenCardsFromDeck(String gameId, int numCards);
    void addCardsToPlayerFromOpenCards(String gameId, String playerId, List<Card> cards);
    Stack<Card> getCardsDeckForGame(String gameId);
    List<Card> getOpenCardForGame(String gameId);
    List<Card> getCards(String gameId, Card.State state);
    List<Card> getPlayerCards(String gameId, String playerId);
}
