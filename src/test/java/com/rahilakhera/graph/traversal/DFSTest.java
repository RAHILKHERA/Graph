package com.rahilakhera.graph.traversal;

import java.util.Arrays;
import java.util.List;

import com.rahilakhera.graph.graph.Graph;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class DFSTest  extends TestCase {
      
    private Graph graph;
    private DFS dfsTraversal;

    public  DFSTest() {
        super ("DFS Test");
        graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(3, 6);
        dfsTraversal = new DFS(graph);
    }

    public static Test suite()
    {
        return new TestSuite( DFSTest.class );
    }

    public void testBasicDFS()
    {
        List<Integer> nodes = dfsTraversal.dfs(1);
        List<Integer> expected =  Arrays.asList(1,2,4,5,3,6);
        assertEquals(expected, nodes);
    }

}
