package executor;

import exception.CurrentPlayerNotFoundException;
import manager.BoardManager;
import manager.GameManager;
import manager.PlayerManager;
import models.*;
import models.action.AddTrainCarAction;
import models.action.DrawCardsAction;
import models.action.PlayerAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameExecutor {

    private final BoardManager boardManager;
    private final GameManager gameManager;
    private final PlayerManager playerManager;
    private static final int MAX_CARDS_CAN_BE_PICKED = 2;

    public GameExecutor(BoardManager boardManager, GameManager gameManager, PlayerManager playerManager) {
        this.boardManager = boardManager;
        this.gameManager = gameManager;
        this.playerManager = playerManager;
    }

    public void startNewGame() {
        Game game = gameManager.create(4, 50);
        resumeGame(game.getId());
    }

    public void resumeGame(String gameId) {
        Game game = gameManager.getGame(gameId);
        while (!game.isGameComplete()) {
            Board board = boardManager.getBoardForGame(game.getId());

            List<Player> playerList = playerManager.getPlayersForGame(game.getId());
            Player currentPlayer = getPlayerWithCurrentChance(playerList, game);
            if (currentPlayer == null) {
                throw new CurrentPlayerNotFoundException("current player with chance not found!!");
            }
            printCurrentGameState(game, board, currentPlayer);

            Scanner scanner = new Scanner(System.in);
            PlayerAction playerAction = capturePlayerAction(scanner, game, currentPlayer);
            gameManager.registerPlayerAction(gameId, playerAction);

            gameManager.checkIfGameCompleted(gameId);
        }
    }

    private void printCurrentGameState(Game game, Board board, Player currentPlayer) {
        board.getCityConnection().print();
        System.out.println("Current Open Cards");
        game.printOpenCards();
        System.out.println("Next chance is for Player: " + currentPlayer.toString());
    }

    private Player getPlayerWithCurrentChance(List<Player> playerList, Game game) {
        String nextChancePlayerId = game.getNextChance();
        if (nextChancePlayerId == null) {
            return playerList.stream()
                    .filter(player -> player.getSequenceId() == 0)
                    .findFirst()
                    .orElse(null);
        } else {
            return playerList.stream()
                    .filter(player -> nextChancePlayerId.equals(player.getId()))
                    .findFirst()
                    .orElse(null);
        }
    }

    private PlayerAction capturePlayerAction(Scanner scanner, Game game, Player currentPlayer) {
        String gameId = game.getId();
        System.out.println("Choose Action: Enter 1 for picking Cards or 2 for placing train Car");
        PlayerAction playerAction;
        int actionInt = scanner.nextInt();
        switch (actionInt) {
            case 1:
                playerAction = getDrawCardsAction(scanner, currentPlayer, game.getOpenCards());
                break;
            case 2:
                playerAction = getAddTrainCarAction(scanner, currentPlayer);
                break;
            default:
                return capturePlayerAction(scanner, game, currentPlayer);
        }
        if (!gameManager.validatePlayerAction(gameId, playerAction)) {
            System.out.println("Action performed by the user is not valid!! Please enter correct action inputs");
            return capturePlayerAction(scanner, game, currentPlayer);
        }
        return playerAction;
    }

    private PlayerAction getDrawCardsAction(Scanner scanner, Player currentPlayer, List<Card> openCards) {
        System.out.println("Select ids for card to be picked: ");
        List<Integer> cardIds = new ArrayList<>();
        for (int i=0; i<MAX_CARDS_CAN_BE_PICKED; i++) {
            int cardId = takeCardIdInput(scanner, openCards.size(), cardIds);
            cardIds.add(cardId);
        }
        return new DrawCardsAction(currentPlayer, getCardsList(cardIds, openCards));
    }

    private int takeCardIdInput(Scanner scanner, int maxSize, List<Integer> cardIds) {
        int cardId = scanner.nextInt();
        if (cardId >= maxSize || cardIds.contains(cardId)) {
            return takeCardIdInput(scanner, maxSize, cardIds);
        }
        return cardId;
    }

    private List<Card> getCardsList(List<Integer> cardIds, List<Card> openCards) {
        List<Card> cardsSelected = new ArrayList<>();
        for (int cardId: cardIds) {
            cardsSelected.add(openCards.get(cardId));
        }
        return cardsSelected;
    }

    private PlayerAction getAddTrainCarAction(Scanner scanner, Player currentPlayer) {
        System.out.println("Source City: ");
        City sourceCity = takeCityInput(scanner);

        System.out.println("Destination City: ");
        City destinationCity = takeCityInput(scanner);

        // TODO: add userCards submitted
        return new AddTrainCarAction(currentPlayer, sourceCity, destinationCity, List.of());
    }

    private City takeCityInput(Scanner scanner) {
        try {
            return City.valueOf(scanner.next());
        } catch (Exception e) {
            System.out.println("City entered is not recognised!! Please try again!!");
            return takeCityInput(scanner);
        }
    }
}
