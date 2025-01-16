package models.action;

import models.Card;
import models.Player;

import java.util.List;

public class DrawCardsAction extends PlayerAction {
    private final List<Card> cardsToDraw;

    public DrawCardsAction(Player player, List<Card> cardsToDraw) {
        super(PlayerActionType.DRAW_CARDS, player);
        this.cardsToDraw = cardsToDraw;
    }

    public List<Card> getCardsToDraw() {
        return cardsToDraw;
    }
}
