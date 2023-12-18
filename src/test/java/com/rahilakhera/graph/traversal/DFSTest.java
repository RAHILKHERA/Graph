/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 12-Dec-2023 23:16:30
 * @desc [description]
 */

package com.rahilakhera.graph.traversal;

import java.util.Arrays;
import java.util.List;

import com.rahilakhera.graph.graph.Graph;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DFSTest {

    @Test
    public void testBasicCase() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);

        DFS dfs = new DFS(graph);
        List<Integer> result = dfs.dfs(1);

        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 3, 6);
        assertEquals(expected, result);
    }

    @Test
    public void testDisconnectedGraph() {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(4, 5);

        DFS dfs = new DFS(graph);
        List<Integer> result = dfs.dfs(1);

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

        DFS dfs = new DFS(graph);
        List<Integer> result = dfs.dfs(1);

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

        DFS dfs = new DFS(graph);
        List<Integer> result = dfs.dfs(1);

        List<Integer> expected = Arrays.asList(1, 2, 3, 5, 4);
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

        DFS dfs = new DFS(graph);
        List<Integer> result = dfs.dfs(1);

        List<Integer> expected = Arrays.asList(1, 2, 4, 7, 5, 8, 3, 6);
        assertEquals(expected, result);
    }

    @Test
    public void testEmptyGraph() {
        Graph graph = new Graph();

        DFS dfs = new DFS(graph);
        List<Integer> result = dfs.dfs(1);

        List<Integer> expected = Arrays.asList();
        assertEquals(expected, result);
    }
}