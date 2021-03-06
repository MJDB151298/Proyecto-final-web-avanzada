package sym.eventmicroservice.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Producto {

    @Id
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
