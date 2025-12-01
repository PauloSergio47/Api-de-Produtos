package com.paulo.Api.de.produtos.service;

import com.paulo.Api.de.produtos.dto.ProdutoRequestDTO;
import com.paulo.Api.de.produtos.exception.ProdutoNaoEncontradoException;
import com.paulo.Api.de.produtos.model.Produto;
import com.paulo.Api.de.produtos.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepo;

    public ProdutoService(ProdutoRepository produtoRepo) {
        this.produtoRepo = produtoRepo;
    }

    public Produto criarProduto(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidade(dto.quantidade());
        produto.setDescricao(dto.descricao());

        return produtoRepo.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepo.findAll();
    }

    public Produto obterProdutoPorId(Long id) {
        return produtoRepo.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public Produto atualizarProduto(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepo.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setQuantidade(dto.quantidade());
        produto.setDescricao(dto.descricao());

        return produtoRepo.save(produto);
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepo.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        produtoRepo.delete(produto);
    }
}