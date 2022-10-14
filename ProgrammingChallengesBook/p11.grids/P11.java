import java.util.*;
import java.io.*;

// Output is not the expected

public class P11 {
    public static void main(java.lang.String[] args)
            throws IOException, FileNotFoundException, ClassCastException {

        FileReader fr = new FileReader("./input.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./output.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        String lineaEntera;

        while ((lineaEntera = br.readLine()) != null) {
            pp.println(Count(Integer.parseInt(lineaEntera)));
        }
        
        pp.close();
    }

    public static java.lang.String Count(int n) {
        String counts = "";
        counts += FindSquare(n, 2);
        Figure fig = new Figure();
        counts += " " + fig.FindRect(n, n, 1, 1, 2);
        counts += " " + FindSquare(n, 3);
        fig = new Figure();
        counts += " " + fig.FindRect(n, n, n, 1, 3);
        counts += " " + FindSquare(n, 4);
        fig = new Figure();
        counts += " " + fig.FindRect(n, n, n, n, 4);
        return counts;
    }

    public static Integer FindSquare(long n, int d) {
        int squares = 0;
        int i = 0;
        while (n-i >= 1) {
            squares += Math.pow(n-i, d);
            i++;
        }
        return squares;
    }

}

class Coord implements Comparable<Coord> {
    int x, y, j, k;
    Coord(int x, int y, int j, int k) {
        this.x = x;
        this.y = y;
        this.j = j;
        this.k = k;
    }
    @Override
    public int compareTo(Coord other) {
        return this.x == other.x && this.y == other.y && this.j == other.j && this.k == other.k ? 0 : 1;
    }
}

class Figure {
    Set<Coord> visited = new TreeSet<Coord>();

    Figure()   {
        visited = new TreeSet<Coord>();
    }

    public Integer FindRect(int n1, int n2, int n3, int n4, int dim) {
        if (visited.contains(new Coord(n1, n2, n3, n4))) return 0;
        if (n1 == 0 || n2 == 0 || n3 == 0 || n4 == 0) return 0;

        int rectangles = 0;
        for (int i = 1; i <= n1; i++ ) 
            for (int j = 1; j <= n2; j++ ) 
                for (int k = 1; k <= n3; k++ )
                    for (int l = 1; l <= n3; l++ )
                        if (!isSquare(i, j, k, l, dim)) rectangles ++;

        rectangles += FindRect(n1-1, n2, n3, n4, dim);
        rectangles += FindRect(n1, n2-1, n3, n4, dim);
        rectangles += FindRect(n1, n2, n3-1, n4, dim);
        rectangles += FindRect(n1, n2, n3, n4-1, dim);
        
        this.visited.add(new Coord(n1, n2, n3, n4));
        return rectangles;
    }

    public static boolean isSquare(int i, int j, int k, int l, int dim) {
        if (dim == 2) {
            if (i==j && k==1 && l==1) return true;
            else return false;
        }
        else if (dim == 3) {
            if (i==j && j==k && l==1) return true;
            else return false;
        }
        else if (dim == 4) {
            if (i==j && j==k && k==l)return true;
            else return false;
        }
        return true;
    }
}