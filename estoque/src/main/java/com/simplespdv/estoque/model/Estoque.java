package com.simplespdv.estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Estoque {

    @Id
    private Long produtoId;  // O produtoId será o identificador do estoque
    private int quantidade;

    // Getters e Setters
    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
