/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 07-Jan-2024 13:38:56
 * @desc [Prim's Algorithm Implementation for finding Minimum Spanning Tree.]
 */

package com.rahilakhera.graph.minimumspanningtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import com.rahilakhera.graph.graph.Edge;
import com.rahilakhera.graph.graph.Graph;

/**
 * This class implements the Prim's Algorithm for the Minimum Spanning Tree.
 * This implementation assumes that the Graph's vertex are represented with
 * integers and it has vertex with value 0. Vertex 0 will be used as the
 * arbitary vertex for the algorithm.
 * The graph should be undirected weighted graph.
 */

public class Prims {

    private Graph graph;
    private Map<Integer, List<Edge>> vertexEdgeMap;
    private Set<Integer> vertexSet;
    private List<Edge> minimumSpanningTree;
    private int minimumSpanningTreeWeight;

    public Prims(Graph graph) {

        this.graph = graph;
        this.vertexEdgeMap = new HashMap<>();
        this.vertexSet = new HashSet<>();
        this.minimumSpanningTree = new ArrayList<>();
        init();
    }

    /**
     * This will init a graph of vertex and all its corresponding edges. So, when
     * the
     * vertex is added to MST set, all the related edges can be fetched.
     */
    private void init() {

        for (Edge edge : graph.getEdges()) {
            vertexEdgeMap.computeIfAbsent(edge.getVertex(), node -> new ArrayList<>()).add(edge);
            vertexEdgeMap.computeIfAbsent(edge.getDestination(), node -> new ArrayList<>()).add(edge);
        }
    }

    /**
     * This will generate the minimum spanning tree, for the given unidrected
     * weighted graph.
     */
    public void generateMinimumSpanningTree() {

        vertexSet.add(0);

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.addAll(vertexEdgeMap.get(0));

        while (true) {

            /**
             * vertexSet.equals(graph.getNodes()) This means all the nodes are visited.
             * If it is a disconnected graph, all the nodes cannot be visited, hence
             * terminate it when no more edges are available to add.
             */
            if (vertexSet.equals(graph.getNodes()) || queue.isEmpty()) {
                break;
            }

            Edge edge = queue.poll();
            int source = edge.getVertex();
            int destination = edge.getDestination();

            if (!vertexSet.contains(destination)) {
                vertexSet.add(destination);
                queue.addAll(vertexEdgeMap.get(destination));
                minimumSpanningTree.add(edge);
                minimumSpanningTreeWeight += edge.getWeight();
            } else if (!vertexSet.contains(source)) {
                vertexSet.add(source);
                queue.addAll(vertexEdgeMap.get(source));
                minimumSpanningTree.add(edge);
                minimumSpanningTreeWeight += edge.getWeight();
            }
        }

    }

    public List<Edge> getMinimumSpanningTree() {
        return minimumSpanningTree;
    }

    public int getMinimumSpanningTreeWeight() {
        return minimumSpanningTreeWeight;
    }

}
