package projetotechsupport.apitechsupport.model.ticket;

import org.springframework.cglib.core.Local;
import projetotechsupport.apitechsupport.model.ticket.Status;
import projetotechsupport.apitechsupport.model.ticket.Ticket;
import projetotechsupport.apitechsupport.model.ticket.TipoTicket;

import java.time.LocalDateTime;

public record DadosVisualizacaoTicketById(
        Long id,
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
        String grupoAssignadoNome,
        String descricao,
        String dadosPessoais,
        Long categoriaReportadaId,
        String categoriaReportadaNome,
        Long categoriaAfetadaId,
        String categoriaAfetadaNome,
        Long tagId,
        String tagNome,
        Long subtagId,
        String subtagNome,
        String solucao,
        String solucaoDadosPessoais
) {
    public DadosVisualizacaoTicketById(Ticket ticket) {
        this(ticket.getId() ,ticket.getStatus(),
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
                ticket.getGrupoAssignado().getNome(),
                ticket.getDescricao(),
                ticket.getDadosPessoais(),
                ticket.getCategoriaReportada().getId(),
                ticket.getCategoriaReportada().getNome(),
                ticket.getCategoriaAfetada().getId(),
                ticket.getCategoriaAfetada().getNome(),
                ticket.getIdTag(),
                ticket.getNomeTag(),
                ticket.getIdSubTag(),
                ticket.getNomeSubTag(),
                ticket.getSolucao(),
                ticket.getSolucaoDadosPessoais());
    }
}
