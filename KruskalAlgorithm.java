//Qn3b
import java.util.*;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

public class KruskalAlgorithm {

    // Function to find the root of the set that a vertex belongs to
    static int find(int[] parent, int vertex) {
        if (parent[vertex] == vertex) {
            return vertex;
        } else {
            return find(parent, parent[vertex]);
        }
    }

    // Function to perform union operation of two sets
    static void union(int[] parent, int x, int y) {
        int xRoot = find(parent, x);
        int yRoot = find(parent, y);
        if (xRoot != yRoot) {
            parent[yRoot] = xRoot;
        }
    }

    // Function to find Minimum Spanning Tree using Kruskal's algorithm
    public static List<Edge> findMinimumSpanningTree(Edge[] graph, int numberOfVertices) {
        PriorityQueue<Edge> minHeap = new PriorityQueue<>();
        int[] parent = new int[numberOfVertices];

        // Initialize parent array for Union-Find operations
        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }

        // Add all edges to the priority queue
        for (Edge edge : graph) {
            minHeap.add(edge);
        }

        List<Edge> minimumSpanningTree = new ArrayList<>();

        // Process edges in ascending order of weights
        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            int x = find(parent, edge.source);
            int y = find(parent, edge.destination);

            // Add edge to MST if it doesn't form a cycle
            if (x != y) {
                minimumSpanningTree.add(edge);
                union(parent, x, y);
            }
        }

        return minimumSpanningTree;
    }

    public static void main(String[] args) {
        int numberOfVertices = 4;
        Edge[] graph = new Edge[4];
        graph[0] = new Edge(0, 1, 10);
        graph[1] = new Edge(0, 2, 6);
        graph[2] = new Edge(0, 3, 5);
        graph[3] = new Edge(1, 2, 4);

        List<Edge> minimumSpanningTree = findMinimumSpanningTree(graph, numberOfVertices);

        System.out.println("Minimum Spanning Tree Edges:");
        for (Edge edge : minimumSpanningTree) {
            System.out.println(edge.source + " -- " + edge.destination + " == " + edge.weight);
        }
    }
}
