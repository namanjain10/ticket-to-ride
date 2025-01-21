package models.action;

public interface PlayerActionVisitor<T> {
    T visit(AddTrainCarAction playerAction);
    T visit(DrawCardsAction playerAction);
}
