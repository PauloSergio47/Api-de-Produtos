package com.paulo.Api.de.produtos.service;

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
        return produtoRepo.findById(id).orElse(null);
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Produto produto = produtoRepo.findById(id).orElse(null);

        if (produto == null) {
            return null;
        }

        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidade(produtoAtualizado.getQuantidade());
        produto.setDescricao(produtoAtualizado.getDescricao());

        return produtoRepo.save(produto);
    }

    public boolean deletarProduto(Long id) {
        Produto produto = produtoRepo.findById(id).orElse(null);

        if (produto == null) {
            return false;
        }

        produtoRepo.delete(produto);
        return true;
    }
}
