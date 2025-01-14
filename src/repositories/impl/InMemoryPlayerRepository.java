package repositories.impl;

import exception.DataGetException;
import exception.DataSaveException;
import models.Player;
import repositories.PlayerRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class InMemoryPlayerRepository implements PlayerRepository {

    private Map<String, List<Player>> gameVsPlayerMap;
    private Map<String, Player> playerMap;

    @Override
    public String save(Player player) {
        String gameId = player.getGameId();
        String playerId = player.getId();
        if (Objects.isNull(gameId)) {
            throw new DataSaveException("GameId should not be null!!");
        }
        if (gameVsPlayerMap.containsKey(gameId)) {
            gameVsPlayerMap.put(gameId, List.of(player));
        } else {
            List<Player> players =  gameVsPlayerMap.get(gameId);
            players.add(player);
            gameVsPlayerMap.put(gameId, players);
        }
        playerMap.put(playerId, player);
        return player.getId();
    }

    @Override
    public Player get(String id) {
        return playerMap.get(id);
    }

    @Override
    public void update(String gameId, Player player) {
        String playerId = player.getId();

        Optional<Player> playerToBeUpdatedOptional = gameVsPlayerMap.get(gameId).stream()
                .filter(player1 -> playerId.equals(player1.getId()))
                .findFirst();
        if (playerToBeUpdatedOptional.isEmpty() || !playerMap.containsKey(playerId)) {
            throw new DataGetException("Player to be updated Not found!!");
        }
        Player playerToBeUpdated = playerToBeUpdatedOptional.get();
        playerToBeUpdated = player;
        playerMap.put(playerId, player);
    }

    @Override
    public List<Player> getPlayersForGame(String gameId) {
        return gameVsPlayerMap.get(gameId);
    }
}
