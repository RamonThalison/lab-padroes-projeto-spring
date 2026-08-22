package com.ramonthalison.delivery.erro;

// Exceção de domínio convertida em HTTP 404 pelo tratador global.
public class RecursoNaoEncontradoException extends RuntimeException {
    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
