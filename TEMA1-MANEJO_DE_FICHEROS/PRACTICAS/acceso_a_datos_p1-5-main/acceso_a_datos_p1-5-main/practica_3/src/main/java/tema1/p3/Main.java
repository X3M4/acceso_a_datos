package tema1.p3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File f = new File("practica_3/src/main/java/tema1/p3/LoremIpsum.txt");
        File fcopia = new File("practica_3/src/main/java/tema1/p3/LoremIpsumCopia.txt");

        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            fcopia.createNewFile();
            fr = new FileReader(f);
            br = new BufferedReader(fr);

            fw = new FileWriter(fcopia);
            bw = new BufferedWriter(fw);

            String linea;
            while ((linea = br.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }
            bw.write("Chema Fernandez");
            // Leo el archivo LoremIpsumCopia.txt

        } catch (Exception e) {
            System.out.println("No se pudo leer el archivo");
        }finally {
            try {
                if (br != null)
                    br.close();
                if (bw != null)
                    bw.close();
            } catch (IOException e) {

            }
        }

        File copia = new File("practica_3/src/main/java/tema1/p3/LoremIpsumCopia.txt");
        imprimeArchivo(copia);

    }

    public static void imprimeArchivo(File fcopia) {
        String linea;
        BufferedReader br;
        try{
            br = new BufferedReader(new FileReader(fcopia));
            while((linea = br.readLine()) != null){
                System.out.println(linea);
            }
        }catch (Exception e) {

        }
        
    }
}