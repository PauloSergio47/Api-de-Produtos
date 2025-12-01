package com.paulo.Api.de.produtos.controller;

import com.paulo.Api.de.produtos.dto.ProdutoRequestDTO;
import com.paulo.Api.de.produtos.dto.ProdutoResponseDTO;
import com.paulo.Api.de.produtos.model.Produto;
import com.paulo.Api.de.produtos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(
            @Valid @RequestBody ProdutoRequestDTO dto) {

        Produto criado = produtoService.criarProduto(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ProdutoResponseDTO(
                        criado.getId(),
                        criado.getNome(),
                        criado.getPreco(),
                        criado.getQuantidade(),
                        criado.getDescricao()
                ));
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {

        List<ProdutoResponseDTO> lista = produtoService.listarProdutos()
                .stream()
                .map(p -> new ProdutoResponseDTO(
                        p.getId(),
                        p.getNome(),
                        p.getPreco(),
                        p.getQuantidade(),
                        p.getDescricao()))
                .toList();

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> obterProdutoPorId(@PathVariable Long id) {

        Produto produto = produtoService.obterProdutoPorId(id);

        ProdutoResponseDTO dto = new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getDescricao()
        );

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(
            @PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {

        Produto atualizado = produtoService.atualizarProduto(id, dto);

        ProdutoResponseDTO resposta = new ProdutoResponseDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getPreco(),
                atualizado.getQuantidade(),
                atualizado.getDescricao()
        );

        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletarProduto(id);
        return ResponseEntity.noContent().build();
    }
}
