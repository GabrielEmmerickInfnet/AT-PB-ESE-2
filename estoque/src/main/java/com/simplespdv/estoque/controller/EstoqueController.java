package com.simplespdv.estoque.controller;

import com.simplespdv.estoque.model.Estoque;
import com.simplespdv.estoque.service.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(name = "Estoque", description = "API para gestão de estoque")
@RequestMapping("/")
public class EstoqueController {

    private static final Logger log = LoggerFactory.getLogger(EstoqueController.class);
    private final EstoqueService estoqueService;

    // Construtor para injeção de dependência
    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @Operation(summary = "Buscar todos os estoques", description = "Retorna a lista de todos os estoques cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Estoque[].class)))
    })
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(estoqueService.findAll());
    }

    @Operation(summary = "Buscar estoque por ID", description = "Busca um estoque pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque encontrado",
                    content = @Content(schema = @Schema(implementation = Estoque.class))),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        log.info("Find estoque by id: {}", id);
        Optional<Estoque> optional = estoqueService.findById(id);
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar estoque por ID", description = "Deleta um estoque pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        estoqueService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Atualizar um estoque", description = "Atualiza as informações de um estoque existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = Estoque.class))),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Estoque> updateEstoque(@PathVariable Long id, @RequestBody Estoque estoque) {
        Optional<Estoque> optionalEstoque = estoqueService.findById(id);
        if (optionalEstoque.isPresent()) {
            Estoque estoqueExistente = optionalEstoque.get();
            estoqueExistente.setProdutoId(estoque.getProdutoId());
            estoqueExistente.setQuantidade(estoque.getQuantidade());
            Estoque atualizado = estoqueService.update(estoqueExistente);
            return ResponseEntity.ok(atualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
