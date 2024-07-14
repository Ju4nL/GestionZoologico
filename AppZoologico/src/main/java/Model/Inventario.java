package Model;

public class Inventario {
    private int id;
    private String nombre;
    private Area area;

    public Inventario() {}

    public Inventario(int id, String nombre, Area area) {
        this.id = id;
        this.nombre = nombre;
        this.area = area;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
}
