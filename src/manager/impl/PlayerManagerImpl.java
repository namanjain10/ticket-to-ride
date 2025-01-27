package manager.impl;

import exception.PlayerCreationFailedException;
import manager.PlayerManager;
import models.player.Player;
import models.player.PlayerColor;
import models.action.PlayerAction;
import repositories.PlayerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerManagerImpl implements PlayerManager {

    private final PlayerRepository playerRepository;

    public PlayerManagerImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> initPlayers(String gameId, int playerCount) {
        PlayerColor[] playerColorsAvailable = PlayerColor.values();
        if (playerCount > playerColorsAvailable.length) {
            throw new PlayerCreationFailedException("Number of players can't be greater than " +
                    playerColorsAvailable.length);
        }
        List<Player> players = new ArrayList<>();
        for (int i=0; i<playerCount; i++) {
            String playerId = UUID.randomUUID().toString();
            // TODO: get tickets and cards for players
            Player player = new Player(playerId, playerId, gameId, playerColorsAvailable[i],
                    null, null, 45, i);
            playerRepository.save(player);
            players.add(player);
        }
        return players;
    }

    @Override
    public List<Player> getPlayersForGame(String gameId) {
        return playerRepository.getPlayersForGame(gameId);
    }

    @Override
    public void registerPlayerAction(PlayerAction playerAction) {

    }
}
