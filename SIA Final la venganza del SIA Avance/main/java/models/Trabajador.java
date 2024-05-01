package models;

// Clase Trabajador, subclase de Persona
public class Trabajador extends Persona {
    // Atributos específicos de Trabajador
    private String cargo;

    // Constructor de Trabajador que llama al constructor de Persona
    public Trabajador(String nombre, String email, String cargo) {
        super(nombre, email);
        this.cargo = cargo;
    }

    // Getter y Setter para el cargo
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
