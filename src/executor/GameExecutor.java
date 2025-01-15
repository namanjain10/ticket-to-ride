package executor;

import manager.BoardManager;
import manager.GameManager;
import manager.PlayerManager;
import models.Board;
import models.Game;
import models.Player;
import models.action.AddTrainCarAction;
import models.action.DrawCardsAction;
import models.action.PlayerAction;

import java.util.List;
import java.util.Scanner;

public class GameExecutor {

    public final BoardManager boardManager;
    public final GameManager gameManager;
    public final PlayerManager playerManager;

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
        while (game.isGameComplete()) {
            Board board = boardManager.getBoardForGame(game.getId());
            System.out.println(board.getCityConnection());

            List<Player> playerList = playerManager.getPlayersForGame(game.getId());
            String nextChancePlayerId = game.getNextChance();

            Scanner scanner = new Scanner(System.in);

            PlayerAction playerAction = capturePlayerAction(scanner, gameId);

            gameManager.registerPlayerAction(gameId, playerAction);

            gameManager.checkIfGameCompleted(gameId);
        }
    }

    private PlayerAction capturePlayerAction(Scanner scanner, String gameId) {
        System.out.println("Choose Action: Enter 1 for picking Cards or 2 for placing train Car");
        PlayerAction playerAction;
        int actionInt = scanner.nextInt();
        switch (actionInt) {
            case 0:
                playerAction = new DrawCardsAction();
                break;
            case 1:
                playerAction = new AddTrainCarAction();
                break;
            default:
                return capturePlayerAction(scanner, gameId);
        }
        if (!gameManager.validatePlayerAction(gameId, playerAction)) {
            System.out.println("Action performed by the user is not valid!! Please enter correct action inputs");
            return capturePlayerAction(scanner, gameId);
        }
        return playerAction;
    }
}
