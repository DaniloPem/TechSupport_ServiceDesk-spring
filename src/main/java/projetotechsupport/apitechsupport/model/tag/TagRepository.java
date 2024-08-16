package projetotechsupport.apitechsupport.model.tag;

import org.springframework.data.jpa.repository.JpaRepository;
import projetotechsupport.apitechsupport.shared.dtos.IdNomeDTO;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByCategoriaId(Long categoriaId);

}
