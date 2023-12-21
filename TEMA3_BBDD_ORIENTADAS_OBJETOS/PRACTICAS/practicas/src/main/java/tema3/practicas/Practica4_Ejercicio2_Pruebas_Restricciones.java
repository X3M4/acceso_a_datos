package tema3.practicas;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ODBRuntimeException;

public class Practica4_Ejercicio2_Pruebas_Restricciones {
    public static void main(String[] args) {

        ODB odb = ODBFactory.open("practica_LOTR");
        //INSERTAR CHARACTER
        Character personaje = new Character(254042603, "Bartolomé", null, "elfo",
                null, null, null, null, null, 0);
        Character personaje2 = new Character(01, "Bartolomé", null, "elfo",
                null, null, null, null, null, 0);

        //INSERTAR MOVIES
        Movie peli = new Movie(parseaIntHexadecimal("bccde58"), "Die Hard III", 0, 
            0, 0, 0, 0, 0);
        Movie peli2 = new Movie(0, "Die Hard III", 0, 
            0, 0, 0, 0, 0);
        
        //INSERTAR OBJETOS REALM
        Realm realm = new Realm(5000015, "Dale,Laketown", 0, 0);
        Realm realm2 = new Realm(01, "Dale,Laketown", 0, 0);
        Realm realm3 = new Realm(5000015, "Villagarcia del LLano", 0, 0);
        Realm realm4 = new Realm(02, "Villagarcia del LLano", 0, 0);
        

        try {

            creaObjetosRealm(odb, realm4);
            System.out.println("Realm añadido");

        } catch (ODBRuntimeException e) {
            e.printStackTrace();
        }
    }

    public static int parseaIntHexadecimal(String string) {
        return Integer.parseInt(string, 16);
    }

    public static void creaObjetosCharacter(ODB odb, Character personaje) {
        odb.store(personaje);
        odb.commit();
    }

    public static void creaOBjetosMovie(ODB odb, Movie peli) {
        odb.store(peli);
        odb.commit();
    }

    public static void creaObjetosRealm(ODB odb, Realm reino) {
        odb.store(reino);
        odb.commit();
    }
}
