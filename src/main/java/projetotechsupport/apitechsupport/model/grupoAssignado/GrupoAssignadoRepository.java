package projetotechsupport.apitechsupport.model.grupoAssignado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoAssignadoRepository extends JpaRepository<GrupoAssignado, Long> {

    List<GrupoAssignado> findByCategoriasId(Long categoriaId);
}
