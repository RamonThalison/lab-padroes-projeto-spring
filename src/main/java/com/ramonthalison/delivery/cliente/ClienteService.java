package com.ramonthalison.delivery.cliente;

import com.ramonthalison.delivery.erro.RecursoNaoEncontradoException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    // Injeção por construtor facilita testes e deixa a dependência explícita.
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente criar(ClienteRequest request) {
        return repository.save(new Cliente(request.nome(), request.cep()));
    }

    public List<Cliente> listar() {
        return repository.findAll();
    }

    public Cliente buscar(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado: " + id));
    }

    public Cliente atualizar(Long id, ClienteRequest request) {
        var cliente = buscar(id);
        cliente.atualizar(request.nome(), request.cep());
        return repository.save(cliente);
    }

    public void excluir(Long id) {
        repository.delete(buscar(id));
    }
}
