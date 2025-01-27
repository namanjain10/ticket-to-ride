package utils;

import models.board.City;
import models.cards.Color;
import models.board.Connection;

import java.util.List;

public class BoardUtil {

    private BoardUtil() {}

    public static List<Connection> createBoardCityConnections() {
        return List.of(
                new Connection(City.AMSTERDAM, City.PARIS, 3, Color.GREEN),
                new Connection(City.PARIS, City.WERSAW, 3, Color.BLUE),
                new Connection(City.WERSAW, City.WIEN, 4, Color.PURPLE),
                new Connection(City.WERSAW, City.LONDON, 3, Color.YELLOW),
                new Connection(City.LONDON, City.EDINBURGH, 3, Color.ORANGE),
                new Connection(City.LONDON, City.LISBON, 2, Color.YELLOW),
                new Connection(City.LISBON, City.ZURICH, 3, Color.WHITE),
                new Connection(City.LISBON, City.MOSCOW, 1, Color.GREEN),
                new Connection(City.ZURICH, City.WIEN, 2, Color.WHITE),
                new Connection(City.WIEN, City.MOSCOW, 4, Color.PURPLE)
                );
    }
}
