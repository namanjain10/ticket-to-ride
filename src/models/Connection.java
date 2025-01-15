package models;

public class Connection {
    private City from;
    private City to;
    private int size;
    private Color color;

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
}
