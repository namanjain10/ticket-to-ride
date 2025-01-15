package executor;

import manager.BoardManager;
import manager.GameManager;
import manager.PlayerManager;
import models.Board;
import models.Game;
import models.Player;

import java.util.List;

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
            List<Player> playerList = playerManager.getPlayersForGame(game.getId());
            // TODO: add userAction here
            gameManager.checkIfGameCompleted(gameId);
        }
    }
}
