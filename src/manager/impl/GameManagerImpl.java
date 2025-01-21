package manager.impl;

import manager.BoardManager;
import manager.CardManager;
import manager.GameManager;
import manager.PlayerManager;
import models.Player;
import models.action.*;
import models.Game;
import repositories.GameRepository;

import java.util.List;
import java.util.UUID;

public class GameManagerImpl implements GameManager {

    private final BoardManager boardManager;
    private final PlayerManager playerManager;
    private final CardManager cardManager;
    private final GameRepository gameRepository;
    private static final int NUM_CARDS_PER_PLAYER = 4;
    private static final int NUM_OPEN_CARDS = 4;

    public GameManagerImpl(BoardManager boardManager, PlayerManager playerManager, CardManager cardManager,
                           GameRepository gameRepository) {
        this.boardManager = boardManager;
        this.playerManager = playerManager;
        this.cardManager = cardManager;
        this.gameRepository = gameRepository;
    }

    @Override
    public Game create(int numPlayer, int numCards) {
        String gameId = UUID.randomUUID().toString();
        List<Player> players = playerManager.initPlayers(gameId, numPlayer);
        cardManager.initCardsDeck(gameId, numCards);
        distributeCards(gameId, players);
        boardManager.createBoard(gameId);
        Game game = new Game(gameId);
        gameRepository.save(game);
        return game;
    }

    private void distributeCards(String gameId, List<Player> players) {
        for (Player player: players) {
            cardManager.addCardsToPlayerFromDeck(gameId, player.getId(), NUM_CARDS_PER_PLAYER);
        }
        cardManager.addCardsToOpenCardsFromDeck(gameId, NUM_OPEN_CARDS);
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
        playerAction.accept(new PlayerActionVisitor<Void>() {
                    @Override
                    public Void visit(DrawCardsAction drawCardsAction) {
                        cardManager.addCardsToPlayerFromOpenCards(gameId, drawCardsAction.getPlayer().getId(),
                                drawCardsAction.getCardsToDraw());
                        return null;
                    }

                    @Override
                    public Void visit(AddTrainCarAction addTrainCarAction) {
                        return null;
                    }
                });
    }

    @Override
    public void checkIfGameCompleted(String gameId) {
        Game game = getGame(gameId);
//        game.setGameComplete(true);
//        gameRepository.update(gameId, game);
    }
}
