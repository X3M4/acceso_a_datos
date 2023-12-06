package examen;

import java.io.File;
import java.sql.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Main {
    public static void main(String[] args) {
        existenTablas();
    }

    public static void existenTablas() {
        String url = "jdbc:mariadb://localhost:3306/gotbd";

        try {
            String dbNombre = "gotdb";
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement stmt = con.createStatement();
            DatabaseMetaData metadatos = con.getMetaData();
            ResultSet tablas = metadatos.getTables(null, dbNombre, null, null);

            while (tablas.next()) {
                if (!tablas.getString("TABLE_NAME").equals("character")) {
                    String tabla = "CREATE TABLE IF NOT EXISTS characters (id INT AUTO_INCREMENT PRIMARY KEY,name VARCHAR(255) NOT NULL,gender VARCHAR(255),"
                            +
                            "culture VARCHAR(255)," +
                            "born VARCHAR(255)," +
                            "died VARCHAR(255)," +
                            "alive BOOLEAN," +
                            "father VARCHAR(255)," +
                            " mother VARCHAR(255)," +
                            "spouse VARCHAR(255))";

                    stmt.executeQuery(tabla);

                }
                if (!tablas.getString("TABLE_NAME").equals("titles")) {
                    String tabla = "CREATE TABLE IF NOT EXISTS titles (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "title VARCHAR(255) NOT NULL," +
                            "id_char INT NOT NULL," +
                            "FOREIGN KEY(id_char) REFERENCES characters(id))";
                    stmt.executeQuery(tabla);

                }
                if (!tablas.getString("TABLE_NAME").equals("aliases")) {
                    String tabla = "CREATE TABLE IF NOT EXISTS aliases (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "alias VARCHAR(255) NOT NULL," +
                            "id_char INT NOT NULL," +
                            "FOREIGN KEY(id_char) REFERENCES characters(id))";

                    stmt.executeQuery(tabla);

                }
                if (!tablas.getString("TABLE_NAME").equals("allegiances")) {
                    String tabla = "CREATE TABLE IF NOT EXISTS allegiances (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "allegiance VARCHAR(255) NOT NULL)";

                    stmt.executeQuery(tabla);

                }
                if (tablas.getString("TABLE_NAME").equals("books")) {
                    String tabla = "CREATE TABLE IF NOT EXISTS books (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "book VARCHAR(255) NOT NULL)";

                    stmt.executeQuery(tabla);

                }
                if (tablas.getString("TABLE_NAME").equals("tvSeries")) {
                    String tabla = "CREATE TABLE IF NOT EXISTS tvseries (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY," +
                            "season VARCHAR(255) NOT NULL)";

                    stmt.executeQuery(tabla);
                    System.out.println("Tablas creadas");
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void introducePersonajes() {
        try {
            String dbNombre = "gotdb";
            String url = "jdbc:mariadb://localhost:3306/gotbd";
            Connection con = DriverManager.getConnection(url, "root", "");
            Statement stmt = con.createStatement();
            DatabaseMetaData metadatos = con.getMetaData();
            ResultSet tablas = metadatos.getTables(null, dbNombre, null, null);

            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document documento = builder.parse(new File("DAM_AD_UD01_P6_GOT_Ini.xml"));
            documento.getDocumentElement().normalize();
            NodeList personajes = documento.getElementsByTagName("character");
            String pers = "INSERT I(name, gender, culture, born, died, alive, "
            + "father, mother, spouse) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            StringBuilder sb = new StringBuilder();


            for(int i = 0; i < personajes.getLength(); i++) {
                Node personaje = personajes.item(i);
                Element elemento = (Element) personaje;
                try(PreparedStatement ps = con.prepareStatement(pers)){
                    
                ps.setInt(1, Integer.parseInt(elemento.getElementsByTagName("id").item(0).getTextContent()));
                ps.setString(2, elemento.getElementsByTagName("name").item(0).getTextContent());
                ps.setString(3, elemento.getElementsByTagName("gender").item(0).getTextContent());
                ps.setString(4, elemento.getElementsByTagName("culture").item(0).getTextContent());
                ps.setString(5, elemento.getElementsByTagName("born").item(0).getTextContent());
                ps.setBoolean(6, Boolean.parseBoolean(elemento.getElementsByTagName("died").item(0).getTextContent()));
                ps.setBoolean(7, Boolean.parseBoolean(elemento.getElementsByTagName("alive").item(0).getTextContent()));
                ps.setString(8, elemento.getElementsByTagName("father").item(0).getTextContent());
                ps.setString(9, elemento.getElementsByTagName("mother").item(0).getTextContent());
                ps.setString(10, elemento.getElementsByTagName("spouse").item(0).getTextContent());
                
                ps.executeUpdate();

                }catch(SQLException e){
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}