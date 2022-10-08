import java.util.*;
import java.io.*;

public class P4 {
    public static void main(java.lang.String[] args) throws IOException, FileNotFoundException, ClassCastException {
        FileReader fr = new FileReader("./input.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./output.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        String lineaEntera;
        while ((lineaEntera = br.readLine()) != null) {
            double n = Double.parseDouble(lineaEntera);
            pp.println(Game(n));
        }
        pp.close();
    }
    public static java.lang.String Game(double num) {
        boolean firstWins = false;
        if (num < 162) {

        }
        else {
            while (num > 1) {
                firstWins = !firstWins;
                if (firstWins) {
                    num /= 9;
                }
                else {
                    num /= 2;
                }
            }
        }

        if (firstWins) {
            return "Stan wins.";
        }
        else {
            return "Ollie wins.";
        }
    }
}