package com.simplespdv.repository;

import com.simplespdv.model.ProdutoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoHistoricoRepository extends JpaRepository<ProdutoHistorico, Long> {
    // Repositório padrão para o histórico de produtos
}