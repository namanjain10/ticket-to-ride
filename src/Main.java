import executor.GameExecutor;
import manager.BoardManager;
import manager.GameManager;
import manager.PlayerManager;
import manager.impl.BoardManagerImpl;
import manager.impl.GameManagerImpl;
import manager.impl.PlayerManagerImpl;
import repositories.BoardRepository;
import repositories.GameRepository;
import repositories.PlayerRepository;
import repositories.impl.InMemoryBoardRepository;
import repositories.impl.InMemoryGameRepository;
import repositories.impl.InMemoryPlayerRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BoardRepository boardRepository = new InMemoryBoardRepository();
        PlayerRepository playerRepository = new InMemoryPlayerRepository();
        GameRepository gameRepository = new InMemoryGameRepository();

        BoardManager boardManager = new BoardManagerImpl(boardRepository);
        PlayerManager playerManager = new PlayerManagerImpl(playerRepository);
        GameManager gameManager = new GameManagerImpl(boardManager, playerManager, gameRepository);

        GameExecutor gameExecutor = new GameExecutor(boardManager, gameManager, playerManager);
        gameExecutor.startNewGame();
    }
}