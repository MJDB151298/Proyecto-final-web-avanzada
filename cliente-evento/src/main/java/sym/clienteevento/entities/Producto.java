package sym.clienteevento.entities;

public class Producto {


    private String nombre;

    public Producto(){}

    public Producto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
