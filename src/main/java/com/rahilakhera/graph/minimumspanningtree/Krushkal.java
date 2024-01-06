/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 06-Jan-2024 22:33:27
 * @desc [Krushkal's algorithm for Minimum Spanning Tree.]
 */

package com.rahilakhera.graph.minimumspanningtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rahilakhera.graph.graph.DisjointSet;
import com.rahilakhera.graph.graph.Edge;
import com.rahilakhera.graph.graph.Graph;

public class Krushkal {

    private Graph graph;
    private List<Edge> treeEdgeList;
    private int minimumWeight;

    public Krushkal(Graph graph) {
        this.graph = graph;
        this.treeEdgeList = new ArrayList<>();
    }

    public void generateMinimumSpannigTree() {

        int n = graph.getTotalNodes();

        /**
         * Step 1 : Sort the edges as per the weight.
         */
        List<Edge> edges = graph.getEdges();
        Collections.sort(edges);

        DisjointSet set = new DisjointSet(n);

        for (Edge edge : edges) {

            int source = edge.getVertex();
            int destination = edge.getDestination();

            /**
             * If both the vertices are in the same set, it represents that adding edge to
             * the current set will create cycle.
             * 
             * If both are in different set, we can safely add the edge. Hence, perform
             * union operation.
             */
            if (!set.inSameSet(source, destination)) {
                if (set.union(source, destination)) {
                    treeEdgeList.add(edge);
                    minimumWeight += edge.getWeight();
                }
            }
        }
    }

    public int getMinimumWeight() {
        return minimumWeight;
    }

    public List<Edge> getTreeEdges() {
        return treeEdgeList;
    }
}
