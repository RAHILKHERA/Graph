/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 19-Dec-2023 00:20:05
 * @desc [FloydWarshall Algo to find the all pairs of shortest path]
 */

package com.rahilakhera.graph.allpairsshortestpath;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.HashSet;

import com.rahilakhera.graph.graph.Graph;

public class FloydWarshall {

    private Graph graph;
    private Map<Integer, Map<Integer, Set<Integer>>> paths;

    public FloydWarshall(Graph graph) {
        this.graph = graph;
        this.graph.initAdjancencyMatrix();
        paths = new HashMap<>();
        intiPaths();
    }

    public int[][] getAllShortesPath() {

        int n = graph.getTotalNodes();
        int[][] adjacencyMatrix;

        if (!graph.isAdjancenyMatrixInitialized()) {
            throw new RuntimeException("FloydWarshall, Adjancency Matrix is not intialized.");
        }

        adjacencyMatrix = graph.getAdjancencyMatrix();

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adjacencyMatrix[i][k] != Integer.MAX_VALUE &&
                            adjacencyMatrix[k][j] != Integer.MAX_VALUE &&
                            adjacencyMatrix[i][j] > adjacencyMatrix[i][k] + adjacencyMatrix[k][j]) {
                        adjacencyMatrix[i][j] = adjacencyMatrix[i][k] + adjacencyMatrix[k][j];

                        /**
                         * Update paths.
                         */
                        Set<Integer> path = new HashSet<>();
                        path.add(i);
                        path.addAll(paths.get(i).getOrDefault(k, new HashSet<>()));
                        path.add(k);
                        path.add(j);
                        paths.get(i).put(j, path);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++)
            if (adjacencyMatrix[i][i] < 0)
                throw new RuntimeException("FlyodWarshall, Negative cycle found.");

        return adjacencyMatrix;
    }

    private void intiPaths() {

        int N = graph.getTotalNodes();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                paths.computeIfAbsent(i, key -> new HashMap<>()).computeIfAbsent(j, key -> new HashSet<>());
                if (graph.getAdjancencyMatrix()[i][j] != Integer.MAX_VALUE) {
                    Set<Integer> pair = new HashSet<>();
                    pair.add(i);
                    pair.add(j);
                    paths.get(i).put(j, pair);
                }

                if (!graph.isDirected()) {
                    paths.computeIfAbsent(j, key -> new HashMap<>()).computeIfAbsent(i, key -> new HashSet<>());
                    if (graph.getAdjancencyMatrix()[j][i] != Integer.MAX_VALUE) {
                        Set<Integer> pair = new HashSet<>();
                        pair.add(j);
                        pair.add(i);
                        paths.get(i).put(j, pair);
                    }
                }

            }
        }
    }

    public Map<Integer, Map<Integer, Set<Integer>>> getPaths() {
        return paths;
    }

}
