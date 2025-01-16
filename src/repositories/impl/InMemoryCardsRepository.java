package repositories.impl;

import models.cards.Card;
import repositories.CardsRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class InMemoryCardsRepository implements CardsRepository {

    private final Map<String, Stack<Card>> gameVsCardsStack = new HashMap<>();
    private Map<String, Map<Card.State, List<Card>>> gameVsStateVsCards;

    @Override
    public void saveCardsDeck(String gameId, Stack<Card> cards) {
        gameVsCardsStack.put(gameId, cards);
        Map<Card.State, List<Card>> stateVsCards = gameVsStateVsCards.get(gameId);
        if (stateVsCards == null || stateVsCards.isEmpty()) {
            stateVsCards = new HashMap<>();
            stateVsCards.put(Card.State.GAME_DECK, cards);
        }
        gameVsStateVsCards.put(gameId, stateVsCards);
    }

    @Override
    public Stack<Card> getCardsDeck(String gameId) {
        return gameVsCardsStack.get(gameId);
    }

    @Override
    public String save(Card card) {
        return "";
    }

    @Override
    public Card get(String id) {
        return null;
    }

    @Override
    public List<Card> getCards(String gameId, Card.State state) {
        return gameVsStateVsCards.get(gameId)
                .get(state);
    }

    @Override
    public void update(String id, Card card) {

    }
}
