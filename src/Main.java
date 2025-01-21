import executor.GameExecutor;
import manager.BoardManager;
import manager.CardManager;
import manager.GameManager;
import manager.PlayerManager;
import manager.impl.BoardManagerImpl;
import manager.impl.CardManagerImpl;
import manager.impl.GameManagerImpl;
import manager.impl.PlayerManagerImpl;
import repositories.BoardRepository;
import repositories.CardsRepository;
import repositories.GameRepository;
import repositories.PlayerRepository;
import repositories.impl.InMemoryBoardRepository;
import repositories.impl.InMemoryCardsRepository;
import repositories.impl.InMemoryGameRepository;
import repositories.impl.InMemoryPlayerRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BoardRepository boardRepository = new InMemoryBoardRepository();
        PlayerRepository playerRepository = new InMemoryPlayerRepository();
        GameRepository gameRepository = new InMemoryGameRepository();
        CardsRepository cardsRepository = new InMemoryCardsRepository();

        BoardManager boardManager = new BoardManagerImpl(boardRepository);
        PlayerManager playerManager = new PlayerManagerImpl(playerRepository);
        CardManager cardManager = new CardManagerImpl(cardsRepository);
        GameManager gameManager = new GameManagerImpl(boardManager, playerManager, cardManager, gameRepository);

        GameExecutor gameExecutor = new GameExecutor(boardManager, gameManager, cardManager, playerManager);
        gameExecutor.startNewGame();
    }
}