/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 23-Dec-2023 17:37:34
 * @modify date 23-Dec-2023 17:37:34
 * @desc [Johnson's algorithm to find the all shortest path. 
 * It takes the help of Bellman Ford and Dijsktra's Algorithms.
 * The time complexity of this algorithm is O(V^2 log V + VE).
 * This algrorithm doesn't work with undirected graphs with negative edges.
 * As a single undirected edge, results in two directional edges making a cycle.]
 */
package com.rahilakhera.graph.allpairsshortestpath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rahilakhera.graph.graph.Edge;
import com.rahilakhera.graph.graph.Graph;
import com.rahilakhera.graph.singlesourceshortestpath.BellmanFord;
import com.rahilakhera.graph.singlesourceshortestpath.Dijkstras;

public class Johnson {

    private Graph graph;
    private Map<Integer, Map<Integer, List<Integer>>> paths;

    Johnson(Graph graph) {
        this.graph = graph;
        this.paths = new HashMap<>();
    }

    /**
     * Calculate all shortest paths in the graph using Johnson's algorithm.
     *
     * @return A map containing all pairs shortest paths.
     * @throws RuntimeException If certain conditions are not met.
     */

    public Map<Integer, Map<Integer, Integer>> getAllShortestPath() throws RuntimeException {

        /**
         * Step 0 : Check wheter there is a negative weighted egdge.
         * If there are no negative edges we can skip steps 1, 2, 3 and 5.
         * Dirctly move to Step 4 : Dijisktra's Algorithm.
         * In absence of negative edges, This algorithm reduces to calling Dijisktra's
         * Algo for all the vertices.
         */

        boolean negativeEdgeFound = false;
        for (Edge edge : graph.getEdges()) {
            if (edge.getWeight() < 0) {
                negativeEdgeFound = true;
                if (!graph.isDirected()) {
                    throw new RuntimeException("Johnson, Undirected graph with negative edge implies negative cycle.");
                }
                break;
            }
        }

        Map<Integer, Integer> shortestPathFromDummy = null;

        if (negativeEdgeFound) {

            /**
             * Step 1 : Add dummy vertex (-1), to the graph.
             * Add the edges from the dummy vertex to all the vertices with weight zero.
             * The incoming degree of the dummy vertex will be 0
             */

            for (Integer vertex : graph.getNodes()) {
                graph.addEdge(-1, vertex, 0);
            }

            /**
             * Step 2: Calculate shortest distance from the dummy vertex to all the
             * vertices.
             * Using Bellman-Ford Algorithm.
             */

            BellmanFord bellmanFord = new BellmanFord(graph);

            shortestPathFromDummy = bellmanFord.findShortestPath(-1);

            if (shortestPathFromDummy == null) {
                throw new RuntimeException("Jhonson, Negative cycle found.");
            }

            /**
             * Step 3 : Rewrite the weights of all the edges using the following formula.
             * w'(u,v) = w(u,v) + h(u) - h(v);
             */

            for (Edge edge : graph.getEdges()) {

                int weight = edge.getWeight();
                int sourceWeight = shortestPathFromDummy.get(edge.getVertex());
                int destinationWeight = shortestPathFromDummy.get(edge.getDestination());
                edge.setWeight(weight + sourceWeight - destinationWeight);

            }
        }

        /**
         * Step 4 : Calculate shortest Path for the vertices using Dijikstra's
         * Algorithm.
         */
        Dijkstras dijkstras = new Dijkstras(graph);
        Map<Integer, Map<Integer, Integer>> allShortestPath = new HashMap<>();

        for (Integer vertex : graph.getNodes()) {
            allShortestPath.put(vertex, dijkstras.findShortestPath(vertex));
            paths.put(vertex, dijkstras.getPaths());
        }

        if (negativeEdgeFound) {
            /**
             * Step 5 : If the weights were recalculated for negative edges.
             * Readjust the weights using following formula :
             * w(u,v) = w'(u,v) - h[u] + h[v];
             */
            for (Map.Entry<Integer, Map<Integer, Integer>> entry : allShortestPath.entrySet()) {

                int source = entry.getKey();
                Map<Integer, Integer> destinationMap = entry.getValue();

                for (Map.Entry<Integer, Integer> subEntry : destinationMap.entrySet()) {

                    int destination = subEntry.getKey();
                    int weight = subEntry.getValue();
                    destinationMap.put(destination,
                            weight - shortestPathFromDummy.get(source) + shortestPathFromDummy.get(destination));
                }
            }
        }

        return allShortestPath;
    }

    /**
     * Get the paths calculated by the last invocation of getAllShortestPath.
     *
     * @return A map containing paths for each vertex.
     */
    public Map<Integer, Map<Integer, List<Integer>>> getPaths() {
        return paths;
    }

}
