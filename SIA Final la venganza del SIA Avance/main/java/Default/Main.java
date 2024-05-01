//El main importa todas las clases involucradas en el MVC.
import controller.Controller;
import views.VentanaMain;
import views.VentanaAgregar;
//import views.VentanaEditar;
import views.VentanaEliminar;
import views.VentanaMostrar;


public class Main {
    //En este caso, al ser un programa simple, sólo se inicializan los componentes.
    public static void main(String[] args) {
        VentanaMain viewMain = new VentanaMain();
        VentanaAgregar viewAgregar = new VentanaAgregar();
        VentanaEliminar viewEliminar = new VentanaEliminar();
        VentanaMostrar viewMostrar = new VentanaMostrar();

        Controller controller = new Controller(viewMain, viewAgregar, viewEliminar, viewMostrar);

        viewMain.setVisible(true);
    }
}
