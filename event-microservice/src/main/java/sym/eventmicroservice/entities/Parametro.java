package sym.eventmicroservice.entities;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parametro implements Serializable {
    @Id
    Integer codigo;
    String nombre;
    String valor;

    public Parametro(int i, String cuenta_negocio_paypal, String s) {
        this.codigo = i;
        this.nombre = cuenta_negocio_paypal;
        this.valor = s;
    }
}