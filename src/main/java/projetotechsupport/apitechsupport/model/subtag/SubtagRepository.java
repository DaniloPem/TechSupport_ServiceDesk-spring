package projetotechsupport.apitechsupport.model.subtag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubtagRepository extends JpaRepository<Subtag, Long> {

    List<Subtag> findByTagId(Long tagId);
}
