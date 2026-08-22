package com.ramonthalison.delivery.erro;

import java.time.Instant;
import java.util.Map;

// Formato padronizado de erro para os consumidores da API.
public record ErroResposta(
    Instant instante,
    int status,
    String mensagem,
    Map<String, String> campos
) {
}
