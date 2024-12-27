package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import projetotechsupport.apitechsupport.model.ticket.*;
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

    public CoursePageDTO findAllByTypeTicket(TipoTicket tipoTicket, String filtro, @PositiveOrZero int page, @Positive @Max(30) int pageSize) {
        Page<Ticket> pageTicketsByTipo = ticketRepository.findByTipoAndFiltro(tipoTicket, '%' + filtro + '%' ,PageRequest.of(page, pageSize));
        List<DadosVisualizacaoTicketByTipo> tickets = pageTicketsByTipo.map(DadosVisualizacaoTicketByTipo::new).toList();
        return new CoursePageDTO(tickets, pageTicketsByTipo.getTotalElements(), pageTicketsByTipo.getTotalPages());
    }

    public Ticket create(DadosCadastroTicket dadosTicket) {
        Usuario usuarioReportadoPor = getUsuarioReportado(dadosTicket.reportadoPorId());
        Usuario usuarioReportadoPara = getUsuarioReportadoPara(dadosTicket.reportadoParaId(), usuarioReportadoPor);
        GrupoAssignado grupoAssignado = getGrupoAssignado(dadosTicket.grupoAssignadoId());
        Categoria categoriaReportada = getCategoriaReportada(dadosTicket.categoriaReportadaId());
        Categoria categoriaAfetada = getCategoriaAfetada(dadosTicket.categoriaAfetadaId(), categoriaReportada);
        Tag tag = getTag(dadosTicket.tagId());
        Subtag subtag = getSubTag(dadosTicket.subtagId());
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

    private Usuario getUsuarioReportado(Long usuarioReportadoPorId) {
        Optional<Usuario> usuarioReportadoPorOpt = usuarioRepository.findById(usuarioReportadoPorId);
        return usuarioReportadoPorOpt.orElseThrow(() -> new DataIntegrityViolationException("USUÁRIO INSERIDO NÃO EXISTE."));
    }

    private Usuario getUsuarioReportadoPara(Long usuarioReportadoParaId, Usuario usuarioReportadoPor) {
        if (usuarioReportadoParaId == null) {
            return usuarioReportadoPor;
        }
        Optional<Usuario> usuarioReportadoParaOpt = usuarioRepository.findById(usuarioReportadoParaId);
        return usuarioReportadoParaOpt.orElseThrow(() -> new DataIntegrityViolationException("USUÁRIO INSERIDO NÃO EXISTE."));
    }

    private GrupoAssignado getGrupoAssignado(Long grupoAssignadoId) {
        Optional<GrupoAssignado> grupoAssignadoOpt = grupoAssignadoRepository.findById(grupoAssignadoId);
        return grupoAssignadoOpt.orElseThrow(() -> new DataIntegrityViolationException("GRUPO ASSIGNADO INSERIDO NÃO EXISTE."));
    }

    private Categoria getCategoriaReportada(Long categoriaReportadaId) {
        Optional<Categoria> categoriaReportadaOpt = categoriaRepository.findById(categoriaReportadaId);
        return categoriaReportadaOpt.orElseThrow(() -> new DataIntegrityViolationException("CATEGORIA INSERIDA NÃO EXISTE."));
    }

    private Categoria getCategoriaAfetada(Long categoriaAfetadaId, Categoria categoriaReportada) {
        if (categoriaAfetadaId == null) {
            return categoriaReportada;
        }
        Optional<Categoria> categoriaAfetadaOpt = categoriaRepository.findById(categoriaAfetadaId);
        return categoriaAfetadaOpt.orElseThrow(() -> new DataIntegrityViolationException("CATEGORIA INSERIDA NÃO EXISTE."));
    }

    private Tag getTag(Long tagId) {
        if (tagId == null) return null;
        Optional<Tag> tagOpt = tagRepository.findById(tagId);
        return tagOpt.orElseThrow(() -> new DataIntegrityViolationException("TAG INSERIDO NÃO EXISTE."));
    }

    private Subtag getSubTag(Long subTagId) {
        if (subTagId == null) return null;
        Optional<Subtag> subTagOpt = subtagRepository.findById(subTagId);
        return subTagOpt.orElseThrow(() -> new DataIntegrityViolationException("TAG INSERIDO NÃO EXISTE."));
    }

    public String getNumeroTicket(TipoTicket tipoTicket) {
        return gerenciadorNumeroTicketComponent.getNumeroTicket(tipoTicket);
    }

    public DadosVisualizacaoTicketById getTicketById(Long id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        Ticket ticket = ticketOptional.orElseThrow(() -> new DataIntegrityViolationException("TICKET NÃO EXISTE"));
        return new DadosVisualizacaoTicketById(ticket);
    }

    public Ticket atualizarTicket(DadosCadastroTicket dadosTicket, Long id) {
        Optional<Ticket> ticketOptional = ticketRepository.findById(id);
        Ticket ticketEncontrado = ticketOptional.orElseThrow(() -> new DataIntegrityViolationException("TICKET NÃO EXISTE."));
        ticketEncontrado.setTitulo(dadosTicket.titulo());
        ticketEncontrado.setNumeroTicketSegundoTipo(dadosTicket.numeroTicketSegundoTipo());
        Usuario usuarioReportadoPor = getUsuarioReportado(dadosTicket.reportadoPorId());
        ticketEncontrado.setReportadoPor(usuarioReportadoPor);
        Usuario usuarioReportadoPara = getUsuarioReportadoPara(dadosTicket.reportadoParaId(), usuarioReportadoPor);
        ticketEncontrado.setReportadoPara(usuarioReportadoPara);
        GrupoAssignado grupoAssignado = getGrupoAssignado(dadosTicket.grupoAssignadoId());
        ticketEncontrado.setGrupoAssignado(grupoAssignado);
        ticketEncontrado.setDescricao(dadosTicket.descricao());
        ticketEncontrado.setDadosPessoais(dadosTicket.dadosPessoais());
        Categoria categoriaReportada = getCategoriaReportada(dadosTicket.categoriaReportadaId());
        ticketEncontrado.setCategoriaReportada(categoriaReportada);
        Categoria categoriaAfetada = getCategoriaAfetada(dadosTicket.categoriaAfetadaId(), categoriaReportada);
        ticketEncontrado.setCategoriaAfetada(categoriaAfetada);
        Tag tag = getTag(dadosTicket.tagId());
        ticketEncontrado.setTag(tag);
        Subtag subtag = getSubTag(dadosTicket.subtagId());
        ticketEncontrado.setSubtag(subtag);
        ticketEncontrado.setSolucao(dadosTicket.solucao());
        ticketEncontrado.setSolucaoDadosPessoais(dadosTicket.solucaoDadosPessoais());
        return ticketEncontrado;
    }

}
