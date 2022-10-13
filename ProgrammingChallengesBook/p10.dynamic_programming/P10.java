import java.util.*;
import java.io.*;

// Solved a variant of this problem

public class P10 {
    public static void main(java.lang.String[] args)
            throws IOException, FileNotFoundException, ClassCastException {

        FileReader fr = new FileReader("./input.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./output.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        String lineaEntera;
        StringTokenizer linea;

        Set<Turtle> turtles = new TreeSet<Turtle>();

        while ((lineaEntera = br.readLine()) != null) {
            linea = new StringTokenizer(lineaEntera);
            turtles.add(new Turtle(Integer.parseInt(linea.nextToken()), Integer.parseInt(linea.nextToken())));
        }

        pp.println(NumTurtles(turtles));
        pp.close();
    }
    
    public static int NumTurtles(Set<Turtle> turtles) {
        List<Turtle> turtlesList = new ArrayList<Turtle>(turtles);
        Collections.reverse(turtlesList);

        List<Turtle> stack = new ArrayList<Turtle>(Arrays.asList(turtlesList.get(0)));
        int minExtra = stack.get(0).extra;
        int it = 1;
        while (minExtra > 0 && it < turtlesList.size()) {
            Turtle nextTurtle = turtlesList.get(it);
            if (nextTurtle.w < minExtra) {
                stack.add(nextTurtle);
                minExtra = Math.min(minExtra, nextTurtle.extra);
            }
            it++;
        }

        return stack.size();
    }

}

class Turtle implements Comparable<Turtle> {
    int w, s, extra;

    Turtle(int we, int st) {
        w = we;
        s = st;
        extra = st - we;
    }

    @Override
    public int compareTo(Turtle other) {
        return extra - other.extra;
    }
}
