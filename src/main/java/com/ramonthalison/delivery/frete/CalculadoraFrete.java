package com.ramonthalison.delivery.frete;

import java.math.BigDecimal;

public interface CalculadoraFrete {

    // Identifica qual modalidade esta estratégia atende.
    TipoEntrega tipo();

    // Contrato comum que permite trocar o cálculo sem alterar o serviço de pedidos.
    BigDecimal calcular(BigDecimal valorPedido);
}
