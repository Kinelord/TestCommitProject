//package com.shakirov.config.websocket.exchangetype;
//
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.FanoutExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author igor@shakirov-dev.ru on 12.12.2022
// * @version 1.0
// */
//@Configuration
//public class RabbitMQFanoutConfig {
//
//    @Bean
//    Queue marketingQueue() {
//        return new Queue("marketingQueue", false);
//    }
//
//    @Bean
//    Queue financeQueue() {
//        return new Queue("financeQueue", false);
//    }
//
//    @Bean
//    Queue adminQueue() {
//        return new Queue("adminQueue", false);
//    }
//
//    @Bean
//    FanoutExchange exchange() {
//        return new FanoutExchange("fanout-exchange");
//    }
//
//    @Bean
//    Binding marketingBinding(Queue marketingQueue, FanoutExchange exchange) {
//        return BindingBuilder.bind(marketingQueue).to(exchange);
//    }
//
//    @Bean
//    Binding financeBinding(Queue financeQueue, FanoutExchange exchange) {
//        return BindingBuilder.bind(financeQueue).to(exchange);
//    }
//
//    @Bean
//    Binding adminBinding(Queue adminQueue, FanoutExchange exchange) {
//        return BindingBuilder.bind(adminQueue).to(exchange);
//    }
//
//}
