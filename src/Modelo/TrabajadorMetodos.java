package Modelo;

import Vista.IngresoDatos;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class TrabajadorMetodos {

    ArrayList<Trabajador> trabajador;
    ArrayList<Integer> cedulas;
    ArrayList<String> nombres;
    ArrayList<Integer> saldos;
    ArrayList<Double> parafiscales;
    ArrayList<Double> prestacionessociales;

    public TrabajadorMetodos() {
    }

    public void CrearArrayList() { // crear la linea del vector
        trabajador = new ArrayList();
    }

    public void InsertarTrabajador(Trabajador dato) { // llamatos todos los datos enviados del SET o CONTROCUTOR CON PARAMETRO
        trabajador.add(dato);
    }

    public ArrayList<Trabajador> Listar() {
        return trabajador;
    }

    public int BuscarCedula(int cedula) { // buscar cliente repetido
        int buscar = 0;
        for (int i = 0; i < trabajador.size(); i++) {
            if (trabajador.get(i).getCedula() == cedula) { // buscamos la codigo con el array almacenado
                buscar = 1;
            }
        }
        return buscar; // retormanos toodo lo qye se gusrde en mostrar

    }

    public boolean ModificarTrabajador(Trabajador dato) {

        boolean encontrado = false;
        for (int i = 0; i < trabajador.size(); i++) {
            if (trabajador.get(i).getCedula() == dato.getCedula()) { // buscamos la codigo con el array almacenado
                encontrado = true;
                trabajador.get(i).setCedula(dato.getCedula());
                trabajador.get(i).setNombre(dato.getNombre());
                trabajador.get(i).setSueldomensual(dato.getSueldomensual());
                trabajador.get(i).setDiastrabajo(dato.getDiastrabajo());
                trabajador.get(i).setNHED(dato.getNHED());
                trabajador.get(i).setNHEN(dato.getNHEN());
                trabajador.get(i).setNHEDD(dato.getNHEND());
                trabajador.get(i).setNHEND(dato.getNHEND());
                trabajador.get(i).setNHRN(dato.getNHRN());
            }
        }
        return encontrado;
    }

    public boolean EliminarTrabajador(int cedula) {
        boolean encontrado = false;
        for (int i = 0; i < trabajador.size(); i++) {
            if (trabajador.get(i).getCedula() == cedula) { // buscamos la codigo con el array almacenado
                encontrado = true;
                trabajador.remove(i); // elimino la posicion del array que esta la codigo encontrada
            }
        }
        return encontrado;
    }

    public String Calculo(int cedula) { // buscar cliente repetido
        String mostrar = "";
        String nombre = "";
        int sueldosalario = 0;
        int diastrabajo = 0;
        int NHED = 0; // Numero Horas extras diurna
        int NHEN = 0; //  Numero Horas extras norcturna
        int NHEDD = 0; //  Numero Horas extras diurna dominical
        int NHEND = 0; //  Numero Horas extras norcturna dominical
        int nivelARP = 0;
        int NHRN = 0;  //  Numero  horas recargo nocturno
        int respuesta = 0;
        int salarioMinimo = 1380000;

        for (int i = 0; i < trabajador.size(); i++) {
            if (trabajador.get(i).getCedula() == cedula) { // buscamos la codigo con el array almacenado
                nombre = trabajador.get(i).getNombre();
                sueldosalario = trabajador.get(i).getSueldomensual();
                diastrabajo = trabajador.get(i).getDiastrabajo();
                NHED = trabajador.get(i).getNHED();
                NHEN = trabajador.get(i).getNHEN();
                NHEDD = trabajador.get(i).getNHEDD();
                NHEND = trabajador.get(i).getNHEND();
                nivelARP = trabajador.get(i).getNivelARP();
                NHRN = trabajador.get(i).getNHRN();
                respuesta = 1;

                int AuxilioTransporte = 0;

                if (sueldosalario <= (salarioMinimo * 2)) {
                    AuxilioTransporte = 162000;
                } else {
                    AuxilioTransporte = 0;
                }

                double HorasExtraDiurnas = 0; // Declaración de la variable totalHorasExtraDiurnas
                double HorasExtraNocturnas = 0; // Declaración de la variable totalHorasExtraNocturnas
                double HorasExtraDiurnasDominical = 0; // Declaración de la variable totalHorasExtraDiurnasFestivas
                double HorasExtraNocturnasDominical = 0; // Declaración de la variable totalHorasExtraNocturnasFestivas
                double HorasRecargoNocturno = 0; // Declaración de la variable totalHorasExtraNocturnasFestivas

                double TotalHorasExtra = 0; // Declaración de la variable totalHorasExtra

                double valorhoraordinaria = sueldosalario / 30 / 7.83333; // valor de una hora ordinaria
                HorasExtraDiurnas = ((valorhoraordinaria * 25) / 100) + valorhoraordinaria;
                HorasExtraNocturnas = ((valorhoraordinaria * 75) / 100) + valorhoraordinaria;
                HorasExtraDiurnasDominical = ((valorhoraordinaria * 100) / 100) + valorhoraordinaria;
                HorasExtraNocturnasDominical = ((valorhoraordinaria * 150) / 100) + valorhoraordinaria;

                TotalHorasExtra = HorasExtraDiurnas + HorasExtraNocturnas + HorasExtraDiurnasDominical + HorasExtraNocturnasDominical;

                HorasRecargoNocturno = (valorhoraordinaria * 35) / 100;

                double valorARL = 0;
                if (nivelARP == 1) {
                    valorARL = 0;
                } else if (nivelARP == 2) {
                    valorARL = sueldosalario * 0.01044;
                } else if (nivelARP == 3) {
                    valorARL = sueldosalario * 0.02436;
                } else if (nivelARP == 4) {
                    valorARL = sueldosalario * 0.04350;
                } else if (nivelARP == 5) {
                    valorARL = sueldosalario * 0.06096;
                } else if (nivelARP > 5) {

                }

                // Calcular el total de horas extras trabajadas
                // Calcular el cuatro por ciento del sueldo
                double cuatroPorciento = (0.04 * sueldosalario) / 100;
                double FondoSolidario = 0;
                // Calcular las deducciones por salud, pensión, fondo solidario y retención en la fuente
                double Salud = (4 * sueldosalario) / 100;
                double Pension = (4 * sueldosalario) / 100;
                if (sueldosalario >= salarioMinimo * 4 && sueldosalario <= salarioMinimo * 16) {
                    FondoSolidario = sueldosalario * 0.01;
                } else if (sueldosalario >= salarioMinimo * 16 && sueldosalario <= salarioMinimo * 17) {
                    FondoSolidario = sueldosalario * 0.012;
                } else if (sueldosalario >= salarioMinimo * 17 && sueldosalario <= salarioMinimo * 18) {
                    FondoSolidario = sueldosalario * 0.014;
                } else if (sueldosalario >= salarioMinimo * 18 && sueldosalario <= salarioMinimo * 19) {
                    FondoSolidario = sueldosalario * 0.016;
                } else if (sueldosalario >= salarioMinimo * 19 && sueldosalario <= salarioMinimo * 20) {
                    FondoSolidario = sueldosalario * 0.018;
                } else if (sueldosalario > salarioMinimo * 20) {
                    FondoSolidario = sueldosalario * 0.02;
                }

                double UVT = 47065; // Declaración y asignación del valor de la UVT (Unidad de Valor Tributario)
                double RetencionFuente = 0; // Declaración y asignación inicial de la variable reteFuente
                double Prima = 0; // Declaración de la variable prima

                if (sueldosalario < UVT * 95) {
                    RetencionFuente = 0;
                } else if (sueldosalario >= UVT * 95 && sueldosalario <= UVT * 150) {
                    RetencionFuente = (UVT - 95) * 0.19;
                } else if (sueldosalario >= UVT * 150 && sueldosalario <= UVT * 360) {
                    RetencionFuente = (UVT - 150) * 0.28 + 10;
                } else if (sueldosalario >= UVT * 360 && sueldosalario <= UVT * 640) {
                    RetencionFuente = (UVT - 360) * 0.33 + 69;
                } else if (sueldosalario >= UVT * 640 && sueldosalario <= UVT * 945) {
                    RetencionFuente = (UVT - 640) * 0.35 + 162;
                } else if (sueldosalario >= UVT * 945 && sueldosalario <= UVT * 2300) {
                    RetencionFuente = (UVT - 945) * 0.37 + 268;
                } else if (sueldosalario >= UVT * 2300) {
                    RetencionFuente = (UVT - 2300) * 0.39 + 770;
                }

                // Calcular el total devengado
                double TotalDevengado = (sueldosalario + TotalHorasExtra + AuxilioTransporte);

                // Calcular el total deducido
                double TotalDeducido = Salud + Pension + FondoSolidario + RetencionFuente;

                // Calcular el neto
                double SueldoNeto = TotalDevengado - TotalDeducido;

                // Calcular los aportes patronales
                double saludPatron = sueldosalario * 0.085;
                double pensionPatron = sueldosalario * 0.12;
                double SENA = sueldosalario * 0.02;
                double ICBF = sueldosalario * 0.03;
                double CAJA = sueldosalario * 0.04;
                double TotalParafiscales = saludPatron + pensionPatron + SENA + ICBF + CAJA;

                // Calcular el valor de las prestaciones sociales
                double Vacaciones = sueldosalario * 0.0417;
                double Cesantias = sueldosalario * 0.0833;
                double Int_cesantias = Cesantias * 0.01;

                Prima = sueldosalario;

                double TotalPrestaciones = Vacaciones + Cesantias + Int_cesantias + sueldosalario + Prima;

                // Calcular el total de la nómina
                double TotalNomina = TotalDevengado + TotalPrestaciones + TotalParafiscales;
                if (respuesta == 0) {
                    JOptionPane.showMessageDialog(null, "Cedula No. encontrad");
                } else {
                    DecimalFormat redondeo = new DecimalFormat("#.00");

                    mostrar = mostrar + "RECIBO DE NOMINA\n\n";
                    mostrar = mostrar + "Colaborador: " + nombre + "\n";
                    mostrar = mostrar + "Cedula: " + cedula + "\n";
                    mostrar = mostrar + "Basico: " + sueldosalario + "\n";
                    mostrar = mostrar + "Auxilio de Transporte: " + AuxilioTransporte + "\n";
                    mostrar = mostrar + "Total Horas Extra : " + redondeo.format(TotalHorasExtra) + "\n";
                    mostrar = mostrar + "TOTAL DEVENGADO: " + redondeo.format(TotalDevengado) + "\n";
                    mostrar = mostrar + "\n\n";
                    mostrar = mostrar + "Salud: " + redondeo.format(Salud) + "\n";
                    mostrar = mostrar + "Pension: " + redondeo.format(Pension) + "\n";
                    mostrar = mostrar + "Fondo Solidario: " + redondeo.format(FondoSolidario) + "\n";
                    mostrar = mostrar + "Retencion en la Fuente: " + redondeo.format(RetencionFuente) + "\n";
                    mostrar = mostrar + "TOTAL DEDUCIDO: " + redondeo.format(TotalDeducido) + "\n";
                    mostrar = mostrar + "\n\n";
                    mostrar = mostrar + "SUELDO NETO: " + redondeo.format(SueldoNeto) + "\n";
                    mostrar = mostrar + "\n\n";
                    mostrar = mostrar + "Salud Patron: " + redondeo.format(Salud) + "\n";
                    mostrar = mostrar + "Pension Patron: " + redondeo.format(Pension) + "\n";
                    mostrar = mostrar + "ARL: " + redondeo.format(valorARL) + "\n";
                    mostrar = mostrar + "SENA: " + redondeo.format(SENA) + "\n";
                    mostrar = mostrar + "ICBF: " + redondeo.format(ICBF) + "\n";
                    mostrar = mostrar + "Cajas: " + redondeo.format(CAJA) + "\n";
                    mostrar = mostrar + "TOTAL PARAFISCALES: " + redondeo.format(TotalParafiscales) + "\n";
                    mostrar = mostrar + "\n\n";
                    mostrar = mostrar + "Prima: " + redondeo.format(Prima) + "\n";
                    mostrar = mostrar + "Vacaiones: " + redondeo.format(Vacaciones) + "\n";
                    mostrar = mostrar + "Cesantias: " + redondeo.format(Cesantias) + "\n";
                    mostrar = mostrar + "Intereses de Cesantias: " + redondeo.format(Int_cesantias) + "\n";
                    mostrar = mostrar + "TOTAL PRESTACIONES: " + redondeo.format(TotalPrestaciones) + "\n";
                    mostrar = mostrar + "\n";
                    mostrar = mostrar + "TOTAL NOMINA: " + redondeo.format(TotalNomina) + "\n";
                }
            } else {
                mostrar = mostrar + "NO ENCOTRADO";
            }
        }
        return mostrar;
    }

}
