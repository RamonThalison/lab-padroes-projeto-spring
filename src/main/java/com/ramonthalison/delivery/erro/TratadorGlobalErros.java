package com.ramonthalison.delivery.erro;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorGlobalErros {

    // Converte recursos inexistentes em uma resposta HTTP 404 previsível.
    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ErroResposta> tratarNaoEncontrado(RecursoNaoEncontradoException excecao) {
        var erro = new ErroResposta(Instant.now(), 404, excecao.getMessage(), Map.of());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    // Reúne as mensagens de Bean Validation por nome de campo.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> tratarValidacao(MethodArgumentNotValidException excecao) {
        Map<String, String> campos = new LinkedHashMap<>();
        excecao.getBindingResult().getFieldErrors()
            .forEach(erro -> campos.put(erro.getField(), erro.getDefaultMessage()));

        var resposta = new ErroResposta(Instant.now(), 400, "Dados inválidos", campos);
        return ResponseEntity.badRequest().body(resposta);
    }
}
