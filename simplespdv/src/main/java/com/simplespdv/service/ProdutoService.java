package com.simplespdv.service;

import com.simplespdv.config.RabbitMQConfig;
import com.simplespdv.model.Produto;
import com.simplespdv.repository.ProdutoRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // Enviar mensagem para o RabbitMQ quando um produto é criado ou atualizado
    public void enviarProdutoCriadoEvento(Produto produto) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, produto);
        System.out.println("Mensagem enviada para RabbitMQ: Produto criado/atualizado com ID " + produto.getId());
    }

    // Listar todos os produtos
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Salvar um novo produto e enviar evento para RabbitMQ
    public Produto salvarProduto(Produto produto) {
        Produto produtoSalvo = produtoRepository.save(produto);
        enviarProdutoCriadoEvento(produtoSalvo); // Enviar o evento para o RabbitMQ
        return produtoSalvo;
    }

    // Buscar produto por ID
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    // Deletar um produto por ID
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
