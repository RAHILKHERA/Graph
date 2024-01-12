/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 11-Jan-2024 17:39:30
 * @desc [Kahn's Algorithm to find the topological sort]
 */

package com.rahilakhera.graph.topologicalsort;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.rahilakhera.graph.graph.Edge;
import com.rahilakhera.graph.graph.Graph;

public class Kahn {

    private Graph graph;
    private List<Integer> topologicalSortOrder;
    private boolean cycleFound;

    /**
     * Constructor to initialize Kahn's Algorithm with a given graph
     * 
     * @param graph The directed graph for which topological sort is to be found
     */
    public Kahn(Graph graph) {
        this.graph = graph;
        this.topologicalSortOrder = new ArrayList<>();
        this.cycleFound = false;

        if (!graph.isDirected()) {
            throw new RuntimeException("Topological Sorting, Graph is undirected.");
        }
    }

    /**
     * Method to perform topological sort on the graph
     */
    public void topologicalSort() {

        int n = graph.getTotalNodes();
        int[] inOrderDegree = new int[n];

        /**
         * Step 1: Calculate Inorder Degree for all the vertices.
         */
        for (int node : graph.getNodes()) {
            List<Edge> edges = graph.getNeighbors(node);
            for (Edge edge : edges) {
                inOrderDegree[edge.getDestination()]++;
            }
        }

        /**
         * Step 2: Add all the nodes with zero indegree. These are the nodes, which
         * cannot be reached from other nodes.Any of these nodes can be the starting of
         * the order generated from the topological sort.
         */
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < inOrderDegree.length; i++) {
            if (inOrderDegree[i] == 0) {
                queue.offer(i);
            }
        }

        /**
         * Step 3 : Iterate through the queue, nodes received from the queue should be
         * added to the order
         * and the inorder degrees should be reduced. If any nodes degree is
         * reduced to 0, then add it to the queue.
         */

        int visitedNodes = 0;

        while (!queue.isEmpty()) {

            int node = queue.poll();
            topologicalSortOrder.add(node);
            visitedNodes++;
            List<Edge> edges = graph.getNeighbors(node);
            for (Edge edge : edges) {
                int destination = edge.getDestination();
                if (--inOrderDegree[destination] == 0) {
                    queue.add(destination);
                }
            }
        }

        /**
         * Step 4 : If all the nodes are not visited, then cycle is found.
         */
        cycleFound = !(visitedNodes == n);
    }

    /**
     * Method to check if a cycle is present in the graph
     * 
     * @return true if a cycle is present, false otherwise
     */
    public boolean isCyclePresent() {
        return cycleFound;
    }

    /**
     * Method to get the topological order of the graph
     * 
     * @return List containing the topological order of the graph
     * @throws RuntimeException if a cycle is detected
     */
    public List<Integer> getTopologicalOrder() throws RuntimeException {
        if (cycleFound) {
            throw new RuntimeException("Topological Sort, Cycle detected in the graph, cannot find the order.");
        } else {
            return topologicalSortOrder;
        }
    }

}
