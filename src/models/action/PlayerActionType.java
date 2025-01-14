package models.action;

public enum PlayerActionType {
    DRAW_CARDS {
        @Override
        public <T> T accept(PlayerActionTypeVisitor<T> visitor) {
            return visitor.visitPickCards(this);
        }
    },
    ADD_TRAIN_CAR {
        @Override
        public <T> T accept(PlayerActionTypeVisitor<T> visitor) {
            return visitor.visitAddTrainCar(this);
        }
    };

    public abstract <T> T accept(PlayerActionTypeVisitor<T> visitor);
}
