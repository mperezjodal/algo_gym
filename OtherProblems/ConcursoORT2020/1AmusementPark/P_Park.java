import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class P_Park {
    public static void main(java.lang.String[] args) throws IOException, FileNotFoundException, ClassCastException {
        FileReader fr = new FileReader("./input.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./output.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        int cant = Integer.parseInt(br.readLine());

        for (int i = 0; i < cant; i++) {
            br.readLine();
            StringBuilder sequence = new StringBuilder(br.readLine());
            int tunnelsNeeded = 0;
            
            for (int j = 0; j < sequence.length()-1; j++) {
                if (sequence.charAt(j) == sequence.charAt(j+1)) {
                    sequence.deleteCharAt(j);
                    sequence.deleteCharAt(j);
                }
            }

            for (int j = 0; j < sequence.length()-3; j++) {
                if (sequence.charAt(j) != sequence.charAt(j+1) && sequence.charAt(j+1) != sequence.charAt(j+2) && sequence.charAt(j+2) != sequence.charAt(j+3)) {
                    sequence.deleteCharAt(j);
                    sequence.deleteCharAt(j);
                    sequence.deleteCharAt(j);
                    sequence.deleteCharAt(j);
                }
            }

            for (int j = 0; j < sequence.length()-2; j++) {
                if (sequence.charAt(j) != sequence.charAt(j+1) && sequence.charAt(j+1) != sequence.charAt(j+2)) {
                    sequence.deleteCharAt(j);
                    sequence.deleteCharAt(j);
                    sequence.deleteCharAt(j);
                    tunnelsNeeded++;
                }
            }

            for (int j = 0; j < sequence.length(); j++) {
                tunnelsNeeded++;
            }
            
            pp.println("Case #"+(i+1)+": "+tunnelsNeeded);
        }
        pp.close();
    }

}