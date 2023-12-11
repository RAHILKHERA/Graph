package com.rahilakhera.graph.traversal;

import com.rahilakhera.graph.graph.Graph;

public class DFSTest {
      
    private Graph graph;

    DFSTest() {
        graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
    }
}
