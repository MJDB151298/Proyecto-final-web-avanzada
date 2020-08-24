package com.proyectofinal.notificationsmicroservice.services;

import com.proyectofinal.notificationsmicroservice.entities.Factura;
import com.proyectofinal.notificationsmicroservice.repositories.FacturaRepositories;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

@Service
public class EmailServices {


    public boolean sendEmail(String username, String username_email, String subject, String content){
        Email desdeEmail = new Email("20160370@ce.pucmm.edu.do");
        String asuntoEmail = subject;
        Email paraEmail = new Email(username_email);
        Content cuerpoEmail = new Content("text/plain", content);
        Mail email = new Mail(desdeEmail, asuntoEmail, paraEmail, cuerpoEmail);

        File file = new File("SENDGRID_API_KEY.txt");
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String value = sc.nextLine();
        SendGrid sg = new SendGrid(value);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(email.build());
            Response response = sg.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    /**public boolean sendNewUserVerification(String username, String username_email){
        return
    }

    public boolean sendFacturaNotification(String username, String username_email){
        return sendEmail(username, username_email, "Confirmacion de factura", "Aqui esta el reporte de su factura");
    }**/
}
