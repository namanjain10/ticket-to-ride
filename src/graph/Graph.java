package graph;

public interface Graph<T, U> {
    void addEdge(T source, T destination, U weight);
    void addVertex(T vertex);
    void print();
    U isConnected(T source, T destination);
}
