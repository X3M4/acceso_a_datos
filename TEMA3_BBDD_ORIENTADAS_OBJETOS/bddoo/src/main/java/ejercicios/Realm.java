package ejercicios;

import java.util.Set;

public class Realm {
	private int id;
	private String name;
	private int population;
	private int area;
	private Set<Character> character;

	//Getters y Setters generados automáticamente
	public Realm(int id, String name, int population, int area, Set<Character> character) {
		this.id = id;
		this.name = name;
		this.population = population;
		this.area = area;
		this.character = character;
	}
	//Getters y Setters generados automáticamente
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
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public Set<Character> getCharacter() {
		return character;
	}
	public void setCharacter(Set<Character> character) {
		this.character = character;
	}
	
	@Override
	public String toString() {
		return "Realm [id=" + id + ", name=" + name + ", population=" + population + ", area=" + area + ", character="
				+ character + "]";
	}
	
	
}
