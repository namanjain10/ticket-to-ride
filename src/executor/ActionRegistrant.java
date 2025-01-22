package executor;

import manager.GameManager;
import models.City;
import models.Game;
import models.Player;
import models.action.AddTrainCarAction;
import models.action.DrawCardsAction;
import models.action.PlayerAction;
import models.cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActionRegistrant {

    private final GameManager gameManager;
    private final Scanner scanner;
    private static final int MAX_CARDS_CAN_BE_PICKED = 2;

    public ActionRegistrant(GameManager gameManager) {
        this.gameManager = gameManager;
        this.scanner = new Scanner(System.in);
    }

    public PlayerAction register(Game game, Player currentPlayer, List<Card> openCards,
                                 List<Card> playerCards) {
        return capturePlayerAction(game, currentPlayer, openCards, playerCards);
    }

    private PlayerAction capturePlayerAction(Game game, Player currentPlayer, List<Card> openCards,
                                             List<Card> playerCards) {
        String gameId = game.getId();
        System.out.println("Choose Action: Enter 1 for picking Cards or 2 for placing train Car");
        PlayerAction playerAction;
        int actionInt = scanner.nextInt();
        switch (actionInt) {
            case 1:
                playerAction = getDrawCardsAction(currentPlayer, openCards);
                break;
            case 2:
                playerAction = getAddTrainCarAction(currentPlayer, playerCards);
                break;
            default:
                return capturePlayerAction(game, currentPlayer, openCards, playerCards);
        }
        if (!gameManager.validatePlayerAction(gameId, playerAction)) {
            System.out.println("Action performed by the user is not valid!! Please enter correct action inputs");
            return capturePlayerAction(game, currentPlayer, openCards, playerCards);
        }
        return playerAction;
    }

    private PlayerAction getDrawCardsAction(Player currentPlayer, List<Card> openCards) {
        System.out.println("Select ids for cards to be picked: ");
        List<Integer> cardIds = new ArrayList<>();
        for (int i=0; i<MAX_CARDS_CAN_BE_PICKED; i++) {
            int cardId = takeCardIdInput(openCards.size(), cardIds);
            cardIds.add(cardId);
        }
        return new DrawCardsAction(currentPlayer, getCardsList(cardIds, openCards));
    }

    private List<Card> getCardsList(List<Integer> cardIds, List<Card> openCards) {
        List<Card> cardsSelected = new ArrayList<>();
        for (int cardId: cardIds) {
            cardsSelected.add(openCards.get(cardId));
        }
        return cardsSelected;
    }

    private PlayerAction getAddTrainCarAction(Player currentPlayer, List<Card> playerCards) {
        System.out.println("Source City: ");
        City sourceCity = takeCityInput();

        System.out.println("Destination City: ");
        City destinationCity = takeCityInput();

        return new AddTrainCarAction(currentPlayer, sourceCity, destinationCity,
                getSubmittedCards(currentPlayer, playerCards));
    }


    private List<Card> getSubmittedCards(Player currentPlayer, List<Card> playerCards) {
        System.out.println("Select number of cards you are submitting: ");
        int cardsSubmitted = scanner.nextInt();
        System.out.println("Select ids for card to be picked: ");
        List<Integer> cardIds = new ArrayList<>();
        List<Card> cards = new ArrayList<>();
        for (int i=0; i<cardsSubmitted; i++) {
            int cardId = takeCardIdInput(playerCards.size(), cardIds);
            cardIds.add(cardId);
            cards.add(playerCards.get(cardId));
        }
        return cards;
    }

    private City takeCityInput() {
        try {
            return City.valueOf(scanner.next());
        } catch (Exception e) {
            System.out.println("City entered is not recognised!! Please try again!!");
            return takeCityInput();
        }
    }

    private int takeCardIdInput(int maxSize, List<Integer> cardIds) {
        int cardId = scanner.nextInt();
        if (cardId >= maxSize || cardIds.contains(cardId)) {
            System.out.println("Select valid ids for card to be picked: ");
            return takeCardIdInput(maxSize, cardIds);
        }
        return cardId;
    }
}
