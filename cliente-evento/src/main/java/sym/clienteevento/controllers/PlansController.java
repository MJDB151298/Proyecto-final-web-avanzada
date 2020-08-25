package sym.clienteevento.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import sym.clienteevento.entities.Evento;
import sym.clienteevento.entities.Producto;

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
        model.addAttribute("comprasCount", responseEntityCompras.getBody()+1);

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
    public String paypal() {
        //TODO: llamar el microservicio de eventos

        return "plans";
    }
}