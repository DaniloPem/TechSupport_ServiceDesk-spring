package projetotechsupport.apitechsupport.model.ticket;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTicket(
        @NotBlank
        String titulo,
        @NotBlank
        TipoTicket tipo,
        @NotBlank
        String numeroTicketSegundoTipo,
        @NotNull
        Long reportadoPorId,
        Long reportadoParaId,
        @NotNull
        Long grupoAssignadoId,
        @NotBlank
        String descricao,
        @NotBlank
        String dadosPessoais,
        @NotNull
        Long categoriaReportadaId,
        Long categoriaAfetadaId,
        Long tagId,
        Long subtagId,
        String solucao,
        String solucaoDadosPessoais
) {
}
