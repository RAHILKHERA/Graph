/**
 * @author Rahil Khera
*/
package com.rahilakhera.graph.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<Integer, List<Integer>> adjancencyList;
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

        adjancencyList.computeIfAbsent(source, vertex -> new ArrayList<>()).add(destination);
        
        if (!isDirected) {
            adjancencyList.computeIfAbsent(destination, vertex -> new ArrayList<>()).add(source);
        }
    }

    public List<Integer> getNegihbors (int source) {
        return adjancencyList.getOrDefault(adjancencyList, new ArrayList<>());
    }

    public Set<Integer> getNodes () {
        return adjancencyList.keySet();
    }

    public int getTotalNodes() {
        return adjancencyList.keySet().size();
    }
}
