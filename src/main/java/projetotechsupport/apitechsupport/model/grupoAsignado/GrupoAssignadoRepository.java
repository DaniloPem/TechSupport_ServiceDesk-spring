package projetotechsupport.apitechsupport.model.grupoAsignado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoAssignadoRepository extends JpaRepository<GrupoAssignado, Long> {
}
