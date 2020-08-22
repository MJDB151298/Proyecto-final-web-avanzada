package sym.eventmicroservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import sym.eventmicroservice.entities.Parametro;
import sym.eventmicroservice.repository.ParametroRepository;

import java.util.logging.Logger;

@Component
public class BootStrap implements ApplicationRunner {

    Logger logger =  Logger.getLogger(BootStrap.class.getName());
    @Autowired
    private ParametroRepository parametroRepository;

    @Override
    public void run(ApplicationArguments args) {
        logger.info("Iniciando Aplicación Spring Boot Paypal");
        parametroRepository.save(new Parametro(1, "CUENTA_NEGOCIO_PAYPAL", "sb-wtds63009120@business.example.com"));
    }
}
