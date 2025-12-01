package com.paulo.Api.de.produtos.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProdutoRequestDTO(
        @NotBlank(message = "O nome é obrigatório!")
        @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres.")
        String nome,

        @NotNull(message = "O preço é obrigatório!")
        @Positive(message = "O preço deve ser maior que zero.")
        BigDecimal preco,

        @NotNull(message = "A quantidade é obrigatório!")
        @PositiveOrZero(message = "A quantidade não pode ser negativa.")
        Integer quantidade,

        @Size(max = 400, message = "A descrição deve ter no máximo 400 caracteres.")
        String descricao
) {}
