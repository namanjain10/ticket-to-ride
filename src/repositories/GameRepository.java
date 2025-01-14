package repositories;

import models.Game;

public interface GameRepository {
    String save(Game game);
    Game get(String id);
    void update(String id, Game game);
}
