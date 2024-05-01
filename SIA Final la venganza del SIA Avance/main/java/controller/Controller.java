//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\
package controller;
//-\\//-\\//-\\//-\\//-\\//-\\
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate; 
import java.time.LocalTime; 
//-\\//-\\//-\\//-\\//-\\//-\\
import models.Jefe;
import models.Persona;
import models.Reunion;
import models.Trabajador;
//-\\//-\\//-\\//-\\//-\\//-\\
import views.VentanaMain;
import views.VentanaAgregar;
import views.VentanaEliminar;
import views.VentanaMostrar;
import views.VentanaEditar;
//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\-//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

public class Controller  {
    List<Persona> personas = new ArrayList<>();
    private VentanaMain viewMain;
    private VentanaAgregar viewAgregar;
    private VentanaEliminar viewEliminar;
    private VentanaMostrar viewMostrar;
    private VentanaEditar viewEditar;

  // Constructor
  public Controller(VentanaMain viewMain, VentanaAgregar viewAgregar, VentanaEliminar viewEliminar, VentanaMostrar viewMostrar, VentanaEditar viewEditar) {
        this.viewMain = viewMain;
        this.viewAgregar = viewAgregar;
        this.viewEliminar = viewEliminar;
        this.viewMostrar = viewMostrar;
        this.viewEditar = viewEditar;
  }

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\-//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

  public void inicializarDatos() throws FileNotFoundException, IOException {
    String rutaArchivo = "C:\\Users\\javie\\Documents\\NetBeansProjects\\Prueba\\src\\main\\java\\controller\\datos.txt"; // Ruta del archivo CSV a leer

    try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
        String linea;
        int cantAgregado = -1;
        while ((linea = reader.readLine()) != null) {
            String[] campos = linea.split(",");

            String nombre = campos[0].trim();
            String correo = campos[1].trim();
            String tipo = campos[2].trim();
            String departamento= campos[3].trim();

            agregarPersona(nombre, correo, tipo, departamento);
            cantAgregado++;

            // Verificar si hay más datos de reunión
            if (campos.length > 4) {
              for (int i = 4; i < campos.length; i++) {
                  String reunion = campos[i].trim();
                  i++; // Avanzar al siguiente campo
                  String lugar = campos[i].trim();
                  i++; 
                  int anio = Integer.parseInt(campos[i].trim());
                  i++; 
                  int mes = Integer.parseInt(campos[i].trim());
                  i++; 
                  int dia = Integer.parseInt(campos[i].trim());
                  i++; 
                  int hora = Integer.parseInt(campos[i].trim());
                  i++; 
                  int minuto = Integer.parseInt(campos[i].trim());

                  Reunion reunion1 = new Reunion(reunion, lugar, LocalDate.of(anio,mes,dia), LocalTime.of(hora, minuto));

                  agregarReuniones(personas.get(cantAgregado),reunion1);

              }
            }
        }
    } catch (IOException e) {
      //e.printStackTrace();
      
    }
  }

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\-//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

  public String cadenaPersona(Persona persona){
      String cadena;
      if(persona instanceof Jefe){
          Jefe jefe = (Jefe) persona;
          cadena = persona.getNombre()+","+persona.getEmail()+",Jefe,"+jefe.getDepartamento();
      }
      else{
          Trabajador tra = (Trabajador) persona;
          cadena = persona.getNombre()+","+persona.getEmail()+",Trabajador,"+tra.getCargo();
      }
      if(persona.getReuniones() != null){
          for (Map.Entry<String, Reunion> entrada : persona.getReuniones().entrySet()){
              String clave = entrada.getKey();

              Reunion reu = persona.getReuniones().get(clave);
              LocalDate fecha = reu.getFecha();
              LocalTime hora = reu.getHora();

              cadena = cadena+","+reu.getNombre()+","+reu.getLugar()+","+fecha.getYear()+","+fecha.getMonthValue()+","+fecha.getDayOfMonth()+","+hora.getHour()+","+hora.getMinute();
          } 
      }
      return cadena;
  }

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\-//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

  public void exportarCSV() throws IOException {
      //Direccion del archivo
      String nombreArchivo = "C:\\Users\\javie\\Documents\\NetBeansProjects\\Prueba\\src\\main\\java\\controller\\datos.txt";
      BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo));
      for (int i = 0; i < personas.size(); i++){
          if(i!=0)
             writer.newLine();
          try {
              String cadena = cadenaPersona(personas.get(i));
              writer.write(cadena);
 
          } catch (IOException e) {
              //e.printStackTrace();
              
          }
      }
    writer.close();
  }

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\-//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

