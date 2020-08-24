package sym.clienteevento.entities;

import java.util.Set;

public class User {
    private String username;
    private String password;
    private String mail;
    private String role;

    public User(){}

    public User(String username, String password, String mail, String role){
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
