/**
 * @author Rahil Khera
*/
package com.rahilakhera.graph.traversal;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.rahilakhera.graph.graph.Graph;

public class DFS {
    private Graph graph;
    private Set<Integer> visited; 


    public DFS(Graph graph) {
        this.graph = graph;
        this.visited = new HashSet<>();
    }


    public List<Integer> dfs(int source) {
        List <Integer> result = new LinkedList<>();

        if (graph.getNodes().size() == 0) {
            return result;
        }
        
        traversal(source, result);
        return result; 
    }

    private void traversal(int source, List<Integer> result) {

        visited.add(source);

        List<Integer> neighbors = graph.getNegihbors(source);
        result.add(source);

        for (Integer neighbor : neighbors) {

            if (!visited.contains(neighbor)) {
                traversal(neighbor, result);
            }
            
        }
    }
}
