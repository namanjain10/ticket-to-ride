package models.action;

public interface PlayerActionTypeVisitor<T> {
    T visitPickCards(PlayerActionType playerAction);
    T visitAddTrainCar(PlayerActionType playerAction);
}
