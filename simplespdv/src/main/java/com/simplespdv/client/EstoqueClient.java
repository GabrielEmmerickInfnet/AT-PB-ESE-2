package com.simplespdv.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "estoque-service", url = "http://localhost:8081")
public interface EstoqueClient {

    
    @GetMapping("/api/estoque/{produtoId}")
    int verificarEstoque(@PathVariable Long produtoId);

    @PostMapping("/api/estoque/reduzir")
    boolean reduzirEstoque(@RequestParam Long produtoId, @RequestParam int quantidadeVendida);
}
