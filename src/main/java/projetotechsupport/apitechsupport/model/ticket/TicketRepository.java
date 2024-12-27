package projetotechsupport.apitechsupport.model.ticket;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Optional<Ticket> findTopByTipoOrderByNumeroTicketSegundoTipoDesc(TipoTicket tipoTicket);

    Page<Ticket> findByTipo(TipoTicket tipoTicket, Pageable pageable);

    @Query("""
            SELECT ticket FROM Ticket ticket
            JOIN ticket.reportadoPor reportadoPor
            JOIN ticket.reportadoPara reportadoPara
            JOIN ticket.categoriaReportada categoriaReportada
            JOIN ticket.categoriaAfetada categoriaAfetada
            JOIN ticket.grupoAssignado grupoAssignado
            JOIN ticket.tag tag
            JOIN ticket.subtag subtag
            WHERE ticket.tipo = :tipoTicket AND (
            ticket.numeroTicketSegundoTipo LIKE :filtro OR
            ticket.titulo LIKE :filtro OR
            ticket.status LIKE :filtro OR
            reportadoPor.nome LIKE :filtro OR
            reportadoPor.codigo LIKE :filtro OR
            reportadoPara.nome LIKE :filtro OR
            reportadoPara.codigo LIKE :filtro OR
            categoriaReportada.nome LIKE :filtro OR
            categoriaAfetada.nome LIKE :filtro OR
            grupoAssignado.nome LIKE :filtro OR
            tag.nome LIKE :filtro OR
            subtag.nome LIKE :filtro
            )
            """)
    Page<Ticket> findByTipoAndFiltro(TipoTicket tipoTicket, String filtro, Pageable pageable);

}
