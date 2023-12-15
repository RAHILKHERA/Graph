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

    public Graph () {
        this.adjancencyList = new HashMap<>();
        this.isDirected = false; 
    }

    public Graph(boolean isDirected) {
        this.adjancencyList = new HashMap<>();
        this.isDirected = isDirected;
    }

    public void addEdge(int source, int destination) {
        addEdge(source, destination, 1);
    }

    public void addEdge(int source, int destination, int weight) {

        adjancencyList.computeIfAbsent(source, vertex -> new ArrayList<>()).add(new Edge(destination, weight));
        
        if (!isDirected) {
            adjancencyList.computeIfAbsent(destination, vertex -> new ArrayList<>()).add(new Edge(destination, weight));
        }
    }

    public List<Edge> getNegihbors (int source) {
        return adjancencyList.getOrDefault(source, new ArrayList<>());
    }

    public Set<Integer> getNodes () {
        return adjancencyList.keySet();
    }

    public int getTotalNodes() {
        return adjancencyList.keySet().size();
    }
}
