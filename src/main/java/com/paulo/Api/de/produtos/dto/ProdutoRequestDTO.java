package com.paulo.Api.de.produtos.dto;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        String nome,
        BigDecimal preco,
        Integer quantidade,
        String descricao
) {}
