package com.simplespdv.controller;

import com.simplespdv.model.Produto;
import com.simplespdv.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@Tag(name = "Produto", description = "API para gestão de produtos")
@RequestMapping("/")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private RabbitTemplate rabbitTemplate; // Injeta o RabbitTemplate para enviar mensagens ao RabbitMQ

    // Listar todos os produtos
    @Operation(summary = "Listar todos os produtos", description = "Retorna uma lista com todos os produtos cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Produto.class)))),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    // Salvar um novo produto e notificar o estoque
    @Operation(summary = "Salvar um novo produto", description = "Cria um novo produto e envia notificação para o serviço de estoque")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto salvo com sucesso", content = @Content(schema = @Schema(implementation = Produto.class))),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<?> salvarProduto(@RequestBody Produto produto) {
        // Salva o produto no simplespdv
        Produto produtoSalvo = produtoService.salvarProduto(produto);

        // Envia uma mensagem para o RabbitMQ informando o ID do produto recém-cadastrado
        rabbitTemplate.convertAndSend("estoqueExchange", "estoqueRoutingKey", produtoSalvo.getId());

        return ResponseEntity.ok(produtoSalvo);
    }

    // Buscar um produto por ID
    @Operation(summary = "Buscar um produto por ID", description = "Retorna um produto específico com base no ID fornecido")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto encontrado com sucesso", content = @Content(schema = @Schema(implementation = Produto.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    // Atualizar um produto existente
    @Operation(summary = "Atualizar um produto existente", description = "Atualiza um produto específico com base no ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content = @Content(schema = @Schema(implementation = Produto.class))),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Produto produtoExistente = produtoService.buscarPorId(id);

        if (produtoExistente != null) {
            // Atualiza as informações do produto
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setCodigoBarras(produtoAtualizado.getCodigoBarras());
            produtoExistente.setPreco(produtoAtualizado.getPreco());

            Produto produtoAtualizadoFinal = produtoService.salvarProduto(produtoExistente);
            return ResponseEntity.ok(produtoAtualizadoFinal);
        }

        return ResponseEntity.notFound().build(); // Retorna 404 caso o produto não seja encontrado
    }

    // Deletar um produto
    @Operation(summary = "Deletar um produto", description = "Remove um produto específico do sistema com base no ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Produto deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    })
    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}
