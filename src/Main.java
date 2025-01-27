import executor.ActionRegistrant;
import executor.GameExecutor;
import manager.*;
import manager.impl.*;
import repositories.*;
import repositories.impl.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BoardRepository boardRepository = new InMemoryBoardRepository();
        PlayerRepository playerRepository = new InMemoryPlayerRepository();
        GameRepository gameRepository = new InMemoryGameRepository();
        CardsRepository cardsRepository = new InMemoryCardsRepository();
        TicketRepository ticketRepository = new InMemoryTicketRepository();

        BoardManager boardManager = new BoardManagerImpl(boardRepository);
        PlayerManager playerManager = new PlayerManagerImpl(playerRepository);
        CardManager cardManager = new CardManagerImpl(cardsRepository);
        TicketManager ticketManager = new TicketManagerImpl(ticketRepository);
        GameManager gameManager = new GameManagerImpl(boardManager, playerManager, cardManager, ticketManager,
                gameRepository);
        ActionRegistrant actionRegistrant = new ActionRegistrant(gameManager);

        GameExecutor gameExecutor = new GameExecutor(boardManager, gameManager, cardManager,
                playerManager, ticketManager, actionRegistrant);
        gameExecutor.startNewGame();
    }
}