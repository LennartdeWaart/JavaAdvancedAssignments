package nl.inholland.myfirstapi.controllers;

import lombok.extern.java.Log;
import nl.inholland.myfirstapi.interfaces.IPianoService;
import nl.inholland.myfirstapi.models.Piano;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Log
@Path("/pianos")
@Produces(MediaType.APPLICATION_JSON_VALUE)
public class PianoController {

    @Autowired
    private IPianoService pianoService;

    @GET
    @Path("")
    public Response getPianos(@QueryParam("brand") String brand) {
        log.info("getPianos was invoked.");
        List<Piano> pianos = null;
        if (brand != null && brand != "") {
            pianos = pianoService.getPianosByBrand(brand);
        } else {
            pianos = (List<Piano>)pianoService.findAll();
        }

        if (pianos != null) {
            return Response.ok(pianos).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Path("/save")
    public Response savePiano(Piano piano) {
        log.info("savePiano was invoked.");
        Piano saved = pianoService.save(piano);
        if (saved != null) {
            return Response.ok(saved).build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }

    @POST
    @Path("/save/subset")
    public Response savePianos( Iterable<Piano> pianos) {
        log.info("savePianos was invoked.");
        Iterable<Piano> saved = pianoService.saveAll(pianos);
        if (saved != null) {
            return Response.ok(saved).build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }
}
