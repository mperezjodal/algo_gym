import java.util.*;
import java.io.*;

public class P1 {
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
            int number_in_line = Integer.parseInt(linea.nextToken());
            int[] nums = new int[number_in_line];
            for (int j = 0; j < number_in_line; j++) {
                nums[j] = Integer.parseInt(linea.nextToken());
            }
            pp.println(JollyJumpers(nums));
        }
        pp.close();
    }

    public static java.lang.String JollyJumpers(int[] nums) {
        int nums_length = nums.length;
        
        int[] all_nums = new int[nums_length];
        for (int i = 0; i < nums_length; i++) {
            all_nums[i] = 0;
        }

        for (int i = 0; i < nums_length - 1; i++) {
            int diff = Math.abs(nums[i] - nums[i + 1]);
            if (diff > nums_length - 1) {
                return "Not jolly";
            }
            if (all_nums[diff] == 1) {
                return "Not jolly";
            }
            all_nums[diff] = 1;
        }
        return "Jolly";
    }
}