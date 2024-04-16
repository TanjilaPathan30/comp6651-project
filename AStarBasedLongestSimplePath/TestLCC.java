package AStarBasedLongestSimplePath;

import java.util.LinkedList;

import GraphGenerator.Graph;
import GraphGenerator.Vertex;

public class TestLCC {
    public static void main(String[] args) {
        String filePath = "./EDGES/graph_300Nodes.EDGES";
        Graph graph = new Graph();
        // Create adjacency list to store the graph
        LinkedList<Vertex>[] adjacencyList = graph.readGeometricGraphWithCoordinates(filePath);

        LongestSimplePath longestSimplePath = new LongestSimplePath();
        int longestSimplePathLength = longestSimplePath.astartLongestSimplePath(adjacencyList);

        //System.out.println("longest path by AStar: " + longestSimplePathLength);
    }
}
