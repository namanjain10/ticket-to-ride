package manager.impl;

import manager.CardManager;
import models.cards.Card;
import repositories.CardsRepository;
import utils.CardsUtil;

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
    public Stack<Card> getCardsDeckForGame(String gameId) {
        return cardsRepository.getCardsDeck(gameId);
    }

    @Override
    public List<Card> getCards(String gameId, Card.State state) {
        return cardsRepository.getCards(gameId, state);
    }
}
