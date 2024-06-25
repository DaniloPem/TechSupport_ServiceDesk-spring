package projetotechsupport.apitechsupport.model.ticket;

import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.grupoAsignado.GrupoAsignado;
import projetotechsupport.apitechsupport.model.subtag.Subtag;
import projetotechsupport.apitechsupport.model.tag.Tag;
import projetotechsupport.apitechsupport.model.usuario.Usuario;

import java.util.List;

public record DadosCadastroTicket(
        String titulo,
        Usuario reportadoPor,
        Usuario reportadoPara,
        GrupoAsignado grupoAsignado,
        String descricao,
        String dadosPessoais,
        Categoria categoriaReportada,
        Categoria categoriaAfetada,
        Tag tag,
        Subtag subtag,
        String solucao,
        String solucaoDadosPessoais
) {
}
