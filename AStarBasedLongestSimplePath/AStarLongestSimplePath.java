package AStarBasedLongestSimplePath;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

import GraphGenerator.Vertex;

public class AStarLongestSimplePath {
    private class GeometricVertex {
        int id;
        double x;
        double y;
        double d;
        double h;
        double f;

        public GeometricVertex(int id, double x, double y) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.d = Double.NEGATIVE_INFINITY;
            this.h = 0;
            this.f = Double.NEGATIVE_INFINITY;
        }
    }

    public static double euclideanHeuristic(GeometricVertex vertex, GeometricVertex destination) {
        return Math.sqrt(Math.pow(destination.x - vertex.x, 2) + Math.pow(destination.y - vertex.y, 2));
    }

    public int astar(LinkedList<Vertex>[] adjacencyList, Vertex src, Vertex dest) {
        int numVertices = adjacencyList.length;
        int[] dist = new int[numVertices];
        Arrays.fill(dist, Integer.MIN_VALUE); // Initialize distances to negative infinity
        dist[src.getId()] = 0;
        GeometricVertex source = new GeometricVertex(src.getId(), src.getX(), src.getY());
        GeometricVertex destination = new GeometricVertex(dest.getId(), dest.getX(), dest.getY());
        source.d = 0;
        source.h = euclideanHeuristic(source, destination);
        source.f = source.h + source.d;

        boolean[] visited = new boolean[numVertices];
        int longestPath = 0;

        Set<GeometricVertex> S = new HashSet<>();
        PriorityQueue<GeometricVertex> maxHeap = new PriorityQueue<>((v1, v2) -> Double.compare(v2.f, v1.f));
        maxHeap.add(destination);
        maxHeap.add(source);

        while (!maxHeap.isEmpty()) {
            GeometricVertex currentVertex = maxHeap.poll();
            S.add(currentVertex);
            int uId = currentVertex.id;

            if (currentVertex == destination) {
                break;
            }

            if (visited[uId])
                continue; // Skip visited vertices
            visited[uId] = true;

            for (int i = 0; i < adjacencyList[currentVertex.id].size(); i++) {
                Vertex neighbor = adjacencyList[currentVertex.id].get(i);
                GeometricVertex geometricNeighbor = new GeometricVertex(neighbor.getId(), neighbor.getX(),
                        neighbor.getY());
                geometricNeighbor.d = 0;
                geometricNeighbor.h = euclideanHeuristic(geometricNeighbor, destination);

                if (dist[uId] + 1 + geometricNeighbor.h > dist[geometricNeighbor.id]) {
                    dist[neighbor.getId()] = dist[uId] + 1;
                    geometricNeighbor.f = dist[uId] + 1 + geometricNeighbor.h;

                    if (dist[neighbor.getId()] > longestPath) {
                        longestPath = dist[geometricNeighbor.id];
                    }
                    if (S.contains(geometricNeighbor)) {
                        S.remove(geometricNeighbor);
                        maxHeap.add(geometricNeighbor);
                    } else {
                        geometricNeighbor.d = dist[uId] + 1;
                        geometricNeighbor.f = geometricNeighbor.d + geometricNeighbor.h;
                        maxHeap.add(geometricNeighbor);
                    }
                }
            }
        }
        return longestPath;
    }
}
