package tema1.p1;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // CREACIÓN DE OBJETOS Y VARIABLES NECESARIOS
        String directorio;
        File archivo;
        String[] f_lista;
        File[] fl_lista;
        Scanner sc = new Scanner(System.in);

        // INICIO DE LA INTERACCIÓN CON EL USUARIO
        System.out.println("Escribe la ruta al directorio");
        directorio = sc.nextLine();
        archivo = new File(directorio);
        f_lista = archivo.list();
        fl_lista = archivo.listFiles();

        // LLAMADA A LA FUNCIÓN listDir y listFileDir
        if (archivo.exists()) {
            listDir(f_lista);
            listFileDir(fl_lista);
            sc.close();
        }else{
            System.out.println("El directorio no existe");
        }

    }

    // FUNCION listDir IMPRIME EL CONTENIDO EL CONTENIDO DEL DIRECTORIO CON list
    public static void listDir(String[] lista) {
        System.out.println("\n MOSTRANDO CONTENIDO DEL DIRECTORIO CON list()");
        for (int i = 0; i < lista.length; i++) {
            System.out.println(lista[i]);
        }
    }

    // FUNCION listDir IMPRIME EL CONTENIDO EL CONTENIDO DEL DIRECTORIO CON
    // listFile()
    public static void listFileDir(File[] lista) {
        System.out.println("\nMOSTRANDO CONTENIDO DEL DIRECTORIO CON listFiles()");
        for (File f : lista) {
            System.out.println(f.getName());
        }
    }
}