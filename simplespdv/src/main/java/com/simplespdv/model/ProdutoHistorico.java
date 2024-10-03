package com.simplespdv.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProdutoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeOriginal;
    private String nomeAlterado;
    private String codigoBarrasOriginal;
    private String codigoBarrasAlterado;
    private double precoOriginal;
    private double precoAlterado;

    private LocalDateTime dataAlteracao;

    // Construtores
    public ProdutoHistorico() {}

    public ProdutoHistorico(String nomeOriginal, String nomeAlterado, String codigoBarrasOriginal, 
                            String codigoBarrasAlterado, double precoOriginal, double precoAlterado, 
                            LocalDateTime dataAlteracao) {
        this.nomeOriginal = nomeOriginal;
        this.nomeAlterado = nomeAlterado;
        this.codigoBarrasOriginal = codigoBarrasOriginal;
        this.codigoBarrasAlterado = codigoBarrasAlterado;
        this.precoOriginal = precoOriginal;
        this.precoAlterado = precoAlterado;
        this.dataAlteracao = dataAlteracao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeOriginal() {
        return nomeOriginal;
    }

    public void setNomeOriginal(String nomeOriginal) {
        this.nomeOriginal = nomeOriginal;
    }

    public String getNomeAlterado() {
        return nomeAlterado;
    }

    public void setNomeAlterado(String nomeAlterado) {
        this.nomeAlterado = nomeAlterado;
    }

    public String getCodigoBarrasOriginal() {
        return codigoBarrasOriginal;
    }

    public void setCodigoBarrasOriginal(String codigoBarrasOriginal) {
        this.codigoBarrasOriginal = codigoBarrasOriginal;
    }

    public String getCodigoBarrasAlterado() {
        return codigoBarrasAlterado;
    }

    public void setCodigoBarrasAlterado(String codigoBarrasAlterado) {
        this.codigoBarrasAlterado = codigoBarrasAlterado;
    }

    public double getPrecoOriginal() {
        return precoOriginal;
    }

    public void setPrecoOriginal(double precoOriginal) {
        this.precoOriginal = precoOriginal;
    }

    public double getPrecoAlterado() {
        return precoAlterado;
    }

    public void setPrecoAlterado(double precoAlterado) {
        this.precoAlterado = precoAlterado;
    }

    public LocalDateTime getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
