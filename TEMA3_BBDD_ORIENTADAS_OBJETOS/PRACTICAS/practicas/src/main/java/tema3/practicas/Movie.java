package tema3.practicas;

import java.util.HashSet;
import java.util.Set;

public class Movie {
	private int id;
	private String name;
	private int runtimeInMinutes;
	private int budgetInMillions;
	private int boxOfficeRevenueInMillions;
	private int academyAwardNominations;
	private int academyAwardWins;
	private int rottenTomatoesScore;
	private Set<Book> book;
	private Set<Dialog> dialog;
	
	//Constructor automático
	public Movie(int id, String name, int runtimeInMinutes, int budgetInMillions, int boxOfficeRevenueInMillions,
			int academyAwardNominations, int academyAwardWins, int rottenTomatoesScore) {
		super();
		this.id = id;
		this.name = name;
		this.runtimeInMinutes = runtimeInMinutes;
		this.budgetInMillions = budgetInMillions;
		this.boxOfficeRevenueInMillions = boxOfficeRevenueInMillions;
		this.academyAwardNominations = academyAwardNominations;
		this.academyAwardWins = academyAwardWins;
		this.rottenTomatoesScore = rottenTomatoesScore;
	}
	
	//Getters y Setters automáticos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRuntimeInMinutes() {
		return runtimeInMinutes;
	}

	public void setRuntimeInMinutes(int runtimeInMinutes) {
		this.runtimeInMinutes = runtimeInMinutes;
	}

	public int getBudgetInMillions() {
		return budgetInMillions;
	}

	public void setBudgetInMillions(int budgetInMillions) {
		this.budgetInMillions = budgetInMillions;
	}

	public int getBoxOfficeRevenueInMillions() {
		return boxOfficeRevenueInMillions;
	}

	public void setBoxOfficeRevenueInMillions(int boxOfficeRevenueInMillions) {
		this.boxOfficeRevenueInMillions = boxOfficeRevenueInMillions;
	}

	public int getAcademyAwardNominations() {
		return academyAwardNominations;
	}

	public void setAcademyAwardNominations(int academyAwardNominations) {
		this.academyAwardNominations = academyAwardNominations;
	}

	public int getAcademyAwardWins() {
		return academyAwardWins;
	}

	public void setAcademyAwardWins(int academyAwardWins) {
		this.academyAwardWins = academyAwardWins;
	}

	public int getRottenTomatoesScore() {
		return rottenTomatoesScore;
	}

	public void setRottenTomatoesScore(int rottenTomatoesScore) {
		this.rottenTomatoesScore = rottenTomatoesScore;
	}

	public Set<Book> getBook() {
		return book;
	}

	public void setBook(Set<Book> book) {
		this.book = book;
	}

	public Set<Dialog> getDialog() {
		return dialog;
	}

	public void setDialog(Set<Dialog> dialog) {
		this.dialog = dialog;
	}

	public void addBook(Book book) {
		if(this.book == null){
			this.book = new HashSet<Book>();
		}
		this.book.add(book);
		
	}

	public void addDialog(Dialog dialog) {
		if(this.dialog == null){
			this.dialog = new HashSet<Dialog>();
		}
		this.dialog.add(dialog);
	}

	@Override
	public String toString() {
		
		StringBuilder sbbook = new StringBuilder();
		
		if(this.book != null){
			for(Book book : this.book){
				sbbook.append(book).append(" ");
			}
		}

		return "Movie [id=" + id + ", name=" + name + ", runtimeInMinutes=" + runtimeInMinutes + ", budgetInMillions="
				+ budgetInMillions + ", boxOfficeRevenueInMillions=" + boxOfficeRevenueInMillions
				+ ", academyAwardNominations=" + academyAwardNominations + ", academyAwardWins=" + academyAwardWins
				+ ", rottenTomatoesScore=" + rottenTomatoesScore + ", book=" + book + ", dialog=" + dialog + "]";
	}
	
	
	
}
