package Model;

public class Categoria {
    private int id;
    private String nombre;
    private String contacto;

    public Categoria() {}

    public Categoria(int id, String nombre, String contacto) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
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

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
    
    @Override
    public String toString() {
        return nombre;  
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Categoria categoria = (Categoria) obj;
        return id == categoria.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
