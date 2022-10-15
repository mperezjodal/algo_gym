import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class P_Exam {
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
            linea = new StringTokenizer(lineaEntera);
            int correct = Integer.parseInt(linea.nextToken());
            int equal = 0;
            int total = 0;
            
            String me = br.readLine();
            String him = br.readLine();
            
            for (int j = 0; j < me.length(); j++) {
                if (me.charAt(j) == him.charAt(j)) equal++;
                total++;
            }
            
            if (correct > equal)
                pp.println("Case #"+(i+1)+": "+(equal + total-correct));
            else
                pp.println("Case #"+(i+1)+": "+(correct + total-equal));
        }
        pp.close();
    }

}