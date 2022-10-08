import java.util.*;
import java.io.*;

public class P2 {
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
            // read matrix of chars
            lineaEntera = br.readLine();
            linea = new StringTokenizer(lineaEntera);
            int n = Integer.parseInt(linea.nextToken());
            int m = Integer.parseInt(linea.nextToken());
            char[][] matrix = new char[n][m];
            for (int j = 0; j < n; j++) {
                lineaEntera = br.readLine();
                for (int k = 0; k < m; k++) {
                    matrix[j][k] = lineaEntera.charAt(k);
                }
            }
            // read words
            lineaEntera = br.readLine();
            linea = new StringTokenizer(lineaEntera);
            int cantWords = Integer.parseInt(linea.nextToken());
            String[] words = new String[cantWords];
            for (int j = 0; j < cantWords; j++) {
                words[j] = br.readLine();
                pp.println(WheresWaldorf(matrix, words[j]));
            }
        }
        pp.close();
    }

    public static java.lang.String WheresWaldorf(char[][] matrix, String word) {
        //word to lowercase
        word = word.toLowerCase();
        // matrix to lowercase
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = Character.toLowerCase(matrix[i][j]);
            }
        }
        // search word first letter with nested for loops
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == word.charAt(0)) {
                    // search word in all directions
                    if (searchWord(matrix, word, i, j, 0, 1)) {
                        return (i + 1) + " " + (j + 1);
                    }
                    if (searchWord(matrix, word, i, j, 1, 1)) {
                        return (i + 1) + " " + (j + 1);
                    }
                    if (searchWord(matrix, word, i, j, 1, 0)) {
                        return (i + 1) + " " + (j + 1);
                    }
                    if (searchWord(matrix, word, i, j, 1, -1)) {
                        return (i + 1) + " " + (j + 1);
                    }
                    if (searchWord(matrix, word, i, j, 0, -1)) {
                        return (i + 1) + " " + (j + 1);
                    }
                    if (searchWord(matrix, word, i, j, -1, -1)) {
                        return (i + 1) + " " + (j + 1);
                    }
                    if (searchWord(matrix, word, i, j, -1, 0)) {
                        return (i + 1) + " " + (j + 1);
                    }
                    if (searchWord(matrix, word, i, j, -1, 1)) {
                        return (i + 1) + " " + (j + 1);
                    }
                }
            }
        }
        return "not found";
    }

    public static boolean searchWord(char[][] matrix, String word, int i, int j, int x, int y) {
        int k = 0;
        while (k < word.length()) {
            if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length) {
                return false;
            }
            if (matrix[i][j] != word.charAt(k)) {
                return false;
            }
            i += x;
            j += y;
            k++;
        }
        return true;
    }
}