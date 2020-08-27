package sym.eventmicroservice.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evento {

    @Id
    private String nombre;
    @OneToMany
    private List<Producto> productos;
    private float precio;

    public Evento(){}

    public Evento(String nombre, List<Producto> productos, float precio) {
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

    public List<Producto> getProductos() {
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
