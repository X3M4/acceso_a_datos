package tema3.practicas;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Book {
	private int id;
	private String tittle;
	private Set<Chapter> chapter;
	private Set<Movie> movie;

	// Constructor generado desde Source -> Generate Constructor using fields
	public Book(int id, String tittle) {
		super();
		this.id = id;
		this.tittle = tittle;
		this.chapter = new HashSet<Chapter>();
		this.movie = new HashSet<Movie>();
	}

	// Getters y Setters generados desde Source -> Generate Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public Set<Chapter> getChapter() {
		return chapter;
	}

	public void addChapter(Chapter chapter) {
		if (this.chapter == null) {
			this.chapter = new HashSet<Chapter>();
		}
		this.chapter.add(chapter);
	}

	public Set<Movie> getMovie() {
		return movie;
	}

	public void addMovie(Movie movie) {
		if (this.movie == null) {
			this.movie = new HashSet<Movie>();
		}
		this.movie.add(movie);
	}

	@Override
	public String toString() {
		StringBuilder sbChapter = new StringBuilder();
		StringBuilder sbMovie = new StringBuilder();

		if (this.chapter != null) {
			for (Chapter chapter : this.chapter) {
				sbChapter.append(chapter.getChapter_name()).append(" ");
			}
		}

		// Recorrer el Set de Movies y meterlo en un StringBuilder...
		if (this.movie != null) {
			for (Movie movie : this.movie) {
				sbMovie.append(movie.getName()).append(" ");
			}
		}

		return "Book [id=" + id + ", tittle=" + tittle + ", chapter=" + sbChapter.toString() + ", movie="
				+ sbMovie.toString() + "]";
	}

}
