//package com.shakirov.config.websocket.exchangetype;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
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
//
//@Configuration
//public class RabbitMQTopicConfig {
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
//    Queue allQueue() {
//        return new Queue("allQueue", false);
//    }
//
//    @Bean
//    TopicExchange topicExchange() {
//        return new TopicExchange("topic-exchange");
//    }
//
//    @Bean
//    Binding marketingBinding(Queue marketingQueue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(marketingQueue).to(topicExchange).with("queue.marketing");
//    }
//
//    @Bean
//    Binding financeBinding(Queue financeQueue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(financeQueue).to(topicExchange).with("queue.finance");
//    }
//
//    @Bean
//    Binding adminBinding(Queue adminQueue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(adminQueue).to(topicExchange).with("queue.admin");
//    }
//
//    @Bean
//    Binding allBinding(Queue allQueue, TopicExchange topicExchange) {
//        return BindingBuilder.bind(allQueue).to(topicExchange).with("queue.*");
//    }
//
//}
