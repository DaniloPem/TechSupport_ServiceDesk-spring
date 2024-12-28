package projetotechsupport.apitechsupport.model.ticket;

import java.time.LocalDateTime;

public record DadosVisualizacaoTicketByTipo(
        Long id,
        Status status,
        LocalDateTime dataEHorarioDeCriacao,
        String numeroTicketSegundoTipo,
        String titulo,
        String reportadoPorNome,
        String reportadoPorCodigo,
        String reportadoParaNome,
        String reportadoParaCodigo,
        String grupoAssignadoNome,
        String categoriaReportadaNome,
        String categoriaAfetadaNome,
        String tagNome,
        String subtagNome
) {
    public DadosVisualizacaoTicketByTipo(Ticket ticket) {
        this(ticket.getId(), ticket.getStatus(),
                ticket.getDataEHorarioDeCriacao(),
                ticket.getNumeroTicketSegundoTipo(),
                ticket.getTitulo(),
                ticket.getReportadoPor().getNome(),
                ticket.getReportadoPor().getCodigo(),
                ticket.getReportadoPara().getNome(),
                ticket.getReportadoPara().getCodigo(),
                ticket.getGrupoAssignado().getNome(),
                ticket.getCategoriaReportada().getNome(),
                ticket.getCategoriaAfetada().getNome(),
                ticket.getNomeTag(),
                ticket.getNomeSubTag());
    }
}
