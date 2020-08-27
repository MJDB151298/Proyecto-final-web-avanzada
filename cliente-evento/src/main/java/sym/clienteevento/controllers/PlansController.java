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
import sym.clienteevento.entities.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public String paypal(@RequestParam("user") String username, @RequestParam("item_name") String item_name,
            @RequestParam("plan") String plan, @RequestParam("productos") List<String> productos)/**@RequestParam("invoice") String invoice, @RequestParam("txn_id") String txn_id, @RequestParam("item_name") String item_name, @RequestParam("payment_status") String payment_status
            , @RequestParam("payment_gross") BigDecimal payment_gross, @RequestParam("handling_amount") BigDecimal handling_amount, @RequestParam("payment_fee") BigDecimal payment_fee, @RequestParam("shipping") BigDecimal shipping,
                         @RequestParam("payer_email") String payer_email, @RequestParam("business") String business, @RequestParam("address_city") String address_city, @RequestParam("address_zip") String address_zip,
                         @RequestParam("address_state") String address_state, @RequestParam("address_name") String address_name, @RequestParam("user") String user)**/ {
        System.out.println("klk");
        List<Producto> productos_object = new ArrayList<>();
        for(String producto : productos){
            productos_object.add(new Producto(producto));
        }
        Evento evento = new Evento(plan, productos_object, 2000);

        RestTemplate restTemplate = new RestTemplate();
        final String uri_user = "http://localhost:8080/user-microservice/findUser?username=" + username;
        ResponseEntity<User> responseEntityUser = restTemplate.getForEntity(uri_user, User.class);
        User user = responseEntityUser.getBody();

        Compra compra = new Compra(evento, user.getUsername(), user.getMail());


        final String uri_compra = "http://localhost:8080/event-microservice/procesarCompraPaypal";
        restTemplate.postForObject(uri_compra, compra, Compra.class);

        final String uri =  "http://localhost:8080/user-microservice/getEmployees";
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(uri, User[].class);
        User[] users = responseEntity.getBody();

        List<String> nombres = new ArrayList<>();
        List<String> correos = new ArrayList<>();
        assert users != null;
        for(User user_object: users){
            nombres.add(user_object.getUsername());
            correos.add(user_object.getMail());
        }

        final String uri_correos =  "http://localhost:8080/notifications-microservice/factura/correoEmpleados?empleados=" + nombres + "&correos=" + correos;
        restTemplate.postForEntity(uri_correos, null, null);



        return "plans";
    }
}