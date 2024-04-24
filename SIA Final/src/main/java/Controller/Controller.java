package Controller;

import java.util.HashMap;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
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
      Reunion reunion1 = new Reunion("Reunion Ventas","Sala de Conferencias", "2024-03-20", 10);
      Reunion reunion2 = new Reunion("Reunion Jefe","Oficina", "2024-03-22", 15.5);
      persona1.agregarReuniones(reunion1);
      persona1.agregarReuniones(reunion2);
      persona2.agregarReuniones(reunion1);
      persona2.agregarReuniones(reunion2);
      //PREGUNTAR PROFE SI METODO AGREGAR REUNION DEBE IR EN CLASE PERSONA O EN CLASE CONTROLLER
  }

  public void agregarPersona(String nombre, String email){
    personas.add(new Persona(nombre, email));
    return;
  }

  public void agregarReuniones(String nombrePersona, String nombre, String lugar, String fecha, double hora){
    Reunion newReunion = new Reunion(nombre, lugar, fecha, hora);
    for (int i = 0; i < personas.size(); i++) {
        if(personas.get(i).getNombre() == nombrePersona){
          reuniones.put(newReunion.getNombre(), newReunion);
          return;
        }
    }
    //PONER WEA EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
    return;
  }

  public void mostrarReuniones(String nombrePersona){
    for (int i = 0; i < personas.size(); i++) {
        if(personas.get(i).getNombre() == nombrePersona){
          //PONER DATOS DE LA PERSONA EN VENTANA
          return;
        }
      //PONER WEA EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
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
        if(personas.get(i).getNombre() == nombrePersona){
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
        if(personas.get(i).getNombre() == nombrePersona){
          personas.remove(i);
          return;
        }
    }
    //PONER WEA EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
    return;
  }

  //POSIBLE SOBRECARGA DE METODOS CON PARAMETROS
  public void editarReunion(String nombrePersona, String nombreReunion, String lugar, String fecha, double hora){
    for (int i = 0; i < personas.size(); i++){
      if(personas.get(i).getNombre() == nombrePersona){
        for (int j = 0; j < personas.get(i).getReuniones().size(); j++){
          if(personas.get(i).getReuniones().get(j).getNombre() == nombreReunion){
            personas.get(i).getReuniones().get(j).setLugar(lugar);
            personas.get(i).getReuniones().get(j).setFecha(fecha);
            personas.get(i).getReuniones().get(j).setHora(hora);
            return;
            }
          //PONER WEA EN VENTANA DE QUE NO SE ENCONTRO LA REUNION  
          return;
          }
    //PONER WEA EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
      return;
      }
  }
}

  public void editarPersona(String nombrePersona, String nombre, String email){
    for (int i = 0; i < personas.size(); i++){
      if(personas.get(i).getNombre() == nombrePersona){
        personas.get(i).setNombre(nombre);
        personas.get(i).setEmail(email);
        return;
      }
    }
    //PONER WEA EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
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