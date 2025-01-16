package manager.impl;

import manager.BoardManager;
import manager.GameManager;
import manager.PlayerManager;
import models.Board;
import models.cards.Card;
import models.Game;
import models.Player;
import models.action.PlayerAction;
import repositories.GameRepository;
import utils.CardsUtil;

import java.util.List;
import java.util.Stack;
import java.util.UUID;

public class GameManagerImpl implements GameManager {

    private final BoardManager boardManager;
    private final PlayerManager playerManager;
    private final GameRepository gameRepository;

    public GameManagerImpl(BoardManager boardManager, PlayerManager playerManager, GameRepository gameRepository) {
        this.boardManager = boardManager;
        this.playerManager = playerManager;
        this.gameRepository = gameRepository;
    }

    @Override
    public Game create(int numPlayer, int numCards) {
        String gameId = UUID.randomUUID().toString();
        List<Player> playerList = playerManager.initPlayers(gameId, numPlayer);
        Stack<Card> cardsDeck = CardsUtil.createCardsDeck(numCards);
        Board board = boardManager.createBoard(gameId);
        Game game = new Game(gameId, cardsDeck, new Stack<>());
        gameRepository.save(game);
        return game;
    }

    @Override
    public Game getGame(String gameId) {
        return gameRepository.get(gameId);
    }

    @Override
    public boolean validatePlayerAction(String gameId, PlayerAction playerAction) {
        return true;
    }

    @Override
    public void registerPlayerAction(String gameId, PlayerAction playerAction) {

    }

    @Override
    public void checkIfGameCompleted(String gameId) {
        Game game = getGame(gameId);
        game.setGameComplete(true);
        gameRepository.update(gameId, game);
    }
}
