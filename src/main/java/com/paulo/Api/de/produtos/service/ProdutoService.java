package com.paulo.Api.de.produtos.service;

import com.paulo.Api.de.produtos.exception.ProdutoNaoEncontradoException;
import com.paulo.Api.de.produtos.model.Produto;
import com.paulo.Api.de.produtos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepo;

    public ProdutoService(ProdutoRepository produtoRepo) {
        this.produtoRepo = produtoRepo;
    }

    public Produto criarProduto(Produto produto) {
        return produtoRepo.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepo.findAll();
    }

    public Produto obterProdutoPorId(Long id) {
        return produtoRepo.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = produtoRepo.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidade(produtoAtualizado.getQuantidade());
        produto.setDescricao(produtoAtualizado.getDescricao());

        return produtoRepo.save(produto);
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepo.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));

        produtoRepo.delete(produto);
    }
}
