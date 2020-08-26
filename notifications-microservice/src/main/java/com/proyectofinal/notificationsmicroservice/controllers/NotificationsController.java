package com.proyectofinal.notificationsmicroservice.controllers;

import com.proyectofinal.notificationsmicroservice.entities.Factura;
import com.proyectofinal.notificationsmicroservice.services.EmailServices;
import com.proyectofinal.notificationsmicroservice.services.FacturaServices;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sendgrid.*;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String createFactura(String username, String username_email, ArrayList<String> products, double total, HttpServletResponse response) throws IOException, JRException {
        facturaServices.createFactura(new Factura(username, products, total));
        emailServices.sendEmail(username, username_email, "Compra realizada!", "Aqui el resumen de su compra: ");
        String sourceFileName = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "SampleJasperTemplate.jasper").getAbsolutePath();
        // creating our list of beans
        List<Factura> dataList = new ArrayList<Factura>();
        Factura sampleBean = new Factura();
        sampleBean.setUsername(username);
        sampleBean.setProducts(products);
        sampleBean.setTotal(total);
        dataList.add(sampleBean);
        // creating datasource from bean list
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataList);
        Map parameters = new HashMap();
        JasperPrint jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "inline; filename=jasper.pdf;");
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
