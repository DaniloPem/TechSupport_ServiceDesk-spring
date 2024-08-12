package projetotechsupport.apitechsupport.model.ticket;

public record DadosCadastroTicket(
        Status status,
        String titulo,
        Long reportadoPorId,
        Long reportadoParaId,
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
}
