package com.ramonthalison.delivery.frete;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class FreteExpresso implements CalculadoraFrete {

    // Associa esta implementação à opção EXPRESSA.
    public TipoEntrega tipo() {
        return TipoEntrega.EXPRESSA;
    }

    // Regra simplificada: entrega expressa possui taxa fixa de R$ 25,00.
    public BigDecimal calcular(BigDecimal valorPedido) {
        return new BigDecimal("25.00");
    }
}
