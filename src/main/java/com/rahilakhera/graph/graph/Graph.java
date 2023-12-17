/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 16-Dec-2023 00:11:33
 * @desc [Graph class]
 */

package com.rahilakhera.graph.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<Integer, List<Edge>> adjancencyList;
    private boolean isDirected; 
    private List<Edge> edgeList;

    public Graph () {
        this.adjancencyList = new HashMap<>();
        this.isDirected = false;
        this.edgeList = new ArrayList<>(); 
    }

    public Graph(boolean isDirected) {
        this.adjancencyList = new HashMap<>();
        this.isDirected = isDirected;
        this.edgeList = new ArrayList<>();
    }

    public void addEdge(int source, int destination) {
        addEdge(source, destination, 1);
    }

    public void addEdge(int source, int destination, int weight) {


        Edge edge = new Edge(destination, weight, source); 
        edgeList.add(edge);

        adjancencyList.computeIfAbsent(source, vertex -> new ArrayList<>()).add(edge);
        if (!isDirected) {
            adjancencyList.computeIfAbsent(destination, vertex -> new ArrayList<>()).add(new Edge(source, weight, destination));
        } else {
            /**
             * In case, of Directed graph, if the node has no outgoing edge,
             * this will make an empty arraylist to complete the adjancency list. 
             */

            adjancencyList.computeIfAbsent(destination, vertex -> new ArrayList<>());
        }
    }

    public List<Edge> getNeighbors (int source) {
        return adjancencyList.getOrDefault(source, new ArrayList<>());
    }

    public Set<Integer> getNodes () {
        return adjancencyList.keySet();
    }

    public int getTotalNodes() {
        return adjancencyList.keySet().size();
    }

    public List<Edge> getEdges () {
        return edgeList;
    }

    public boolean isDirected () {
        return isDirected;
    }

    
}
