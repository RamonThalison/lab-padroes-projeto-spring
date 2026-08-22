package com.ramonthalison.delivery.frete;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

class FreteStrategyTest {

    // Cada teste valida uma Strategy isoladamente, sem iniciar o Spring.
    @Test
    void retiradaDeveSerGratis() {
        assertThat(new RetiradaNaLoja().calcular(new BigDecimal("100"))).isZero();
    }

    @Test
    void entregaNormalDeveCustarDoze() {
        assertThat(new FreteNormal().calcular(new BigDecimal("100"))).isEqualByComparingTo("12.00");
    }

    @Test
    void expressoDeveCustarVinteECinco() {
        assertThat(new FreteExpresso().calcular(new BigDecimal("100"))).isEqualByComparingTo("25.00");
    }
}
