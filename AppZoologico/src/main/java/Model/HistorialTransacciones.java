package Model;

import java.util.Date;

public class HistorialTransacciones {
    private int id;
    private Suministro suministro;
    private Date fechaAccion;
    private int cantidad;
    private String accion;

    public HistorialTransacciones() {}

    public HistorialTransacciones(int id, Suministro suministro, Date fechaAccion, int cantidad, String accion) {
        this.id = id;
        this.suministro = suministro;
        this.fechaAccion = fechaAccion;
        this.cantidad = cantidad;
        this.accion = accion;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Suministro getSuministro() {
        return suministro;
    }

    public void setSuministro(Suministro suministro) {
        this.suministro = suministro;
    }

    public Date getFechaAccion() {
        return fechaAccion;
    }

    public void setFechaAccion(Date fechaAccion) {
        this.fechaAccion = fechaAccion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
}
