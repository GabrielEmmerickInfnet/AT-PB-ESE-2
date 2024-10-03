package com.simplespdv.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import com.simplespdv.config.RabbitMQConfig;
import com.simplespdv.model.Produto;

@Component
public class ProdutoListener {

    // Ouvir a fila para eventos de produto criado
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receberProdutoCriadoEvento(Produto produto) {
        System.out.println("Mensagem recebida do RabbitMQ: Produto criado com ID " + produto.getId());
        
    }
}
