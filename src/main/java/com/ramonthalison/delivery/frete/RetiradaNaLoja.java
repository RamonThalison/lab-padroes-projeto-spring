package com.ramonthalison.delivery.frete;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class RetiradaNaLoja implements CalculadoraFrete {

    // Associa esta implementação à opção RETIRADA.
    public TipoEntrega tipo() {
        return TipoEntrega.RETIRADA;
    }

    // A retirada pelo cliente não gera custo de frete.
    public BigDecimal calcular(BigDecimal valorPedido) {
        return BigDecimal.ZERO;
    }
}
