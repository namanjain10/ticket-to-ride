package manager.impl;

import manager.*;
import models.player.Player;
import models.action.*;
import models.Game;
import repositories.GameRepository;

import java.util.List;
import java.util.UUID;

public class GameManagerImpl implements GameManager {

    private final BoardManager boardManager;
    private final PlayerManager playerManager;
    private final CardManager cardManager;
    private final TicketManager ticketManager;
    private final GameRepository gameRepository;
    private static final int NUM_CARDS_PER_PLAYER = 4;
    private static final int NUM_OPEN_CARDS = 4;

    public GameManagerImpl(BoardManager boardManager, PlayerManager playerManager, CardManager cardManager,
                           TicketManager ticketManager, GameRepository gameRepository) {
        this.boardManager = boardManager;
        this.playerManager = playerManager;
        this.cardManager = cardManager;
        this.ticketManager = ticketManager;
        this.gameRepository = gameRepository;
    }

    @Override
    public Game create(int numPlayer, int numCards) {
        String gameId = UUID.randomUUID().toString();
        List<Player> players = playerManager.initPlayers(gameId, numPlayer);
        cardManager.initCardsDeck(gameId, numCards);
        ticketManager.initTickets(gameId);
        distributeCardsAndTickets(gameId, players);
        boardManager.createBoard(gameId);
        Game game = new Game(gameId);
        gameRepository.save(game);
        return game;
    }

    private void distributeCardsAndTickets(String gameId, List<Player> players) {
        for (Player player: players) {
            cardManager.addCardsToPlayerFromDeck(gameId, player.getId(), NUM_CARDS_PER_PLAYER);
            ticketManager.addTicketToPlayerFromDeck(gameId, player.getId());
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
                        boardManager.addTrainCarConnection(gameId, addTrainCarAction.getSource(),
                                addTrainCarAction.getDestination(), addTrainCarAction.getPlayer(),
                                addTrainCarAction.getCardSubmitted());
                        cardManager.addCardsToReturnedFromPlayerCards(gameId,
                                addTrainCarAction.getPlayer().getId(), addTrainCarAction.getCardSubmitted());
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

    @Override
    public void passChanceToNextPlayer(String gameId, List<Player> playerList) {
        Game game = getGame(gameId);
        String currentPlayerId = game.getNextChance();
        Player nextPlayer = null;
        if (currentPlayerId == null) {
            // setting player with id 1 as the next chance
            nextPlayer = getPlayerWithSeqId(playerList, 1);
        } else {
            Player currentPlayer = playerList.stream()
                    .filter(player -> currentPlayerId.equals(player.getId()))
                    .findFirst()
                    .orElse(null);
            if (currentPlayer == null) {
                return;
            }
            int seqId = currentPlayer.getSequenceId() + 1;
            if (seqId >= playerList.size()) {
                seqId = 0;
            }
            nextPlayer = getPlayerWithSeqId(playerList, seqId);
        }
        if (nextPlayer == null) {
            return;
        }
        game.setNextChance(nextPlayer.getId());
    }

    private Player getPlayerWithSeqId(List<Player> playerList, int seqId) {
        return playerList.stream()
                .filter(player -> player.getSequenceId() == seqId)
                .findFirst()
                .orElse(null);
    }
}
