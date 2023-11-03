package tema1.p5;

import java.io.Serializable;

public class Persona implements Serializable{
    private String nombre;
    private String apellido;
    private transient String dni;

    public Persona(String nombre, String apellido, String dni){
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido(){
        return apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDni(){
        return dni;
    }

    @Override
    public String toString(){
        return "Nombre:" + nombre + " " +  apellido + " - Dni:" + dni;
    }

}

