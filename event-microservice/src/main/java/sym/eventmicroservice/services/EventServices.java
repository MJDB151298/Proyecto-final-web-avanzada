package sym.eventmicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import sym.eventmicroservice.entities.Evento;
import sym.eventmicroservice.repository.EventRepository;

import java.util.ArrayList;

public class EventServices {
    @Autowired
    EventRepository eventRepository;

    public boolean generarPlanes()
    {
        Evento preBoda = new Evento("Pre-Boda",1000);
        Evento boda = new Evento("Boda", 5000);
        Evento cumpleanos = new Evento("Cumpleaños", 3000);
        Evento videoEvento = new Evento("Video de evento", 4000);

        eventRepository.save(preBoda);
        eventRepository.save(boda);
        eventRepository.save(cumpleanos);
        eventRepository.save(videoEvento);

        return eventRepository.count() > 0;
    }
}
