package Model;

import java.util.Date;

public class Suministro {

    private int id;
    private Alimento alimento;
    private int stock;
    private Date fechaVencimiento;
    private Date fechaIngreso;
    private int cantidad;
    private Proveedor proveedor;

    public Suministro(int id, Alimento alimento) {
        this.id = id;
        this.alimento = alimento;
    }

    public Suministro(int id, Alimento alimento, int stock, Date fechaVencimiento, Date fechaIngreso, int cantidad, Proveedor proveedor) {
        this.id = id;
        this.alimento = alimento;
        this.stock = stock;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaIngreso = fechaIngreso;
        this.cantidad = cantidad;
        this.proveedor = proveedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alimento getAlimento() {
        return alimento;
    }

    public void setAlimento(Alimento alimento) {
        this.alimento = alimento;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

}
