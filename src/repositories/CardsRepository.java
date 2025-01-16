package repositories;

import models.cards.Card;

import java.util.List;
import java.util.Stack;

public interface CardsRepository {
    void saveCardsDeck(String gameId, Stack<Card> cards);
    Stack<Card> getCardsDeck(String gameId);
    String save(Card card);
    Card get(String id);
    List<Card> getCards(String id, Card.State state);
    void update(String id, Card card);
}
