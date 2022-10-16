import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.util.*;

public class P_Permutations {
	public static void main(java.lang.String[] args)
			throws IOException, FileNotFoundException, ClassCastException {
		// carga de datos
		FileReader fr = new FileReader("./input.txt");
		BufferedReader br = new BufferedReader(fr);
		// salida
		FileWriter wr = new FileWriter("./output.txt");
		BufferedWriter bwr = new BufferedWriter(wr);
		PrintWriter pp = new PrintWriter(bwr);
		// la primera linea es la cantidad de casos
		String lineaEntera;
		lineaEntera = br.readLine();
		StringTokenizer linea = new StringTokenizer(lineaEntera);
		int cant = Integer.parseInt(linea.nextToken());

		for (int i = 0; i < cant; i++) {
			lineaEntera = br.readLine();
			linea = new StringTokenizer(lineaEntera);
			int index = Integer.parseInt(linea.nextToken());
			StringBuilder number = new StringBuilder(linea.nextToken());

			int length = number.length();
			boolean done = false;
			Set<Integer> orderNums = new TreeSet<Integer>(new IntComparator());
			int breakPoint = -1;
			int numInBreakPoint = -1;

			orderNums.add(Character.getNumericValue(number.charAt(length - 1)));
			for (int j = length - 2; j > 0; j--) {
				int it = Character.getNumericValue(number.charAt(j));
				int it2 = Character.getNumericValue(number.charAt(j + 1));
				orderNums.add(it);
				if (it < it2) {
					breakPoint = j;
					numInBreakPoint = it;
					done = true;
					break;
				}
			}

			if (!done) {
				pp.println(index + " BIG NUMBER");
			} else {
				String s = "";
				for (int k = 0; k < breakPoint; k++) {
					s = s + number.charAt(k);
				}

				List<Integer> nums = new ArrayList<>(orderNums);

				int firstNum = -1;
				int itt = 0;
				while (firstNum == -1) {
					if (nums.get(itt) > numInBreakPoint) {
						firstNum = nums.get(itt);
						nums.remove(itt);
					}
					itt++;
				}
				s += firstNum;

				for (int it : nums) {
					s += it;
				}

				pp.print(index + " ");
				pp.println(s);
			}

		}
		pp.close();

	}
}

class IntComparator implements Comparator<Integer>
{
    @Override
    public int compare(Integer s1, Integer s2)
    {
        if(s1.compareTo(s2) == 0){
            return 1;
        }
        else{
            return s1.compareTo(s2);
        }
    }
}