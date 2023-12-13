package com.rahilakhera.graph.traversal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.rahilakhera.graph.graph.Graph;

public class BFSTest {
     @Test
    public void testBasicCase() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);

        BFS bfs = new BFS(graph);
        List<Integer> result = bfs.traversal(1);

        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertEquals(expected, result);
    }

    @Test
    public void testDisconnectedGraph() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);

        BFS bfs = new BFS(graph);
        List<Integer> result = bfs.traversal(1);

        List<Integer> expected = Arrays.asList(1, 2, 3);
        assertEquals(expected, result);
    }

    @Test
    public void testCycleDetection() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);
        graph.addEdge(3, 4);

        BFS bfs = new BFS(graph);
        List<Integer> result = bfs.traversal(1);

        List<Integer> expected = Arrays.asList(1, 2, 3, 4);
        assertEquals(expected, result);
    }

    @Test
    public void testDirectedGraph() {
        Graph graph = new Graph(true);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);

        BFS bfs = new BFS(graph);
        List<Integer> result = bfs.traversal(1);

        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, result);
    }

    @Test
    public void testComplexGraphStructure() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        graph.addEdge(4, 7);
        graph.addEdge(5, 8);

        BFS bfs = new BFS(graph);
        List<Integer> result = bfs.traversal(1);

        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
        assertEquals(expected, result);
    }

    @Test
    public void testEmptyGraph() {
        Graph graph = new Graph();

        BFS bfs = new BFS(graph);
        List<Integer> result = bfs.traversal(1);

        List<Integer> expected = Arrays.asList();
        assertEquals(expected, result);
    }

}
