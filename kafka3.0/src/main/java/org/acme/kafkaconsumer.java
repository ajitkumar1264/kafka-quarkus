//package org.acme;
//
//import jakarta.enterprise.context.ApplicationScoped;
//import org.eclipse.microprofile.reactive.messaging.Incoming;
//import org.eclipse.microprofile.reactive.messaging.Incoming;
//
//
//
//@ApplicationScoped
//
//public class kafkaconsumer
//{
//    // Method to consume messages from the Kafka topic
//    @Incoming("my-incoming-channel")  // Channel name matches the one defined in application.properties
//    public void consume(String message) {
//        System.out.println("Received message: " + message);
//    }
//}


package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("/messages")  // Define a base path for your resource
public class kafkaconsumer {

    private final List<String> messages = new ArrayList<>(); // List to store received messages

    // Method to consume messages from the Kafka topic
    @Incoming("my-incoming-channel")  // Channel name matches the one defined in application.properties
    public void consume(String message) {
        System.out.println("Received message: " + message);
        messages.add(message);  // Store the received message

        websocket.broadcastMessage(message);
    }

    // REST endpoint to retrieve received messages
    @GET
    @Produces(MediaType.APPLICATION_JSON)  // Specify the response format
    public List<String> getMessages() {
        return messages;  // Return the list of received messages
    }
}
