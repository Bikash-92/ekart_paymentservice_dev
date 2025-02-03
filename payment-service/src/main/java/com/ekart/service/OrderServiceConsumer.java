package com.ekart.service;


import com.ekart.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceConsumer {
    Logger logger = LoggerFactory.getLogger(OrderServiceConsumer.class);

    @Autowired
    private PaymentService paymentService;
    @KafkaListener(groupId = "order-group", topics = "order-topic")
    public void processOrder(Order order){
        paymentService.processPayment(order);
        logger.info("Consumed order : {}", order);
    }

}
