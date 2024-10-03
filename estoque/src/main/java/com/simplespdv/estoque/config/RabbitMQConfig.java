package com.simplespdv.estoque.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Usar a mesma exchange e routing key do simplespdv
    public static final String QUEUE_NAME = "estoqueQueue";           // Nome da fila do estoque
    public static final String EXCHANGE_NAME = "produtoExchange";     // Usar a mesma exchange do simplespdv
    public static final String ROUTING_KEY = "produtoRoutingKey";     // Usar a mesma routing key do simplespdv

    @Bean
    public Queue queue() {
        // Configurar a fila do estoque
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange exchange() {
        // Associar a mesma exchange do simplespdv
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        // Vincular a fila do estoque à exchange usando a mesma routing key
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}
