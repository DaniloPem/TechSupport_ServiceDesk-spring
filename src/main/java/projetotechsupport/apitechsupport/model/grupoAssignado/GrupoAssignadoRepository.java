package projetotechsupport.apitechsupport.model.grupoAssignado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoAssignadoRepository extends JpaRepository<GrupoAssignado, Long> {

    List<GrupoAssignado> findByCategoriasId(Long categoriaId);

    @Query("""
            SELECT grupo FROM GrupoAssignado grupo
            JOIN grupo.categorias categorias
            JOIN grupo.usuarios usuarios
            WHERE grupo.nome LIKE :filtro OR
            categorias.nome LIKE :filtro OR
            usuarios.nome LIKE :filtro
            """)
    Page<GrupoAssignado> findByFiltro(String filtro, Pageable pageable);
}
