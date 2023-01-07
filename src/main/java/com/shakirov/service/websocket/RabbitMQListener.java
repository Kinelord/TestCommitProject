//package com.shakirov.service.websocket;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageListener;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RabbitMQListener implements MessageListener {
//
//
//    public void onMessage(Message message) {
//        System.out.println("Consuming Message - " + new String(message.getBody()));
//    }
//
//}