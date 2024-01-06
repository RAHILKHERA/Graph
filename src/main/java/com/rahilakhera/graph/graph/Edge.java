/**
 * @author Rahil Khera
 * @email rahilakhera@gmail.com
 * @create date 16-Dec-2023 00:09:58
 * @desc [Defination for Edge class it contains the destination edge and the weight or 
 * cost of the edge]
 */

package com.rahilakhera.graph.graph;

public class Edge implements Comparable<Edge> {

    private int vertex;
    private int weight;
    private int destination;

    public Edge(int vertex, int weight) {

        this.vertex = vertex;
        this.weight = weight;
    }

    public Edge(int vertex, int destination, int weight) {
        this.vertex = vertex;
        this.weight = weight;
        this.destination = destination;
    }

    public int getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getDestination() {
        return destination;
    }

    @Override
    public int compareTo(Edge o) {
        return Integer.compare(weight, o.getWeight());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[ ")
                .append(vertex)
                .append(" : ")
                .append(destination)
                .append(" : ")
                .append(weight)
                .append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + vertex;
        result = prime * result + weight;
        result = prime * result + destination;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Edge other = (Edge) obj;
        if (vertex != other.vertex)
            return false;
        if (weight != other.weight)
            return false;
        if (destination != other.destination)
            return false;
        return true;
    }

}