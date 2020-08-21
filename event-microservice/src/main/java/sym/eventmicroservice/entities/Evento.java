package sym.eventmicroservice.entities;

import org.hibernate.annotations.Generated;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;

public class Evento {

    @Id
    private String nombre;
    private ArrayList<Producto> productos;
    private float precio;

    public Evento(String nombre, ArrayList<Producto> productos, float precio) {
        this.nombre = nombre;
        this.productos = productos;
        this.precio = precio;
    }

    public Evento(String nombre, float precio) {
        this.nombre = nombre;
        this.productos = new ArrayList<>();
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<Producto> productos) {
        this.productos = productos;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
