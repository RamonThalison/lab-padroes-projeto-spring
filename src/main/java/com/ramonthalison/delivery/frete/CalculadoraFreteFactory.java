package com.ramonthalison.delivery.frete;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class CalculadoraFreteFactory {

    // EnumMap oferece uma busca simples e eficiente por modalidade de entrega.
    private final Map<TipoEntrega, CalculadoraFrete> estrategias = new EnumMap<>(TipoEntrega.class);

    // O Spring injeta automaticamente todas as implementações de CalculadoraFrete.
    public CalculadoraFreteFactory(List<CalculadoraFrete> calculadoras) {
        calculadoras.forEach(calculadora -> estrategias.put(calculadora.tipo(), calculadora));
    }

    // A Factory esconde do restante da aplicação a escolha da Strategy concreta.
    public CalculadoraFrete obter(TipoEntrega tipo) {
        var estrategia = estrategias.get(tipo);
        if (estrategia == null) {
            throw new IllegalArgumentException("Tipo de entrega não suportado: " + tipo);
        }
        return estrategia;
    }
}
