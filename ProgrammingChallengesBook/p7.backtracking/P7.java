import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;

public class P7 {
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
            int numWeights = Integer.parseInt(br.readLine());
            ArrayList<Integer> weights = new ArrayList<Integer>();
            for (int j = 0; j < numWeights; j++) {
                weights.add(Integer.parseInt(br.readLine()));
            }
            ArrayList<Integer> result = DistributeBacktracking(weights, new ArrayList<Integer>(), new ArrayList<Integer>());
            if (result.get(0) < result.get(2)) {
                pp.println(result.get(0) + " " + result.get(2));
            }
            else {
                pp.println(result.get(2) + " " + result.get(0));
            }
        }
        pp.close();
    }

    public static ArrayList<Integer> DistributeBacktracking(ArrayList<Integer> remaining, ArrayList<Integer> oneStack, ArrayList<Integer> otherStack) {

        if (remaining.size() > 0) {
            int thisWeight = remaining.get(remaining.size()-1);
            remaining.remove(remaining.size()-1);
            oneStack.add(thisWeight);
            ArrayList<Integer> caseLeft = DistributeBacktracking(remaining, oneStack, otherStack);
            oneStack.remove(oneStack.size()-1);
            otherStack.add(thisWeight);
            ArrayList<Integer> caseRight = DistributeBacktracking(remaining, oneStack, otherStack);
            otherStack.remove(otherStack.size()-1);
            if (Math.abs(caseLeft.get(1) - caseLeft.get(3)) <= 1) {
                if (Math.abs(caseRight.get(1) - caseRight.get(3)) <= 1) {
                    if (Math.abs(caseLeft.get(0) - caseLeft.get(2)) < Math.abs(caseRight.get(0) - caseRight.get(2))) {
                        oneStack.add(thisWeight);
                    }
                    else {
                        otherStack.add(thisWeight);
                    }
                }
                else {
                    oneStack.add(thisWeight);
                }
            }
            else if (Math.abs(caseRight.get(1) - caseRight.get(3)) <= 1) {
                otherStack.add(thisWeight);
            }
            else {
                ArrayList<Integer> error = new ArrayList<>();
                error.add(0);
                error.add(1);
                error.add(0);
                error.add(3);
                return error;
            }
        }

        ArrayList<Integer> ret =  new ArrayList<Integer>();
        ret.add(TotalWeight(oneStack));
        ret.add(oneStack.size());
        ret.add(TotalWeight(otherStack));
        ret.add(otherStack.size());
        return ret;
    }

    public static Integer TotalWeight(ArrayList<Integer> arr) {
        Integer sum = 0;
        for(int d : arr)
            sum += d;
        return sum;
    }

}