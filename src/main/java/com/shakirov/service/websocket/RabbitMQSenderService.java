package com.shakirov.service.websocket;

import com.shakirov.model.websocket.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;


//@Service
@RequiredArgsConstructor
public class RabbitMQSenderService {

    private final AmqpTemplate rabbitTemplate;

    @Value("${igor.rabbitmq.exchange}")
    private String exchange;

    @Value("${igor.rabbitmq.routingKey}")
    private String routingKey;

    // Преобразуйте объект Java в сообщение Amqp и отправьте его на определенную биржу с определенным ключом маршрутизации.
    public void send(Employee company) {
        rabbitTemplate.convertAndSend(exchange, routingKey, company);
        System.out.println("PRODUCER Send msg = " + company);
    }

    public void sendForDLQ(Employee company) {
        rabbitTemplate.convertAndSend("igorExchange", "igorDLQ", company);
        System.out.println("PRODUCER Send msg = " + company);
    }

//    @RabbitListener(queues = "${igor.rabbitmq.queue}")
//    public void process(@Payload Message message){
//        System.out.println("@RabbitListener Message Recus: "+message.toString());
//        rabbitTemplate.send(message);
//    }

//    @RabbitListener(queues = "${igor.rabbitmq.queue}")
//    public void processMyQueue(String message) {
//        System.out.printf("*****String message Received from myQueue : %s ", new String(message.getBytes()));
//    }
}