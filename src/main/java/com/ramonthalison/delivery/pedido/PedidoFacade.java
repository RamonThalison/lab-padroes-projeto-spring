package com.ramonthalison.delivery.pedido;

import com.ramonthalison.delivery.frete.CalculadoraFreteFactory;
import com.ramonthalison.delivery.cliente.ClienteService;
import com.ramonthalison.delivery.erro.RecursoNaoEncontradoException;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PedidoFacade {

    private final PedidoRepository repository;
    private final CalculadoraFreteFactory fretes;
    private final ClienteService clientes;

    public PedidoFacade(
        PedidoRepository repository,
        CalculadoraFreteFactory fretes,
        ClienteService clientes
    ) {
        this.repository = repository;
        this.fretes = fretes;
        this.clientes = clientes;
    }

    // A Facade coordena cliente, cálculo do frete e persistência em uma única operação.
    public Pedido criar(CriarPedidoRequest request) {
        var cliente = clientes.buscar(request.clienteId());
        var frete = fretes.obter(request.tipoEntrega()).calcular(request.valor());
        return repository.save(new Pedido(cliente, request.valor(), request.tipoEntrega(), frete));
    }

    public Pedido buscar(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido não encontrado: " + id));
    }

    public List<Pedido> listar() {
        return repository.findAll();
    }

    // A atualização reutiliza a busca centralizada e salva a nova situação do pedido.
    public Pedido alterarStatus(Long id, StatusPedido status) {
        var pedido = buscar(id);
        pedido.alterarStatus(status);
        return repository.save(pedido);
    }
}
