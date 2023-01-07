//package com.shakirov.http.controller.exchangetype;
//
///**
// * @author igor@shakirov-dev.ru on 12.12.2022
// * @version 1.0
// */
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.amqp.support.converter.SimpleMessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/api/rabbitmq/header/")
//public class RabbitMQHeaderWebController {
//
//    private final AmqpTemplate amqpTemplate;
//
//    public RabbitMQHeaderWebController(AmqpTemplate amqpTemplate) {
//        this.amqpTemplate = amqpTemplate;
//    }
//
//    @GetMapping(value = "/producer")
//    public String producer(@RequestParam("exchangeName") String exchange, @RequestParam("department") String department,
//                           @RequestParam("messageData") String messageData) {
//
//        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setHeader("department", department);
//        MessageConverter messageConverter = new SimpleMessageConverter();
//        Message message = messageConverter.toMessage(messageData, messageProperties);
//        amqpTemplate.send(exchange, "", message);
//
//        return "Message sent to the RabbitMQ Header Exchange Successfully";
//    }
//
//}
