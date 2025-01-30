package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import projetotechsupport.apitechsupport.model.categoria.Categoria;
import projetotechsupport.apitechsupport.model.categoria.CategoriaRecord;
import projetotechsupport.apitechsupport.model.categoria.CategoriaRepository;
import projetotechsupport.apitechsupport.model.grupoAssignado.*;
import projetotechsupport.apitechsupport.model.usuario.DadosCadastroUsuario;
import projetotechsupport.apitechsupport.model.usuario.Usuario;
import projetotechsupport.apitechsupport.model.usuario.UsuarioRepository;
import projetotechsupport.apitechsupport.shared.dtos.IdNomeDTO;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GrupoAssignadoService {

    private final GrupoAssignadoRepository grupoAssignadoRepository;
    private final CategoriaRepository categoriaRepository;

    public DadosVisualizacaoGrupoAssignado findById(Long id) {
        Optional<GrupoAssignado> grupoAssignadoOptional = grupoAssignadoRepository.findById(id);
        GrupoAssignado grupoAssignado = grupoAssignadoOptional.orElseThrow(() -> new DataIntegrityViolationException("GRUPO ASSIGNADO NÃO EXISTE."));
        return new DadosVisualizacaoGrupoAssignado(grupoAssignado);
    }

    public List<IdNomeDTO> findByCategoriaId(Long categoriaId) {
        List<GrupoAssignado> gruposAssignados = grupoAssignadoRepository.findByCategoriasId(categoriaId);
        return gruposAssignados.stream().map(grupoAssignado -> new IdNomeDTO(grupoAssignado.getId(), grupoAssignado.getNome())).toList();
    }

    public GrupoAssignadoPageDTO findAllGruposAssignados(String filtro, @PositiveOrZero int page, @Positive @Max(30) int pageSize) {
        Page<GrupoAssignado> pageGrupoAssignados = grupoAssignadoRepository.findByFiltro('%' + filtro + '%', PageRequest.of(page, pageSize));
        List<DadosVisualizacaoAllGruposAssignados> gruposAssignados = pageGrupoAssignados.map(DadosVisualizacaoAllGruposAssignados::new).toList();
        return new GrupoAssignadoPageDTO(gruposAssignados, pageGrupoAssignados.getTotalElements(), pageGrupoAssignados.getTotalPages());
    }

    public List<GrupoAssignadoRecord> findByNomeLike(String namePattern) {
        List<GrupoAssignado> grupoAssignados = grupoAssignadoRepository.findByNomeLike(namePattern + "%");
        return grupoAssignados.stream()
                .map(GrupoAssignadoRecord::new)
                .toList();
    }

    public List<GrupoAssignadoRecord> findByIds(List<Long> ids) {
        List<GrupoAssignado> grupoAssignados = grupoAssignadoRepository.findByIdIn(ids);
        return grupoAssignados.stream().map(GrupoAssignadoRecord::new).toList();
    }

    public GrupoAssignado criarGrupo(DadosCadastroGrupoAssignado dadosCadastroGrupoAssignado) {
        List<Categoria> categorias = getCategorias(dadosCadastroGrupoAssignado.categoriasId());
        GrupoAssignado grupoAssignado = new GrupoAssignado(dadosCadastroGrupoAssignado, categorias);
        return grupoAssignadoRepository.save(grupoAssignado);
    }

    public GrupoAssignado atualizarGrupo(DadosCadastroGrupoAssignado dadosCadastroGrupoAssignado, Long id) {
        Optional<GrupoAssignado> grupoAssignadoOptional = grupoAssignadoRepository.findById(id);
        GrupoAssignado grupoAssignado = grupoAssignadoOptional.orElseThrow(() -> new DataIntegrityViolationException("GRUPO NÃO EXISTE."));
        grupoAssignado.setNome(dadosCadastroGrupoAssignado.nome());
        List<Categoria> categorias = getCategorias(dadosCadastroGrupoAssignado.categoriasId());
        grupoAssignado.setCategorias(categorias);
        grupoAssignadoRepository.save(grupoAssignado);
        return grupoAssignado;
    }

    private List<Categoria> getCategorias(List<Long> categoriasId) {
        return categoriaRepository.findAllById(categoriasId);
    }

    public void deshabilitar(Long id) {
        GrupoAssignado grupoAssignado = grupoAssignadoRepository.getReferenceById(id);
        grupoAssignado.desabilitar();
    }

}
