package com.ramonthalison.delivery.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

// Repository abstrai SQL e oferece CRUD para a entidade Pedido.
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
