package tema2.p4;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // listaPlanetas();
        //addPersonajes();
        quienMata();

    }

    public static void listaPlanetas() {
        String url = "jdbc:mariadb://localhost:3306/starwars";

        try {
            Connection con = DriverManager.getConnection(url, "star", "wars");
            StringBuilder sb = new StringBuilder();

            String consulta = String.format("SELECT * FROM planets WHERE diameter BETWEEN ? AND ?   ");
            PreparedStatement stmt = con.prepareStatement(consulta);

            Scanner sc = new Scanner(System.in);
            System.out.println("Escribe el valor del diámetro menor del rango:\n");
            stmt.setDouble(1, sc.nextDouble());
            System.out.println("Escribe el valor del diámetro mayor del rango:\n");
            stmt.setDouble(2, sc.nextDouble());

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                sb.append(rs.getInt("id") + " - ").append(rs.getString("name") + " - ");
                sb.append(rs.getInt("rotation_period") + " - ").append(rs.getInt("orbital_period") + " - ");
                sb.append(rs.getInt("diameter") + " - ").append(rs.getString("climate") + " - ");
                sb.append(rs.getString("gravity") + " - ").append(rs.getString("terrain") + " - ");
                sb.append(rs.getString("surface_water") + " - ").append(rs.getLong("population") + " - ");
                sb.append(rs.getString("created_date") + " - ").append(rs.getString("updated_date") + " - ");
                sb.append(rs.getString("url") + "\n");

            }

            System.out.println(sb.toString());

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addPersonajes() {
        String url = "jdbc:mariadb://localhost:3306/starwars";

        try {
            Connection conn = DriverManager.getConnection(url, "star", "wars");
            String consulta = "SELECT * FROM planets";
            PreparedStatement stmt = conn.prepareStatement(consulta);
            ResultSet rs = stmt.executeQuery();
            Map<Integer, String> mapaPlanetas = new LinkedHashMap<>();
            int posicion = 0;
            while (rs.next()) {
                
                mapaPlanetas.put(rs.getInt("id"), rs.getString("name"));
                posicion = rs.getInt("id");
            }

            if (mapaPlanetas.containsValue("Jakku")) {
                System.out.println("El planeta Jakku ya se encuentra en la base de datos");
            } else {
                String sql = "INSERT INTO planets (id, name, rotation_period, orbital_period, diameter," +
                        "climate, gravity, terrain, surface_water, population, created_date, updated_date, url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {

                    ps.setString(2, "Jakku");
                    ps.setInt(3, 23);
                    ps.setInt(4, 304);
                    ps.setInt(5, 10465);
                    ps.setString(6, "arid");
                    ps.setString(7, "1");
                    ps.setString(8, "standard");
                    ps.setString(9, "desert");
                    ps.setInt(10, 200000);
                    ps.setDate(11, new Date(System.currentTimeMillis()));
                    ps.setDate(12, new Date(System.currentTimeMillis()));
                    ps.setString(13, "https://swapi.co/api/planets/1/");
                    ps.setInt(1, posicion + 1);

                    ps.executeUpdate();
                    System.out.println("Nuevo planeta introducido en la base de datos");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // AÑADO CLASE INTERNA PARA DEVOLVER UN INT CON EL VALOR DE LOS PLANETAS
            class DevuelvePlaneta {
                int numPlaneta = 0;

                public int numeroPlaneta(String planeta, Map<Integer, String> mapa) {
                    for (Map.Entry<Integer, String> planet : mapa.entrySet()) {
                        if (planet.getValue().equals(planeta)) {
                            numPlaneta = planet.getKey();
                        }
                    }
                    return numPlaneta;
                }
            }

            // CREO UN OBJETO CON LA CLASE INTERNA PARA LLAMARLO CUANDO TENGA QUE BUSCAR EL
            // Nº DE PLANETA
            DevuelvePlaneta dvPlaneta = new DevuelvePlaneta();

            // DECLARO UNA VARIABLE INT PARA SABER EL NUMERO DE ID DEL CARACTER
            int personaje = 0;

            // AHORA UNA NUEVA CONSULTA PARA SELECCIONAR EL ID DE LOS PERSONAJES
            String consultaPersonajes = "SELECT * FROM characters";
            stmt = conn.prepareStatement(consultaPersonajes);
            ResultSet rsPersonajes = stmt.executeQuery();
            while (rsPersonajes.next()) {
                personaje = rsPersonajes.getInt("id");
            }

            // AHORA UNA NUEVA CONSULTA PARA AÑADIR LOS PERSONAJES
            String consultaAddPersonaje = "INSERT I(id, name, height, mass, hair_color, skin_color, eye_color, "
                    + "birth_year, gender, planet_id, created_date, updated_date, url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"NTO characters ; 

            String consultaPersonajesPelis = "INSERT INTO character_films (id_character, id_film) VALUES (?, ?)";

            stmt = conn.prepareStatement(consultaAddPersonaje);

            // AHORA SE INICIAN LAS INSERCIONES DE PERSONAJES EN EL EPISODIO VII
            try (PreparedStatement ps = conn.prepareStatement(consultaAddPersonaje); 
                    PreparedStatement ps2 = conn.prepareStatement(consultaPersonajesPelis)) {
                ps.setInt(1, personaje + 1);
                ps.setString(2, "Rey");
                ps.setInt(3, 170);
                ps.setFloat(4, 54);
                ps.setString(5, "black");
                ps.setString(6, "white");
                ps.setString(7, "brown");
                ps.setString(8, "15DBY");
                ps.setString(9, "female");
                ps.setInt(10, dvPlaneta.numeroPlaneta("Jakku", mapaPlanetas));
                ps.setDate(11, new Date(System.currentTimeMillis())); 
                ps.setDate(12, new Date(System.currentTimeMillis())); 
                ps.setString(13, "https://swapi.co/api/characters/1/");

                ps.executeUpdate();

                ps2.setInt(1, personaje + 1);
                ps2.setInt(2, 7);
                ps2.executeUpdate();

                personaje++;

                ps.setInt(1, personaje + 1);
                ps.setString(2, "Finn");
                ps.setInt(3, 178);
                ps.setFloat(4, 73);
                ps.setString(5, "black");
                ps.setString(6, "dark");
                ps.setString(7, "dark");
                ps.setString(8, "11DBY");
                ps.setString(9, "male");
                ps.setInt(10, dvPlaneta.numeroPlaneta("Kamino", mapaPlanetas));
                ps.setDate(11, new Date(System.currentTimeMillis())); 
                ps.setDate(12, new Date(System.currentTimeMillis())); 
                ps.setString(13, "https://swapi.co/api/characters/1/");

                ps.executeUpdate();

                ps2.setInt(1, personaje + 1);
                ps2.setInt(2, 7);
                ps2.executeUpdate();

                personaje++;

                ps.setInt(1, personaje + 1);
                ps.setString(2, "Kylo Ren");
                ps.setInt(3, 189);
                ps.setFloat(4, 89);
                ps.setString(5, "black");
                ps.setString(6, "white");
                ps.setString(7, "brown");
                ps.setString(8, "5DBY");
                ps.setString(9, "male");
                ps.setInt(10, dvPlaneta.numeroPlaneta("Chandrila", mapaPlanetas));
                ps.setDate(11, new Date(System.currentTimeMillis())); 
                ps.setDate(12, new Date(System.currentTimeMillis())); 
                ps.setString(13, "https://swapi.co/api/characters/1/");

                ps.executeUpdate();

                ps2.setInt(1, personaje + 1);
                ps2.setInt(2, 7);
                ps2.executeUpdate();

                personaje++;

            } catch (SQLException e) {
                e.printStackTrace();
            }            

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void quienMata(){
        String url = "jdbc:mariadb://localhost:3306/starwars";

        try{
            Connection conn = DriverManager.getConnection(url, "star", "wars");
            String consultaPelis = "SELECT * FROM films";
            PreparedStatement stmt = conn.prepareStatement(consultaPelis);
            ResultSet rsPelis = stmt.executeQuery();

            while(rsPelis.next()){
                System.out.println("\n" + rsPelis.getString("episode") + ": " + rsPelis.getString("title") + "\n");
                
                String consultaPersonajes = "SELECT p1.name AS muerto, p2.name AS asesino FROM deaths d" + 
                    " JOIN characters p1 ON d.id_character = p1.id" +
                    " JOIN characters p2 ON d.id_killer = p2.id" +
                    " WHERE d.id_film = " + rsPelis.getInt("id") +
                    " ORDER BY d.id_film";
                
                stmt = conn.prepareStatement(consultaPersonajes);
                ResultSet rsPersonajes = stmt.executeQuery();

                while(rsPersonajes.next()) {
                    System.out.println(rsPersonajes.getString("muerto") + ": " + rsPersonajes.getString("asesino"));
                }
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }    
}