/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 16-Dec-2023 00:31:49
 * @desc [Dijkstra's Algorithm. This implementation finds the shortest distance and the path from the provided source.]
 */

package com.rahilakhera.graph.singlesourceshortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.rahilakhera.graph.graph.Edge;
import com.rahilakhera.graph.graph.Graph;

public class Dijkstras {

    private Graph graph;
    private Set<Integer> visited;
    private Map<Integer, List<Integer>> paths;
    private PriorityQueue<Edge> queue;

    public Dijkstras(Graph graph) {
        this.graph = graph;
        this.queue = new PriorityQueue<>();
    }

    public Map<Integer, List<Integer>> getPaths() {
        return paths;
    }

    public Map<Integer, Integer> findShortestPath(int source) {

        Map<Integer, Integer> shortestPath = new HashMap<>();

        /**
         * If graph is empty, return an empty list.
         */
        if (graph.getNodes().size() == 0) {
            return shortestPath;
        }

        this.visited = new HashSet<>();
        /**
         * Intialzing the paths structure to store the paths.
         */

        this.paths = new HashMap<>();
        for (int node : graph.getNodes()) {
            paths.put(node, new ArrayList<>());
        }

        // for (int i = 0; i < graph.getTotalNodes(); i++) {
        // paths.put(i, new ArrayList<>());
        // }

        /**
         * Intialize the distance to source vertex to 0 and other vertexes to infinity.
         * Also add source to its paths.
         */

        shortestPath.put(source, 0);
        paths.computeIfAbsent(source, vertex -> new ArrayList<>()).add(source);

        queue.offer(new Edge(source, 0));
        visited.add(source);

        while (!queue.isEmpty()) {

            int node = queue.poll().getVertex();

            for (Edge edge : graph.getNeighbors(node)) {

                int currentWeight = shortestPath.getOrDefault(edge.getVertex(), Integer.MAX_VALUE);
                int newWeight = shortestPath.getOrDefault(node, Integer.MAX_VALUE) + edge.getWeight();

                if (currentWeight > newWeight) {

                    shortestPath.put(edge.getVertex(), newWeight);
                    queue.offer(new Edge(edge.getVertex(), newWeight));

                    /*
                     * Updating paths.
                     * First clear the exisiting path.
                     * Add, the path of the parent node.
                     * Add, the node itself to complete the path.
                     */
                    List<Integer> path = paths.get(edge.getVertex());
                    path.clear();
                    path.addAll(paths.get(node));
                    path.add(edge.getVertex());
                    paths.put(edge.getVertex(), path);
                }
            }
        }

        return shortestPath;
    }

}
