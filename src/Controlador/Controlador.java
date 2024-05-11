package Controlador;

import Modelo.Trabajador;
import Modelo.TrabajadorMetodos;
import Vista.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Controlador {

    private Login vistalogin;
    private IngresoDatos vistainsertar;
    private Menu vistamenu;
    private ConsultarCedula vistacedula;
    private Reportes vistareporte;

    private Trabajador modelo;
    TrabajadorMetodos metodotrabajo = new TrabajadorMetodos();

    LinkedList CedulaKey; // busca la ID DE EMPLEADO
    int seleccion = 0;
    DefaultTableModel modelotabla = new DefaultTableModel(); // cargar los datos a la tabla
    DecimalFormat redondeo = new DecimalFormat("#.00");

    public Controlador(Login vistalogin, Menu vistamenu, IngresoDatos vistainsertar, ConsultarCedula vistacedula, Reportes vistareporte, Trabajador modelo) {
        this.vistalogin = vistalogin;
        this.vistainsertar = vistainsertar;
        this.vistamenu = vistamenu;
        this.vistacedula = vistacedula;
        this.vistareporte = vistareporte;

        this.vistalogin.setLocationRelativeTo(null); // centrar el jFrame
        this.vistainsertar.setLocationRelativeTo(null); // centrar el jFrame
        this.vistamenu.setLocationRelativeTo(null); // centrar el jFrame
        this.vistacedula.setLocationRelativeTo(null); // centrar el jFrame
        this.vistareporte.setLocationRelativeTo(null); // centrar el jFrame

        this.modelo = modelo;
        this.metodotrabajo.CrearArrayList();

        this.vistalogin.jButtonLogin.addActionListener((e) -> {
            Sesion();
        });

        this.vistamenu.jButtonNomina.addActionListener((e) -> {
            VistaIngresar();
        });

        this.vistamenu.jButtonCedula.addActionListener((e) -> {
            VistaCedula();
        });
        this.vistamenu.jButtonReporte.addActionListener((e) -> {
            VistaReporte();
        });

        this.vistamenu.jButtonSalir.addActionListener((e) -> {
            System.exit(0);
        });

        // INGRESAR
        this.vistainsertar.jButtonNuevo.addActionListener((e) -> {
            LimpiarTrabajador();
        });

        this.vistainsertar.jButtonGuardar.addActionListener((e) -> {
            InsertarTrabajador();
        });

        this.vistainsertar.jButtonSeleccionar.addActionListener((e) -> {
            SeleccionarTrabajador();
        });

        this.vistainsertar.jButtonActualizar.addActionListener((e) -> {
            ActualizarTrabajador();
        });

        this.vistainsertar.jButtonEliminar.addActionListener((e) -> {
            EliminarTrabajador();
        });

        this.vistainsertar.jButtonVer.addActionListener((e) -> {
            MostrarTrabajadores();
        });

        this.vistainsertar.jButtonMenu.addActionListener((e) -> {
            VistaMenu();
        });

        this.vistacedula.jButtonMenu.addActionListener((e) -> {
            VistaMenu();
        });

        this.vistacedula.jButtonBuscar.addActionListener((e) -> {
            BuscarTrabajador();
        });

        this.vistareporte.jButtonMenu.addActionListener((e) -> {
            VistaMenu();
        });

        this.vistareporte.jButtonBuscar.addActionListener((e) -> {
            int buscar = vistareporte.jComboBoxSeleccion.getSelectedIndex(); // posicion 
            if (buscar == 0) {
                ReporteNominaTrabajadores();
            } else if (buscar == 1) {
                ReporteTrabajadoresSaldo();
            } else if (buscar == 2) {
                ReporteTrabajadoresParaFiscal();
            } else if (buscar == 3) {
                ReporteTrabajadoresSocial();
            }

        });

    }

    public void VistaLogin() {
        this.vistalogin.setVisible(true);
        this.vistamenu.setVisible(false);
        this.vistainsertar.setVisible(false);
        this.vistacedula.setVisible(false);
        this.vistareporte.setVisible(false);

    }

    public void VistaMenu() {
        this.vistalogin.setVisible(false);
        this.vistamenu.setVisible(true);
        this.vistainsertar.setVisible(false);
        this.vistacedula.setVisible(false);
        this.vistareporte.setVisible(false);

    }

    public void VistaIngresar() {
        this.vistalogin.setVisible(false);
        this.vistamenu.setVisible(false);
        this.vistainsertar.setVisible(true);
        this.vistacedula.setVisible(false);
        this.vistareporte.setVisible(false);

    }

    public void VistaCedula() {
        this.vistalogin.setVisible(false);
        this.vistamenu.setVisible(false);
        this.vistainsertar.setVisible(false);
        this.vistacedula.setVisible(true);
        this.vistareporte.setVisible(false);

    }

    public void VistaReporte() {
        this.vistalogin.setVisible(false);
        this.vistamenu.setVisible(false);
        this.vistainsertar.setVisible(false);
        this.vistacedula.setVisible(false);
        this.vistareporte.setVisible(true);

    }

    public void Sesion() {
        String usuario = vistalogin.jTextUsuario.getText();
        char contrasena[] = vistalogin.jPasswordContrasena.getPassword();
        for (int i = 0; i < contrasena.length; i++) {
            char c = contrasena[i];
            if (!Character.isLetterOrDigit(c)) {
                return;
            }
        }
        String contra = new String(contrasena);

        if (usuario.equals("") || vistalogin.jPasswordContrasena.getPassword().length == 0) {
            JOptionPane.showMessageDialog(null, "Datos obligatorios");
        } else {
            if (usuario.equals("ADMIN") && contra.equals("12345")) {
                VistaMenu();
            } else {
                JOptionPane.showMessageDialog(null, "Por favor ingrese usuario y contrasena");

            }
        }
    }

    private void InsertarTrabajador() {
        if (vistainsertar.jTextNombre.getText().length() == 0
                || vistainsertar.jTextCedula.getText().length() == 0
                || vistainsertar.jTextSueldo.getText().length() == 0
                || vistainsertar.jTextDiasTrabajo.getText().length() == 0
                || vistainsertar.jTextHEDiurna.getText().length() == 0
                || vistainsertar.jTextHENocturna.getText().length() == 0
                || vistainsertar.jTextHEDiurnaFestiva.getText().length() == 0
                || vistainsertar.jTextHENocturnaFestiva.getText().length() == 0
                || vistainsertar.jTextHRN.getText().length() == 0
                || vistainsertar.jTextARP.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese Todos los Datos");
        } else {
            // Solicitar nombre del colaborador al usuario
            String nombre = vistainsertar.jTextNombre.getText(); // Leer el nombre ingresado por el usuario
            // Solicitar cédula del colaborador al usuario
            int cedula = Integer.parseInt(vistainsertar.jTextCedula.getText());  // Leer la cédula ingresada por el usuario
            // Solicitar sueldo del colaborador al usuario
            int sueldo = Integer.parseInt(vistainsertar.jTextSueldo.getText()); // Leer el sueldo ingresado por el usuario
            // Solicitar cantidad de días trabajados al usuario
            int dias = Integer.parseInt(vistainsertar.jTextDiasTrabajo.getText());  // Leer la cédula ingresada por el usuario  
            // Calcular el valor de horas extra diurnas si aplica
            int HED = Integer.parseInt(vistainsertar.jTextHEDiurna.getText());
            int HEN = Integer.parseInt(vistainsertar.jTextHENocturna.getText());
            int HEDD = Integer.parseInt(vistainsertar.jTextHEDiurnaFestiva.getText());
            int HEND = Integer.parseInt(vistainsertar.jTextHENocturnaFestiva.getText());
            int HRD = Integer.parseInt(vistainsertar.jTextHRN.getText());
            int nivelARL = Integer.parseInt(vistainsertar.jTextARP.getText());

            if (nivelARL < 0) {
                JOptionPane.showMessageDialog(null, "NIVEL ARL 1 a 6");
            } else if (nivelARL > 6) {
                JOptionPane.showMessageDialog(null, "NIVEL ARL 1 a 6");
            } else {
                int cantidad = metodotrabajo.BuscarCedula(cedula);
                if (cantidad != 0) {
                    JOptionPane.showMessageDialog(null, "Cedula ya existe");
                } else {
                    Trabajador dato = new Trabajador(cedula, nombre, sueldo, dias, HED, HEN, HEDD, HEND, nivelARL, HRD);
                    metodotrabajo.InsertarTrabajador(dato);
                    JOptionPane.showMessageDialog(null, "Dato Ingresados");
                    LimpiarTrabajador();
                    MostrarTrabajadores();
                }
            }

        }

    }

    public void MostrarTrabajadores() {
        ArrayList<Trabajador> lista = metodotrabajo.Listar();
        vistainsertar.jTableNomina.getTableHeader().setReorderingAllowed(false); // reiniciar la tabla de 0
        modelotabla.setRowCount(0);
        String fila[] = new String[10];
        String[] Titulos = {"cedula", "Nombre", "Sueldo", "Dias", "NHED", "NHEN", "NHEDD", "NHEND", "NHRD", "ARL"};
        modelotabla.setColumnIdentifiers(Titulos);
        CedulaKey = new LinkedList();

        for (int i = 0; i < lista.size(); i++) {
            CedulaKey.add(lista.get(i).getCedula());
            fila[0] = String.valueOf(lista.get(i).getCedula());
            fila[1] = lista.get(i).getNombre();
            fila[2] = String.valueOf(lista.get(i).getSueldomensual());
            fila[3] = String.valueOf(lista.get(i).getDiastrabajo());
            fila[4] = String.valueOf(lista.get(i).getNHED());
            fila[5] = String.valueOf(lista.get(i).getNHEN());
            fila[6] = String.valueOf(lista.get(i).getNHEDD());
            fila[7] = String.valueOf(lista.get(i).getNHEND());
            fila[8] = String.valueOf(lista.get(i).getNHRN());
            fila[9] = String.valueOf(lista.get(i).getNivelARP());
            modelotabla.addRow(fila);
        }

        vistainsertar.jTableNomina.setModel(modelotabla);
    }

    public void SeleccionarTrabajador() {
        ArrayList<Trabajador> lista = metodotrabajo.Listar();

        seleccion = vistainsertar.jTableNomina.getSelectedRow();
        if (seleccion < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un dato");
        } else {

            String id = CedulaKey.get(vistainsertar.jTableNomina.getSelectedRow()).toString();
            int cedula = Integer.parseInt(id);
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getCedula() == cedula) {
                    vistainsertar.jTextNombre.setText(lista.get(i).getNombre());
                    vistainsertar.jTextCedula.setText(String.valueOf(lista.get(i).getCedula()));
                    vistainsertar.jTextCedulaVieja.setText(String.valueOf(lista.get(i).getCedula()));
                    vistainsertar.jTextSueldo.setText(String.valueOf(lista.get(i).getSueldomensual()));
                    vistainsertar.jTextDiasTrabajo.setText(String.valueOf(lista.get(i).getDiastrabajo()));
                    vistainsertar.jTextHEDiurna.setText(String.valueOf(lista.get(i).getNHED()));
                    vistainsertar.jTextHENocturna.setText(String.valueOf(lista.get(i).getNHEN()));
                    vistainsertar.jTextHEDiurnaFestiva.setText(String.valueOf(lista.get(i).getNHEDD()));
                    vistainsertar.jTextHENocturnaFestiva.setText(String.valueOf(lista.get(i).getNHEND()));
                    vistainsertar.jTextHRN.setText(String.valueOf(lista.get(i).getNHRN()));
                    vistainsertar.jTextARP.setText(String.valueOf(lista.get(i).getNivelARP()));
                }
            }

        }
    }

    private void ActualizarTrabajador() {
        if (vistainsertar.jTextNombre.getText().length() == 0
                || vistainsertar.jTextCedula.getText().length() == 0
                || vistainsertar.jTextCedulaVieja.getText().length() == 0
                || vistainsertar.jTextSueldo.getText().length() == 0
                || vistainsertar.jTextDiasTrabajo.getText().length() == 0
                || vistainsertar.jTextHEDiurna.getText().length() == 0
                || vistainsertar.jTextHENocturna.getText().length() == 0
                || vistainsertar.jTextHEDiurnaFestiva.getText().length() == 0
                || vistainsertar.jTextHENocturnaFestiva.getText().length() == 0
                || vistainsertar.jTextHRN.getText().length() == 0
                || vistainsertar.jTextARP.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Ingrese Todos los Datos");
        } else {
            // Solicitar nombre del colaborador al usuario
            String nombre = vistainsertar.jTextNombre.getText(); // Leer el nombre ingresado por el usuario
            // Solicitar cédula del colaborador al usuario
            int cedula = Integer.parseInt(vistainsertar.jTextCedula.getText());  // Leer la cédula ingresada por el usuario
            int cedulavieja = Integer.parseInt(vistainsertar.jTextCedulaVieja.getText());  // Leer la cédula ingresada por el usuario

            // Solicitar sueldo del colaborador al usuario
            int sueldo = Integer.parseInt(vistainsertar.jTextSueldo.getText()); // Leer el sueldo ingresado por el usuario
            // Solicitar cantidad de días trabajados al usuario
            int dias = Integer.parseInt(vistainsertar.jTextDiasTrabajo.getText());  // Leer la cédula ingresada por el usuario  
            // Calcular el valor de horas extra diurnas si aplica
            int HED = Integer.parseInt(vistainsertar.jTextHEDiurna.getText());
            int HEN = Integer.parseInt(vistainsertar.jTextHENocturna.getText());
            int HEDD = Integer.parseInt(vistainsertar.jTextHEDiurnaFestiva.getText());
            int HEND = Integer.parseInt(vistainsertar.jTextHENocturnaFestiva.getText());
            int HRD = Integer.parseInt(vistainsertar.jTextHRN.getText());
            int nivelARL = Integer.parseInt(vistainsertar.jTextARP.getText());

            if (nivelARL < 0) {
                JOptionPane.showMessageDialog(null, "NIVEL ARL 1 a 6");
            } else if (nivelARL > 6) {
                JOptionPane.showMessageDialog(null, "NIVEL ARL 1 a 6");
            } else {
                int cantidad = metodotrabajo.BuscarCedula(cedula);

                if (cedula == cedulavieja) {
                    Trabajador dato = new Trabajador(cedulavieja, nombre, sueldo, dias, HED, HEN, HEDD, HEND, nivelARL, HRD);
                    metodotrabajo.ModificarTrabajador(dato);
                    JOptionPane.showMessageDialog(null, "Dato Actualizado");
                    LimpiarTrabajador();
                    MostrarTrabajadores();
                } else {
                    if (cantidad != 0) {
                        JOptionPane.showMessageDialog(null, "Cedula ya existe");
                    } else {
                        Trabajador dato = new Trabajador(cedula, nombre, sueldo, dias, HED, HEN, HEDD, HEND, nivelARL, HRD);
                        metodotrabajo.ModificarTrabajador(dato);
                        JOptionPane.showMessageDialog(null, "Dato Actualizado");
                        LimpiarTrabajador();
                        MostrarTrabajadores();
                    }
                }

            }

        }
    }

    private void EliminarTrabajador() {
        seleccion = vistainsertar.jTableNomina.getSelectedRow();
        if (seleccion < 0) {
            JOptionPane.showMessageDialog(null, "Seleccione un dato");
        } else {

            String id = CedulaKey.get(vistainsertar.jTableNomina.getSelectedRow()).toString();
            int dato = Integer.parseInt(id);
            metodotrabajo.EliminarTrabajador(dato);
            LimpiarTrabajador();
            JOptionPane.showMessageDialog(null, "Dato Eliminado");
            MostrarTrabajadores();

        }
    }

    public void BuscarTrabajador() {
        int cedula = Integer.parseInt(vistacedula.jTextBuscarCedula.getText());
        System.out.println(cedula);
        String informacion = metodotrabajo.Calculo(cedula);
        vistacedula.jTextAreaDatos.setText("");
        vistacedula.jTextAreaDatos.setText(informacion);
    }

    private void LimpiarTrabajador() {

        vistainsertar.jTextNombre.setText("");
        vistainsertar.jTextCedula.setText("");
        vistainsertar.jTextCedulaVieja.setText("");
        vistainsertar.jTextSueldo.setText("");
        vistainsertar.jTextDiasTrabajo.setText("");
        vistainsertar.jTextHEDiurna.setText("");
        vistainsertar.jTextHENocturna.setText("");
        vistainsertar.jTextHEDiurnaFestiva.setText("");
        vistainsertar.jTextHENocturnaFestiva.setText("");
        vistainsertar.jTextHRN.setText("");
        vistainsertar.jTextARP.setText("");
    }

    public void ReporteNominaTrabajadores() {
        ArrayList<Trabajador> lista = metodotrabajo.Listar();
        vistareporte.jTableRepotte.getTableHeader().setReorderingAllowed(false); // reiniciar la tabla de 0
        modelotabla.setRowCount(0);
        String fila[] = new String[10];
        String[] Titulos = {"cedula", "Nombre", "Sueldo", "Dias", "NHED", "NHEN", "NHEDD", "NHEND", "NHRD", "ARL"};
        modelotabla.setColumnIdentifiers(Titulos);
        CedulaKey = new LinkedList();

        for (int i = 0; i < lista.size(); i++) {
            CedulaKey.add(lista.get(i).getCedula());
            fila[0] = String.valueOf(lista.get(i).getCedula());
            fila[1] = lista.get(i).getNombre();
            fila[2] = String.valueOf(lista.get(i).getSueldomensual());
            fila[3] = String.valueOf(lista.get(i).getDiastrabajo());
            fila[4] = String.valueOf(lista.get(i).getNHED());
            fila[5] = String.valueOf(lista.get(i).getNHEN());
            fila[6] = String.valueOf(lista.get(i).getNHEDD());
            fila[7] = String.valueOf(lista.get(i).getNHEND());
            fila[8] = String.valueOf(lista.get(i).getNHRN());
            fila[9] = String.valueOf(lista.get(i).getNivelARP());
            modelotabla.addRow(fila);
        }

        vistareporte.jTableRepotte.setModel(modelotabla);
    }

    public void ReporteTrabajadoresSaldo() {
        ArrayList<Trabajador> lista = metodotrabajo.Listar();
        vistareporte.jTableRepotte.getTableHeader().setReorderingAllowed(false); // reiniciar la tabla de 0
        modelotabla.setRowCount(0);
        String fila[] = new String[3];
        String[] Titulos = {"cedula", "Nombre", "Sueldo"};
        modelotabla.setColumnIdentifiers(Titulos);
        for (int i = 0; i < lista.size(); i++) {
            fila[0] = String.valueOf(lista.get(i).getCedula());
            fila[1] = lista.get(i).getNombre();
            fila[2] = String.valueOf(lista.get(i).getSueldomensual());
            modelotabla.addRow(fila);
        }

        vistareporte.jTableRepotte.setModel(modelotabla);
    }

    public void ReporteTrabajadoresSocial() {
        ArrayList<Trabajador> lista = metodotrabajo.Listar();
        vistareporte.jTableRepotte.getTableHeader().setReorderingAllowed(false); // reiniciar la tabla de 0
        modelotabla.setRowCount(0);
        String fila[] = new String[3];
        String[] Titulos = {"cedula", "Nombre", "Sueldo ParaFiscales"};
        modelotabla.setColumnIdentifiers(Titulos);
        double saludPatron = 0;
        double pensionPatron = 0;
        double SENA = 0;
        double ICBF = 0;
        double caja = 0;
        double TotalParafiscales = 0;
        for (int i = 0; i < lista.size(); i++) {
            saludPatron = lista.get(i).getSueldomensual() * 0.085;
            pensionPatron = lista.get(i).getSueldomensual() * 0.12;
            SENA = lista.get(i).getSueldomensual() * 0.02;
            ICBF = lista.get(i).getSueldomensual() * 0.03;
            caja = lista.get(i).getSueldomensual() * 0.04;
            TotalParafiscales = saludPatron + pensionPatron + SENA + ICBF + caja;

            fila[0] = String.valueOf(lista.get(i).getCedula());
            fila[1] = lista.get(i).getNombre();
            fila[2] = String.valueOf(redondeo.format(TotalParafiscales));
            modelotabla.addRow(fila);
        }

        vistareporte.jTableRepotte.setModel(modelotabla);
    }

    public void ReporteTrabajadoresParaFiscal() {
        ArrayList<Trabajador> lista = metodotrabajo.Listar();
        vistareporte.jTableRepotte.getTableHeader().setReorderingAllowed(false); // reiniciar la tabla de 0
        modelotabla.setRowCount(0);
        String fila[] = new String[3];
        String[] Titulos = {"cedula", "Nombre", "Prestaciones Sociales"};
        modelotabla.setColumnIdentifiers(Titulos);
        double Vacaciones = 0;
        double Cesantias = 0;
        double Int_cesantias = 0;
        int Prima = 0;
        double TotalPrestaciones = 0;
        for (int i = 0; i < lista.size(); i++) {
            Vacaciones = lista.get(i).getSueldomensual() * 0.0417;
            Cesantias = lista.get(i).getSueldomensual() * 0.0833;
            Int_cesantias = Cesantias * 0.01;
            Prima = lista.get(i).getSueldomensual();
            TotalPrestaciones = Vacaciones + Cesantias + Int_cesantias + lista.get(i).getSueldomensual() + Prima;
            fila[0] = String.valueOf(lista.get(i).getCedula());
            fila[1] = lista.get(i).getNombre();
            fila[2] = String.valueOf(redondeo.format(TotalPrestaciones));
            modelotabla.addRow(fila);
        }

        vistareporte.jTableRepotte.setModel(modelotabla);
    }
}
