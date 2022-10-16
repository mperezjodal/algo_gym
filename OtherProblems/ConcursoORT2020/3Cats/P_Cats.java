import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class P_Cats {
    public static void main(java.lang.String[] args) throws IOException, FileNotFoundException, ClassCastException {
        FileReader fr = new FileReader("./input.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./output.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        int cant = Integer.parseInt(br.readLine());

        for (int i = 0; i < cant; i++) {
            String lineaEntera = br.readLine();
            StringTokenizer linea = new StringTokenizer(lineaEntera);
            int vertices = Integer.parseInt(linea.nextToken());
            int maxCats = Integer.parseInt(linea.nextToken());

            lineaEntera = br.readLine();
            linea = new StringTokenizer(lineaEntera);
            boolean[] cats = new boolean[vertices];
            for (int j = 0; j < cats.length; j++)
                cats[j] = "1".equals(linea.nextToken());

            List<Edge> edges = new ArrayList<>();
            for (int j = 0; j < vertices - 1; j++) {
                lineaEntera = br.readLine();
                linea = new StringTokenizer(lineaEntera);
                edges.add(new Edge(Integer.parseInt(linea.nextToken()), Integer.parseInt(linea.nextToken())));
            }

            Graph graph = new Graph(edges, vertices, cats, maxCats);
            graph.DFS(1, 0);
            
            pp.println("Case #"+(i+1)+": " + graph.restaurants);
        }
        pp.close();
    }
}

class Edge {
    int src, dest;

    Edge(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }
}

class Graph {

    List<List<Integer>> adj_list = new ArrayList<>();
    boolean[] cats;
    int restaurants;
    int maxCats;

    public Graph(List<Edge> edges, int vertices, boolean[] cats, int maxCats) {
        adj_list.add(0, new ArrayList<>());

        for (int i = 0; i < vertices; i++)
            adj_list.add(i, new ArrayList<>());

        for (Edge e : edges)
            adj_list.get(e.src).add(e.dest);
        
        this.cats = cats;
        this.maxCats = maxCats;
        this.restaurants = 0;
    }

    public void DFS(int index, int catsTillNow) {
        if (cats[index-1]) catsTillNow++;
        if (catsTillNow > maxCats) return;
        if (!cats[index-1]) catsTillNow = 0;

        if (adj_list.get(index).size() == 0) {
            this.restaurants++;
        }
        else {
            List<Integer> iter = adj_list.get(index);
            for (int it : iter) {
                DFS(it, catsTillNow);
            }
        }
    }
}