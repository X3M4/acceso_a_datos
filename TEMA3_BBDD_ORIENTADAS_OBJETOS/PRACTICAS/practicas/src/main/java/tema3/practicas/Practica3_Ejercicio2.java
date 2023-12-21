package tema3.practicas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;

public class Practica3_Ejercicio2 {

    public static void main(String[] args) {
        ODB odb = ODBFactory.open("practica_LOTR");
        // cuantosCasados(odb);
        // cuantosBaggings(odb);
        // infoPelisPresupRottenTomatoes(odb);
        // infoValinorRealm(odb);
        // infoValinorCharacter(odb);
        peliculasElHobbit(odb);
    }

    // MÉTODO PARA OBTENER CUANTOS ESTÁN CASADOS Y CUANTOS NO
    public static void cuantosCasados(ODB odb) {
        Objects<Character> personajes = odb.getObjects(Character.class);

        int casados = 0;
        int solteros = 0;

        try {
            while (personajes.hasNext()) {
                Character c = personajes.next();
                if (c.getSpouse() != null) {
                    casados++;
                } else
                    solteros++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(String.format("Casados: %s\nSolteros: %s", casados, solteros));
    }

    // MÉTODO PARA OBTENER CUANTOS MIEMBROS DEL CLAN BAGGINS
    public static void cuantosBaggings(ODB odb) {
        Objects<Character> personajes = odb.getObjects(Character.class);
        int numero = 0;
        List<String> baggins = new ArrayList<String>();

        try {
            while (personajes.hasNext()) {
                Character c = personajes.next();
                if (c.getName() != null) {
                    if (c.getName().contains("Baggins")) {
                        baggins.add(c.getName());
                        numero++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("TOTAL MIEMBROS CLAN BAGGIN: " + numero);

        for (String nombre : baggins) {
            System.out.println(nombre);
        }
    }

    // INFORMACIÓN DE PELÍCULAS QUE TENGAN PRESUPUESTO > 100M Y PUNTUACIÓN ROTTEN
    // TOMATOES < 70
    public static void infoPelisPresupRottenTomatoes(ODB odb) {
        Objects<Movie> peliculas = odb.getObjects(Movie.class);

        while (peliculas.hasNext()) {
            Movie m = peliculas.next();

            if (m.getBudgetInMillions() > 100 && m.getRottenTomatoesScore() < 70) {
                System.out.println(m);
            }
        }
    }

    // INFORMACIÓN DE REINOS QUE CONTENGAN VALINOR Y SUS PERSONAJES A TRAVÉS DE LA
    // CLASE REALM
    public static void infoValinorRealm(ODB odb) {
        Objects<Realm> reinos = odb.getObjects(Realm.class);

        while (reinos.hasNext()) {
            Realm r = reinos.next();
            if (r.getName().contains("Valinor")) {
                System.out.println(r);
            }
        }
    }

    // INFORMACIÓN DE REINOS QUE CONTENGAN VALINOR Y SUS PERSONAJES A TRAVÉS DE LA
    // CLASE CHARACTER

    /*
     * TAR DE EN EJECUTAR LA FUNCIÓN PERO ACABA. SE PODRÍA MEJORAR LA EFICIENCIA DE
     * LA FUNCIÓN CON CYCLIC BARRIER O SIMILAR DE PARALELIZACIÓN DE LA ASIGNATURA
     * DE PROGRAMACIÓN DE SERVICIOS Y PROCESOS PERO NO SE SI PUEDO USAR ESAS
     * TÉCNICAS AQUÍ
     */
    public static void infoValinorCharacter(ODB odb) {
        Objects<Character> personajes = odb.getObjects(Character.class);
        Set<Realm> setReinos = new HashSet<Realm>();

        // HAGO ESTA CLASE INTERNA PARA RECUPERAR EL REINO DESDE CHARACTER
        class RecuperaRealm {
            public Realm recuperar(int id) {
                Objects<Realm> reinos = odb.getObjects(Realm.class);
                Realm r = null;

                while (reinos.hasNext()) {
                    Realm reino = reinos.next();
                    if (reino.getId() == id) {
                        r = reino;
                    }
                }
                return r;
            }
        }

        while (personajes.hasNext()) {
            Character c = personajes.next();
            RecuperaRealm reino = new RecuperaRealm();
            Realm reinoRecup = reino.recuperar(c.getRealm());
            if (reinoRecup.getName().contains("Valinor")) {
                setReinos.add(reinoRecup);
            }
        }

        for (Realm r : setReinos) {
            System.out.println(r);
        }
    }

    // TODAS LA PELÍCULAS RELACIONADAS CON EL LIBRO "THE HOBBIT"
    public static void peliculasElHobbit(ODB odb) {
        Objects<Movie> peliculas = odb.getObjects(Movie.class);

        while (peliculas.hasNext()) {
            Movie m = peliculas.next();
            Set<Book> books = m.getBook();
            if (books != null) {
                for (Book b : books) {
                    if (b.getTittle().contains("The Hobbit")) {
                        System.out.println(m);
                    }
                }
            }
        }
    }
}
