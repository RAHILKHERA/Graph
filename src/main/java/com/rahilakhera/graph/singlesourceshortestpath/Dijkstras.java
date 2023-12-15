package com.rahilakhera.graph.singlesourceshortestpath;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;

import com.rahilakhera.graph.graph.Graph;

public class Dijkstras {
    
    private Graph graph;
    private Set<Integer> visited; 
    private Map<Integer, List<Integer>> paths;

    public Dijkstras(Graph graph) {
        this.graph = graph;
        this.visited = new HashSet<>();
        this.paths = new HashMap<>();
    }



}
