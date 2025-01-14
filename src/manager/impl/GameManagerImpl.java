package manager.impl;

import manager.BoardManager;
import manager.GameManager;
import manager.PlayerManager;
import models.Card;
import models.Game;
import models.Player;
import models.action.PlayerAction;
import utils.CardsUtil;

import java.util.List;
import java.util.Stack;

public class GameManagerImpl implements GameManager {

    private BoardManager boardManager;
    private PlayerManager playerManager;

    @Override
    public Game create(int numPlayer, int numCards) {
        List<Player> playerList = playerManager.initPlayers(numPlayer);
        Stack<Card> cardsDeck = CardsUtil.createCardsDeck(numCards);
        return new Game(playerList, cardsDeck, new Stack<>(), boardManager.createBoard());
    }

    @Override
    public void registerPlayerAction(Game game, PlayerAction playerAction) {

    }
}
