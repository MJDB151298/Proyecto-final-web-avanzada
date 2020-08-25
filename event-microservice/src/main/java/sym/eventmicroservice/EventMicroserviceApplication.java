package sym.eventmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import sym.eventmicroservice.services.EventServices;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class EventMicroserviceApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(EventMicroserviceApplication.class, args);
        EventServices eventServices = (EventServices) applicationContext.getBean("eventServices");
        eventServices.generarPlanes();
    }

}
