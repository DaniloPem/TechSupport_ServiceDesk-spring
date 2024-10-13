package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import projetotechsupport.apitechsupport.component.GerenciadorNumeroTicketComponent;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.categoria.CategoriaRepository;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoRepository;
import projetotechsupport.apitechsupport.model.subtag.Subtag;
import projetotechsupport.apitechsupport.model.subtag.SubtagRepository;
import projetotechsupport.apitechsupport.model.tag.Tag;
import projetotechsupport.apitechsupport.model.tag.TagRepository;
import projetotechsupport.apitechsupport.model.ticket.DadosCadastroTicket;
import projetotechsupport.apitechsupport.model.ticket.Ticket;
import projetotechsupport.apitechsupport.model.ticket.TicketRepository;
import projetotechsupport.apitechsupport.model.ticket.TipoTicket;
import projetotechsupport.apitechsupport.model.usuario.Usuario;
import projetotechsupport.apitechsupport.model.usuario.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UsuarioRepository usuarioRepository;
    private final GrupoAssignadoRepository grupoAssignadoRepository;
    private final TagRepository tagRepository;
    private final SubtagRepository subtagRepository;
    private final CategoriaRepository categoriaRepository;
    private final GerenciadorNumeroTicketComponent gerenciadorNumeroTicketComponent;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Ticket create(DadosCadastroTicket dadosTicket) {
        Usuario usuarioReportadoPor = getUsuarioReportado(dadosTicket);
        Usuario usuarioReportadoPara = getUsuarioReportadoPara(dadosTicket, usuarioReportadoPor);
        GrupoAssignado grupoAssignado = getGrupoAssignado(dadosTicket);
        Categoria categoriaReportada = getCategoriaReportada(dadosTicket);
        Categoria categoriaAfetada = getCategoriaAfetada(dadosTicket, categoriaReportada);
        Tag tag = getTag(dadosTicket);
        Subtag subtag = getSubTag(dadosTicket);
        validarGrupoAssignado(grupoAssignado, categoriaAfetada);

        Ticket ticket = new Ticket(dadosTicket, usuarioReportadoPor, usuarioReportadoPara, grupoAssignado, categoriaReportada, categoriaAfetada, tag, subtag);
        return ticketRepository.save(ticket);
    }

    private void validarGrupoAssignado(GrupoAssignado grupoAssignado, Categoria categoriaAfetada) {
        boolean categoriaContemGrupo = categoriaAfetada.getGruposAssignados().contains(grupoAssignado);
        if (!categoriaContemGrupo) {
            throw new DataIntegrityViolationException("O GRUPO INSERIDO NÃO GERENCIA A CATEGORIA ESPECIFICADA.");
        }
    }

    private Usuario getUsuarioReportado(DadosCadastroTicket dadosTicket) {
        Long usuarioReportadoPorId = dadosTicket.reportadoPorId();
        Optional<Usuario> usuarioReportadoPorOpt = usuarioRepository.findById(usuarioReportadoPorId);
        return usuarioReportadoPorOpt.orElseThrow(() -> new DataIntegrityViolationException("USUÁRIO INSERIDO NÃO EXISTE."));
    }

    private Usuario getUsuarioReportadoPara(DadosCadastroTicket dadosTicket, Usuario usuarioReportadoPor) {
        Long reportadoParaId = dadosTicket.reportadoParaId();
        if (reportadoParaId == null) {
            return usuarioReportadoPor;
        }
        Optional<Usuario> usuarioReportadoParaOpt = usuarioRepository.findById(reportadoParaId);
        return usuarioReportadoParaOpt.orElseThrow(() -> new DataIntegrityViolationException("USUÁRIO INSERIDO NÃO EXISTE."));
    }

    private GrupoAssignado getGrupoAssignado(DadosCadastroTicket dadosTicket) {
        Long grupoAssignadoId = dadosTicket.grupoAssignadoId();
        Optional<GrupoAssignado> grupoAssignadoOpt = grupoAssignadoRepository.findById(grupoAssignadoId);
        return grupoAssignadoOpt.orElseThrow(() -> new DataIntegrityViolationException("GRUPO ASSIGNADO INSERIDO NÃO EXISTE."));
    }

    private Categoria getCategoriaReportada(DadosCadastroTicket dadosTicket) {
        Long categoriaReportadaId = dadosTicket.categoriaReportadaId();
        Optional<Categoria> categoriaReportadaOpt = categoriaRepository.findById(categoriaReportadaId);
        return categoriaReportadaOpt.orElseThrow(() -> new DataIntegrityViolationException("CATEGORIA INSERIDA NÃO EXISTE."));
    }

    private Categoria getCategoriaAfetada(DadosCadastroTicket dadosTicket, Categoria categoriaReportada) {
        Long categoriaAfetadaId = dadosTicket.categoriaReportadaId();
        if (categoriaAfetadaId == null) {
            return categoriaReportada;
        }
        Optional<Categoria> categoriaAfetadaOpt = categoriaRepository.findById(categoriaAfetadaId);
        return categoriaAfetadaOpt.orElseThrow(() -> new DataIntegrityViolationException("CATEGORIA INSERIDA NÃO EXISTE."));
    }

    private Tag getTag(DadosCadastroTicket dadosTicket) {
        Long tagId = dadosTicket.tagId();
        if (tagId == null) return null;
        Optional<Tag> tagOpt = tagRepository.findById(tagId);
        return tagOpt.orElseThrow(() -> new DataIntegrityViolationException("TAG INSERIDO NÃO EXISTE."));
    }

    private Subtag getSubTag(DadosCadastroTicket dadosTicket) {
        Long subTagId = dadosTicket.subtagId();
        if (subTagId == null) return null;
        Optional<Subtag> subTagOpt = subtagRepository.findById(subTagId);
        return subTagOpt.orElseThrow(() -> new DataIntegrityViolationException("TAG INSERIDO NÃO EXISTE."));
    }

    public String getNumeroTicket(TipoTicket tipoTicket) {
        return gerenciadorNumeroTicketComponent.getNumeroTicket(tipoTicket);
    }

}
