/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 16-Dec-2023 00:31:49
 * @desc [Dijkstra's Algorithm]
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
        this.visited = new HashSet<>();
        this.paths = new HashMap<>();
        this.queue = new PriorityQueue<>();
    }

    public Map<Integer, List<Integer>> getPaths() {
        return paths;
    }

    public Map<Integer, Integer> findShortestPath(int source) {

        Map<Integer, Integer> shortestPath = new HashMap<>();

        if (graph.getNodes().size() == 0) {
            return shortestPath;
        }

        for (int i = 0; i < graph.getTotalNodes(); i++) {
            paths.put(i, new ArrayList<>());
        }
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