public boolean agregarPersona(String nombre, String email, String tipo, String DepaCargo) {
    // Verificar si la persona ya existe
    if (existePersona(nombre)) {
        // Mostrar que ya existe por pantalla
        return false;
    }
    // Crear la persona según el tipo
    Persona nuevaPersona = crearPersona(nombre, email, tipo, DepaCargo);
    // Agregar la persona a la lista
    if (nuevaPersona != null) {
        personas.add(nuevaPersona);
        return true;
    } else {
        // Manejar un error si la creación de la persona falla
        return false;
    }
}

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

private boolean existePersona(String nombre) {
  if (personas.stream().anyMatch(persona -> (persona.getNombre().equals(nombre)))) {
      return true;
  }
  return false;
}

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

private Persona crearPersona(String nombre, String email, String tipo, String DepaCargo) {
    // Dependiendo del tipo, creamos un trabajador o un jefe
    if (tipo.equals("Trabajador")) {
        return new Trabajador(nombre, email, DepaCargo);
    } else if (tipo.equals("Jefe")) {
        return new Jefe(nombre, email, DepaCargo);
    } else {
        // Manejar un tipo desconocido
        return null;
    }
}

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\-//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

  public final int agregarReuniones(Persona persona,Reunion newReunion){
    for (int i = 0; i < personas.size(); i++) {
      if (personas.get(i).getNombre().equals(persona.getNombre())){
        for(int j = 0; j < personas.get(i).getReuniones().size(); j++){
          if(personas.get(i).getReuniones().get(newReunion.getNombre())!=null){
            //Mostrar que ya existe por pantalla
            return 0;
          }
        }
        persona.getReuniones().put(newReunion.getNombre(), newReunion);
        //Caso que se agregue correctamente
        return 1;
      }
    }
    //Caso que no se agreguen las reuniones
    return 2;
  }

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\
  public void mostrarPersona(String nombrePersona){
    //PONER DATOS DE LAS PERSONAS EN VENTANA
    for (int i = 0; i < personas.size(); i++){
      if (personas.get(i).getNombre().equals(nombrePersona)){
        //PONER DATOS DE LA PERSONA EN VENTANA
        
        return;
      }
    }
    //CASO DE QUE NO EXISTAN PERSONAS
  }

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\-//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

  public void eliminarReunion(String nombrePersona, String nombreReunion){
    for (int i = 0; i < personas.size(); i++) {
        if(personas.get(i).getNombre().equals(nombrePersona)){
          personas.get(i).getReuniones().remove(nombreReunion);
          return;
        }
    }
  }

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\-//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

  public void eliminarPersona(String nombrePersona){
    for (int i = 0; i < personas.size(); i++) {
        if(personas.get(i).getNombre().equals(nombrePersona)){
          if(personas.get(i).getReuniones() != null){
            for (Map.Entry<String, Reunion> entrada : personas.get(i).getReuniones().entrySet()){
                String clave = entrada.getKey();

                personas.get(i).getReuniones().remove(clave);
            }
          }
          personas.remove(i);
          return;
        }
    }
    //PONER EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
  }
  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\-//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

  //POSIBLE SOBRECARGA DE METODOS CON PARAMETROS
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

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

  //Sobrecarga sin fecha ni hora
    public void editarReunion(String nombrePersona, String nombreReunion, String nuevoNombreReunion, String lugar) {
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

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

  //sobrecarga solo fecha y hora
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

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

  public void editarPersona(String nombrePersona, String nombre, String email){
    for (int i = 0; i < personas.size(); i++){
      if(personas.get(i).getNombre().equals(nombrePersona)){
        personas.get(i).setNombre(nombre);
        personas.get(i).setEmail(email);
        return;
      }
    }
    //PONER EN VENTANA DE QUE NO SE ENCONTRO LA PERSONA
  }
  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\-//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

  //funcion propia, mostrar el correo de las personas sin reuniones asignadas
  public void mostrarTipos(){

    int opcion = 0; //esta opcion cambiaria a 0 si jefe, 1 trabajadores

    //preguntar por ventana si se quiere ver los trabajadores(1) o los jefe(0)
    

    switch(opcion){
      case 0:
        for(int i = 0; i < personas.size(); i++){
          //ver si es instancia de jefe
          if(personas.get(i) instanceof Jefe){
            
            
          }
        }
        break;
      case 1:
        for(int i = 0; i < personas.size(); i++){
          //ver si es instancia de jefe
          if(personas.get(i) instanceof Trabajador){
            
          }
        break;
    }
  }}

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\
    //Interaccion Con Vista Agregar
    public void getViewInfoPersona() {
        //Obtener datos.
        String nombre = viewAgregar.getTextNombrePersona().getText();
        String correo = viewAgregar.getTextCorreoPersona().getText();
        String tipo = viewAgregar.getTexTipo().getText();
        String depaCargo = viewAgregar.getDepaCargo().getText();

        agregarPersona(nombre, correo, tipo , depaCargo);

        viewAgregar.getTexTipo().setText("");
        viewAgregar.getTextNombrePersona().setText("");
        viewAgregar.getTextCorreoPersona().setText("");
        viewAgregar.getDepaCargo().setText("");
    }

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\
  public void getViewInfoReunion() {
      //Obtener datos.
      String nombrePersonaReu = viewAgregar.getTextNombrePersonaReunion().getText();
      String nombreReu = viewAgregar.getTextNombreReunion().getText();
      String lugar = viewAgregar.getTextLugarReunion().getText();
      String fecha = viewAgregar.getTextFechaReunion().getText();
      String hora = viewAgregar.getTextHoraReunion().getText();  

      Persona persona = null;

      for(int i = 0 ; i < personas.size() ; i++){
        if(personas.get(i).getNombre().equals(nombrePersonaReu))
          persona = personas.get(i);
      }

      // Convertir fecha y hora a objetos LocalDate y LocalTime
      LocalDate fechaReal = LocalDate.parse(fecha);
      LocalTime horaReal = LocalTime.parse(hora);
      // Crear objeto Reunion
      Reunion reunion = new Reunion(nombreReu, lugar, fechaReal, horaReal);

      // Agregar reunion a la lista de reuniones de la persona
      agregarReuniones(persona, reunion);
  }

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

    //Interaccion Con Vista Eliminar
    public void getViewInfoPersonaEliminar() {
        //Obtener datos.
        String nombre = viewEliminar.getTextNombrePersonaEliminar().getText();

        eliminarPersona(nombre);
    }

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\

    public void getViewInfoReunionEliminar() {
        //Obtener datos.
        String nombre = viewEliminar.getTextNombrePersonaReunionEliminar().getText();
        String reunion = viewEliminar.getTextNombreReunionEliminar().getText();

        eliminarReunion(nombre,reunion);
    }

 public void searchByName() {
    String nombre = viewMostrar.getTextReunionBuscar().getText();
    // Recorre el ArrayList de personas
    for (Persona persona : personas) {
        // Si encuentra una persona con el nombre especificado
        if (persona.getNombre().equals(nombre)) {
            // Obtiene el mapa de reuniones de la persona
            persona.getReuniones().values().stream().map(reunion -> {
                // Muestra la información de cada reunión en el TextArea
                viewMostrar.getTextEscribirReuniones().append("Nombre: " + reunion.getNombre() + "\n");
                return reunion;
            }).map(reunion -> {
                viewMostrar.getTextEscribirReuniones().append("Lugar: " + reunion.getLugar() + "\n");
                return reunion;
            }).map(reunion -> {
                viewMostrar.getTextEscribirReuniones().append("Fecha: " + reunion.getFecha() + "\n");
                return reunion;
            }).forEachOrdered(reunion -> {
                viewMostrar.getTextEscribirReuniones().append("Hora: " + reunion.getHora() + "\n\n");
            });
            // Termina el bucle una vez que se ha encontrado la persona
            return;
        }
    }
    // Si no se encuentra la persona con el nombre especificado
    viewMostrar.getTextEscribirReuniones().append("No se encontró ninguna persona con el nombre '" + nombre + "'.\n");
}
}

  //-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\-//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\//-\\
