package sym.eventmicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sym.eventmicroservice.entities.Compra;
import sym.eventmicroservice.entities.Evento;
import sym.eventmicroservice.repository.CompraRepository;
import sym.eventmicroservice.repository.EventRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventServices {
    @Autowired
    EventRepository eventRepository;

    @Autowired
    CompraRepository compraRepository;

    public boolean generarPlanes()
    {
        if(eventRepository.count() == 0){
            Evento preBoda = new Evento("Pre-Boda",1000);
            Evento boda = new Evento("Boda", 5000);
            Evento cumpleanos = new Evento("Cumpleaños", 3000);
            Evento videoEvento = new Evento("Video de evento", 4000);

            eventRepository.save(preBoda);
            eventRepository.save(boda);
            eventRepository.save(cumpleanos);
            eventRepository.save(videoEvento);
        }
        return eventRepository.count() > 0;
    }

    public List<Evento> getEventos(){
        return eventRepository.findAll();
    }

    public long countCompras(){
        return compraRepository.count();
    }

    //no se ta usando
    @Transactional
    public Compra generarCompra(String invoice, String txn_id, String item_name, String payment_status, BigDecimal payment_gross, BigDecimal handling_amount,
                                 BigDecimal payment_fee, BigDecimal shipping, String payer_email, String business, String address_city, String address_zip,
                                 String address_state, String address_name, String user)
    {
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

        compraRepository.save(compra);

        return compra;
    }

}
