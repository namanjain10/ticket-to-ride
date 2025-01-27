package models.action;

import models.cards.Card;
import models.board.City;
import models.player.Player;

import java.util.List;

public class AddTrainCarAction extends PlayerAction {
    private City source;
    private City destination;
    private List<Card> cardSubmitted;

    public AddTrainCarAction(Player player, City source, City destination,
                             List<Card> cardSubmitted) {
        super(PlayerActionType.ADD_TRAIN_CAR, player);
        this.source = source;
        this.destination = destination;
        this.cardSubmitted = cardSubmitted;
    }

    public City getSource() {
        return source;
    }

    public City getDestination() {
        return destination;
    }

    public List<Card> getCardSubmitted() {
        return cardSubmitted;
    }

    @Override
    public <T> T accept(PlayerActionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
