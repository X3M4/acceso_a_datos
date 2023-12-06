package practicas;

public class Chapter {
	private int id;
	private String chapter_name;
	private int id_book;
	
	//Constructor generado desde Source -> Generate Constructor using fields
	public Chapter(int id, String chapter_name, int id_book) {
		super();
		this.id = id;
		this.chapter_name = chapter_name;
		this.id_book = id_book;
	}
	
	//Getters y Setters generados desde Source -> Generate Getters and Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getChapter_name() {
		return chapter_name;
	}
	public void setChapter_name(String chapter_name) {
		this.chapter_name = chapter_name;
	}
	public int getId_book() {
		return id_book;
	}
	public void setId_book(int id_book) {
		this.id_book = id_book;
	}

	@Override
	public String toString() {
		return "Chapter [id=" + id + ", chapter_name=" + chapter_name + ", id_book=" + id_book + "]";
	}
	
	
}
