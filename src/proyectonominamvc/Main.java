package proyectonominamvc;

import Controlador.Controlador;
import Modelo.Trabajador;
import Vista.*;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        Login venlogin = new Login();
        Menu venmenu = new Menu();
        IngresoDatos veningresodatos = new IngresoDatos();
        ConsultarCedula vencedula = new ConsultarCedula();
        Reportes reporte = new Reportes();

        Trabajador modelo = new Trabajador();
        Controlador control = new Controlador(venlogin, venmenu, veningresodatos, vencedula, reporte, modelo);
        control.VistaLogin();
    }

}
