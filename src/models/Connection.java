package models;

public class Connection {
    private City from;
    private City to;
    private int size;
    private Color color;
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
}
