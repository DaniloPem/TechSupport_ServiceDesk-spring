package projetotechsupport.apitechsupport.model.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByCodigoLike(String codigo);
}
