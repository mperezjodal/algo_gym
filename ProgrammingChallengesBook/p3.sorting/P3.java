import java.util.*;
import java.util.stream.Collectors;
import java.io.*;

public class P3 {
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
            br.readLine();
            lineaEntera = br.readLine();
            linea = new StringTokenizer(lineaEntera);
            int number_in_case = Integer.parseInt(linea.nextToken());
            int[] nums = new int[number_in_case];
            for (int j = 0; j < number_in_case; j++) {
                nums[j] = Integer.parseInt(br.readLine());
            }
            List<Integer> strategy = Bridge(nums);
            pp.println(strategy.get(strategy.size()-1));
            int numberOfCrossings = 0;
            for (int j = 0; j < strategy.size() - 1; j++) {
                if (numberOfCrossings%3 == 1) {
                    pp.println(" " + strategy.get(j));
                } else if (numberOfCrossings%3 == 2) {
                    pp.println(strategy.get(j));
                }
                else {
                    pp.print(strategy.get(j));
                }
                numberOfCrossings += 1;
            }
        }
        pp.close();
    }

    public static List<Integer> Bridge(int[] nums) {
        boolean flashlightThisSide = true;
        List<Integer> thisSide = Arrays.stream(nums).boxed().collect(Collectors.toList());
        List<Integer> otherSide = new ArrayList<Integer>();
        List<Integer> strategy = new ArrayList<Integer>();
        int totalTime = 0;

        while (thisSide.size() > 0) {
            thisSide.sort(null);
            otherSide.sort(null);

            if (flashlightThisSide) {
                boolean moveLast = false;
                if (otherSide.size() > 0 && thisSide.size() > 2
                        && thisSide.get(thisSide.size() - 3) < otherSide.get(0)) {
                    moveLast = true;
                }
                totalTime += Math.max(Move(thisSide, otherSide, strategy, moveLast),
                        Move(thisSide, otherSide, strategy, moveLast));
            } else {
                totalTime += Move(otherSide, thisSide, strategy, false);
            }
            flashlightThisSide = !flashlightThisSide;
        }

        strategy.add(totalTime);
        return strategy;
    }

    public static int Move(List<Integer> fromSide, List<Integer> toSide, List<Integer> strategy, boolean moveLast) {
        int index = 0;
        if (moveLast) {
            index = fromSide.size() - 1;
        }
        int toMove = fromSide.get(index);
        fromSide.remove(index);
        toSide.add(toMove);
        strategy.add(toMove);
        return toMove;
    }

}