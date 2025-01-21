package manager.impl;

import manager.CardManager;
import models.cards.Card;
import repositories.CardsRepository;
import utils.CardsUtil;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CardManagerImpl implements CardManager {

    private final CardsRepository cardsRepository;

    public CardManagerImpl(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    @Override
    public void initCardsDeck(String gameId, int numCards) {
        Stack<Card> deckCards = CardsUtil.createCardsDeck(gameId, numCards);
        cardsRepository.saveCardsDeck(gameId, deckCards);
    }

    @Override
    public void addCardsToPlayerFromDeck(String gameId, String playerId, int numCards) {
        cardsRepository.addCardsToPlayerFromDeck(gameId, playerId, numCards);
    }

    @Override
    public void addCardsToOpenCardsFromDeck(String gameId, int numCards) {
        cardsRepository.addCardsFromDeckToOpenCards(gameId, numCards);
    }

    @Override
    public void addCardsToPlayerFromOpenCards(String gameId, String playerId, List<Card> cards) {
        cardsRepository.addCardsToPlayerFromOpenCards(gameId, playerId, cards);
        cardsRepository.addCardsFromDeckToOpenCards(gameId, cards.size());
    }

//    @Override
//    public List<Card> getCardsFromDeck(String gameId, int numCards) {
//        return List.of();
//    }

    @Override
    public Stack<Card> getCardsDeckForGame(String gameId) {
        return cardsRepository.getCardsDeck(gameId);
    }

    @Override
    public List<Card> getOpenCardForGame(String gameId) {
        return getCards(gameId, Card.State.GAME_OPEN);
    }

    @Override
    public List<Card> getCards(String gameId, Card.State state) {
        return cardsRepository.getCards(gameId, state);
    }

    @Override
    public List<Card> getPlayerCards(String gameId, String playerId) {
        List<Card> playerCardsForGame = getCards(gameId, Card.State.WITH_PLAYER);
        if (playerCardsForGame == null || playerCardsForGame.isEmpty()) {
            return Collections.emptyList();
        }
        return playerCardsForGame.stream()
                .filter(card -> playerId.equals(card.getPlayerId()))
                .toList();
    }
}
