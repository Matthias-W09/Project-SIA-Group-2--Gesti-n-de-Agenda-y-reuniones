package Controller;

import java.util.HashMap;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.time.LocalDate; // new
import java.time.LocalTime; // new
import Models.Persona;
import Models.Reunion;

public class Controller implements MouseListener {

  // Creacion lista de personas
  List<Persona> personas = new ArrayList<Persona>();
  Map<String, Reunion> reuniones = new HashMap<>();
  
  // Constructor
  public Controller() {
      // Creación de Personas
      Persona persona1 = new Persona("Juan", "juan@example.com");
      Persona persona2 = new Persona("María", "maria@example.com");

      // Agregar personas a la lista
      personas.add(persona1);
      personas.add(persona2);
      // Creación de algunas reuniones y agregación a la lista de reuniones de la persona1 y persona2
      Reunion reunion1 = new Reunion("Reunion Ventas", "Sala de Conferencias", LocalDate.of(2024, 3, 20),
        LocalTime.of(10, 0));
     // Reunion reunion1 = new Reunion("Reunion Ventas","Sala de Conferencias", "2024-03-20", 10);

      Reunion reunion2 = new Reunion("Reunion Jefe", "Oficina", LocalDate.of(2024, 3, 22), LocalTime.of(15, 30));

     // Reunion reunion2 = new Reunion("Reunion Jefe","Oficina", "2024-03-22", 15.5);
      agregarReuniones(persona1, reunion1);
      agregarReuniones(persona1, reunion1);
      agregarReuniones(persona2, reunion1);
      agregarReuniones(persona2, reunion2);
      //PREGUNTAR PROFE SI METODO AGREGAR REUNION DEBE IR EN CLASE PERSONA O EN CLASE CONTROLLER
  }

  public void agregarPersona(String nombre, String email){
    for(Persona persona : personas){
      if(persona.getNombre().equals(nombre)){
        //Mostrar que ya existe por pantalla
        return;
      }
    }
    personas.add(new Persona(nombre, email));
    return;
  }

  public void agregarReuniones(Persona persona,Reunion newReunion){
    for (int i = 0; i < personas.size(); i++) {
      if (personas.get(i).getNombre().equals(persona.getNombre())){
        for(int j = 0; j < personas.get(i).getReuniones().size(); j++){
          if(personas.get(i).getReuniones().get(newReunion.getNombre())!=null){
            //Mostrar que ya existe por pantalla
            return;
          }
        }
        persona.getReuniones().put(newReunion.getNombre(), newReunion);
        return;
      }
    }
    return;
    //Poner por Dialogo no se encontro persona
  }

  public void mostrarReuniones(String nombrePersona){
    for (int i = 0; i < personas.size(); i++) {
        if (personas.get(i).getNombre().equals(nombrePersona)){
          //PONER DATOS DE LA PERSONA EN VENTANA
          return;
        }
      //PONER WEA EN dialogo DE QUE NO SE ENCONTRO LA PERSONA
      return;
    }
  }

  public void mostrarPersonas(){
    //PONER DATOS DE LAS PERSONAS EN VENTANA
    //CASO DE QUE NO EXISTAN PERSONAS
    return;
  }

  public void eliminarReunion(String nombrePersona){
    for (int i = 0; i < personas.size(); i++) {
        if(personas.get(i).getNombre().equals(nombrePersona)){
          //mostrar reuniones de la peronsa
          //preguntar nombre de la reunion a eliminar
          //j el valor que entregue el usuario a eleiminar(numero o nombre nose)
          personas.get(i).getReuniones().remove(j);
          return;
        }
    }
  }

  public void eliminarPersona(String nombrePersona){
    for (int i = 0; i < personas.size(); i++) {
        if(personas.get(i).getNombre().equals(nombrePersona)){
          personas.remove(i);
          return;
        }
    }
    //PONER EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
    return;
  }

  //POSIBLE SOBRECARGA DE METODOS CON PARAMETROS --->> new 
  public void editarReunion(String nombrePersona, String nombreReunion, String nuevoNombreReunion, String lugar, LocalDate fecha, LocalTime hora) {
      for (int i = 0; i < personas.size(); i++) {
          if (personas.get(i).getNombre().equals(nombrePersona)) {
              if (personas.get(i).getReuniones().get(nombreReunion) != null) {
                  personas.get(i).getReuniones().get(nombreReunion).setNombre(nuevoNombreReunion);
                  personas.get(i).getReuniones().get(nombreReunion).setLugar(lugar);
                  personas.get(i).getReuniones().get(nombreReunion).setFecha(fecha);
                  personas.get(i).getReuniones().get(nombreReunion).setHora(hora);
                  return;
              }
              // PONER EN VENTANA DE QUE NO SE ENCONTRO LA REUNION
          }
      }
    //PONER EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
  }

  //Sobrecarga sin fecha ni hora
  public void editarReunion(String nombrePersona, String nombreReunion, String nuevoNombreReunion, String lugar,) {
      for (int i = 0; i < personas.size(); i++) {
          if (personas.get(i).getNombre().equals(nombrePersona)) {
              if (personas.get(i).getReuniones().get(nombreReunion) != null) {
                  personas.get(i).getReuniones().get(nombreReunion).setNombre(nuevoNombreReunion);
                  personas.get(i).getReuniones().get(nombreReunion).setLugar(lugar);
                  return;
              }
              // PONER EN VENTANA DE QUE NO SE ENCONTRO LA REUNION
          }
      }
    //PONER EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
  }

  //sobrecarga solo fecha
  public void editarReunion(String nombrePersona, String nombreReunion, LocalDate fecha, LocalTime hora) {
      for (int i = 0; i < personas.size(); i++) {
          if (personas.get(i).getNombre().equals(nombrePersona)) {
              if (personas.get(i).getReuniones().get(nombreReunion) != null) {
                  personas.get(i).getReuniones().get(nombreReunion).setFecha(fecha);
                  personas.get(i).getReuniones().get(nombreReunion).setHora(hora);
                  return;
              }
              // PONER EN VENTANA DE QUE NO SE ENCONTRO LA REUNION
          }
      }
    //PONER EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
  }
  // original
 /*public void editarReunion(String nombrePersona, String nombreReunion, String lugar, String fecha, double hora){
    for (int i = 0; i < personas.size(); i++){
      if(personas.get(i).getNombre() == nombrePersona){
        if(personas.get(i).getReuniones().get(nombreReunion)!=null){
              personas.get(i).getReuniones().get(nombreReunion).setNombre(nombreReunion);
              personas.get(i).getReuniones().get(nombreReunion).setLugar(lugar);
              personas.get(i).getReuniones().get(nombreReunion).setFecha(fecha);
              personas.get(i).getReuniones().get(nombreReunion).setHora(hora);
              return;
            }
          //PONER EN VENTANA DE QUE NO SE ENCONTRO LA REUNION  
          }
    }
  }
  //PONER EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
}*/

  public void editarPersona(String nombrePersona, String nombre, String email){
    for (int i = 0; i < personas.size(); i++){
      if(personas.get(i).getNombre().equals(nombrePersona)){
        personas.get(i).setNombre(nombre);
        personas.get(i).setEmail(email);
        return;
      }
    }
    //PONER EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
    return;
  }

@Override
public void mousePressed(MouseEvent me) {

}

@Override
public void mouseReleased(MouseEvent me) {

}

@Override
public void mouseEntered(MouseEvent me) {

}

@Override
public void mouseExited(MouseEvent me) {

}
@Override
public void mouseClicked(MouseEvent me) {

   }

}
