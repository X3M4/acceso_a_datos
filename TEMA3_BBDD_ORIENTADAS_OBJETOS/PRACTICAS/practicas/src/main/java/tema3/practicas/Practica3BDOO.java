package tema3.practicas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class Practica3BDOO {
    public static void main(String[] args) {

        ODB odb = ODBFactory.open("practica_LOTR");
        // MÉTODOS PARA RELLENAR TABLAS
        //tablaRealms(odb);
        // tablaCharacters(odb);
        // tablaMarriage(odb);
        // tablaBooks(odb);
        // tablaChapter(odb);
        // tablaMovies(odb);
        // tablaBooksMovies(odb);
        tablaDialogs(odb);
        odb.close();

        Objects<Book> libros = odb.getObjects(Book.class);
        Objects<Chapter> capitulos = odb.getObjects(Chapter.class);
        Objects<Character> personajes = odb.getObjects(Character.class);
        Objects<Dialog> dialogos = odb.getObjects(Dialog.class);
        Objects<Movie> peliculas = odb.getObjects(Movie.class);
        Objects<Realm> reinos = odb.getObjects(Realm.class);

        /*
         * while (reinos.hasNext()) {
         * Realm r = reinos.next();
         * System.out.println(r);
         * }
         */

        /*
         * while(personajes.hasNext()){
         * Character r = personajes.next();
         * System.out.println(r);
         * }
         */

        /*
         * while(capitulos.hasNext()){
         * Chapter r = capitulos.next();
         * System.out.println(r);
         * }
         */

        /*
         * while (peliculas.hasNext()) {
         * Movie m = peliculas.next();
         * System.out.println(m);
         * }
         */

    }

    // MÉTODOS PARA PARSEAR LOS STRINGS ID DE LAS TABLAS A ENTEROS Y HEXADECIMALES
    public static int parseaInteger(String string) {
        return Integer.parseInt(string);
    }

    public static int parseaIntHexadecimal(String string) {
        return Integer.parseInt(string, 16);
    }

    // MÉTODO PARA PASAR LOS DATOS DE LA TABLA REALMS
    public static void tablaRealms(ODB bdoo) {

        String url = "jdbc:mariadb://localhost:3306/lotr";

        try {
            Connection con = DriverManager.getConnection(url, "star", "wars");
            String consulta = "SELECT * FROM realms";
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            int reino = 0;
            while (rs.next()) {
                Realm realm = new Realm(parseaInteger(rs.getString("id")), rs.getString("name"),
                        rs.getInt("population"), rs.getInt("area"));
                bdoo.store(realm);
                bdoo.commit();
                System.out.println(reino++);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // MÉTODO PARA PASAR LOS DATOS DE LA TABLA CHARACTERS
    public static void tablaCharacters(ODB bdoo) {

        String url = "jdbc:mariadb://localhost:3306/lotr";

        try {
            Connection con = DriverManager.getConnection(url, "star", "wars");
            String consulta = "SELECT * FROM characters";
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Character character = new Character(parseaIntHexadecimal(rs.getString("id")), rs.getString("name"),
                        rs.getString("wikiUrl"),
                        rs.getString("race"), rs.getString("birth"), rs.getString("gender"), rs.getString("death"),
                        rs.getString("hair"),
                        rs.getString("height"), rs.getInt("id_realm"));

                bdoo.store(character);

                Objects<Realm> reinos = bdoo.getObjects(Realm.class);

                int numero = 0;

                while (reinos.hasNext()) {
                    Realm r = reinos.next();
                    if (character.getRealm() == r.getId()) {
                        r.addCharacter(character);
                        numero++;
                        bdoo.store(r);

                        System.out.println("Personajes añadido: " + numero);
                    }
                }

                bdoo.commit();

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // MÉTODO PARA RECUPERAR UN PERSONAJE. ME ES ÚTIL PARA PASAR LA TABLA MARRIAGES
    public static Character recuperaPersonaje(ODB bdoo, int id) {
        Character c = null;
        Objects<Character> objects = bdoo.getObjects(Character.class);
        while (objects.hasNext()) {
            Character character = objects.next();
            if (character.getId() == id) {
                c = character;
            }
        }
        return c;
    }

    // MÉTODO PARA PASAR LA TABLA MARRIAGE
    public static void tablaMarriage(ODB bdoo) {

        String url = "jdbc:mariadb://localhost:3306/lotr";

        try {
            Connection con = DriverManager.getConnection(url, "star", "wars");
            String consulta = "SELECT * FROM marriage";
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            int pareja = 0;
            while (rs.next()) {
                int id = parseaIntHexadecimal(rs.getString("id_spouse1"));

                String _id = rs.getString("id_spouse2");

                if (_id != null) {
                    Integer id_spouse = parseaIntHexadecimal(rs.getString("id_spouse2"));
                    Character ch = recuperaPersonaje(bdoo, id);

                    if (ch != null) {
                        ch.setSpouse(recuperaPersonaje(bdoo, id_spouse));
                        bdoo.store(ch);
                        bdoo.commit();
                        System.out.println("pareja cambiada" + pareja++);
                    }
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public static void tablaBooks(ODB bdoo) {
        String url = "jdbc:mariadb://localhost:3306/lotr";

        try {
            Connection con = DriverManager.getConnection(url, "star", "wars");
            String consulta = "SELECT * FROM books";
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            int libro = 0;

            while (rs.next()) {
                Book book = new Book(parseaInteger(rs.getString("id")), rs.getString("title"));
                bdoo.store(book);
                bdoo.commit();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // MÉTODOS PARA AÑADIR LA TABLA CHAPTER Y CADA CAPÍTULO A AL SET CHAPTER DE LA
    // TABLA BOOK
    public static void tablaChapter(ODB bdoo) {

        String url = "jdbc:mariadb://localhost:3306/lotr";

        try {
            Connection con = DriverManager.getConnection(url, "star", "wars");
            String consulta = "SELECT * FROM chapters";
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            int capitulo = 0;

            while (rs.next()) {
                Chapter chapter = new Chapter(parseaIntHexadecimal(rs.getString("id")), rs.getString("chapter_name"),
                        parseaInteger(rs.getString("id_book")));

                bdoo.store(chapter);

                Objects<Book> libros = bdoo.getObjects(Book.class);

                while (libros.hasNext()) {
                    Book b = libros.next();
                    if (b.getId() == chapter.getId_book()) {
                        b.addChapter(chapter);
                        System.out.println(
                                String.format("Capítulo %s añadido a: %s", chapter.getChapter_name(), b.getTittle()));
                        bdoo.store(b);
                        bdoo.commit();

                    }
                }

            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    // MÉTODO PARA AÑADIR LA TABLA MOVIES
    public static void tablaMovies(ODB bdoo) {
        String url = "jdbc:mariadb://localhost:3306/lotr";

        try {
            Connection con = DriverManager.getConnection(url, "star", "wars");
            String consulta = "SELECT * FROM movies";
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            int pelis = 0;
            while (rs.next()) {
                Movie movie = new Movie(parseaIntHexadecimal(rs.getString("id")), rs.getString("name"),
                        rs.getInt("runtimeInMinutes"), rs.getInt("budgetInMillions"),
                        rs.getInt("boxOfficeRevenueInMillions"),
                        rs.getInt("academyAwardNominations"), rs.getInt("academyAwardWins"),
                        rs.getInt("rottenTomatoesScore"));
                bdoo.store(movie);
                bdoo.commit();
                System.out.println("Película añadida: " + pelis++);
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static Book recuperaLibro(ODB bdoo, int id) {
        Objects<Book> libros = bdoo.getObjects(Book.class);
        Book libro = null;
        while (libros.hasNext()) {
            Book l = libros.next();
            if (l.getId() == id) {
                libro = l;
            }
        }
        return libro;
    }

    public static Movie recuperaPelicula(ODB bdoo, int id) {
        Objects<Movie> pelis = bdoo.getObjects(Movie.class);
        Movie peli = null;
        while (pelis.hasNext()) {
            Movie m = pelis.next();
            if (m.getId() == id) {
                peli = m;
            }
        }
        return peli;
    }

    // MÉTODO PARA AÑADIR LA TABLA BOOKS_MOVIES
    public static void tablaBooksMovies(ODB bdoo) {
        String url = "jdbc:mariadb://localhost:3306/lotr";

        try {
            Connection con = DriverManager.getConnection(url, "star", "wars");
            String consulta = "SELECT * FROM books_movies";
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();

            Objects<Movie> pelis = bdoo.getObjects(Movie.class);
            Objects<Book> libros = bdoo.getObjects(Book.class);
            List<Book> listaLibros = new ArrayList<Book>();
            List<Movie> listaPelis = new ArrayList<Movie>();

            while (rs.next()) {
                Book libro = recuperaLibro(bdoo, parseaInteger(rs.getString("id_book")));
                listaLibros.add(libro);

                Movie peli = recuperaPelicula(bdoo, parseaIntHexadecimal(rs.getString("id_movie")));
                listaPelis.add(peli);

                // System.out.println(libro.getTittle()+"----"+ peli.getName()+"\n");
                // System.out.println(libro.getId()+"----"+ peli.getId()+"\n");
            }

            for (Book libro : listaLibros) {
                System.out.println("nombre del libro: " + libro.getTittle());
            }

            while (libros.hasNext()) {
                Book l = libros.next();
                for (Movie peli : listaPelis) {
                    if (peli.getName().contains(l.getTittle())) {
                        l.addMovie(peli);
                        System.out.println("Pelis añadidos: " + peli.getName());
                        bdoo.store(l);
                    }
                }

                bdoo.commit();
            }

            while (pelis.hasNext()) {
                Movie m = pelis.next();
                for (Book libro : listaLibros) {
                    if (m.getName().contains(libro.getTittle())) {
                        m.addBook(libro);
                        System.out.println("Libros añadidos: " + libro.getTittle());
                        bdoo.store(m);

                    }
                }
                bdoo.commit();

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
    }

    // MÉTODO PARA AÑADIR DIALOGS
    public static void tablaDialogs(ODB bdoo) {
        String url = "jdbc:mariadb://localhost:3306/lotr";
        try {
            Connection con = DriverManager.getConnection(url, "star", "wars");
            String consulta = "SELECT * FROM dialogs";
            PreparedStatement ps = con.prepareStatement(consulta);
            ResultSet rs = ps.executeQuery();
            Objects<Movie> peliculas = bdoo.getObjects(Movie.class);

            while (rs.next()) {
                Dialog dialogo = new Dialog(parseaIntHexadecimal(rs.getString("id")), rs.getString("dialog"),
                        parseaIntHexadecimal(rs.getString("id_movie")),
                        parseaIntHexadecimal(rs.getString("id_Character")));

                bdoo.store(dialogo);

                while (peliculas.hasNext()) {
                    Movie m = peliculas.next();
                    if (m != null) {
                        if (m.getId() == dialogo.getId_movie()) {
                            m.addDialog(dialogo);
                            bdoo.store(m);
                            bdoo.commit();
                            System.out.println(
                                    String.format("Añadido diálogo %d a %s", dialogo.getId(), m.getName()));
                        }
                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

}