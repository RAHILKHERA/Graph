/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 13-Dec-2023 15:31:30
 * @desc Breath First Search Algorithm implementation.
 */
package com.rahilakhera.graph.traversal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.List;
import java.util.Queue;

import com.rahilakhera.graph.graph.Edge;
import com.rahilakhera.graph.graph.Graph;

public class BFS {

    private Graph graph;
    private Set<Integer> visited;
    private Queue<Integer> queue;

    public BFS(Graph graph) {
        this.graph = graph;
        visited = new HashSet<>();
        queue = new LinkedList<>();
    }

    public List<Integer> traversal(int source) {
        
        List<Integer> result = new ArrayList<>();
        /**
         * Empty graph
         */
        if (graph.getNodes().size() == 0) {
            return result;
        }

        queue.offer(source);

        while (!queue.isEmpty()) {

            int node = queue.poll();

            if (!visited.contains(node)) {
                result.add(node);    
            }
            visited.add(node);
            List<Edge> neighbors = graph.getNegihbors(node);
            for (Edge neighbor : neighbors) {
                
                if (!visited.contains(neighbor.getVertex())) {
                    queue.offer(neighbor.getVertex());
                }
            }
        }

        return result;
    }
}
