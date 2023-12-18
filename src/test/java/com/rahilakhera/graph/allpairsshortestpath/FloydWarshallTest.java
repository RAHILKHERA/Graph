/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 19-Dec-2023 00:41:21
 * @desc [FloydWarshall Tests, all pair shortest path.]
 */

package com.rahilakhera.graph.allpairsshortestpath;

import org.junit.jupiter.api.Test;

import com.rahilakhera.graph.graph.Graph;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

public class FloydWarshallTest {

    @Test
    void basicCase() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 2);
        graph.addEdge(1, 2, 3);
        graph.addEdge(2, 0, 4);

        FloydWarshall floydWarshall = new FloydWarshall(graph);

        int[][] expected = {
                { 0, 2, 4 },
                { 2, 0, 3 },
                { 4, 3, 0 }
        };

        assertArrayEquals(expected, floydWarshall.getAllShortesPath());
    }

    // @Test
    // void graphWithNegativeWeight() {
    // Graph graph = new Graph();
    // graph.addEdge(0, 1, 1);
    // graph.addEdge(1, 2, -3);
    // graph.addEdge(2, 3, 2);
    // graph.addEdge(3, 0, -2);

    // FloydWarshall floydWarshall = new FloydWarshall(graph);

    // int[][] expected = {
    // { 0, 1, -2, 0 },
    // { 3, 0, -3, 2 },
    // { 1, 2, 0, 3 },
    // { -1, 0, 1, 0 }
    // };

    // assertArrayEquals(expected, floydWarshall.getAllShortesPath());
    // }

    // @Test
    // void disconnectedGraph() {
    // Graph graph = new Graph();

    // int[][] expected = {
    // { 0, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE },
    // { Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE },
    // { Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE },
    // { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0 }
    // };

    // assertArrayEquals(expected, FloydWarshall.run(graph));
    // }

    // @Test
    // void graphWithNegativeCycle() {
    // Graph graph = new Graph(3);
    // graph.addEdge(0, 1, -1);
    // graph.addEdge(1, 2, -1);
    // graph.addEdge(2, 0, -1);

    // assertThrows(RuntimeException.class, () -> FloydWarshall.run(graph));
    // }

    // @Test
    // void graphWithSelfLoops() {
    // Graph graph = new Graph(3);
    // graph.addEdge(0, 1, 2);
    // graph.addEdge(1, 1, 1); // Self-loop
    // graph.addEdge(2, 2, 3);

    // int[][] expected = {
    // { 0, 2, Integer.MAX_VALUE },
    // { Integer.MAX_VALUE, 1, Integer.MAX_VALUE },
    // { Integer.MAX_VALUE, Integer.MAX_VALUE, 3 }
    // };

    // assertArrayEquals(expected, FloydWarshall.run(graph));
    // }
}
