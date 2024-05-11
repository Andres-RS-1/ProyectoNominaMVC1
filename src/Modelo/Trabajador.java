package Modelo;

import java.util.ArrayList;

public class Trabajador {

    private int cedula;
    private String nombre;
    private int sueldomensual;
    private int diastrabajo;
    private int NHED; // Numero Horas extras diurna
    private int NHEN; //  Numero Horas extras norcturna
    private int NHEDD; //  Numero Horas extras diurna dominical
    private int NHEND; //  Numero Horas extras norcturna dominical
    private int nivelARP;
    private int NHRN;  //  Numero  horas recargo nocturno

    public Trabajador(int cedula, String nombre, int sueldomensual, int diastrabajo, int NHED, int NHEN, int NHEDD, int NHEND, int nivelARP, int NHRN) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.sueldomensual = sueldomensual;
        this.diastrabajo = diastrabajo;
        this.NHED = NHED;
        this.NHEN = NHEN;
        this.NHEDD = NHEDD;
        this.NHEND = NHEND;
        this.nivelARP = nivelARP;
        this.NHRN = NHRN;
    }

    public Trabajador() {
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getSueldomensual() {
        return sueldomensual;
    }

    public void setSueldomensual(int sueldomensual) {
        this.sueldomensual = sueldomensual;
    }

    public int getDiastrabajo() {
        return diastrabajo;
    }

    public void setDiastrabajo(int diastrabajo) {
        this.diastrabajo = diastrabajo;
    }

    public int getNHED() {
        return NHED;
    }

    public void setNHED(int NHED) {
        this.NHED = NHED;
    }

    public int getNHEN() {
        return NHEN;
    }

    public void setNHEN(int NHEN) {
        this.NHEN = NHEN;
    }

    public int getNHEDD() {
        return NHEDD;
    }

    public void setNHEDD(int NHEDD) {
        this.NHEDD = NHEDD;
    }

    public int getNHEND() {
        return NHEND;
    }

    public void setNHEND(int NHEND) {
        this.NHEND = NHEND;
    }

    public int getNivelARP() {
        return nivelARP;
    }

    public void setNivelARP(int nivelARP) {
        this.nivelARP = nivelARP;
    }

    public int getNHRN() {
        return NHRN;
    }

    public void setNHRN(int NHRN) {
        this.NHRN = NHRN;
    }
    
    
     @Override
    public String toString() { // nos devuelve los valores en String
        return "" + cedula + "---" + nombre + "---" + sueldomensual + "---" + diastrabajo + "---" + NHED + "---" + NHEN + "---" + NHEDD + "---" + NHEND + "---" + NHRN + "";
    }
    
  

}
