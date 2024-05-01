package models;

// Clase Jefe, subclase de Persona
public class Jefe extends Persona {
    // Atributos específicos de Jefe
    private String departamento;

    // Constructor de Jefe que llama al constructor de Persona
    public Jefe(String nombre, String email, String departamento) {
        super(nombre, email);
        this.departamento = departamento;
    }

    // Getter y Setter para el departamento
    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}