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

    public Edge(int vertex, int weight, int destination) {
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

}