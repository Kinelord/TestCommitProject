package com.shakirov.config.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile(value = "dev")
@Slf4j
public class RabbitMQConfig {

    @Value("${igor.rabbitmq.queue}")
    String queueName;

    @Value("${igor.rabbitmq.exchange}")
    String exchange;

    @Value("${igor.rabbitmq.routingKey}")
    private String routingkey;

    @Value("${spring.rabbitmq.username}")
    String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

//    @Value("${spring.rabbitmq.host}")
//    private String host;

//    @Value("${spring.rabbitmq.port:5672}")
//    private int port;


    //объявляем очередь с именем queueName
//    @Bean
//    Queue queue() {
//        // This queue has the following properties:
//        // name: my_durable
//        // durable: false  // прочный defaul true
//        // exclusive: false  //эксклюзив
//        // auto_delete: false  // авто _ удалить
//        return new Queue(queueName, false);
//    }

    @Bean
    Queue queue() {
        return QueueBuilder.durable("igorForDLQ.queue").withArgument("x-dead-letter-exchange", "deadLetterExchange")
                .withArgument("x-dead-letter-routing-key", "deadLetter").build();
    }

    // Мертвая очередь
    @Bean
    Queue dlq() {
        return QueueBuilder.durable("deadLetter.queue").build();
    }


    // Простой контейнер, собирающий информацию для описания прямого обмена. Используется в
    // сочетании с административными операциями.
//    @Bean
//    DirectExchange exchange() {
//        return new DirectExchange(exchange);
//    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("igorExchange");
    }

    // Отправляет мертвые письма в мертвую очередь
    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange("deadLetterExchange");
    }

    // Простой контейнер, собирающий информацию для описания привязки. Принимает имена назначения
    // строк и имен обмена в качестве аргументов для облегчения подключения с помощью конфигурации
    // на основе кода. Может использоваться в сочетании с AmqpAdmin или создаваться с помощью BindingBuilder.
//    @Bean
//    Binding binding(Queue queue, DirectExchange exchange) {
//        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
//    }

    @Bean
    Binding binding() {
        return BindingBuilder.bind(queue()).to(exchange()).with("igorDLQ");
    }

    // Binding мертвой очереди
    @Bean
    Binding DLQbinding() {
        return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with("deadLetter");
    }


    // Интерфейс конвертера сообщений.
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    //    создать MessageListenerContainer с помощью фабрики подключений по умолчанию
//    @Bean
//    MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
//        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
//        simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
//        simpleMessageListenerContainer.setQueues(queue());
////        simpleMessageListenerContainer.setMessageListener(new RabbitMQListener());
//        return simpleMessageListenerContainer;
//    }

    //настраиваем соединение с RabbitMQ
    //создание пользовательской фабрики подключений
/*    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
//        cachingConnectionFactory.setPort(port);
        cachingConnectionFactory.setUsername(username);
        cachingConnectionFactory.setUsername(password);
        log.info("Creating connection factory with: " + username + "@" + *//*host +*//* ":" *//*+ port*//*);
        return cachingConnectionFactory;
    }*/


    // Задает базовый набор переносимых административных операций AMQP для AMQP > 0.9.
    // Требуется для выполнения функций администрирования для брокера AMQP
/*    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }*/


    //создать MessageListenerContainer с помощью пользовательской фабрики подключений
/*    @Bean
    @DependsOn("connectionFactory")
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(queue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQListener());
        return simpleMessageListenerContainer;
    }*/

    // Задает базовый набор операций AMQP. Предоставляет синхронные методы отправки и получения. Методы convertAndSend(Object) и receiveAndConvert() позволяют отправлять и получать объекты POJO. Ожидается, что реализации будут делегированы экземпляру org.springframework.amqp.support.converter.MessageConverter для выполнения преобразования в тип полезной нагрузки AMQP byte[] и из него.
/*    @Bean
    public AmqpTemplate rabbitTemplate() {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }*/

//    @Bean
//    MessageListenerAdapter listenerAdapter(RabbitConsumer receiver) {
//        return new MessageListenerAdapter(receiver, "consumeMessage");
//    }

}
