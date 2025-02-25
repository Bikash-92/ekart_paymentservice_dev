/**
 * Author: BIKASH
 */
package com.ekart.service;

import com.ekart.dto.Invoice;
import com.ekart.dto.Item;
import com.ekart.dto.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class PaymentService {
    Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private PubSubPublisher pubSubPublisher;

    @Autowired
    private Storage storage;


    @Value("${gcs.bucket.name}")
    private String bucketName;

    public void processPayment(Order order) {
        logger.info("payment process for:{}", order.getInvoice());
        pubSubPublisher.publishMessage(constructInvoiceJson(order));
    }

    private String constructInvoiceJson(Order order) {
        Invoice invoice = new Invoice();
        invoice.setInvoiceNo(order.getInvoice());
        invoice.setAmount(order.getItems().stream().map(Item::getAmount).reduce(0.0, Double::sum));
        invoice.setCustomerName("System Generate");
        invoice.setPurchaseDate(new Date());
        invoice.setCity(order.getShippingAddress().getCity());
        invoice.setState(order.getShippingAddress().getName());
        invoice.setRecipientName(order.getShippingAddress().getState());
        invoice.setZipCode(order.getShippingAddress().getZipCode());
        String jsonString = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonString = objectMapper.writeValueAsString(invoice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonString;
    }


    public byte[] downloadInvoice(String fileName) throws IOException {
        BlobId blobId = BlobId.of(bucketName, fileName);
        Blob blob = storage.get(blobId);
        logger.info("Bucket: " + bucketName);
        logger.info("File Name: " + fileName);
        if (blob == null) {
            throw new IOException("File not found in the bucket.");
        }
        byte[] inputStream = blob.getContent();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        StreamUtils.copy(inputStream, byteArrayOutputStream);
        byte[] fileContent = byteArrayOutputStream.toByteArray();
        return fileContent;
    }


}



