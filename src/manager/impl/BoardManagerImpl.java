package manager.impl;

import graph.Graph;
import graph.GraphAdjacencyListImpl;
import manager.BoardManager;
import models.Board;
import models.City;
import models.Connection;
import models.Player;
import repositories.BoardRepository;
import utils.BoardUtil;

public class BoardManagerImpl implements BoardManager {

    private final BoardRepository boardRepository;

    public BoardManagerImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public Board createBoard(String gameId) {
        Graph<City, Connection> cityConnections = new GraphAdjacencyListImpl<>();
        for (Connection connection: BoardUtil.createBoardCityConnections()) {
            City sourceCity = connection.getFrom();
            City destinationCity = connection.getTo();
            cityConnections.addVertex(sourceCity);
            cityConnections.addVertex(destinationCity);
            cityConnections.addEdge(sourceCity, destinationCity, connection);
        }
        Board board = new Board(gameId, cityConnections);
        boardRepository.save(board);
        return board;
    }

    @Override
    public void addTrainCarConnection(Board board, City source, City destination, Player player) {

    }

    @Override
    public boolean checkIfRouteIsComplete(Board board, City source, City destination) {
        return false;
    }
}
