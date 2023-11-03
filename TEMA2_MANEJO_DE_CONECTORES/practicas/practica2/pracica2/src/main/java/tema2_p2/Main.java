package tema2_p2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mariadb://localhost:3306/starwars";
        try {
            // CREAMOS LA CONEXION A LA BASE DE DATOS CON USUARIO Y CONTRASEÑA
            Connection conn = DriverManager.getConnection(url, "star", "wars");
            // OBTENEMOS LOS METADATOS Y LAS TABLAS DE LA BASE DE DATOS
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet result = dbmd.getTables(null, "starwars", null, null);

            Scanner sc = new Scanner(System.in);
            System.out.println("Escribe 1 para imprimir por pantalla \n" +
                    "EScribe 2 para guardar en un documento\n");
            String eleccion = sc.nextLine();

            if (eleccion.equals("1")) {
                muestraEnPantalla(dbmd, result);
            } else if (eleccion.equals("2")) {
                System.out.println("Escribe la ruta del archivo donde se guardarán los datos de la base de datos de Star Wars");
                String ruta = sc.nextLine();
                File archivo = new File(ruta);
                File[] lista = archivo.listFiles();

                for (File f : lista) {
                    if (f.getName().equals("Metadatos_SW.txt")) {
                        guardaEnArchivo(ruta, dbmd, result);
                    }else{
                        try{
                            File fl = new File(ruta, "Metadatos_SW.txt");
                            fl.createNewFile();
                            guardaEnArchivo(ruta, dbmd, result);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                
            }

            result.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void muestraEnPantalla(DatabaseMetaData dbmd, ResultSet result) throws Exception {
        while (result.next()) {
            // MOSTRAMOS POR PANTALLA EL NOMBRE DE CADA TABLA
            String tabla = result.getString("TABLE_NAME");
            System.out.println("Tabla: " + tabla);

            // SE INICIA RESULTSET CON LAS CLAVES PRIMARIAS Y LAS COLUMNAS
            ResultSet clavePrimaria = dbmd.getPrimaryKeys(null, null, tabla);
            ResultSet columnas = dbmd.getColumns(null, "starwars", tabla, null);

            // EN ESTE STRINGBUILDER SE VA ALMACENANDO LOS STRINGS
            StringBuilder sb = new StringBuilder("Clave primaria: ");

            while (clavePrimaria.next()) {
                String columna = clavePrimaria.getString("COLUMN_NAME");
                sb.append(columna).append(" - ");
            }
            sb.delete(sb.length() - 3, sb.length());

            sb.append("\n\n" + "Columnas\n");
            while (columnas.next()) {
                String nombreCol = columnas.getString("COLUMN_NAME");
                String tipoCol = columnas.getString("TYPE_NAME");
                sb.append(nombreCol).append(" - ").append(tipoCol + "\n");
            }
            System.out.println(sb.toString());
            // SE CIERRAN LOS RESULTSETS DESPUES DE CADA CICLO DEL WHILE
            clavePrimaria.close();
            columnas.close();
        }
    }

    public static void guardaEnArchivo(String ruta, DatabaseMetaData dbmd, ResultSet result) throws Exception {

        File archivo = new File(ruta, "Metadatos_SW.txt");

        while (result.next()) {
            // MOSTRAMOS POR PANTALLA EL NOMBRE DE CADA TABLA
            String tabla = result.getString("TABLE_NAME");

            try (FileWriter fw = new FileWriter(archivo, true)) {
                fw.write("Tabla: " + tabla);
            } catch (IOException e) {

            }

            // SE INICIA RESULTSET CON LAS CLAVES PRIMARIAS Y LAS COLUMNAS
            ResultSet clavePrimaria = dbmd.getPrimaryKeys(null, null, tabla);
            ResultSet columnas = dbmd.getColumns(null, "starwars", tabla, null);

            // EN ESTE STRINGBUILDER SE VA ALMACENANDO LOS STRINGS
            StringBuilder sb = new StringBuilder("Clave primaria: ");

            while (clavePrimaria.next()) {
                String columna = clavePrimaria.getString("COLUMN_NAME");
                sb.append(columna).append(" - ");
            }
            sb.delete(sb.length() - 3, sb.length());

            sb.append("\n\n" + "Columnas\n");
            while (columnas.next()) {
                String nombreCol = columnas.getString("COLUMN_NAME");
                String tipoCol = columnas.getString("TYPE_NAME");
                sb.append(nombreCol).append(" - ").append(tipoCol + "\n");
            }
            try (FileWriter fw = new FileWriter(archivo, true)) {
                fw.write(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            // SE CIERRAN LOS RESULTSETS DESPUES DE CADA CICLO DEL WHILE
            clavePrimaria.close();
            columnas.close();
            
        }
        System.out.println("Datos guardados correctamente");
    }
}