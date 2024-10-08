package DijkstraBasedLongestSimlePath;

import java.util.LinkedList;

import GraphGenerator.Graph;
import GraphGenerator.LargestConnectedComponent;

public class TestLCC {
	public static void main(String[] args) {
        String filePath = "./EDGES/graph_300Nodes.EDGES";
  
        Graph graph = new Graph();
        // Create adjacency list to store the graph
        LinkedList<Integer>[] adjacencyList = graph.readGraph(filePath, true);

        LargestConnectedComponent lcc = new LargestConnectedComponent();
        LinkedList<Integer> largestComponent = lcc.largestConnectedComponent(adjacencyList);

        LongestSimplePath longestSimplePath = new LongestSimplePath();
        int longestSimplePathLength = longestSimplePath.dijkstraLongestSimplePath(adjacencyList, largestComponent);

        System.out.println("longest path by Dijkstra: " + longestSimplePathLength);
    }
}
