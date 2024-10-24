package org.acme;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;


@ApplicationScoped
public class kafkaproducer {

    @Inject
    @Channel("my-outgoing-channel")  // Channel name matches the one defined in application.properties
    Emitter<String> emitter;

    // Method to send messages
    public void sendMessage(String message) {
        emitter.send(message);
        System.out.println("Sent message: " + message);
    }

}
