package projetotechsupport.apitechsupport.model.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface
UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByCodigoLike(String codigo);

    @Query("""
            SELECT usuario FROM Usuario usuario
            LEFT JOIN usuario.gruposAssignados gruposAssignados
            WHERE usuario.codigo LIKE :filtro OR
            usuario.nome LIKE :filtro OR
            usuario.email LIKE :filtro OR
            COALESCE(usuario.telefone, '') LIKE:filtro OR
            COALESCE(gruposAssignados.nome, '') LIKE:filtro
            """)
    Page<Usuario> findByFiltro(String filtro, Pageable pageable);
}
