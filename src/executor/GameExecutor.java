package executor;

import exception.CurrentPlayerNotFoundException;
import manager.BoardManager;
import manager.CardManager;
import manager.GameManager;
import manager.PlayerManager;
import models.*;
import models.action.PlayerAction;
import models.cards.Card;

import java.util.List;

public class GameExecutor {

    private final BoardManager boardManager;
    private final GameManager gameManager;
    private final CardManager cardManager;
    private final PlayerManager playerManager;
    private final ActionRegistrant actionRegistrant;

    public GameExecutor(BoardManager boardManager, GameManager gameManager, CardManager cardManager,
                        PlayerManager playerManager, ActionRegistrant actionRegistrant) {
        this.boardManager = boardManager;
        this.gameManager = gameManager;
        this.cardManager = cardManager;
        this.playerManager = playerManager;
        this.actionRegistrant = actionRegistrant;
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
            List<Card> openCards = cardManager.getOpenCardForGame(game.getId());
            List<Card> playerCards = cardManager.getPlayerCards(game.getId(), currentPlayer.getId());
            printCurrentGameState(game, board, currentPlayer, openCards, playerCards);
            executeUserAction(game, currentPlayer, openCards, playerCards);
            gameManager.checkIfGameCompleted(gameId);
            gameManager.passChanceToNextPlayer(gameId, playerList);
        }
    }

    private void executeUserAction(Game game, Player currentPlayer, List<Card> openCards,
                                   List<Card> playerCards) {
        try {
            PlayerAction playerAction = actionRegistrant.register(game, currentPlayer, openCards, playerCards);
            gameManager.registerPlayerAction(game.getId(), playerAction);
        } catch (Exception e) {
            System.out.println("Action not valid, Please try again!!");
            System.out.println(e.getMessage());
            System.out.println();
            executeUserAction(game, currentPlayer, openCards, playerCards);
        }
    }

    private void printCurrentGameState(Game game, Board board, Player currentPlayer, List<Card> openCards,
                                       List<Card> playerCards) {
        System.out.println();
        System.out.println("Current Board : ");
        board.getCityConnection().print();
        System.out.println();
        System.out.println("Current Open cards: ");
        printCards(openCards);
        System.out.println("Next chance is for Player: " + currentPlayer.toString());
        System.out.println("Show Cards for Player: ");
        printCards(playerCards);
    }

    private void printCards(List<Card> cards) {
        if (cards == null) {
            return;
        }
        for (int i=0; i<cards.size(); i++) {
            System.out.printf("%d: %s  ", i, cards.get(i));
            System.out.println();
        }
        System.out.println();
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
}
