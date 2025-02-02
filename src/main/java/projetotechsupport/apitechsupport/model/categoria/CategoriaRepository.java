package projetotechsupport.apitechsupport.model.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByNomeLike(String namePattern);

    @Query("""
            SELECT categoria FROM Categoria categoria
            JOIN categoria.gruposAssignados gruposAssignados
            LEFT JOIN categoria.tag tag
            WHERE categoria.nome LIKE :filtro OR
            gruposAssignados.nome LIKE :filtro OR
            COALESCE(tag.nome, '') LIKE :filtro
            """)
    Page<Categoria> findByFiltro(String filtro, Pageable pageable);

    List<Categoria> findByIdIn(List<Long> ids);
}
