package models.action;

import models.Card;
import models.City;

import java.util.List;

public class AddTrainCarAction extends PlayerAction {
    private City source;
    private City destination;
    private List<Card> cardSubmitted;
}
