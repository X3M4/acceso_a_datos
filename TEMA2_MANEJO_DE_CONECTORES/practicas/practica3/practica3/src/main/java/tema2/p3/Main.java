package tema2.p3;

import java.sql.*;
public class Main {
    public static void main(String[] args) {
        planetas();
        quienEsJedi();
        insertaPeliculas();
    }

    public static void planetas(){
        String url = "jdbc:mariadb://localhost:3306/starwars";

        try {
            // CREAMOS LA CONEXION A LA BASE DE DATOS CON USUARIO Y CONTRASEÑA
            Connection conn = DriverManager.getConnection(url, "star", "wars");
            // OBTENEMOS LOS METADATOS Y LAS TABLAS DE LA BASE DE DATOS
            String sql = "SELECT NAME FROM `planets`";
            
            try{
                Statement sentencia = conn.createStatement();
                ResultSet rs = sentencia.executeQuery(sql);

                while (rs.next()) {
                    System.out.printf("%s\n", rs.getString(1));
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void quienEsJedi(){
        String url = "jdbc:mariadb://localhost:3306/starwars";

        try {
            // CREAMOS LA CONEXION A LA BASE DE DATOS CON USUARIO Y CONTRASEÑA
            Connection conn = DriverManager.getConnection(url, "star", "wars");
            // OBTENEMOS LOS METADATOS Y LAS TABLAS DE LA BASE DE DATOS
            String sql = "SELECT  * FROM characters P JOIN character_affiliations A ON P.id = A.id_character WHERE A.id_affiliation = 1";
            
            try{
                Statement sentencia = conn.createStatement();
                ResultSet rs = sentencia.executeQuery(sql);

                while (rs.next()) {
                    System.out.printf("%s %s %s %s %s %s %s %s %s %s %s %s %s\n", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
                }
            }catch(Exception e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertaPeliculas(){
        String url = "jdbc:mariadb://localhost:3306/starwars";

        try {
            // CREAMOS LA CONEXION A LA BASE DE DATOS CON USUARIO Y CONTRASEÑA
            Connection conn = DriverManager.getConnection(url, "star", "wars");
            // OBTENEMOS LOS METADATOS Y LAS TABLAS DE LA BASE DE DATOS
            String sql = "INSERT INTO films (id, episode, title) VALUES (?, ?, ?)";
            
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setInt(1, 7);
                ps.setString(2, "Episode VII");
                ps.setString(3, "The Force Awakens");

                ps.executeQuery();

                ps.setInt(1, 8);
                ps.setString(2, "Episode VIII");
                ps.setString(3, "The Last Jedi");

                ps.executeQuery();

                ps.setInt(1, 9);
                ps.setString(2, "Episode IX");
                ps.setString(3, "The Rise of Skywalker");

                ps.executeQuery();
                
            }catch(Exception e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}