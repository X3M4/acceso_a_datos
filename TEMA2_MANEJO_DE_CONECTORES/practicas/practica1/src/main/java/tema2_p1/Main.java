package tema2_p1;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try{
            String url = "jdbc:sqlite:src/main/resources/veterinaria";
            Connection con = DriverManager.getConnection(url);
            DatabaseMetaData met = con.getMetaData();
            ResultSet rs = met.getTables(null, "midb", null, null);

            while (rs.next()) {
                
                String tabla = rs.getString("TABLE_NAME");
                
                System.out.println("Tabla: " + tabla);
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}