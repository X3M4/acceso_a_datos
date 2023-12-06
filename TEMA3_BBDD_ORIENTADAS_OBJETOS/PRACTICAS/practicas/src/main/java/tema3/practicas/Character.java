package tema3.practicas;

public class Character {
	private int id;
	private String name;
	private String wikiUrl;
	private String race;
	private String birth;
	private String gender;
	private String death;
	private String hair;
	private String height;
	private int realm;
	private int id_spouse1;
	private int id_spouse2;
	
	//Constructor
	public Character(int id, String name, String wikiUrl, String race, String birth, String gender, String death,
			String hair, String height, int realm) {
		super();
		this.id = id;
		this.name = name;
		this.wikiUrl = wikiUrl;
		this.race = race;
		this.birth = birth;
		this.gender = gender;
		this.death = death;
		this.hair = hair;
		this.height = height;
		this.realm = realm;
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

	public String getWikiUrl() {
		return wikiUrl;
	}

	public void setWikiUrl(String wikiUrl) {
		this.wikiUrl = wikiUrl;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDeath() {
		return death;
	}

	public void setDeath(String death) {
		this.death = death;
	}

	public String getHair() {
		return hair;
	}

	public void setHair(String hair) {
		this.hair = hair;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public int getRealm() {
		return realm;
	}

	public void setRealm(int realm) {
		this.realm = realm;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", wikiUrl=" + wikiUrl + ", race=" + race + ", birth=" + birth
				+ ", gender=" + gender + ", death=" + death + ", hair=" + hair + ", height=" + height + ", realm="
				+ realm + ", id_spouse1=" + id_spouse1 + ", id_spouse2=" + id_spouse2 + "]";
	}
	
	
}
