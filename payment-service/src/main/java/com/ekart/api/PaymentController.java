/**
 * Author: BIKASH
 */
package com.ekart.api;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/ekart/pay")
public interface PaymentController {

    @GetMapping("/download/{invoice}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String invoice) throws IOException;
}
