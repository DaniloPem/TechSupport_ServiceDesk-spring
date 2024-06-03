package projetotechsupport.apitechsupport.model.ticket;

import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAsignado;
import projetotechsupport.apitechsupport.model.usuario.Usuario;

public record DadosCadastroTicket(
        String titulo,
        Usuario reportadoPor,
        Usuario reportadoPara,
        GrupoAsignado grupoAsignado,
        String descricao,
        String dadosPessoais,
        Categoria categoria,
        String solucao,
        String solucaoDadosPessoais
) {
}
