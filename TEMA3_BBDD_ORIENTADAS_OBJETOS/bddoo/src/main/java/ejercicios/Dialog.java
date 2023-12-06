package ejercicios;

public class Dialog {
	private int id;
	private String dialog;
	private int id_movie;
	private int id_character;
	
	//Constructor automático
	public Dialog(int id, String dialog, int id_movie, int id_character) {
		super();
		this.id = id;
		this.dialog = dialog;
		this.id_movie = id_movie;
		this.id_character = id_character;
	}
	
	//Getters and Setters automáticos
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDialog() {
		return dialog;
	}

	public void setDialog(String dialog) {
		this.dialog = dialog;
	}

	public int getId_movie() {
		return id_movie;
	}

	public void setId_movie(int id_movie) {
		this.id_movie = id_movie;
	}

	public int getId_character() {
		return id_character;
	}

	public void setId_character(int id_character) {
		this.id_character = id_character;
	}

	@Override
	public String toString() {
		return "Dialog [id=" + id + ", dialog=" + dialog + ", id_movie=" + id_movie + ", id_character=" + id_character
				+ "]";
	}
	
	
	
	
}
