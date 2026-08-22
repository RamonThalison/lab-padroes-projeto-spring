package com.ramonthalison.delivery.pedido;

import com.ramonthalison.delivery.frete.TipoEntrega;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record CriarPedidoRequest(
    // O pedido referencia um cliente já cadastrado.
    @NotNull @Positive Long clienteId,

    // O valor deve ser positivo para representar uma compra válida.
    @NotNull @DecimalMin(value = "0.01") BigDecimal valor,

    // A modalidade determina qual Strategy será usada no cálculo.
    @NotNull TipoEntrega tipoEntrega
) {
}
