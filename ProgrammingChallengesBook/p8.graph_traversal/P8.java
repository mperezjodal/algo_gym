import java.util.*;
import java.io.*;

public class P8 {
    public static void main(java.lang.String[] args)
            throws IOException, FileNotFoundException, ClassCastException {

        FileReader fr = new FileReader("./input.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./output.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        int scenario = 1;
        boolean done = false;
        while (!done) {
            String lineaEntera;

            lineaEntera = br.readLine();
            StringTokenizer linea = new StringTokenizer(lineaEntera);
            int numberOfNodes = Integer.parseInt(linea.nextToken());
            int numberOfEdges = Integer.parseInt(linea.nextToken());

            if (numberOfNodes == 0) {
                done = true;
                break;
            }

            ArrayList<Edge> edges = new ArrayList<Edge>();
            for (int k = 0; k < numberOfEdges; k++) {
                lineaEntera = br.readLine();
                linea = new StringTokenizer(lineaEntera);
                int n1 = Integer.parseInt(linea.nextToken());
                int n2 = Integer.parseInt(linea.nextToken());
                int cost = Integer.parseInt(linea.nextToken());

                edges.add(new Edge(n1, n2, cost));
            }
            Graph graph = new Graph(edges, numberOfNodes);

            lineaEntera = br.readLine();
            linea = new StringTokenizer(lineaEntera);
            int n1 = Integer.parseInt(linea.nextToken());
            int n2 = Integer.parseInt(linea.nextToken());
            int numPass = Integer.parseInt(linea.nextToken());

            int maxPassTrip = graph.DFS(n1, n2);

            pp.println("Scenario #" + scenario);
            scenario++;
            pp.println("Minimum Number of Trips = " + (int)Math.ceil((double)numPass/maxPassTrip));
        }
        pp.close();
    }

}

class Edge {
    int src, dest, weight;

    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    static class Node {
        int value, weight;

        Node(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    };

    List<List<Node>> adj_list = new ArrayList<>();
    int numberOfNodes;
    boolean visited[];

    public Graph(List<Edge> edges, int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        this.visited = new boolean[numberOfNodes+1];
        for (int i = 0; i < edges.size(); i++)
            adj_list.add(i, new ArrayList<>());

        for (Edge e : edges) {
            adj_list.get(e.src).add(new Node(e.dest, e.weight));
        }
    }

    // DFS algorithm
    int DFS(int vertex, int end) {
        if (vertex == end) {
            return Integer.MAX_VALUE;
        }
        this.visited[vertex] = true;
        int maxPass = Integer.MIN_VALUE;

        Iterator<Node> ite = adj_list.get(vertex).iterator();
        while (ite.hasNext()) {
            Node adj = ite.next();
            if (!visited[adj.value]) {
                maxPass = Math.max(maxPass, Math.min(adj.weight, DFS(adj.value, end)));
            }
        }
        return maxPass;
    }

    public static void printGraph(Graph graph) {
        int src_vertex = 0;
        int list_size = graph.adj_list.size();

        System.out.println("The contents of the graph:");
        while (src_vertex < list_size) {
            for (Node edge : graph.adj_list.get(src_vertex)) {
                System.out.print("Vertex:" + src_vertex + " ==> " + edge.value +
                        " (" + edge.weight + ")\t");
            }

            System.out.println();
            src_vertex++;
        }
    }
}

// Graph: https://www.softwaretestinghelp.com/java-graph-tutorial/