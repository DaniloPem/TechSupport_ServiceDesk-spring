package projetotechsupport.apitechsupport.model.ticket;

import org.springframework.cglib.core.Local;
import projetotechsupport.apitechsupport.model.ticket.Status;
import projetotechsupport.apitechsupport.model.ticket.Ticket;
import projetotechsupport.apitechsupport.model.ticket.TipoTicket;

import java.time.LocalDateTime;

public record DadosVisualizacaoTicketById(
        Status status,
        LocalDateTime dataEHorarioDeCriacao,
        TipoTicket tipo,
        String numeroTicketSegundoTipo,
        String titulo,
        Long reportadoPorId,
        String reportadoPorNome,
        String reportadoPorCodigo,
        Long reportadoParaId,
        String reportadoParaNome,
        String reportadoParaCodigo,
        Long grupoAssignadoId,
        String descricao,
        String dadosPessoais,
        Long categoriaReportadaId,
        Long categoriaAfetadaId,
        Long tagId,
        Long subtagId,
        String solucao,
        String solucaoDadosPessoais
) {
    public DadosVisualizacaoTicketById(Ticket ticket) {
        this(ticket.getStatus(),
                ticket.getDataEHorarioDeCriacao(),
                ticket.getTipo(),
                ticket.getNumeroTicketSegundoTipo(),
                ticket.getTitulo(),
                ticket.getReportadoPor().getId(),
                ticket.getReportadoPor().getNome(),
                ticket.getReportadoPor().getCodigo(),
                ticket.getReportadoPara().getId(),
                ticket.getReportadoPara().getNome(),
                ticket.getReportadoPara().getCodigo(),
                ticket.getGrupoAssignado().getId(),
                ticket.getDescricao(),
                ticket.getDadosPessoais(),
                ticket.getCategoriaReportada().getId(),
                ticket.getCategoriaAfetada().getId(),
                ticket.getIdTag(),
                ticket.getIdSubTag(),
                ticket.getSolucao(),
                ticket.getSolucaoDadosPessoais());
    }
}
