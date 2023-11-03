package tema1.p4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {

        String ruta = "practica_4/src/main/java/tema1/p4/data.dat";
        File f = new File(ruta);
        boolean b = true;
        int numero = 25000;
        float decimal = 0.25f;
        double d = 0.00002567;
        char caracter = 'z';
        String linea = "Desarrollo de Aplicaciones Multiplataforma";
        byte bt = -128;

        //Se prueba si el archivo existe y de no existir lo crea y guarda los datos llamando a guardaDatos.
        //De otra manera solo guarda datos
        try {
            if (!f.exists()) {
                f.createNewFile();
                guardaDatos(ruta, b, numero, decimal, d, caracter, linea, bt);
                System.out.println("Datos guardados");
            } else {
                System.out.println("El archivo ya existe y solamente se guardarán los datos");
                guardaDatos(ruta, b, numero, decimal, d, caracter, linea, bt);
                System.out.println("Datos guardados");
            }
            //Llama a imprimeDatos para imprimir las variables por pantalla
            imprimeDatos(ruta);
        } catch (Exception e) {
            System.out.println("No se puede acceder o crear el fichero");
        }

    }

    public static void guardaDatos(String ruta, boolean b, int numero, float decimal, double d, char caracter,
            String linea, Byte bt) {
        FileOutputStream fos = null;
        DataOutputStream dos = null;
        try {
            fos = new FileOutputStream(ruta);
            dos = new DataOutputStream(fos);

            dos.writeBoolean(b);
            dos.writeInt(numero);
            dos.writeFloat(decimal);
            dos.writeDouble(d);
            dos.writeChar(caracter);
            dos.writeUTF(linea);
            dos.writeByte(bt);
        } catch (Exception e) {
            System.out.println("NO ha sido posible guardar los datos");
        } finally {
            try {
                fos.close();
                dos.close();
            } catch (Exception e) {
                System.out.println("No se pudieron cerrar los procesos correctamente");
            }
        }

    }

    public static void imprimeDatos(String ruta) {
        try {
            FileInputStream fis = new FileInputStream(ruta);
            DataInputStream dis = new DataInputStream(fis);

            boolean b = dis.readBoolean();
            int numero = dis.readInt();
            float decimal = dis.readFloat();
            double d = dis.readDouble();
            char caracter = dis.readChar();
            String linea = dis.readUTF();
            byte bt = dis.readByte();

            System.out.printf("boolean: %b\ndecimal: %d\nfloat: %f\ndouble: %f\nchar: %c\nString: %s\nbyte: %d\n", b,numero,decimal,d, caracter, linea, bt);

            dis.close();
        } catch (Exception e) {
            System.out.println("Error al leer los datos");
        }
    }

}