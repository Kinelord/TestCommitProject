//package com.shakirov.http.controller.exchangetype;
//
///**
// * @author igor@shakirov-dev.ru on 12.12.2022
// * @version 1.0
// */
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/api/rabbitmq/direct/")
//public class RabbitMQDirectWebController {
//
//    private final AmqpTemplate amqpTemplate;
//
//    public RabbitMQDirectWebController(AmqpTemplate amqpTemplate) {
//        this.amqpTemplate = amqpTemplate;
//    }
//
//    @GetMapping(value = "/producer")
//    public String producer(@RequestParam("exchangeName") String exchange, @RequestParam("routingKey") String routingKey,
//                           @RequestParam("messageData") String messageData) {
//
//        amqpTemplate.convertAndSend(exchange, routingKey, messageData);
//
//        return "Message sent to the RabbitMQ Successfully";
//    }
//
//}
