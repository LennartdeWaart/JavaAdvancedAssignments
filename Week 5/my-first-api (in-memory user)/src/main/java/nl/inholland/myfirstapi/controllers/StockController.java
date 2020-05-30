package nl.inholland.myfirstapi.controllers;

import lombok.extern.java.Log;
import nl.inholland.myfirstapi.interfaces.IStockService;
import nl.inholland.myfirstapi.models.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Component
@Log
@Path("/stock")
@Produces(MediaType.APPLICATION_JSON_VALUE)
public class StockController {

    @Autowired
    private IStockService stockService;

    @GET
    public Response getStock() {
        log.info("getStock was invoked.");
        List<Stock> stock = (List<Stock>)stockService.findAll();

        if (stock != null) {
            return Response.ok(stock).build();
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getStockItem(@PathParam("id") int id) {
        log.info("getStockItem was invoked.");
        Optional<Stock> stock = stockService.findById(id);

        if (stock.isPresent()) {
            return Response.ok(stock).build();
        } else {
            return Response.noContent().build();
        }
    }

    @POST
    public Response saveStock(Stock stock) {
        log.info("saveStock was invoked.");
        Stock savedStock = stockService.save(stock);
        if (savedStock != null) {
            return Response.ok(savedStock).build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }

    @PUT
    public Response updateStock(Stock stock) {
        log.info("updateStock was invoked.");
        Stock updatedStock = stockService.save(stock);
        if (updatedStock != null) {
            return Response.ok(updatedStock).build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStock(@PathParam("id") int id) {
        log.info("deleteStock was invoked.");
        if (stockService.deleteById(id)) {
            return Response.ok("Stock successfully deleted.").build();
        } else {
            return Response.status(424, "An error occurred, please try again.").build();
        }
    }
}
