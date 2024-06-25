package projetotechsupport.apitechsupport.model.subtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtagRepository extends JpaRepository<Subtag, Long> {
}
