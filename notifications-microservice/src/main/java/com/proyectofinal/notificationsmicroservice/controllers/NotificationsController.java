package com.proyectofinal.notificationsmicroservice.controllers;

import com.proyectofinal.notificationsmicroservice.entities.Factura;
import com.proyectofinal.notificationsmicroservice.services.EmailServices;
import com.proyectofinal.notificationsmicroservice.services.FacturaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

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
}
