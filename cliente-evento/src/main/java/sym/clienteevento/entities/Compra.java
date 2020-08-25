package sym.clienteevento.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Compra {
    private Long id;
    private String nombre;
    private String factura;
    private String transaccion;
    private String estatusPago;

    private BigDecimal montoCompra;
    private BigDecimal montoFee;
    private BigDecimal montoEnvio;
    private BigDecimal montoManejo;

    private String compradorId;
    private String emailComprador;
    private Date fechaCompra;
    private String vendedor;

    private String direccion;
    private String zip;
    private String estado;
    private String ciudad;
    private String user;

    public Compra(){}

    public Compra(String nombre, String factura, String transaccion, String estatusPago, BigDecimal montoCompra, BigDecimal montoFee, BigDecimal montoEnvio, BigDecimal montoManejo, String compradorId, String emailComprador, Date fechaCompra, String vendedor, String direccion, String zip, String estado, String ciudad, String user) {
        this.nombre = nombre;
        this.factura = factura;
        this.transaccion = transaccion;
        this.estatusPago = estatusPago;
        this.montoCompra = montoCompra;
        this.montoFee = montoFee;
        this.montoEnvio = montoEnvio;
        this.montoManejo = montoManejo;
        this.compradorId = compradorId;
        this.emailComprador = emailComprador;
        this.fechaCompra = fechaCompra;
        this.vendedor = vendedor;
        this.direccion = direccion;
        this.zip = zip;
        this.estado = estado;
        this.ciudad = ciudad;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public String getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(String transaccion) {
        this.transaccion = transaccion;
    }

    public String getEstatusPago() {
        return estatusPago;
    }

    public void setEstatusPago(String estatusPago) {
        this.estatusPago = estatusPago;
    }

    public BigDecimal getMontoCompra() {
        return montoCompra;
    }

    public void setMontoCompra(BigDecimal montoCompra) {
        this.montoCompra = montoCompra;
    }

    public BigDecimal getMontoFee() {
        return montoFee;
    }

    public void setMontoFee(BigDecimal montoFee) {
        this.montoFee = montoFee;
    }

    public BigDecimal getMontoEnvio() {
        return montoEnvio;
    }

    public void setMontoEnvio(BigDecimal montoEnvio) {
        this.montoEnvio = montoEnvio;
    }

    public BigDecimal getMontoManejo() {
        return montoManejo;
    }

    public void setMontoManejo(BigDecimal montoManejo) {
        this.montoManejo = montoManejo;
    }

    public String getCompradorId() {
        return compradorId;
    }

    public void setCompradorId(String compradorId) {
        this.compradorId = compradorId;
    }

    public String getEmailComprador() {
        return emailComprador;
    }

    public void setEmailComprador(String emailComprador) {
        this.emailComprador = emailComprador;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
