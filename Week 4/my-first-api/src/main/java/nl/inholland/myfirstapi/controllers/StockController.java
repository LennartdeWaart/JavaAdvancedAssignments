package nl.inholland.myfirstapi.controllers;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.extern.java.Log;
import nl.inholland.myfirstapi.interfaces.IStockService;
import nl.inholland.myfirstapi.models.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Log
@Path("/stock")
@Produces(MediaType.APPLICATION_JSON_VALUE)
public class StockController {

    @Autowired
    private IStockService stockService;

    @GET
    @Path("/pianos")
    public Response getStock(@QueryParam("brand") String brand) {
        log.info("getStock was invoked.");
        List<Stock> stock = null;
        if (brand != null && brand != "") {
            stock = stockService.getStockByPianoBrand(brand);
        } else {
            stock = (List<Stock>)stockService.findAll();
        }

        if (stock != null) {
            return Response.ok(stock).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    @Path("/pianos/save")
    public Response savePianoToStock(Stock stock) {
        log.info("savePianoToStock was invoked.");
        Stock saved = stockService.save(stock);
        if (saved != null) {
            return Response.ok(saved).build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }

    @POST
    @Path("/pianos/save/subset")
    public Response savePianosToStock(Iterable<Stock> stock) {
        log.info("savePianosToStock was invoked.");
        Iterable<Stock> saved = stockService.saveAll(stock);
        if (saved != null) {
            return Response.ok(saved).build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }

    @PUT
    @Path("/pianos/update")
    public Response updateStock(Stock stock) {
        log.info("updateStock was invoked.");
        Stock saved = stockService.save(stock);
        if (saved != null) {
            return Response.ok(saved).build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }

    @DELETE
    @Path("/pianos/delete")
    public Response updateStock(Stock stock, @PathParam("id") ID id) {
        log.info("updateStock was invoked.");
        boolean result;
        if (id != null) {
            result = stockService.deleteById(id);
        } else {
            result = stockService.delete(stock);
        }
        if (result) {
            return Response.ok("Stock successfully deleted.").build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }
}
