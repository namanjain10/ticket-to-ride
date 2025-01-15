package graph;

public class WeightedEdge<T, U> {
    private final T destination;
    private final U weight;

    public WeightedEdge(T destination, U weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public T getDestination() {
        return destination;
    }

    public U getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", destination.toString(), weight.toString());
    }
}
