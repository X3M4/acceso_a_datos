package tema3.practicas;

import java.util.Set;

public class Book {
	private int id;
	private String tittle;
	private Set<Movie> movie;
	
	//Constructor generado desde Source -> Generate Constructor using fields
	public Book(int id, String tittle, Set<Movie> movie) {
		super();
		this.id = id;
		this.tittle = tittle;
		this.movie = movie;
	}
	//Getters y Setters generados desde Source -> Generate Getters and Setters
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
	public Set<Movie> getMovie() {
		return movie;
	}
	public void setMovie(Set<Movie> movie) {
		this.movie = movie;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", tittle=" + tittle + ", movie=" + movie + "]";
	}
	
}
