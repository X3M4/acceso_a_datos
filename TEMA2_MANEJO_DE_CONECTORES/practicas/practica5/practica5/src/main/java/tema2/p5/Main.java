package tema2.p5;

//MENSAJE PARA EL PROFESOR: ESTE CÓDIGO TIENE MUCHOS COMENTARIOS PORQUE HA SIDO NECESARIO PARA NO VOLVERME LOCO
import java.sql.*;

public class Main {

    public static void main(String[] args) throws SQLException {

        // INICIO DEL PRIMER EJERCICIO
        try {
            String bd = "jdbc:sqlite:/home/chema/Documentos/CFGS-DAM-SEGUNDO/ACCESO_A_DATOS/TEMA2_MANEJO_DE_CONECTORES/"
                    +
                    "practicas/practica1/src/main/resources/veterinaria";

            Connection conn = DriverManager.getConnection(bd);
            Statement stmt = conn.createStatement(); // Cambiado a Statement
            DatabaseMetaData dbmt = conn.getMetaData();
            ResultSet rsTablas = dbmt.getTables(null, "veterinaria", null, null);
            ResultSet primKey = null;
            ResultSet forKey = null;
            String nombre = "";

            String dbNOmbre = "veterinaria";
            creaDB(dbNOmbre);

            /*
             * La idea era meter el ResultSetMetaData en un List<ResultSetMetaData> pero
             * siempre se imprimen las columnas de propietario
             * Por lo que he hecho dos ResultSetMetada para poder hacer Strings.
             * He optado con mucho más trabajo de lo que hubiera sido necesario por crear
             * las tablas
             * construyendo un StringBuilder
             */
            while (rsTablas.next()) {
                nombre = rsTablas.getString("TABLE_NAME");
                if (!nombre.startsWith("sqlite_autoindex_") && !nombre.startsWith("sqlite_schema")) {

                    String consulta = "SELECT * FROM " + nombre;

                    // Construyo un ResultSet y los metadatos del ResultSet
                    ResultSet resultado = stmt.executeQuery(consulta);
                    ResultSetMetaData tabla = resultado.getMetaData();

                    // Construyo Metadatos de la tabla
                    DatabaseMetaData metadatos = conn.getMetaData();

                    primKey = metadatos.getPrimaryKeys(null, null, tabla.getTableName(1));

                    forKey = metadatos.getImportedKeys(null, null, tabla.getTableName(1));

                    /*
                     * while(forKey.next()){
                     * System.out.println(String.format("foreign key (%s) references %s(%s)",
                     * forKey.getString("FKCOLUMN_NAME"),
                     * forKey.getString("PKTABLE_NAME"), forKey.getString("PKCOLUMN_NAME")));
                     * }
                     */

                    creaTabla(dbNOmbre, metadatos, nombre, primKey, forKey);
                    System.out.println("\n");

                    while (primKey.next()) {
                        modificaPK(dbNOmbre, nombre, primKey.getString("COLUMN_NAME"));

                    }

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Creo los métodos para la base de datos en MariaDB

    }

    public static void creaDB(String nombre) {
        String url = "jdbc:mariadb://localhost:3306/";

        try (Connection conn = DriverManager.getConnection(url, "root", "")) {
            Statement stmt = conn.createStatement();
            String database = "CREATE DATABASE IF NOT EXISTS " + nombre;
            stmt.executeQuery(database);
            System.out.println(String.format("\nBase de datos %s creada", nombre));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*
     * EN ESTA FUNCIÓN HAGO LAS TAREAS DEL EJERCICIO 2
     * EN ESTA FUNCIÓN SE CONSTRUYE UN STRINGBUILDER CON EL NOMBRE DE LA TABLA Y LAS
     * COLUMNAS
     * AL USAR CREATE TABLE IF NOT EXISTS ES MUY COMPLICADO QUE DE UN ERROR. SI
     * EXISTE SE CREA Y SI NO EXISTE NO.
     */
    public static void creaTabla(String databaseNombre, DatabaseMetaData dbmt, String nombreTabla, ResultSet primKey,
            ResultSet forkey) {
        String url = "jdbc:mariadb://localhost:3306/" + databaseNombre;
        try (Connection conn = DriverManager.getConnection(url, "root", "")) {
            Statement stmt = conn.createStatement();
            ResultSet columnas = dbmt.getColumns(null, databaseNombre, nombreTabla, null);
            StringBuilder sb = new StringBuilder();
            sb.append("CREATE TABLE IF NOT EXISTS ").append(nombreTabla + " (");

            while (columnas.next()) {
                if (columnas.getString("COLUMN_NAME").equals("id")
                        || columnas.getString("TYPE_NAME").equals("DATE")
                        || columnas.getString("TYPE_NAME").equals("int")
                        || columnas.getString("TYPE_NAME").equals("INT")) {
                    sb.append(columnas.getString("COLUMN_NAME") + " ");
                    sb.append(columnas.getString("TYPE_NAME") + ",");

                } else {
                    sb.append(columnas.getString("COLUMN_NAME") + " ");
                    sb.append(columnas.getString("TYPE_NAME") + " ")
                            .append("" + "(" + columnas.getString("COLUMN_SIZE") + "),");

                }

            }

            sb.deleteCharAt(sb.length() - 1);
            sb.append(")");

            System.out.println(sb.toString());
            stmt.executeUpdate(sb.toString());
            System.out.println(
                    String.format("La tabla %s se ha introducido en la base de datos %s", nombreTabla, databaseNombre));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void modificaPK(String database, String nombreTabla, String nombreColumna) throws SQLException {
        String url = "jdbc:mariadb://localhost:3306/" + database;

        try (Connection conn = DriverManager.getConnection(url, "root", "")) {
            Statement stmt = conn.createStatement();
            String modificacion = "ALTER TABLE " + nombreTabla + " ADD PRIMARY KEY (" + nombreColumna + ")";
            System.out.println(modificacion);
            stmt.executeUpdate(modificacion);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Clave primaria añadida");

    }

    public static String sumaFK(ResultSet forKey) throws SQLException {
        String claveForanea = "";
        while (forKey.next()) {
            claveForanea = String.format(",foreign key (%s) references %s(%s),", forKey.getString("FKCOLUMN_NAME"),
                    forKey.getString("PKTABLE_NAME"), forKey.getString("PKCOLUMN_NAME"));
        }
        return claveForanea;
    }

    public static boolean existeTabla(String nombre, ResultSetMetaData tablas) throws SQLException {

        boolean existeTabla = false;
        for (int i = 0; i < tablas.getColumnCount(); i++) {
            if (tablas.getTableName(i + 1).equals(nombre)) {
                existeTabla = true;
            } else {
                existeTabla = false;
            }
        }
        return existeTabla;
    }

    public static void imprimeDatos(ResultSetMetaData rsmdt) {
        try {
            int num = rsmdt.getColumnCount();
            for (int i = 1; i <= num; i++) {
                System.out.println(rsmdt.getColumnName(i));

            }
            System.out.println("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}