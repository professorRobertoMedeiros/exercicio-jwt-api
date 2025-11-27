package br.com.senac.exercicioJwt.api.repositorios;

import br.com.senac.exercicioJwt.api.entidades.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuariosRepositorio extends JpaRepository<Usuarios, Long> {
    Optional<Usuarios> findByEmail(String email);
}
