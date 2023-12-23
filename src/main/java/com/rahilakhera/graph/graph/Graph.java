/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 16-Dec-2023 00:11:33
 * @desc [Graph class]
 */

package com.rahilakhera.graph.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<Integer, List<Edge>> adjacencyList;
    private boolean isDirected;
    private List<Edge> edgeList;
    private int[][] adjacencyMatrix;
    private boolean adjacencyMatrixInitialized;
    private Set<Integer> vertexSet;

    public Graph() {
        this.adjacencyList = new HashMap<>();
        this.isDirected = false;
        this.edgeList = new ArrayList<>();
        this.vertexSet = new HashSet<>();
    }

    public Graph(boolean isDirected) {
        this.adjacencyList = new HashMap<>();
        this.isDirected = isDirected;
        this.edgeList = new ArrayList<>();
        this.vertexSet = new HashSet<>();
    }

    public void addEdge(int source, int destination) {
        addEdge(source, destination, 1);
    }

    public void addEdge(int source, int destination, int weight) {

        Edge edge = new Edge(source, destination, weight);
        edgeList.add(edge);

        this.vertexSet.add(destination);
        this.vertexSet.add(source);

        adjacencyList.computeIfAbsent(source, vertex -> new ArrayList<>()).add(edge);
        if (!isDirected) {
            adjacencyList.computeIfAbsent(destination, vertex -> new ArrayList<>())
                    .add(new Edge(destination, source, weight));
        } else {
            /**
             * In case, of Directed graph, if the node has no outgoing edge,
             * this will make an empty arraylist to complete the adjancency list.
             */

            adjacencyList.computeIfAbsent(destination, vertex -> new ArrayList<>());
        }
    }

    public List<Edge> getNeighbors(int source) {
        return adjacencyList.getOrDefault(source, new ArrayList<>());
    }

    public Set<Integer> getNodes() {
        Set<Integer> copiedSet = new HashSet<>();
        copiedSet.addAll(adjacencyList.keySet());
        return copiedSet;
    }

    public int getTotalNodes() {
        return adjacencyList.keySet().size();
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edgeList);
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void initAdjancencyMatrix() {

        int n = getTotalNodes();
        adjacencyMatrix = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i == j) {
                    adjacencyMatrix[i][j] = 0;
                } else {
                    adjacencyMatrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        for (Edge edge : getEdges()) {
            int source = edge.getVertex();
            int destination = edge.getDestination();
            int weight = edge.getWeight();

            adjacencyMatrix[source][destination] = weight;

            if (!isDirected) {
                adjacencyMatrix[destination][source] = weight;
            }
        }

        adjacencyMatrixInitialized = true;

    }

    public int[][] getAdjancencyMatrix() {
        return adjacencyMatrix;
    }

    public boolean isAdjancenyMatrixInitialized() {
        return adjacencyMatrixInitialized;
    }

    public boolean addVertext(int vertex) {

        boolean added = vertexSet.add(vertex);
        if (added) {
            adjacencyList.computeIfAbsent(vertex, key -> new ArrayList<>());
        }
        return added;
    }

    public boolean removeVertex(int vertex) {

        boolean removed = vertexSet.remove(vertex);
        if (removed) {

            adjacencyList.remove(vertex);
            /**
             * Update values of adjacency list.
             */

            for (Map.Entry<Integer, List<Edge>> entry : adjacencyList.entrySet()) {
                entry.getValue().removeIf(edge -> edge.getDestination() == vertex || edge.getVertex() == vertex);
            }

            /**
             * update edge list.
             */
            edgeList.removeIf(edge -> edge.getDestination() == vertex || edge.getVertex() == vertex);

        }
        return removed;
    }

}
