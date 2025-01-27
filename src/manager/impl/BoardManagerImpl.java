package manager.impl;

import exception.CitiesNotConnectedException;
import exception.ConnectionAlreadyOccupiedException;
import exception.InsufficientConnectionCardsException;
import graph.Graph;
import graph.GraphAdjacencyListImpl;
import manager.BoardManager;
import models.board.Board;
import models.board.City;
import models.board.Connection;
import models.cards.Card;
import models.cards.Color;
import models.player.Player;
import repositories.BoardRepository;
import utils.BoardUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    public void addTrainCarConnection(String gameId, City source, City destination, Player player, List<Card> cards) {
        Board board = getBoardForGame(gameId);
        Graph<City, Connection> cityConnectionGraph = board.getCityConnection();
        Connection connectionEdge = cityConnectionGraph.edgeExists(source, destination);
        if (connectionEdge == null) {
            connectionEdge = cityConnectionGraph.edgeExists(destination, source);
            if (connectionEdge == null) {
                throw new CitiesNotConnectedException("Cities are not connected!!");
            }
        }
        if (connectionEdge.isOccupied()) {
            throw new ConnectionAlreadyOccupiedException("Cities connection already occupied by some other player!!");
        }
        Color edgeColor = connectionEdge.getColor();
        int size = connectionEdge.getSize();
        validateColorAndCarCount(edgeColor, size, cards);
        connectionEdge.setOccupiedBy(player.getId());
        boardRepository.update(board.getId(), board);
    }

    private void validateColorAndCarCount(Color edgeColor, int size, List<Card> cards) {
        Map<Color, Long> cardsColor = cards.stream()
                .map(Card::getColor)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (cardsColor.get(edgeColor) != size) {
            throw new InsufficientConnectionCardsException("Not Sufficient cards with player to create connection!!");
        }
    }

    @Override
    public boolean checkIfRouteIsComplete(String boardId, City source, City destination) {
        return false;
    }
}
