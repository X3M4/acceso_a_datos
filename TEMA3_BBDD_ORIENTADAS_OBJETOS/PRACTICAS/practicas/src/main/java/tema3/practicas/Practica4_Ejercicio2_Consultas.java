package tema3.practicas;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class Practica4_Ejercicio2_Consultas {
    public static void main(String[] args) {
        ODB odb = ODBFactory.open("practica_LOTR");
        infoAragorn(odb);
        inforMetrajeTLOR(odb);
        mediaRottenTomatoes(odb);
    }

    // CONSULTA CON TODA LA INFORMACIÓN DE ARAGORN II
    public static void infoAragorn(ODB odb) {
        IQuery consulta = new CriteriaQuery(Character.class, Where.like("name", "%Aragorn II%"));//Importante poner % al principio y final
        Character Aragorn = (Character)odb.getObjects(consulta).getFirst();
        System.out.println(Aragorn);
    }


    //CONSULTA CON LA SUMA DE LOS MINUTOS DE LAS PELÍCULAS DEL SEÑOR DE LOS ANILLOS
    public static void inforMetrajeTLOR(ODB odb){
        ICriterion criterio = Where.like("name", "%The Lord of the Ring:%");
        Values valores = odb.getValues(new ValuesCriteriaQuery(Movie.class, criterio).sum("runtimeInMinutes"));

        try{
            ObjectValues valor = valores.next();
            BigDecimal minutos = (BigDecimal)valor.getByAlias("runtimeInMinutes");
            System.out.println("El metraje total del Señor de los anillos es: " + minutos + " minutos");
        }catch(Exception e){
            System.out.println("No ha sido posible mostrar la información:");
        }
        
    }

    //NOTA MEDIA DE LAS PELÍCULAS DE CADA TRILOGÍA EN ROTTEN TOMATOES
    public static void mediaRottenTomatoes(ODB odb) {
        ICriterion criterioHobbit = Where.like("name", "%The Hobbit:%");
        Values sumHobbit = odb.getValues(new ValuesCriteriaQuery(Movie.class, criterioHobbit).sum("rottenTomatoesScore"));

        BigInteger cantidadHobbit = odb.count(new CriteriaQuery(Movie.class, criterioHobbit));

        try {
            ObjectValues valorHobbit = sumHobbit.next();
            BigDecimal sumaHobbit = (BigDecimal) valorHobbit.getByAlias("rottenTomatoesScore");

            BigDecimal mediaHobbit = sumaHobbit.divide(new BigDecimal(cantidadHobbit), 2, RoundingMode.HALF_UP);

            System.out.println("La media de la trilogía del Hobbit en Rotten Tomatoes es: " + mediaHobbit);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        ICriterion criterioLord = Where.like("name", "%The Lord of the Ring:%");
        Values sumLord = odb.getValues(new ValuesCriteriaQuery(Movie.class, criterioLord).sum("rottenTomatoesScore"));

        BigInteger cantidadLord = odb.count(new CriteriaQuery(Movie.class, criterioLord));

        try{
            ObjectValues valorLord = sumLord.next();
            BigDecimal sumaLord = (BigDecimal) valorLord.getByAlias("rottenTomatoesScore");

            BigDecimal mediaLord = sumaLord.divide((new BigDecimal(cantidadLord)), 2, RoundingMode.HALF_UP);

            System.out.println("La media de la trilogía del Señor de los Anillos es: " + mediaLord);
        }catch(IndexOutOfBoundsException e){
            e.printStackTrace();
        }
    }

}
