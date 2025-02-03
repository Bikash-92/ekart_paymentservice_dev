/**
 * Author: BIKASH
 */
package com.ekart.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PubSubConsumer {

    Logger logger = LoggerFactory.getLogger(PubSubConsumer.class);
    @Autowired
    private InvoiceService invoiceService;

    @ServiceActivator(inputChannel = "myOutputChannel")
    public void handlePubSubMessage(Message<String> message) {
        String payload = message.getPayload();
        logger.info("Message received by Pubsub invoice consumer : {}", payload);
        invoiceService.storeInvoiceToGCS(payload);
    }
}
