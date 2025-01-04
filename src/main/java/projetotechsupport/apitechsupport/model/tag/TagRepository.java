package projetotechsupport.apitechsupport.model.tag;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.shared.dtos.IdNomeDTO;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByCategoriaId(Long categoriaId);

    @Query("""
            SELECT tag FROM Tag tag
            JOIN tag.categoria categoria
            LEFT JOIN tag.subtags subtags
            WHERE tag.nome LIKE :filtro OR
            categoria.nome LIKE :filtro OR
            COALESCE(subtags.nome, '') LIKE :filtro
            """)
    Page<Tag> findByFiltro(String filtro, Pageable pageable);

}
