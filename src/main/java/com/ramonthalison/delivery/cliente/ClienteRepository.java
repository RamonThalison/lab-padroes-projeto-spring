package com.ramonthalison.delivery.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

// O Spring Data cria em tempo de execução as operações básicas de persistência.
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
