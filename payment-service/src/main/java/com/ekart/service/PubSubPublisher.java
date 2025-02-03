/**
 * Author: BIKASH
 */
package com.ekart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;

@Component
public class PubSubPublisher {

    private final MessageChannel outputChannel;

    @Autowired
    public PubSubPublisher(@Qualifier("myOutputChannel") MessageChannel outputChannel) {
        this.outputChannel = outputChannel;
    }

    public void publishMessage(String messageContent) {
        Message<String> message = new GenericMessage<>(messageContent);
        outputChannel.send(message);
    }


}
