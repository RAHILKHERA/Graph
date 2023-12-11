package com.rahilakhera.graph;

import java.util.List;

import com.rahilakhera.graph.graph.Graph;
import com.rahilakhera.graph.traversal.DFS;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
       
        DFS dfsTraversal = new DFS(graph);

        List<Integer> nodes = dfsTraversal.dfs(1);
        System.out.println(nodes.toString());
    }
}
