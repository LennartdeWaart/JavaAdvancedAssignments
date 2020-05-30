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
import java.util.Optional;

@Component
@Log
@Path("/pianos")
@Produces(MediaType.APPLICATION_JSON_VALUE)
public class PianoController {

    @Autowired
    private IPianoService pianoService;

    @GET
    public Response getPianos(@QueryParam("brand") String brand) {
        log.info("getPianos was invoked.");
        List<Piano> pianos;
        if (brand != null && !brand.equals("")) {
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

    @GET
    @Path("/{id}")
    public Response getPiano(@PathParam("id") int id) {
        log.info("getPiano was invoked.");
        Optional<Piano> piano = pianoService.findById(id);

        if (piano.isPresent()) {
            return Response.ok(piano).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    public Response savePiano(Piano piano) {
        log.info("savePiano was invoked.");
        Piano savedPiano = pianoService.save(piano);
        if (savedPiano != null) {
            return Response.ok(savedPiano).build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }

    @PUT
    public Response updatePiano(Piano piano) {
        log.info("updatePiano was invoked.");
        Piano updatedPiano = pianoService.save(piano);
        if (updatedPiano != null) {
            return Response.ok(updatedPiano).build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePiano(@PathParam("id") int id) {
        log.info("deletePiano was invoked.");
        if (pianoService.deleteById(id)) {
            return Response.ok("Piano successfully deleted.").build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }
}
