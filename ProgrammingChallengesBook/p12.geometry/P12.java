import java.util.*;
import java.io.*;

// Output is not the expected

public class P12 {
    public static void main(java.lang.String[] args)
            throws IOException, FileNotFoundException, ClassCastException {

        FileReader fr = new FileReader("./input.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./output.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        String lineaEntera;
        StringTokenizer linea;

        int samples = Integer.parseInt(br.readLine());
        br.readLine();

        for (int i=0; i<samples; i++) {
            List<Point> chips = new ArrayList<Point>();

            while ((lineaEntera = br.readLine()) != null) {
                linea = new StringTokenizer(lineaEntera);
                Double x = Double.parseDouble(linea.nextToken());
                Double y = Double.parseDouble(linea.nextToken());
                chips.add(new Point(x, y));
            }
            
            pp.println(MaxNumPoints(chips));
        }

        pp.close();
    }

    public static int MaxNumPoints(List<Point> chips) {
        int numOfChips = chips.size();
        double[] dist[] = new double[numOfChips][numOfChips];

        for (int i=0; i<numOfChips-1; i++)
            for (int j=0; j<numOfChips; j++){
                double distance = distance(chips.get(i), chips.get(j));
                dist[i][j] = distance;
                dist[j][i] = distance;
            }

        int max = 0;
        for (int i=0; i<numOfChips-1; i++)
            max = Math.max(max, getPointsInside(chips, dist, i, numOfChips));
        
        return max;
    }

    public static int getPointsInside(List<Point> chips, double[] dist[], int i, int n) {
        List<Pair> angles = new ArrayList<>();

        for (int j=0; j<n; j++) {
            if (i != j && dist[i][j] <= 10) {
                // arc cosine of the complex
                // used for cosine inverse
                double B = Math.acos(dist[i][j] / 10);

                // arg returns the phase angle of the complex
                double A = Math.atan2(chips.get(j).y - chips.get(i).y,
                                      chips.get(j).x - chips.get(i).x);
                angles.add(new Pair(A-B, true));
                angles.add(new Pair(A+B, false));
            }
        }

        Collections.sort(angles);

        int count = 0;
        int max = 0;
        for (int j=0; j<angles.size(); j++) {
            if (angles.get(j).b)
                count++;
            else
                count--;
            max = Math.max(max, count);
        }
        
        return max;
    }

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }
}

class Pair implements Comparable<Pair> {
    double d;
    boolean b;
    Pair(double d, boolean b) {
        this.d = d;
        this.b = b;
    }
    @Override
    public int compareTo(Pair p) {
        if (d < p.d)
            return -1;
        else if (d > p.d)
            return 1;
        else
            return 0;
    }
}

class Point {
    double x, y;
    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

// Solution from: https://www.geeksforgeeks.org/angular-sweep-maximum-points-can-enclosed-circle-given-radius/