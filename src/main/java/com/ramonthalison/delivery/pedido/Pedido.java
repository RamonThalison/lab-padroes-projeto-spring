package com.ramonthalison.delivery.pedido;

import com.ramonthalison.delivery.frete.TipoEntrega;
import com.ramonthalison.delivery.cliente.Cliente;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Pedido {

    // Identificador gerado pelo banco para cada novo pedido.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Muitos pedidos podem pertencer ao mesmo cliente.
    @ManyToOne(optional = false)
    private Cliente cliente;

    @Column(nullable = false)
    private BigDecimal valor;

    // EnumType.STRING mantém valores legíveis no banco e evita dependência da ordem do enum.
    @Enumerated(EnumType.STRING)
    private TipoEntrega tipoEntrega;

    private BigDecimal frete;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    // Construtor protegido usado exclusivamente pelo JPA.
    protected Pedido() {
    }

    public Pedido(Cliente cliente, BigDecimal valor, TipoEntrega tipoEntrega, BigDecimal frete) {
        this.cliente = cliente;
        this.valor = valor;
        this.tipoEntrega = tipoEntrega;
        this.frete = frete;
        this.status = StatusPedido.CRIADO;
    }

    // A mudança de status permanece dentro da entidade, que protege seu próprio estado.
    public void alterarStatus(StatusPedido novoStatus) {
        this.status = novoStatus;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public BigDecimal getFrete() {
        return frete;
    }

    public StatusPedido getStatus() {
        return status;
    }
}
