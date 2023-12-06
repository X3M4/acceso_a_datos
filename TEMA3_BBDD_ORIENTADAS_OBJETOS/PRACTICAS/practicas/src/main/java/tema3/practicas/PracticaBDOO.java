package tema3.practicas;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;

public class PracticaBDOO {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ODB odb = ODBFactory.open("practica_LOTR");
        Book prueba = new Book(0, "Peli de prueba", null);
		Chapter chapter = new Chapter(0, "Capítulo de Prueba", 0);
		Character character = new Character(0, "Personaje de prueba", null, null, null, null, 
										null, null, null, 0);
		Dialog dialog = new Dialog(0, "Diálogo de prueba", 0, 0);
		Movie movie = new Movie(0, "Peli de prueba", 180, 230,	 0, 
										0, 0, 0);
		Realm realm = new Realm(0, "Reino de prueba", 0, 0, null);

        odb.store(prueba);
		odb.store(chapter);
		odb.store(character);
		odb.store(dialog);
		odb.store(movie);
		odb.store(realm);
        odb.commit();

       Objects<Book> libros = odb.getObjects(Book.class);
	   Objects<Chapter> capitulos = odb.getObjects(Chapter.class);
	   Objects<Character> personajes = odb.getObjects(Character.class);
	   Objects<Dialog> dialogos = odb.getObjects(Dialog.class);
	   Objects<Movie> peliculas = odb.getObjects(Movie.class);
	   Objects<Realm> reinos = odb.getObjects(Realm.class);

	   while(libros.hasNext()){
		Book l = libros.next();
		System.out.println(l);
	   }

	}

}
