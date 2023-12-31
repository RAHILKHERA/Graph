/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 17-Dec-2023 00:35:16
 * @desc [Bellman Ford Algorithm. It provides the shortest distance with shortest paths.]
 */

package com.rahilakhera.graph.singlesourceshortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rahilakhera.graph.graph.Edge;
import com.rahilakhera.graph.graph.Graph;

public class BellmanFord {

    private Graph graph;
    private Map<Integer, List<Integer>> paths;

    public BellmanFord(Graph graph) {

        this.graph = graph;
        this.paths = new HashMap<>();
    }

    public Map<Integer, List<Integer>> getPaths() {
        return paths;
    }

    public Map<Integer, Integer> findShortestPath(int startingVertex) {

        Map<Integer, Integer> shortestPath = new HashMap<>();

        /**
         * If graph is empty, return an empty list.
         */
        if (graph.getNodes().size() == 0) {
            return shortestPath;
        }

        /**
         * Intialzing the paths structure to store the paths.
         */

        this.paths = new HashMap<>();
        for (int node : graph.getNodes()) {
            paths.put(node, new ArrayList<>());
        }

        /**
         * Intialize the distance to source vertex to 0 and other vertexes to infinity.
         * Also add source to its paths.
         */
        shortestPath.put(startingVertex, 0);
        paths.computeIfAbsent(startingVertex, vertex -> new ArrayList<>()).add(startingVertex);

        /**
         * Relax through all edges for number of vertex -1 times.
         */

        for (int i = 0; i < graph.getTotalNodes() - 1; i++) {
            for (Edge edge : graph.getEdges()) {

                int source = edge.getVertex();
                int destination = edge.getDestination();
                int weight = edge.getWeight();
                int sourceWeight = shortestPath.getOrDefault(source, Integer.MAX_VALUE);
                int destinationWeight = shortestPath.getOrDefault(destination, Integer.MAX_VALUE);

                if (sourceWeight != Integer.MAX_VALUE && sourceWeight + weight < destinationWeight) {
                    destinationWeight = sourceWeight + weight;
                    shortestPath.put(destination, destinationWeight);

                    /*
                     * Updating paths.
                     * First clear the exisiting path.
                     * Add, the path of the parent node.
                     * Add, the node itself to complete the path.
                     */
                    List<Integer> path = paths.get(destination);
                    path.clear();
                    path.addAll(paths.get(source));
                    path.add(destination);
                    paths.put(destination, path);
                }
                // Consider the reverse direction as well for undirected graph
                if (!graph.isDirected()) {

                    if (destinationWeight != Integer.MAX_VALUE && destinationWeight + weight < sourceWeight) {
                        sourceWeight = destinationWeight + weight;
                        shortestPath.put(source, sourceWeight);

                        List<Integer> path = paths.get(source);
                        path.clear();
                        path.addAll(paths.get(destination));
                        path.add(source);
                        paths.put(source, path);
                    }

                }

            }
        }

        for (Edge edge : graph.getEdges()) {

            int source = edge.getVertex();
            int destination = edge.getDestination();
            int weight = edge.getWeight();
            int sourceWeight = shortestPath.getOrDefault(source, Integer.MAX_VALUE);
            int destinationWeight = shortestPath.getOrDefault(destination, Integer.MAX_VALUE);

            // If it is a negative cycle, return null;
            if (sourceWeight != Integer.MAX_VALUE && sourceWeight + weight < destinationWeight) {
                return null;
            }

            // For directed graph check reverse edge too.
            if (!graph.isDirected() && destinationWeight != Integer.MAX_VALUE
                    && destinationWeight + weight < sourceWeight) {
                return null;
            }

        }

        return shortestPath;
    }
}
