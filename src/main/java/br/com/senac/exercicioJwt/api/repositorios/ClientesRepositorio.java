package br.com.senac.exercicioJwt.api.repositorios;

import br.com.senac.exercicioJwt.api.entidades.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientesRepositorio extends JpaRepository<Clientes, Long> {
}
