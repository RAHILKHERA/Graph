/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 19-Dec-2023 00:41:21
 * @desc [FloydWarshall Tests, all pair shortest path.]
 */

package com.rahilakhera.graph.allpairsshortestpath;

import org.junit.jupiter.api.Test;

import com.rahilakhera.graph.graph.Graph;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

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

    @Test
    void graphWithNegativeWeight() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, -3);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 0, -2);

        FloydWarshall floydWarshall = new FloydWarshall(graph);

        assertThrows(RuntimeException.class, () -> floydWarshall.getAllShortesPath());
    }

    @Test
    void disconnectedGraph() {
        Graph graph = new Graph(true);
        graph.addEdge(1, 0, 2);
        graph.addEdge(3, 2, 4);

        int[][] expected = {
                { 0, 2, Integer.MAX_VALUE, Integer.MAX_VALUE },
                { Integer.MAX_VALUE, 0, Integer.MAX_VALUE, Integer.MAX_VALUE },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 4 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0 }
        };

        FloydWarshall floydWarshall = new FloydWarshall(graph);
        assertArrayEquals(expected, floydWarshall.getAllShortesPath());
    }

    @Test
    void graphWithNegativeCycle() {
        Graph graph = new Graph();
        graph.addEdge(0, 1, -1);
        graph.addEdge(1, 2, -1);
        graph.addEdge(2, 0, -1);

        FloydWarshall floydWarshall = new FloydWarshall(graph);
        assertThrows(RuntimeException.class, () -> floydWarshall.getAllShortesPath());
    }

    @Test
    void basicDirectedGraphWithPaths() {

        Graph graph = new Graph(true);
        graph.addEdge(1, 0, 2);
        graph.addEdge(2, 0, 6);
        graph.addEdge(3, 1, 1);
        graph.addEdge(3, 2, 4);
        graph.addEdge(2, 1, 3);

        FloydWarshall floydWarshall = new FloydWarshall(graph);

        int[][] expected = {
                { 0, 2, 5, 3 },
                { Integer.MAX_VALUE, 0, 3, 1 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 4 },
                { Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0 }
        };

        assertAll("Check distances and paths",
                () -> assertArrayEquals(expected, floydWarshall.getAllShortesPath()),
                () -> assertEquals(Set.of(0), floydWarshall.getPaths().get(0).get(0)),
                () -> assertEquals(Set.of(0, 1), floydWarshall.getPaths().get(0).get(1)),
                () -> assertEquals(Set.of(0, 1, 2), floydWarshall.getPaths().get(0).get(2)),
                () -> assertEquals(Set.of(0, 1, 3), floydWarshall.getPaths().get(0).get(3)),
                () -> assertEquals(Set.of(), floydWarshall.getPaths().get(1).get(0)),
                () -> assertEquals(Set.of(1), floydWarshall.getPaths().get(1).get(1)),
                () -> assertEquals(Set.of(1, 2), floydWarshall.getPaths().get(1).get(2)),
                () -> assertEquals(Set.of(1, 3), floydWarshall.getPaths().get(1).get(3)),
                () -> assertEquals(Set.of(), floydWarshall.getPaths().get(2).get(0)),
                () -> assertEquals(Set.of(), floydWarshall.getPaths().get(2).get(1)),
                () -> assertEquals(Set.of(2), floydWarshall.getPaths().get(2).get(2)),
                () -> assertEquals(Set.of(2, 3), floydWarshall.getPaths().get(2).get(3)),
                () -> assertEquals(Set.of(), floydWarshall.getPaths().get(3).get(0)),
                () -> assertEquals(Set.of(), floydWarshall.getPaths().get(3).get(1)),
                () -> assertEquals(Set.of(), floydWarshall.getPaths().get(3).get(2)),
                () -> assertEquals(Set.of(3), floydWarshall.getPaths().get(3).get(3)));
    }

}
