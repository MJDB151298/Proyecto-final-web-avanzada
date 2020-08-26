package sym.clienteevento.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String plans(Model model)
    {
        RestTemplate restTemplate = new RestTemplate();

        //Obteniendo la cantidad de compras hasta el momento.
        final String uri_compras = "http://localhost:8080/event-microservice/getComprasCount";
        ResponseEntity<Long> responseEntityCompras = restTemplate.getForEntity(uri_compras, null, Long.class);
        long count = responseEntityCompras.getBody();
        model.addAttribute("comprasCount", responseEntityCompras.getBody());

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

        return "formPlanes";
    }

    @RequestMapping("/paypal")
    public String paypal(params.get("invoice"), params.get("txn_id"), params.get("item_name"), params.get("payment_status"), new BigDecimal(params.get("payment_gross")),
            new BigDecimal(params.get("handling_amount")), new BigDecimal(params.get("payment_fee")), new BigDecimal(params.get("shipping")), params.get("payer_email"), params.get("business"),
            params.get("address_city"), params.get("address_zip"), params.get("address_state"), params.get("address_name"), params.get("user")) {

        Compra compra = new Compra();
        compra.setFactura(params.get("invoice"));
        compra.setTransaccion(params.get("txn_id"));
        compra.setNombre(params.get("item_name"));
        compra.setEstatusPago(params.get("payment_status"));

        compra.setMontoCompra(new BigDecimal(params.get("payment_gross")));
        compra.setMontoManejo(new BigDecimal(params.get("handling_amount")));
        compra.setMontoFee(new BigDecimal(params.get("payment_fee")));
        compra.setMontoEnvio(new BigDecimal(params.get("shipping")));

        compra.setCompradorId(params.get("txn_id"));
        compra.setEmailComprador(params.get("payer_email"));
        compra.setFechaCompra(new Date());
        compra.setVendedor(params.get("business"));

        compra.setCiudad(params.get("address_city"));
        compra.setZip(params.get("address_zip"));
        compra.setEstado(params.get("address_state"));
        compra.setDireccion(params.get("address_name"));
        compra.setUser(params.get("user"));


        final String uri_compra = "http://localhost:8080/event-microservice/procesarCompraPaypal";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(uri_compra, compra, Compra.class);



        return "plans";
    }
}