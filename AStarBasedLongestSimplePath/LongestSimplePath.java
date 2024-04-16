package AStarBasedLongestSimplePath;

import java.util.LinkedList;

import GraphGenerator.Vertex;

public class LongestSimplePath {
    public int astartLongestSimplePath(LinkedList<Vertex>[] adjacencyList) {

        AStarLongestSimplePath astar = new AStarLongestSimplePath();

        int maxPathLength = 0;

        Vertex source = new Vertex(187, 0.48, 0.57);
        Vertex destination = new Vertex(218, 0.10 ,0.61);
        maxPathLength = astar.astar(adjacencyList, source, destination);

        System.out.println("longest path by Astar: " + maxPathLength);
        
        return 0;
    }
}
