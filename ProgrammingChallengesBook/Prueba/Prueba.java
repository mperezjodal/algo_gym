import java.util.*;
import java.io.*;

public class Prueba {
    public static void main(java.lang.String[] args) throws IOException, FileNotFoundException, ClassCastException {
        FileReader fr = new FileReader("./archivodatos.txt");
        BufferedReader br = new BufferedReader(fr);

        FileWriter wr = new FileWriter("./archivoresultado.txt");
        BufferedWriter bwr = new BufferedWriter(wr);
        PrintWriter pp = new PrintWriter(bwr);

        String lineaEntera;
        lineaEntera = br.readLine();
        StringTokenizer linea = new StringTokenizer(lineaEntera);
        int cant = Integer.parseInt(linea.nextToken());
        
        for (int i = 0; i < cant; i++) {
            lineaEntera = br.readLine();
            linea = new StringTokenizer(lineaEntera);
            while (linea.hasMoreTokens()) {
                String pal = linea.nextToken();
                pp.println("Linea " + i + " palabra " + pal);
            }
        }
        pp.close();
    }
}