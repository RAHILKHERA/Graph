/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 02-Jan-2024 21:33:08
 * @desc [Disjoint Sets, Implementation of Disjoint Sets with find and union operation.]
 */

package com.rahilakhera.graph.graph;

import java.util.Arrays;

public class DisjointSet {

    private int[] parent;

    public DisjointSet(int members) {
        parent = new int[members + 1];

        /**
         * Each member is in its own set.
         * Each member is it's own parent.
         * A negative sign suggests that the index is a parent.
         * The negative value represents the number of nodes in the set.
         */
        Arrays.fill(parent, -1);
    }

    public int[] getParent() {
        return Arrays.copyOf(parent, parent.length);
    }

    public int getNumElements() {
        return parent.length;
    }

    public int getNumSets() {

        int count = 0;
        for (int i : parent) {
            if (i < 0) {
                count++;
            }
        }
        return count;
    }

    public boolean inSameSet(int x, int y) {
        return find(x) == find(y);
    }

    /**
     * 
     * @param x
     * @return returns the representative of the set.
     */

    public int find(int x) {

        if (parent[x] < 0) {
            return x;
        }

        int root = find(parent[x]); // Find the root recursively
        int current = x;
        while (current != root) {
            int next = parent[current]; // Store the next node on the path
            parent[current] = root; // Point the current node to the root
            current = next; // Move to the next node
        }

        return root;
    }

    /**
     * Performs union between two sets x and y.
     * 
     * @param x
     * @param y
     * @return If union is performed returns true else false;
     */
    public boolean union(int x, int y) {

        int parentX = find(x);
        int parentY = find(y);

        int rankX = parent[parentX];
        int rankY = parent[parentY];

        /**
         * Update parent to merge small tree into larger one.
         * As negative sign is used to represent parent.
         * Less than sign is used to reprsent the larger tree.
         */
        if (parentX != parentY) {
            if (rankX <= rankY) {
                parent[parentY] = parentX;
                parent[parentX] = rankX + rankY;
            } else if (rankY < rankX) {
                parent[parentX] = parentY;
                parent[parentY] = rankX + rankY;
            }
            return true;
        }

        return false;
    }

    public int getRank(int x) {
        return parent[find(x)];
    }

}
