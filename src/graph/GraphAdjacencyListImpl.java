package graph;

import java.util.*;

public class GraphAdjacencyListImpl<T, U> implements Graph<T, U> {

    private final Map<T, List<WeightedEdge<T, U>>> adjacencyList;
    private final Set<T> vertices;

    public GraphAdjacencyListImpl() {
        this.adjacencyList  = new HashMap<>();
        this.vertices = new HashSet<>();
    }

    @Override
    public void addEdge(T source, T destination, U weight) {
        if (!vertices.contains(source) || !vertices.contains(destination)) {
            throw new VertexNotFoundException("vertex not found!!");
        }
        if (adjacencyList.containsKey(source)) {
            adjacencyList.get(source).add(new WeightedEdge<T, U>(destination, weight));
            return;
        }
        List<WeightedEdge<T, U>> edgesList = new LinkedList<>();
        edgesList.add(new WeightedEdge<T, U>(destination, weight));
        adjacencyList.put(source, edgesList);
    }

    @Override
    public void addVertex(T vertex) {
        vertices.add(vertex);
    }

    @Override
    public void print() {
        for (T vertex: adjacencyList.keySet()) {
            System.out.print(vertex + " -> ");
            for (WeightedEdge<T, U> edge: adjacencyList.get(vertex)) {
                System.out.print(edge.toString() + " , ");
            }
            System.out.println();
        }
    }
}
