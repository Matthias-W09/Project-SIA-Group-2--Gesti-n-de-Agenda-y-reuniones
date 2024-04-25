package Models;

//import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public class Reunion {
// Atributos de Reunion
private String nombre;
private String lugar;
private LocalDate fecha;
private LocalTime hora;
// private String fecha;
// private double hora;

// Constructor de Reunion
public Reunion(String nombre, String lugar, LocalDate fecha, LocalTime hora) {
      this.nombre = nombre;
      this.lugar = lugar;
      this.fecha = fecha;
      this.hora = hora;
  }
//public Reunion(String nombre, String lugar, String fecha, double hora) {
//    this.nombre = nombre;
//    this.lugar = lugar;
//    this.fecha = fecha;
//    this.hora = hora;
//}

// Getters y Setters de Reunion
public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public String getLugar() {
    return lugar;
}

public void setLugar(String lugar) {
    this.lugar = lugar;
}

public LocalDate getFecha() {
    return fecha;
}
// public String getFecha() {
//    return fecha;
// }

public void setFecha(LocalDate fecha) {
    this.fecha = fecha;
}
  
// public void setFecha(String fecha) {
//    this.fecha = fecha;
// }

public LocalTime getHora() {
    return hora;
}
// public double getHora() {
//    return hora;
// }

public void setHora(LocalTime hora) {
    this.hora = hora;
}
// public void setHora(double hora) {
//    this.hora = hora;
// }
} 
