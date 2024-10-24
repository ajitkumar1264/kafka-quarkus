package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Incoming;



@ApplicationScoped

public class kafkaconsumer
{
    // Method to consume messages from the Kafka topic
    @Incoming("my-incoming-channel")  // Channel name matches the one defined in application.properties
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}
