package com.proyectofinal.notificationsmicroservice.controllers;

import com.proyectofinal.notificationsmicroservice.entities.Factura;
import com.proyectofinal.notificationsmicroservice.services.EmailServices;
import com.proyectofinal.notificationsmicroservice.services.FacturaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sendgrid.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/factura")
public class NotificationsController {
    @Autowired FacturaServices facturaServices;
    @Autowired EmailServices emailServices;

    @RequestMapping("/test")
    public String test(){
        return "Soy el microservicio de notificaciones!";
    }

    @RequestMapping("/createFactura")
    public String createFactura(String username, String username_email, ArrayList<String> products, double total){
        facturaServices.createFactura(new Factura(username, products, total));
        emailServices.sendEmail(username, username_email, "Compra realizada!", "Aqui el resumen de su compra: ");
        return "compra realizada!";
    }

    @RequestMapping("/sendAccountNotification")
    public String sendAccountNotification(@RequestParam("username") String username, @RequestParam("username_email") String username_email){
        emailServices.sendEmail(username, username_email, "Nueva cuenta!", "Aqui el reporte de su cuenta");
        return "";
    }

    @RequestMapping("/correoCompra")
    public String correoCompra(@RequestParam("empleados") List<String> empleados, @RequestParam("correos") List<String> correos)
    {
       for(int i = 0; i < empleados.size(); i++){
           emailServices.sendEmail(empleados.get(i), correos.get(i), "Compra realizada", "Usted realizo una compra!");
       }
       return "Correos enviados!";
    }
}
