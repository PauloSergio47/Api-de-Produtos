package com.paulo.Api.de.produtos.repository;

import com.paulo.Api.de.produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
