package projetotechsupport.apitechsupport.service;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import projetotechsupport.apitechsupport.model.categoria.*;
import projetotechsupport.apitechsupport.model.grupoAssignado.DadosVisualizacaoAllGruposAssignados;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignado;
import projetotechsupport.apitechsupport.model.grupoAssignado.GrupoAssignadoPageDTO;
import projetotechsupport.apitechsupport.model.tag.Tag;
import projetotechsupport.apitechsupport.model.tag.TagRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private  final TagRepository tagRepository;
    public List<CategoriaRecord> findByNomeLike(String namePattern) {
        List<Categoria> categorias = categoriaRepository.findByNomeLike(namePattern + "%");
        return categorias.stream()
                .map(CategoriaRecord::new)
                .toList();
    }

    public CategoriaPageDTO findAllCategorias(String filtro, @PositiveOrZero int page, @Positive @Max(30) int pageSize) {
        Page<Categoria> pageCategorias = categoriaRepository.findByFiltro('%' + filtro + '%', PageRequest.of(page, pageSize));
        List<DadosVisualizacaoAllCategorias> categorias = pageCategorias.map(DadosVisualizacaoAllCategorias::new).toList();
        return new CategoriaPageDTO(categorias, pageCategorias.getTotalElements(), pageCategorias.getTotalPages());
    }

    public Categoria criar(DadosCadastroCategoria dadosCadastroCategoria) {
        List<Tag> tags = getListTags(dadosCadastroCategoria.tagsId());
        Categoria categoria = new Categoria(dadosCadastroCategoria, tags);
        return categoriaRepository.save(categoria);
    }

    private List<Tag> getListTags(List<Long> tagsId) {
        return tagsId != null ? tagRepository.findAllById(tagsId) : Collections.emptyList();
    }
}
