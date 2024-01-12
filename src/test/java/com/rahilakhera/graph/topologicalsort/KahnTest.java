/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 11-Jan-2024 19:00:23
 * @desc [description]
 */

package com.rahilakhera.graph.topologicalsort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.rahilakhera.graph.graph.Graph;

public class KahnTest {
    @Test
    void testTopologicalSort() {
        // Test with a simple directed acyclic graph
        Graph dag = new Graph(true);
        dag.addEdge(0, 1);
        dag.addEdge(0, 2);
        dag.addEdge(1, 3);
        dag.addEdge(2, 3);

        Kahn kahn = new Kahn(dag);
        kahn.topologicalSort();

        List<Integer> expectedOrder = Arrays.asList(0, 1, 2, 3);
        assertEquals(expectedOrder, kahn.getTopologicalOrder());
        assertFalse(kahn.isCyclePresent());
    }

    @Test
    void testTopologicalSortWithCycle() {
        // Test with a graph containing a cycle
        Graph graphWithCycle = new Graph(true);
        graphWithCycle.addEdge(0, 1);
        graphWithCycle.addEdge(1, 2);
        graphWithCycle.addEdge(2, 0);

        Kahn kahnWithCycle = new Kahn(graphWithCycle);
        kahnWithCycle.topologicalSort();

        assertThrows(RuntimeException.class, () -> kahnWithCycle.getTopologicalOrder());
        assertTrue(kahnWithCycle.isCyclePresent());
    }

    @Test
    void testTopologicalSortEmptyGraph() {
        // Test with an empty graph
        Graph emptyGraph = new Graph(true);

        Kahn kahnEmptyGraph = new Kahn(emptyGraph);
        kahnEmptyGraph.topologicalSort();

        assertTrue(kahnEmptyGraph.getTopologicalOrder().isEmpty());
        assertFalse(kahnEmptyGraph.isCyclePresent());
    }

    @Test
    void testTopologicalSortSingleNode() {
        // Test with a graph containing a single node
        Graph singleNodeGraph = new Graph(true);
        singleNodeGraph.addVertex(0);

        Kahn kahnSingleNode = new Kahn(singleNodeGraph);
        kahnSingleNode.topologicalSort();

        List<Integer> expectedOrder = Arrays.asList(0);
        assertEquals(expectedOrder, kahnSingleNode.getTopologicalOrder());
        assertFalse(kahnSingleNode.isCyclePresent());
    }

    @Test
    void testTopologicalSortDisconnectedGraph() {
        // Test with a disconnected graph
        Graph disconnectedGraph = new Graph(true);
        disconnectedGraph.addEdge(0, 1);
        disconnectedGraph.addEdge(2, 3);

        Kahn kahnDisconnected = new Kahn(disconnectedGraph);
        kahnDisconnected.topologicalSort();

        List<Integer> expectedOrder = Arrays.asList(0, 2, 1, 3);
        assertEquals(expectedOrder, kahnDisconnected.getTopologicalOrder());
        assertFalse(kahnDisconnected.isCyclePresent());
    }
}
