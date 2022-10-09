import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class P5 {
    public static void main(java.lang.String[] args) throws IOException, FileNotFoundException, ClassCastException {
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
            pp.println(PiecesOfLand(Integer.parseInt(lineaEntera)));
        }

        pp.close();
    }

    public static java.lang.String PiecesOfLand(int n) {
        BigDecimal a = new BigDecimal("0");
		BigDecimal b = new BigDecimal("0");
		BigDecimal c = new BigDecimal("0");

        a = BigDecimal.valueOf(n);
        b = a.multiply( a.subtract(BigDecimal.valueOf(1)));
        b = b.multiply(a.subtract(BigDecimal.valueOf(2)));
        b =  b.multiply(a.subtract(BigDecimal.valueOf(3)));
        b = b.divide(BigDecimal.valueOf(24));
        c = a.multiply(a.subtract(BigDecimal.valueOf(1)));
        c = c.divide(BigDecimal.valueOf(2));
        b = b.add(c);
        b = b.add(BigDecimal.valueOf(1));

        return b.toString();
    }

}

// Solution: https://codingstrife.wordpress.com/2013/08/02/solution-uva10213-pc110602-how-many-pieces-of-land/