package models.board;

import models.cards.Color;

public class Connection {
    private final City from;
    private final City to;
    private final int size;
    private final Color color;
    private boolean isFree = true;
    private String occupiedBy;

    public Connection(City from, City to, int size, Color color) {
        this.from = from;
        this.to = to;
        this.size = size;
        this.color = color;
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public boolean isOccupied() {
        return !this.isFree;
    }

    public void setOccupiedBy(String playerId) {
        this.occupiedBy = playerId;
        this.isFree = false;
    }

    @Override
    public String toString() {
        if (this.isFree) {
            return String.format("%s, %d, unoccupied)", this.color, this.size);
        }
        return String.format("%s, %d, occupiedBy: %s)", this.color, this.size, this.occupiedBy);
    }
}
