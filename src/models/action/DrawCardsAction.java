package models.action;

import models.cards.Card;
import models.player.Player;

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

    @Override
    public <T> T accept(PlayerActionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
