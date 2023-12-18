/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 19-Dec-2023 00:20:05
 * @desc [FloydWarshall Algo to find the all pairs of shortest path]
 */

package com.rahilakhera.graph.allpairsshortestpath;

import com.rahilakhera.graph.graph.Graph;

public class FloydWarshall {

    private Graph graph;

    public FloydWarshall(Graph graph) {
        this.graph = graph;
        this.graph.initAdjancencyMatrix();
    }

    public int[][] getAllShortesPath() {

        int N = graph.getTotalNodes();
        int[][] adjancencyMatrix;

        if (!graph.isAdjancenyMatrixInitialized()) {
            throw new RuntimeException("FloydWarshall, Adjancency Matrix is not intialized.");
        }

        adjancencyMatrix = graph.getAdjancencyMatrix();

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (adjancencyMatrix[i][k] != Integer.MAX_VALUE &&
                            adjancencyMatrix[k][j] != Integer.MAX_VALUE &&
                            adjancencyMatrix[i][j] > adjancencyMatrix[i][k] + adjancencyMatrix[k][j]) {
                        adjancencyMatrix[i][j] = adjancencyMatrix[i][k] + adjancencyMatrix[k][j];
                    }

                }
            }
        }

        for (int i = 0; i < N; i++)
            if (adjancencyMatrix[i][i] < 0)
                throw new RuntimeException("FlyodWarshall, Negative cycle found.");

        return adjancencyMatrix;
    }

}
