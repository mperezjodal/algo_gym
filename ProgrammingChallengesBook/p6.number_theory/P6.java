import java.util.*;
import java.io.*;

public class P6 {
    public static void main(java.lang.String[] args) throws IOException, FileNotFoundException, ClassCastException {
        FileReader fr = new FileReader("./input.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./output.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        String lineaEntera;
        while ((lineaEntera = br.readLine()) != null) {
            int n = Integer.parseInt(lineaEntera);
            List<Integer> desc = PrimeDecomposition(n, 4);
            if (desc.size() == 0) pp.println();
            else pp.println(desc.get(0) + " " + desc.get(1) + " " + desc.get(2) + " " + desc.get(3));
        }
        pp.close();
    }
    public static List<Integer> PrimeDecomposition(int num, int it) {
        if (it == 0) {
            return new ArrayList<Integer>();
        }
        if (it == 1) {
            if (isPrime(num)) return Arrays.asList(num);
            else return new ArrayList<Integer>();
        }

        for (int i=0; i<=num-8; i++) {
            if (isPrime(i)) {
                List<Integer> desc = new ArrayList<Integer>();
                desc.add(i);
                desc.addAll(PrimeDecomposition(num-i, it-1));
                if (it != 4) {
                    return desc;
                }
                else if (desc.size() == 4) {
                    return desc;
                }
            }
        }

        return new ArrayList<Integer>();
    }

    public static boolean isPrime(int n) {
        if (n==1) return false;
        if (n==2) return true;
        if (n%2 == 0) return false;
        int i = 3;
        while (i < Math.sqrt(n)+1) {
            if (n%i == 0)  return false;
            else i = i+2;
        }
        return true;
    }
}