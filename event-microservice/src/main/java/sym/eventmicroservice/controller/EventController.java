package sym.eventmicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import sym.eventmicroservice.entities.Compra;
import sym.eventmicroservice.entities.Evento;
import sym.eventmicroservice.entities.Producto;
import sym.eventmicroservice.repository.EventRepository;
import sym.eventmicroservice.services.EventServices;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EventController {

    @Autowired
    EventServices eventServices;

    @RequestMapping("/getEventos")
    public @ResponseBody List<Evento> getEventos(){
        return eventServices.getEventos();
    }

    @RequestMapping("/getFakeProducts")
    public @ResponseBody List<Producto> getFakeProducts(){
        Producto producto1 = new Producto("Camara");
        Producto producto2 = new Producto("Asistentes");
        Producto producto3 = new Producto("Video");
        Producto producto4 = new Producto("Decoracion");
        List<Producto> productoList = new ArrayList<>();
        productoList.add(producto1); productoList.add(producto2); productoList.add(producto3); productoList.add(producto4);
        return productoList;
    }

    @RequestMapping("/procesarCompraPaypal")
    public ResponseEntity<String> procesarCompra(@RequestBody Compra compra)
    {
//        eventServices.generarCompra(params.get("invoice"), params.get("txn_id"), params.get("item_name"), params.get("payment_status"), new BigDecimal(params.get("payment_gross")),
//                new BigDecimal(params.get("handling_amount")), new BigDecimal(params.get("payment_fee")), new BigDecimal(params.get("shipping")), params.get("payer_email"), params.get("business"),
//                params.get("address_city"), params.get("address_zip"), params.get("address_state"), params.get("address_name"), params.get("user"));

        eventServices.guardarCompra(compra);



        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping("/getComprasCount")
    public @ResponseBody long getComprasCount(){
        return eventServices.countCompras();
    }


}
