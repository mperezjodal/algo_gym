import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class P_Canvas {
    public static void main(java.lang.String[] args) throws IOException, FileNotFoundException, ClassCastException {
        FileReader fr = new FileReader("./input.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./output.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        String lineaEntera;
        lineaEntera = br.readLine();
        StringTokenizer linea = new StringTokenizer(lineaEntera);
        int cases = Integer.parseInt(linea.nextToken());

        for (int i = 0; i < cases; i++) {
            lineaEntera = br.readLine();
            int numberOfCanvases = Integer.parseInt(lineaEntera);
            List<Canvas> canvases = new ArrayList<>();

            for (int j = 0; j < numberOfCanvases; j++) {
                lineaEntera = br.readLine();
                linea = new StringTokenizer(lineaEntera);
                canvases.add(new Canvas(Integer.parseInt(linea.nextToken()), Integer.parseInt(linea.nextToken())));
            }

            lineaEntera = br.readLine();
            int numberOfPegs = Integer.parseInt(lineaEntera);
            List<Integer> pegs = new ArrayList<>();
            lineaEntera = br.readLine();
            linea = new StringTokenizer(lineaEntera);

            for (int j = 0; j < numberOfPegs; j++)
                pegs.add(Integer.parseInt(linea.nextToken()));

            pp.println("Case #" + (i + 1) + ": " + MinExtraPegs(canvases, pegs));
        }
        pp.close();
    }

    public static java.lang.String MinExtraPegs(List<Canvas> canvases, List<Integer> pegs) {
        int minPegs = 0;
        FillCanvas(canvases, pegs);
        for (int i = 0; i < canvases.size(); i++) {
            Canvas c = canvases.get(i);
            if (c.pegs.size() > 2) {
                return "impossible";
            }
            if (c.pegs.size() == 0) {
                minPegs += 2;
                if (i + 1 < canvases.size()) {
                    Canvas nextC = canvases.get(i+1);
                    if (nextC.i == c.j) {
                        nextC.pegs.add(c.j);
                        if (nextC.pegs.size() > 2) return "impossible";
                    }
                }
            }
            if (c.pegs.size() == 1) {
                if (c.pegs.contains(c.j)) {
                    minPegs++;
                }
                else {
                    minPegs++;
                    if (i + 1 < canvases.size()) {
                        Canvas nextC = canvases.get(i+1);
                        if (nextC.i == c.j) {
                            nextC.pegs.add(c.j);
                            if (nextC.pegs.size() > 2) return "impossible";
                        }
                    }
                        
                }
            }
        }
        return minPegs + "";
    }

    public static void FillCanvas(List<Canvas> canvases, List<Integer> pegs) {
        int nextPeg = pegs.get(0);
        int it = 0;
        Iterator canvas = canvases.iterator();
        Canvas thisCanvas = (Canvas) canvas.next();
        while (it <= canvases.size()) {
            if (nextPeg < thisCanvas.i) {
                it++;
                if (it >= pegs.size()) return;
                nextPeg = pegs.get(it);
                thisCanvas = (Canvas) canvas.next();
            }
            else if (nextPeg >= thisCanvas.i && nextPeg < thisCanvas.j) {
                thisCanvas.pegs.add(nextPeg);
                it++;
                if (it >= pegs.size()) return;
                nextPeg = pegs.get(it);
            }
            else if (nextPeg == thisCanvas.j) {
                thisCanvas.pegs.add(nextPeg);
                if (canvas.hasNext()) {
                    thisCanvas = (Canvas) canvas.next();
                }
                else return;
            }
            else {
                if (canvas.hasNext()) {
                    thisCanvas = (Canvas) canvas.next();
                }
                else return;
            }
        }
    }

}

class Canvas {
    int i, j;
    List<Integer> pegs;

    Canvas(int i, int j) {
        this.i = i;
        this.j = j;
        this.pegs = new ArrayList<>();
    }
}