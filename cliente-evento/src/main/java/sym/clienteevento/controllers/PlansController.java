package sym.clienteevento.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import sym.clienteevento.entities.Compra;
import sym.clienteevento.entities.Evento;
import sym.clienteevento.entities.Producto;

import java.math.BigDecimal;
import java.util.Date;

@Controller
@RequestMapping("/plans")
public class PlansController {

    @RequestMapping("")
    public String plans(Model model, @CookieValue(value = "username", defaultValue = "nobody") String username)
    {
        RestTemplate restTemplate = new RestTemplate();

        //Obteniendo la cantidad de compras hasta el momento.
        final String uri_compras = "http://localhost:8080/event-microservice/getComprasCount";
        ResponseEntity<Long> responseEntityCompras = restTemplate.getForEntity(uri_compras, Long.class);
        model.addAttribute("comprasCount", responseEntityCompras.getBody().longValue()+1);

        //Obteniendo los planes de eventos disponibles
        final String uri_eventos = "http://localhost:8080/event-microservice/getEventos";
        ResponseEntity<Evento[]> responseEntityEventos = restTemplate.getForEntity(uri_eventos, Evento[].class);
        Evento[] eventos = responseEntityEventos.getBody();
        model.addAttribute("listEventos", eventos);

        //Obteniendo los productos falsos (NO CRUD PARA ESTO)
        final String uri_productos = "http://localhost:8080/event-microservice/getFakeProducts";
        ResponseEntity<Producto[]> responseEntityProductos = restTemplate.getForEntity(uri_productos, Producto[].class);
        Producto[] productos = responseEntityProductos.getBody();
        model.addAttribute("listProductos", productos);

        model.addAttribute("activeUser", username);

        return "formPlanes";
    }

    @RequestMapping("/paypal")
    public String paypal(@RequestParam("invoice") String invoice, @RequestParam("txn_id") String txn_id, @RequestParam("item_name") String item_name, @RequestParam("payment_status") String payment_status
            , @RequestParam("payment_gross") BigDecimal payment_gross, @RequestParam("handling_amount") BigDecimal handling_amount, @RequestParam("payment_fee") BigDecimal payment_fee, @RequestParam("shipping") BigDecimal shipping,
                         @RequestParam("payer_email") String payer_email, @RequestParam("business") String business, @RequestParam("address_city") String address_city, @RequestParam("address_zip") String address_zip,
                         @RequestParam("address_state") String address_state, @RequestParam("address_name") String address_name, @RequestParam("user") String user) {
        System.out.println("klk");
        Compra compra = new Compra();
        compra.setFactura(invoice);
        compra.setTransaccion(txn_id);
        compra.setNombre(item_name);
        compra.setEstatusPago(payment_status);

        compra.setMontoCompra(payment_gross);
        compra.setMontoManejo(handling_amount);
        compra.setMontoFee(payment_fee);
        compra.setMontoEnvio(shipping);

        compra.setCompradorId(txn_id);
        compra.setEmailComprador(payer_email);
        compra.setFechaCompra(new Date());
        compra.setVendedor(business);

        compra.setCiudad(address_city);
        compra.setZip(address_zip);
        compra.setEstado(address_state);
        compra.setDireccion(address_name);
        compra.setUser(user);


        final String uri_compra = "http://localhost:8080/event-microservice/procesarCompraPaypal";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri_compra, compra, Compra.class);



        return "plans";
    }
}