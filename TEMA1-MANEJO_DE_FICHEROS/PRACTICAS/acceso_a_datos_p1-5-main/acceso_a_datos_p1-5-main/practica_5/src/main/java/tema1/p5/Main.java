package tema1.p5;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args){
        Persona p1 = new Persona("Manuel", "González", "34567898H");
        Persona p2 = new Persona("Miguel", "Heras", "22637125L");
        String ruta = "practica_5/src/main/java/tema1/p5/personas.data";
        File archivo = new File(ruta);

        //SI NO EXISTE EL ARCHIVO LO CREA Y SEGUIDAMENTE LLAMA A LA FUNCIÓN PARA GUARDAR LOS OBJETOS
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
                Persona[] parray = new Persona[2];
                parray[0] = p1;
                parray[1] = p2;
                guardaPersonas(ruta, parray);

            } else {
                System.out.println("El archivo ya existe y solamente se guardará nueva información personal");
                Persona[] parray = new Persona[2];
                parray[0] = p1;
                parray[1] = p2;
                guardaPersonas(ruta, parray);
            }
            imprimePersonas(ruta);
        } catch (Exception e) {
            System.out.println("Error al crear el archivo Personas.data");
        }

    }
    //LLAMO A LA FUNCIÓN  guardaPersonas PARA GUARDAR UN ARRAY DE OBJETOS Persona Y PODER GUARDAR SIN SOBREESCRIBIR
    public static void guardaPersonas(String ruta, Persona[] parray) {
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta, true));
            for (int i = 0; i < parray.length; i++) {
                oos.writeObject(parray[i]);
            }
            oos.close();
        } catch (Exception e) {
            System.out.println("Error al guardar el objeto Persona");
        }
    }

    //LLAMO A LA FUNCIÓN imprimePersonas PARA MOSTRAR POR PANTALLA LOS OBJETOS Persona
    public static void imprimePersonas(String ruta){
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
            try {

                Persona p;
                while ((p = (Persona) ois.readObject()) != null) {
                    System.out.println(p.toString());
                }
                ois.close();
            } catch (EOFException e) {
                System.out.println("Impresión realizada");
            }
        } catch (IOException e) {
            System.out.println("Se acabaron de imprimir todos los objetos Persona");
        }catch(ClassNotFoundException e){
         System.out.println("Se acabaron de imprimir todos los objetos Persona");
        }

    }

}