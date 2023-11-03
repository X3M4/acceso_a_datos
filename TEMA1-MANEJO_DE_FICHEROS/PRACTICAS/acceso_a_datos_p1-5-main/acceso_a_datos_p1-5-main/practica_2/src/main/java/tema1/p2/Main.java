package tema1.p2;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // CREACIÓN DE OBJETOS Y VARIABLES NECESARIOS
        String ruta;
        File archivo;
        final String estructura;
        Scanner sc = new Scanner(System.in);

        // INICIO DE LA INTERACCIÓN CON EL USUARIO
        System.out.println("Escribe la ruta al directorio");
        estructura = "/PrimerDirectorio/SegundoDirectorio/TercerDirectorio";
        ruta = sc.nextLine() + estructura;
        archivo = new File(ruta);
        sc.close();
        creaEstructura(archivo, ruta);

        // LLAMO A creaEstructura PARA CREAR LOS 3 NIVELES DE DIRECTORIOS
    }

    public static void creaEstructura(File archivo, String ruta) {

        ruta = ruta + "/MiFichero.txt";
        if (archivo.mkdirs()) {
            System.out.println("Estructura creada correctamente ");
            creaArchivo(ruta);
        } else {
            try {
                creaArchivo(ruta);
            } catch (Exception e) {
                System.out.println("El archivo ya existe o no puede ser creado");
            }
        }

    }

    // creaArchivo PARA CREAR EL ARCHIVO
    public static void creaArchivo(String ruta) {

        File nuevo = new File(ruta);

        try {
            nuevo.createNewFile();
            System.out.println("Archivo creado correctamente");
        } catch (Exception e) {
            System.out.println("Error al cargar el fichero");
        }
    }
}