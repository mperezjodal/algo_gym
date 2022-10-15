import java.util.*;
import java.io.*;

public class P_Graph {
    public static void main(java.lang.String[] args)
            throws IOException, FileNotFoundException, ClassCastException {

        FileReader fr = new FileReader("./input.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./output.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        String lineaEntera;
        lineaEntera = br.readLine();
        StringTokenizer linea = new StringTokenizer(lineaEntera);
        int cant = Integer.parseInt(linea.nextToken());

        for (int i = 0; i < cant; i++) {
            lineaEntera = br.readLine();
            linea = new StringTokenizer(lineaEntera);
            int numberOfLanguages = Integer.parseInt(linea.nextToken());
            int numberOfTransaltions = Integer.parseInt(linea.nextToken());

            List<String> languages = new ArrayList<String>();
            lineaEntera = br.readLine();
            linea = new StringTokenizer(lineaEntera);
            for (int j = 0; j < numberOfLanguages; j++) {
                languages.add(linea.nextToken());
            }

            Graph graph = new Graph(languages);

            for (int k = 0; k < numberOfTransaltions; k++) {
                lineaEntera = br.readLine();
                linea = new StringTokenizer(lineaEntera);
                String l1 = linea.nextToken();
                String l2 = linea.nextToken();
                int cost = Integer.parseInt(linea.nextToken());
                graph.insert(l1, l2, cost);
            }

            pp.println("Case #" + (i + 1) + ": ");
            pp.println(graph.CostOfTranslation());
        }
        pp.close();
    }
}

class Edge {
    String dest;
    int weight;

    Edge(String dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }
}

class Path {
    int level, w;
    Path(int level, int w) {
        this.level = level;
        this.w = w;
    }
}

class Graph {
    Map<String, List<Edge>> adjacencyMap;
    Map<String, Path> costs;
    int numberOfLangs;

    public Graph(List<String> langs) {
        adjacencyMap = new HashMap<>();
        costs = new HashMap<>();
        for (String l : langs) {
            costs.put(l, new Path(Integer.MAX_VALUE, Integer.MAX_VALUE));
        }
        numberOfLangs = langs.size();
    }

    public java.lang.String CostOfTranslation() {
        CostFromBFS("English", 0, 0);
        
        int sum = 0;
        for (String key : this.costs.keySet()) {
            int thisWeight = this.costs.get(key).w;
            if (thisWeight == Integer.MAX_VALUE) return "Impossible";
            sum += thisWeight;
        }

        return sum + "";
    }

    public void CostFromBFS(String from, int level, int carryOn) {
        List<Edge> edges = adjacencyMap.get(from);
        if (edges == null) return;
        
        for (Edge e : edges) {
            Path thisCost = this.costs.get(e.dest);
            if (level <= thisCost.level) {
                int newWeight = e.weight + carryOn;
                if (newWeight < thisCost.w)
                    this.costs.put(e.dest, new Path(level, newWeight));
            }
        }

        boolean cont = false;
        for (String key : this.costs.keySet()) 
            if (this.costs.get(key).w == Integer.MAX_VALUE) cont = true;

        if (cont)
            for (Edge e : edges) {
                int weight = 0;
                if (from != "English" && this.costs.get(from).w == Integer.MAX_VALUE) weight = e.weight;
                CostFromBFS(e.dest, level + 1, carryOn + weight);
            }
                
    }

    public void insert(String source, String target, int cost) {
        if (!adjacencyMap.keySet().contains(source))
            adjacencyMap.put(source, null);
            
        if (!adjacencyMap.keySet().contains(target))
            adjacencyMap.put(target, null);
            
        List<Edge> temp = adjacencyMap.get(source);
        if (temp == null)
            temp = new ArrayList<>();
            
        temp.add(new Edge(target, cost));
        adjacencyMap.put(source, temp);
    }
}