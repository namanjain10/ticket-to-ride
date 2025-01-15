package manager.impl;

import exception.CitiesNotConnectedException;
import exception.ConnectionAlreadyOccupiedException;
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

    public Board get(String boardId) {
        return boardRepository.get(boardId);
    }

    @Override
    public Board getBoardForGame(String gameId) {
        return boardRepository.getBoardForGame(gameId);
    }

    @Override
    public void addTrainCarConnection(String boardId, City source, City destination, Player player) {
        Board board = get(boardId);
        Graph<City, Connection> cityConnectionGraph = board.getCityConnection();
        Connection connectionEdge = cityConnectionGraph.isConnected(source, destination);
        if (connectionEdge == null) {
            connectionEdge = cityConnectionGraph.isConnected(destination, source);
            if (connectionEdge == null) {
                throw new CitiesNotConnectedException("Cities are not connected!!");
            }
        }
        if (connectionEdge.isOccupied()) {
            throw new ConnectionAlreadyOccupiedException("Cities connection already occupied by some other player!!");
        }
        connectionEdge.setOccupiedBy(player.getId());
        boardRepository.update(boardId, board);
    }

    @Override
    public boolean checkIfRouteIsComplete(String boardId, City source, City destination) {
        return false;
    }
}
