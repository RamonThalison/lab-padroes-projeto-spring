package com.ramonthalison.delivery.frete;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class FreteNormal implements CalculadoraFrete {

    // Associa esta implementação à opção NORMAL.
    public TipoEntrega tipo() {
        return TipoEntrega.NORMAL;
    }

    // Regra simplificada: entrega normal possui taxa fixa de R$ 12,00.
    public BigDecimal calcular(BigDecimal valorPedido) {
        return new BigDecimal("12.00");
    }
}
