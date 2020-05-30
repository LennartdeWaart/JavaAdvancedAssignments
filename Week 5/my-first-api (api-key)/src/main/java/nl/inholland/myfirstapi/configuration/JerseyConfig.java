package nl.inholland.myfirstapi.configuration;

import nl.inholland.myfirstapi.controllers.PianoController;
import nl.inholland.myfirstapi.controllers.StockController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(StockController.class);
        register(PianoController.class);
    }
}
