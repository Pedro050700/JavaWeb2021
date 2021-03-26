package br.com.senac.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senac.dominio.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String > {
  Usuario findByLogin(String login);
}
