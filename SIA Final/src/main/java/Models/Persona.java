package Models;

import java.util.HashMap;
import java.util.Map;


// Clase Persona
public class Persona {
    // Atributos de Persona
    private String nombre;
    private String email;
    // Inicializa el mapa de reuniones
    private Map<String, Reunion> reuniones;

    // Constructor de Persona
    public Persona(String nombre, String email) {
        // Inicializa los atributos de la persona
        this.nombre = nombre;
        this.email = email;
         // Inicialización del HashMap
        this.reuniones = new HashMap<String, Reunion>();
    }

    // Getters y Setters Persona
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Reunion> getReuniones(){
      return reuniones;
    }
}