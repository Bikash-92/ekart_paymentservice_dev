/**
 * Author: BIKASH
 */
package com.ekart.service;

import com.ekart.dto.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Storage;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class InvoiceService {

   Logger logger = LoggerFactory.getLogger(InvoiceService.class);

    @Value("${gcs.bucket.name}")
    private String bucketName;

    @Autowired
    private Storage storage;

    public String storeInvoiceToGCS(String invoiceString){
        ObjectMapper objectMapper = new ObjectMapper();
        String link = null;
        try {
            // Convert JSON string to Java object (e.g., Person)
            logger.info("String formated data : {}",invoiceString);
            Invoice invoice = objectMapper.readValue(invoiceString, Invoice.class);
            link =  generateAndStoreInvoiceReport(invoice);
            logger.info("Link : {}",link);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return link;
    }

    private String generateAndStoreInvoiceReport(Invoice invoice) throws IOException, DocumentException {
        // Create PDF in memory
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document, byteArrayOutputStream);

        document.open();
        document.add(new Paragraph("Invoice Details"));
        document.add(new Paragraph("Customer: " + invoice.getCustomerName()));
        document.add(new Paragraph("Total Amount: " + invoice.getAmount()));
        document.add(new Paragraph("Shipping Address : "));
        document.add(new Paragraph("____________________________"));
        document.add(new Paragraph("Name :"+invoice.getRecipientName()));
        StringBuffer buffer = new StringBuffer();
        buffer.setLength(0);
        buffer.append("City: ").append(invoice.getCity()).append("\n").
                append("State: ").append(invoice.getState()).append("\n")
                        .append("Zip code: ").append(invoice.getZipCode());
        document.add(new Paragraph("Shipping Address:  "+buffer.toString()));

        document.close();

        // Convert the byte array to InputStream
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        // Create a blob and upload to GCS
        String fileName = invoice.getInvoiceNo()+".pdf";
        Blob blob = storage.create(
                Blob.newBuilder(bucketName, fileName).build(),
                inputStream
        );
        return  blob.getSelfLink();
    }



}
