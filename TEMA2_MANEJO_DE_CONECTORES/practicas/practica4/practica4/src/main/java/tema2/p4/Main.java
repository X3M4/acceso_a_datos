package tema2.p4;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
             
        listaPlanetas();
        
    }

    public static void listaPlanetas(){
        String url = "jdbc:mariadb://localhost:3306/starwars";

        try{
            Connection con = DriverManager.getConnection(url, "star","wars");
            StringBuilder sb = new StringBuilder();

            String consulta = String.format("SELECT * FROM planets WHERE diameter BETWEEN ? AND ?   ");
            PreparedStatement stmt = con.prepareStatement(consulta);

            Scanner sc = new Scanner(System.in);
            System.out.println("Escribe el valor del diámetro menor del rango:\n");
            stmt.setDouble(1, sc.nextDouble());
            System.out.println("Escribe el valor del diámetro mayor del rango:\n");
            stmt.setDouble(2, sc.nextDouble());

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                sb.append(rs.getInt("id")+" - ").append(rs.getString("name") + " - ");
                sb.append(rs.getInt("rotation_period")+" - ").append(rs.getInt("orbital_period") + " - ");
                sb.append(rs.getInt("diameter")+" - ").append(rs.getString("climate") + " - ");
                sb.append(rs.getString("gravity")+" - ").append(rs.getString("terrain") + " - ");
                sb.append(rs.getString("surface_water")+" - ").append(rs.getLong("population") + " - ");
                sb.append(rs.getString("created_date")+" - ").append(rs.getString("updated_date") + " - ");
                sb.append(rs.getString("url")+"\n");

            }

            System.out.println(sb.toString());

        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}