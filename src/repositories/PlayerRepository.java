package repositories;

import models.Player;

import java.util.List;

public interface PlayerRepository {

    String save(Player player);
    Player get(String id);
    void update(String id, Player player);
    List<Player> getPlayersForGame(String gameId);
}
