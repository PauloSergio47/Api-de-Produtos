package com.paulo.Api.de.produtos.dto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        BigDecimal preco,
        Integer quantidade,
        String descricao
) {}
