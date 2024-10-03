package com.simplespdv.estoque.service;

import com.simplespdv.estoque.model.Estoque;
import com.simplespdv.estoque.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRepository;

    // Criar novo estoque com o produtoId como ID do estoque
    public Estoque create(Estoque estoque) {
        return estoqueRepository.save(estoque);  // O produtoId será usado como o ID do estoque
    }

    // Buscar todos os estoques
    public List<Estoque> findAll() {
        return estoqueRepository.findAll();
    }

    // Buscar estoque por ID (usando o produtoId)
    public Optional<Estoque> findById(Long produtoId) {
        return estoqueRepository.findById(produtoId);
    }

    // Deletar estoque por ID (produtoId)
    public void deleteById(Long produtoId) {
        estoqueRepository.deleteById(produtoId);
    }

    // Atualizar o estoque usando o produtoId como ID do estoque
    public Estoque update(Estoque estoque) {
        // Buscar o estoque pelo produtoId
        Optional<Estoque> estoqueExistente = estoqueRepository.findById(estoque.getProdutoId());
        if (estoqueExistente.isPresent()) {
            // Atualizar a quantidade do estoque existente
            Estoque estoqueAtualizado = estoqueExistente.get();
            estoqueAtualizado.setQuantidade(estoque.getQuantidade());
            return estoqueRepository.save(estoqueAtualizado);
        }
        return null; // Retorna null caso o estoque não seja encontrado
    }
}
