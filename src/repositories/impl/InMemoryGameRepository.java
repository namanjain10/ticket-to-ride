package repositories.impl;

import exception.DataGetException;
import models.Game;
import repositories.GameRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class InMemoryGameRepository implements GameRepository {

    private Map<String, Game> gameMap = new HashMap<>();

    @Override
    public String save(Game game) {
        gameMap.put(game.getId(), game);
        return game.getId();
    }

    @Override
    public Game get(String id) {
        Game game = gameMap.get(id);
        if (Objects.isNull(game)) {
            throw new DataGetException("Game with id not found");
        }
        return game;
    }

    @Override
    public void update(String id, Game game) {
        Game gameOld = get(id);
        gameMap.put(id, game);
    }
}
