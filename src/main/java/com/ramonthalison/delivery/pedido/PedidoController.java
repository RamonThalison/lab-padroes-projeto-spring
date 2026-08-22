package com.ramonthalison.delivery.pedido;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoFacade facade;

    public PedidoController(PedidoFacade facade) {
        this.facade = facade;
    }

    // Cria um pedido e informa sua URI no cabeçalho Location.
    @PostMapping
    public ResponseEntity<Pedido> criar(@Valid @RequestBody CriarPedidoRequest request) {
        var pedido = facade.criar(request);
        return ResponseEntity.created(URI.create("/pedidos/" + pedido.getId())).body(pedido);
    }

    @GetMapping
    public List<Pedido> listar() {
        return facade.listar();
    }

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Long id) {
        return facade.buscar(id);
    }

    // PATCH altera apenas o status, sem substituir todo o pedido.
    @PatchMapping("/{id}/status")
    public Pedido alterarStatus(@PathVariable Long id, @RequestParam StatusPedido status) {
        return facade.alterarStatus(id, status);
    }
}
