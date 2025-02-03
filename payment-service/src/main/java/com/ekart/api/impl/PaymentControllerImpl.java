/**
 * Author: BIKASH
 */
package com.ekart.api.impl;

import com.ekart.api.PaymentController;
import com.ekart.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PaymentControllerImpl implements PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Override
    public ResponseEntity<byte[]> downloadFile(@PathVariable String invoice) throws IOException {
        byte[] fileContent = paymentService.downloadInvoice(invoice);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + invoice);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileContent);
    }
}
