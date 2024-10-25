package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Path("/kafka")
public class GreetingResource {

    @Inject
    kafkaproducer producer;  // Inject the Kafka producer

    @POST
    @Path("/send")
    public Response sendMessage(@QueryParam("message") String message) {
        if (message == null || message.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Message cannot be empty").build();
        }

         final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

//        scheduler.scheduleAtFixedRate(() -> {
        producer.sendMessage(message);  // Send the message to Kafka
//        }, 0, 5, TimeUnit.SECONDS);
        return Response.ok("Message sent: " + message).build();
    }
}
