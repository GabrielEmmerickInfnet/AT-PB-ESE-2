package com.simplespdv.estoque.listener;

import com.simplespdv.estoque.model.Estoque;
import com.simplespdv.estoque.repository.EstoqueRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class EstoqueListener {

    private final EstoqueRepository estoqueRepository;

    public EstoqueListener(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    @RabbitListener(queues = "estoqueQueue")
    public void receberMensagem(Long produtoId) {
        try {
            // Cria um novo registro de estoque com quantidade inicial 1
            Estoque novoEstoque = new Estoque();
            novoEstoque.setProdutoId(produtoId);
            novoEstoque.setQuantidade(1);  // Quantidade inicial

            // Salvar o novo estoque no banco de dados
            estoqueRepository.save(novoEstoque);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
