import java.util.*;
import java.io.*;

public class P9 {
    public static void main(java.lang.String[] args)
            throws IOException, FileNotFoundException, ClassCastException {

        FileReader fr = new FileReader("./input.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./output.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        String lineaEntera = br.readLine();
        StringTokenizer linea = new StringTokenizer(lineaEntera);
        int cases = Integer.parseInt(linea.nextToken());

        int caseN = 1;
        for (int i = 0; i < cases; i++) {
            lineaEntera = br.readLine();
            linea = new StringTokenizer(lineaEntera);
            int numberOfBeads = Integer.parseInt(linea.nextToken());

            Graph graph = new Graph();
            for (int j = 0; j < numberOfBeads; j++) {
                lineaEntera = br.readLine();
                linea = new StringTokenizer(lineaEntera);
                String n1 = linea.nextToken();
                String n2 = linea.nextToken();

                graph.insert(n1, n2);
            }

            pp.println("Case #" + caseN);
            caseN++;
            BuildNecklace(graph, pp);
        }
        pp.close();
    }

    public static void BuildNecklace(Graph graph, PrintWriter pp) {
        Iterator<String> it = graph.adjacencyMap.keySet().iterator();
        boolean done = false;
        
        List<String> path = graph.BuildNecklace(it.next());
        System.out.println(path.get(0));
        System.out.println(path.get(path.size() - 1));
        if (path.size() > 0 && path.get(0).equals(path.get(path.size() - 1))) {
            for (int i = 0; i < path.size() - 1; i++) {
                pp.println(path.get(i) + " " + path.get((i + 1)));
            }
            done = true;
        }
        if (!done) pp.println("some beads may be lost");
    }
}

class Edge {
    String src, dest;

    Edge(String src, String dest) {
        this.src = src;
        this.dest = dest;
    }
}

class Graph {
    Map<String, List<String>> adjacencyMap;
    int numberOfEdges;

    public boolean hasEdge(String source, String target) {
        return adjacencyMap.containsKey(source) && adjacencyMap.get(source) != null
                && adjacencyMap.get(source).contains(target);
    }

    public Graph() {
        adjacencyMap = new HashMap<>();
        numberOfEdges = 0;
    }

    public void insert(String source, String target) {
        if (!adjacencyMap.keySet().contains(source)) {
            adjacencyMap.put(source, null);
        }
        if (!adjacencyMap.keySet().contains(target)) {
            adjacencyMap.put(target, null);
        }
        List<String> temp = adjacencyMap.get(source);
        if (temp == null) {
            temp = new ArrayList<>();
        }
        temp.add(target);
        adjacencyMap.put(source, temp);
        temp = adjacencyMap.get(target);
        if (temp == null) {
            temp = new ArrayList<>();
        }
        temp.add(source);
        adjacencyMap.put(target, temp);
        numberOfEdges++;
    }

    public List<String> BuildNecklace(String node) {
        System.out.println("DFS: " + node);
        if (numberOfEdges == 0) {
            System.out.println("No edges in graph");
            return new ArrayList<String>(Arrays.asList(node));
        }

        ArrayList<String> completeNecklace = new ArrayList<>();
        completeNecklace.add(node);

        if (adjacencyMap.get(node) == null)
            return new ArrayList<String>();
        Iterator<String> nodeAdj = adjacencyMap.get(node).iterator();
        String next = nodeAdj.next();
        adjacencyMap.get(node).remove(next);
        adjacencyMap.get(next).remove(node);
        numberOfEdges--;
        completeNecklace.addAll(BuildNecklace(next));

        return completeNecklace;
    }
}

// Graph: https://www.devglan.com/datastructure/graph-implementation-java