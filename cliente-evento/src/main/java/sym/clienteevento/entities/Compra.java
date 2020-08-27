package sym.clienteevento.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Compra {
    private Evento evento;
    private String username;
    private String username_email;

    public Compra() {
    }

    public Compra(Evento evento, String username, String username_email) {
        this.evento = evento;
        this.username = username;
        this.username_email = username_email;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_email() {
        return username_email;
    }

    public void setUsername_email(String username_email) {
        this.username_email = username_email;
    }
}
