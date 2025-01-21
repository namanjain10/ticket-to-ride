package repositories.impl;

import models.cards.Card;
import repositories.CardsRepository;

import java.util.*;

public class InMemoryCardsRepository implements CardsRepository {

    private final Map<String, Stack<Card>> gameVsCardsStack = new HashMap<>();
    private Map<String, Map<Card.State, List<Card>>> gameVsStateVsCards = new HashMap<>();

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
    public void addCardsToPlayerFromDeck(String gameId, String playerId, int numCards) {
        List<Card> cards = getCardsFromDeck(gameId, numCards);
        List<Card> deckCards = getCards(gameId, Card.State.GAME_DECK);
        List<Card> playerCards = getCards(gameId, Card.State.WITH_PLAYER);
        if (playerCards == null) {
            playerCards = new ArrayList<>();
        }
        for (Card card: cards) {
            deckCards.remove(card);
            card.passCardToPlayer(playerId);
            playerCards.add(card);
        }
        gameVsStateVsCards.get(gameId)
                .put(Card.State.GAME_DECK, deckCards);
        gameVsStateVsCards.get(gameId)
                .put(Card.State.WITH_PLAYER, playerCards);
    }

    @Override
    public void addCardsFromDeckToOpenCards(String gameId, int numCards) {
        List<Card> cards = getCardsFromDeck(gameId, numCards);
        List<Card> deckCards = getCards(gameId, Card.State.GAME_DECK);
        List<Card> openCards = getCards(gameId, Card.State.GAME_OPEN);
        if (openCards == null) {
            openCards = new ArrayList<>();
        }
        for (Card card: cards) {
            deckCards.remove(card);
            card.setState(Card.State.GAME_OPEN);
            openCards.add(card);
        }
        gameVsStateVsCards.get(gameId)
                .put(Card.State.GAME_DECK, deckCards);
        gameVsStateVsCards.get(gameId)
                .put(Card.State.GAME_OPEN, openCards);
    }

    @Override
    public void addCardsToPlayerFromOpenCards(String gameId, String playerId, List<Card> cards) {
        List<Card> openCards = getCards(gameId, Card.State.GAME_OPEN);
        List<Card> playerCards = getCards(gameId, Card.State.WITH_PLAYER);
        for (Card card: cards) {
            openCards.remove(card);
            card.passCardToPlayer(playerId);
            playerCards.add(card);
        }
        gameVsStateVsCards.get(gameId)
                .put(Card.State.WITH_PLAYER, playerCards);
        gameVsStateVsCards.get(gameId)
                .put(Card.State.GAME_OPEN, openCards);
    }

    @Override
    public void addCardsToReturnedFromPlayerCards(String gameId, String playerId, List<Card> cards) {
        List<Card> returnedCards = getCards(gameId, Card.State.RETURNED);
        List<Card> playerCards = getCards(gameId, Card.State.WITH_PLAYER);
        if (returnedCards == null) {
            returnedCards = new ArrayList<>();
        }
        for (Card card: cards) {
            playerCards.remove(card);
            card.setState(Card.State.RETURNED);
            returnedCards.add(card);
        }
        gameVsStateVsCards.get(gameId)
                .put(Card.State.WITH_PLAYER, playerCards);
        gameVsStateVsCards.get(gameId)
                .put(Card.State.RETURNED, returnedCards);
    }

    @Override
    public List<Card> getCardsFromDeck(String gameId, int numCards) {
        Stack<Card> cardsDeck = gameVsCardsStack.get(gameId);
        List<Card> resultantCards = new ArrayList<>();
        for (int i=numCards; i>0; i--) {
            resultantCards.add(cardsDeck.pop());
        }
        return resultantCards;
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
