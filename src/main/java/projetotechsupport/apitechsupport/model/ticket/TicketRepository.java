package projetotechsupport.apitechsupport.model.ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findTopByTipoOrderByNumeroTicketSegundoTipoDesc(TipoTicket tipoTicket);

}
