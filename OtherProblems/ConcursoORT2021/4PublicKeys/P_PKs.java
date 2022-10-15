import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class P_PKs {
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
            int numKeys = Integer.parseInt(linea.nextToken());
            int[] nums = new int[numKeys];
            for (int j = 0; j < numKeys; j++) {
                nums[j] = Integer.parseInt(br.readLine());
            }
            ArrayList<Integer> primes = GetPrimes(nums);
            pp.println("Case #"+(i+1));
            Print(primes, pp);
        }
        pp.close();
    }

    public static void Print(ArrayList<Integer> primes, PrintWriter pp) {
        int count = 0;
        for (int prime : primes) {
            if (count%5 == 4) pp.println(" "+prime);
            else if (count%5 == 0) pp.print(prime);
            else pp.print(" "+ prime);
            count++;
        }
        if (count%5 != 4) pp.println();
    }

    public static ArrayList<Integer> GetPrimes(int[] nums){
        SortedSet<Integer> ourPrimes = new TreeSet<Integer>();
        
        for (int i=0; i<nums.length; i++) {
            ArrayList<Integer> allPrimesBefore = sieveOfEratosthenes((int)Math.sqrt(nums[i]));
            boolean done = false;
            for (int j=0; j<allPrimesBefore.size() && !done; j++){
                if (nums[i]%allPrimesBefore.get(j) == 0){
                    done = true;
                    ourPrimes.add(allPrimesBefore.get(j));
                    ourPrimes.add(nums[i]/allPrimesBefore.get(j));
                }
            }
        }

        return new ArrayList<Integer> (ourPrimes);
    }

    static ArrayList<Integer> sieveOfEratosthenes(int n)
    {                                
        boolean prime[] = new boolean[n + 1];
        for (int i = 0; i <= n; i++)
            prime[i] = true;
 
        for (int p = 2; p * p <= n; p++) {                        
            if (prime[p] == true) {                                                                
                for (int i = p * p; i <= n; i += p)
                    prime[i] = false;
            }
        }
        
        ArrayList<Integer> primes = new ArrayList<Integer>();
        for (int i = 2; i <= n; i++) {
            if (prime[i] == true)
                primes.add(i);
        }

        return primes;
    }

}