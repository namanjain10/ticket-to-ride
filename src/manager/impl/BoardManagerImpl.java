package manager.impl;

import manager.BoardManager;
import models.Board;
import models.City;
import models.Player;

public class BoardManagerImpl implements BoardManager {

    @Override
    public Board createBoard() {
        return null;
    }

    @Override
    public void addTrainCarConnection(Board board, City source, City destination, Player player) {

    }

    @Override
    public boolean checkIfRouteIsComplete(Board board, City source, City destination) {
        return false;
    }
}
